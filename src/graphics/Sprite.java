package graphics;

import java.util.Random;

public class Sprite {
	
	public final int SIZE;
	private int width, height;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite brush = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite sWal1 = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite sWall2 = new Sprite(16, 4, 2, SpriteSheet.tiles);
	public static Sprite wWall = new Sprite(16, 1, 3, SpriteSheet.tiles);
	public static Sprite tree = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite bigTree = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite sFloor1 = new Sprite(16, 4, 1, SpriteSheet.tiles);
	public static Sprite sFloor2 = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite wFloor = new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0);
	
	public static Sprite bullet1 = new Sprite(16, 0, 0, SpriteSheet.bullet);
	
	public static Sprite particle_1 = new Sprite(2, 0xAAAAAA);
	
	protected Sprite(int width, int height, SpriteSheet sheet) {
		if(width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		if(width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColour(int color) {
		for(int i = 0; i < width * height; i++){
			pixels[i] = color;
		}
	}
	
	

	private void load() {
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
}
