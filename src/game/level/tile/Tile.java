package game.level.tile;

import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

public abstract class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile brush = new BrushTile(Sprite.brush);
	public static Tile sWal1 = new Wall1Tile(Sprite.sWal1);
	public static Tile sWall2 = new Wall2Tile(Sprite.sWall2);
	public static Tile wWall = new WoodenWallTile(Sprite.wWall);
	public static Tile tree = new TreeTile(Sprite.tree);
	public static Tile bigTree = new BigTreeTile(Sprite.bigTree);
	public static Tile sFloor1 = new StoneFloor1(Sprite.sFloor1);
	public static Tile sFloor2 = new StoneFloor2(Sprite.sFloor2);
	public static Tile wFloor = new WoodenFloor(Sprite.wFloor);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
}
