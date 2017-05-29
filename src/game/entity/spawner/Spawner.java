package game.entity.spawner;

import game.entity.Entity;
import game.entity.particle.Particle;
import game.level.Level;

public abstract class Spawner extends Entity{
		
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type; 
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		this.x = x;
		this.y = y;
		this.type = type;
		for(int i = 0; i < amount; i++) {
		}
	}
	
}
