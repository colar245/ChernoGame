package game.level;

import game.entity.mob.TestMob;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class SpawnLevel extends Level{
	
	private Random random;

	public SpawnLevel(String path) {
		super(path);
	}
 
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int h = height = image.getHeight();
			int w = width = image .getWidth();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file!");
		}
		for(int i = 0; i <= 5; i++)
		add(new TestMob(50, 35));
	}
	
	protected void generateLevel() {
	}
}
