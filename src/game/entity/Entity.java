package game.entity;

import game.level.Level;
import graphics.Screen;
import graphics.Sprite;

import java.util.Random;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class Entity {

	public int x, y;
	private boolean removed = false;
	protected Sprite sprite;
	protected Level level;
	protected final Random random = new Random();
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}






