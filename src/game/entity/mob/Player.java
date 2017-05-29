package game.entity.mob;

import game.Game;
import game.entity.object.projectile.Bullet1;
import game.entity.object.projectile.Projectile;
import game.input.Keyboard;
import game.input.Mouse;
import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int animate = 0;
	private boolean walking = false;
	
	private AnimatedSprite down = new AnimatedSprite(32, 32, SpriteSheet.player_down, 3);
	private AnimatedSprite up = new AnimatedSprite(32, 32, SpriteSheet.player_up, 3);
	private AnimatedSprite left = new AnimatedSprite(32, 32, SpriteSheet.player_left, 3);
	private AnimatedSprite right = new AnimatedSprite(32, 32, SpriteSheet.player_right, 3);
	
	private AnimatedSprite animation = down;
	
	double w, h;
	
	Projectile p;
	private int fire = 0;
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		//sprite = Sprite.player_b;
		fire = Bullet1.FIRE;
	}
	
	public Player(Keyboard input) {
		this.input = input;
		//sprite = Sprite.player_b;
		fire = Bullet1.FIRE;
	}
	
	public void update() {
		if (walking) animation.update();
		else animation.setFrame(1);
		if(fire > 0) fire--;
		w = Game.WIDTH * Game.scale;
		h = Game.HEIGHT * Game.scale;
		int xa = 0, ya = 0;
		if(animate < 7500)animate++;
		else animate = 0;
		if(input.up) {
			ya--;
			animation = up;
		}
		else if(input.down) {
			ya++;
			animation = down;
		}
		if(input.left) {
			xa--;
			animation = left;
		}
		else if(input.right) {
			xa++;
			animation = right;
		}
		if(xa != 0 || ya != 0) {
			move(xa,ya);
			walking = true;
		}else {
			walking = false;
		}
		clear();
		updateShooting();
	}
	
	private void clear() {
		for(int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if(Mouse.getB() == 1 && fire <= 0){
			double xd = Mouse.getX() - w/2;
			double yd = Mouse.getY() - h/2;
			double dir = Math.atan2(yd, xd);
			shoot(x, y, dir);
			fire = Bullet1.FIRE;
		}
	}

	public void render(Screen screen) {
		/*if(dir == 0) {
			sprite = Sprite.player_f;
			if(walking){
				if(animate % 20 > 10){
					sprite = Sprite.player_f1;
				}else sprite = Sprite.player_f2;
			}
		}		
		if(dir == 1) {
			sprite = Sprite.player_r;
			if(walking){
				if(animate % 20 > 10){
					sprite = Sprite.player_r1;
				}else sprite = Sprite.player_r2;
			}
		}		
		if(dir == 2) {
			sprite = Sprite.player_b;
			if(walking){
				if(animate % 20 > 10){
					sprite = Sprite.player_b1;
				}else sprite = Sprite.player_b2;
			}
		}		
		if(dir == 3) {
			sprite = Sprite.player_l;
			if(walking){
				if(animate % 20 > 10){
					sprite = Sprite.player_l1;
				}else sprite = Sprite.player_l2;
			}
		}*/
		sprite = animation.getSprite();
		screen.renderMob(x, y, sprite);
	}

}
