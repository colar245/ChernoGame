package game.entity.mob;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;
import game.entity.object.projectile.Bullet1;
import game.entity.object.projectile.Projectile;
import game.entity.particle.Particle;
import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;

public abstract class Mob extends Entity{

	protected boolean moving = false;
	protected boolean walking = false;
	
	protected enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction dir;
	
	public void move(int xChange, int yChange) {
		if(xChange > 0) dir = Direction.RIGHT;
		if(xChange < 0) dir = Direction.LEFT;
		if(yChange > 0) dir = Direction.DOWN;
		if(yChange < 0) dir = Direction.UP;
		
		if(!collision(xChange, 0)) {
			x += xChange;
		}
		if(!collision(0, yChange)) {
			y += yChange;
		}
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);

	protected void shoot(int x, int y, double dir) {
		Projectile p = new Bullet1(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			int xt = ((x + xa) + c % 2 * 17 + 6) >> 4;
			int yt = ((y + ya) + c / 2 * 10 + 21) >> 4;
			if(level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
}
