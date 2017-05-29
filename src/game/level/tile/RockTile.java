package game.level.tile;

import graphics.Screen;
import graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); //*16x16
	}
	
	public boolean solid() {
		return true;
	}
}
