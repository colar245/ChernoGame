package game.entity.mob;

import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

public class TestMob extends Mob{
	
	private Sprite sprite;
	
	private int wSpeed = 3;
	private int wTime = 0;
	private int dirX = 0;
	private int dirY = 0;
	private int time = 0;
	
	private boolean walking = false;
	
	private AnimatedSprite down = new AnimatedSprite(32, 32, SpriteSheet.ghoast_down, 3);
	private AnimatedSprite up = new AnimatedSprite(32, 32, SpriteSheet.ghoast_up, 3);
	private AnimatedSprite left = new AnimatedSprite(32, 32, SpriteSheet.ghoast_left, 3);
	private AnimatedSprite right = new AnimatedSprite(32, 32, SpriteSheet.ghoast_right, 3);
	
	private AnimatedSprite animation = down;
	
	public TestMob(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}

	public void update() {
		time++;
		if(time % (random.nextInt(60) + 30) == 0) {
			dirX = random.nextInt(3) - 1;
			dirY = random.nextInt(3) - 1;
			if(random.nextInt(4) == 0) {
				dirX = 0;
				dirY = 0;
			}
		}
		if (walking) animation.update();
		else {
			wTime++;
			if (wTime >= wSpeed) {
				animation = down;
				animation.update();
				wTime = 0;
			}
		}
		
		int xa = 0;
		int ya = 0;
		if(dirY < 0) {
			ya--;
			animation = up;
		}
		else if(dirY > 0) {
			ya++;
			animation = down;
		}
		if(dirX < 0) {
			xa--;
			animation = left;
		}
		else if(dirX > 0) {
			xa++;
			animation = right;
		}
		if(xa != 0 || ya != 0) {
			move(xa,ya);
			walking = true;
		}else {
			walking = false;
		}
	}

	public void render(Screen screen) {
		sprite = animation.getSprite();
		screen.renderMob(x, y, sprite);
	}

}
