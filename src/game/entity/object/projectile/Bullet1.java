package game.entity.object.projectile;

import game.entity.particle.Particle;
import game.entity.spawner.ParticleSpawner;
import game.entity.spawner.Spawner;
import graphics.Screen;
import graphics.Sprite;

public class Bullet1 extends Projectile {
	
	public static final int FIRE = 15;
	
	public Bullet1(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 3;
		damage = 20;
		sprite = Sprite.bullet1;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if(distance() > range) {
			remove();
		}
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

	public void update() {
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 8, 8, 24)) {
			level.add(new ParticleSpawner((int)x,(int) y + 20, 44, 50, level));
			remove();
		}
		move();
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x, (int)y, this);
	}
}
