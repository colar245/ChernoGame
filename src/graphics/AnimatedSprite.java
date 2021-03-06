package graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int length = 3;
	private int time = 0;
	
	public AnimatedSprite(int width, int height, SpriteSheet sheet, int length) {
		super(width, height, sheet);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if(length > sheet.getSprites().length) System.err.println("Error!");
	}

	public void update() {
		time++;
		if(time % rate == 0) {
			if(frame >= length - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
		}
		//System.out.println(sprite +" : "+ frame);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int frames) {
		rate = frames;
	}

	public void setFrame(int i) {
		if(i < 0 || i > sheet.getSprites().length - 1){
			System.err.println("Index out of bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[i];
	}
}
