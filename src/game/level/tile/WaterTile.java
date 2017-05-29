package game.level.tile;

import graphics.Screen;
import graphics.Sprite;

public class WaterTile extends Tile {

	public WaterTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); //*16x16
	}
}