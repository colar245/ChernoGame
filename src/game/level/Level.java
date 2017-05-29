package game.level;

import game.entity.Entity;
import game.entity.object.projectile.Projectile;
import game.entity.particle.Particle;
import game.level.tile.Tile;
import graphics.Screen;

import java.util.ArrayList;
import java.util.List;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public static Level level1 = new SpawnLevel("/levels/level1.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void loadLevel(String path) {
		
	}

	protected void generateLevel() {

	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for(int i = 0; i < particles.size(); i++){
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if(getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;  //kod rez 16*16
		int x1 = ((xScroll + screen.width) >> 4) + 1;
		int y0 = yScroll >> 4;
		int y1 =((yScroll + screen.height) >> 4) + 1;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).render(screen);
		}
	}
	
	public void add(Entity e){
		e.init(this);
		if(e instanceof Particle){
			particles.add((Particle) e);
		}else if(e instanceof Projectile) {
			projectiles.add((Projectile) e);
		}else{
			entities.add(e);
		}
	}
	
	// grass = 0x00C116
	// rock = 0xA0A0A0
	// water = 0x0000DD
	// tree = 0xffFF6600
	// big tree = 0xff7F3300
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if(tiles[x + y * width] == 0xff00C116 ) return Tile.grass;
		if(tiles[x + y * width] == 0xff0000DD) return Tile.water;
		if(tiles[x + y * width] == 0xffA0A0A0) return Tile.rock;
		if(tiles[x + y * width] == 0xffFF0000) return Tile.brush;
		if(tiles[x + y * width] == 0xffFF6600) return Tile.tree;
		if(tiles[x + y * width] == 0xff7F3300) return Tile.bigTree;
		if(tiles[x + y * width] == 0xff000000) return Tile.sWal1;
		if(tiles[x + y * width] == 0xffDDDDDD) return Tile.sWall2;
		if(tiles[x + y * width] == 0xff7A4A4A) return Tile.sFloor2;
		if(tiles[x + y * width] == 0xff606060) return Tile.sFloor1;
		if(tiles[x + y * width] == 0xff683434) return Tile.wFloor;
		if(tiles[x + y * width] == 0xffCE5200) return Tile.wWall;
		return Tile.voidTile;
	}
}







