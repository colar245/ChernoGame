package game;

import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import game.entity.mob.Player;
import game.entity.mob.TestMob;
import game.input.Keyboard;
import game.input.Mouse;
import game.level.Level;
import game.level.RandomLevel;
import game.level.SpawnLevel;
import game.level.TileCoord;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int scale = 3;
	public static String title = "New Game";
	
	boolean gameStarted = false;
	
	private Screen screen;
	private Thread gameThread;
	private Keyboard key;
	private JFrame frame;
	private Level level;
	private Player player;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(WIDTH * scale, HEIGHT * scale);
		setPreferredSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		key = new Keyboard();
		//level = new RandomLevel(64, 64);
		level = Level.level1;
		TileCoord playerSpawn = new TileCoord(46, 35);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);
		
		Mouse mouse = new Mouse();
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		addKeyListener(key);
	}
	
	public synchronized void start() {
		gameStarted = true;
		gameThread = new Thread(this, "display");
		gameThread.start();
	}
	
	public synchronized void stop() {		
		gameStarted = false;
		try {
			gameThread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		key.update();
		player.update();
		level.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		//screen.renderSheet(46, 35, SpriteSheet.ghoast_down, false); //!
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		g.dispose();
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		
		requestFocus();
		while (gameStarted) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title+"   |   "+updates+" ups, "+frames+" fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public static void main(String[] args) {
		Game game = new Game();
			game.frame.setResizable(false);
			game.frame.add(game);
			game.frame.pack();
			game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.frame.setLocationRelativeTo(null);
			game.frame.setVisible(true);
			
			game.start();
	}
}