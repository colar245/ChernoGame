package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	private Sprite[] sprites;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/tiles/spriteSheet.png", 256);
	public static SpriteSheet mobs = new SpriteSheet("/textures/mobs/mobs.png", 256);
	public static SpriteSheet bullet = new SpriteSheet("/textures/projectiles/bullets.png", 256);
	
	public static SpriteSheet player_down = new SpriteSheet(mobs, 3, 4, 3, 1, 32);
	public static SpriteSheet player_up = new SpriteSheet(mobs, 3, 7, 3, 1, 32);
	public static SpriteSheet player_left = new SpriteSheet(mobs, 3, 5, 3, 1, 32);
	public static SpriteSheet player_right = new SpriteSheet(mobs, 3, 6, 3, 1, 32);
	
	public static SpriteSheet ghoast_down = new SpriteSheet(mobs, 0, 4, 3, 1, 32);
	public static SpriteSheet ghoast_left = new SpriteSheet(mobs, 0, 5, 3, 1, 32);
	public static SpriteSheet ghoast_right = new SpriteSheet(mobs, 0, 6, 3, 1, 32);
	public static SpriteSheet ghoast_up = new SpriteSheet(mobs, 0, 7, 3, 1, 32);
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if(width == height) SIZE = w;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for(int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int y0 = 0; y0 < height; y0++) {
			for(int x0 = 0; x0 < width; x0++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y1 = 0; y1 < spriteSize; y1++) {
					for(int x1 = 0; x1 < spriteSize; x1++) {
						spritePixels[x1 + y1 * spriteSize] = pixels[(x1 + x0 * spriteSize) + (y1 + y0 * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		if(width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = SIZE;
		HEIGHT = SIZE;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public Sprite[] getSprites() {
		return sprites;  //***23 min, 87E***//
	}
	
	public int getSIZE() {
		return SIZE;
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
