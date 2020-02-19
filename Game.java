//It should be noted that some of this code was originally written by Zack Berenger (Or RealTutsGML on Youtube). The rest was done by me, PieMonsterEater. 

package PieMonsterEater.Engine.Core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import BuildingBlocks.Block;
import PieMonsterEater.Engine.Audio.MusicPlayer;
import PieMonsterEater.Engine.EntityCore.Handler;
import PieMonsterEater.Engine.LevelLoader.LevelLoader;
import PieMonsterEater.Engine.LevelLoader.Spawner;
import PieMonsterEater.Engine.Menus.InfoMenu;
import PieMonsterEater.Engine.Menus.MainMenu;
import PieMonsterEater.Engine.Menus.StateID;
import PieMonsterEater.Engine.Player.Camera;
import PieMonsterEater.Engine.Player.KeyInput;
import PieMonsterEater.Engine.Player.MouseInput;
import PieMonsterEater.Engine.Player.Player;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	public static StateID state = StateID.MainMenu;
	public boolean running = false;
	
	private Thread thread;
	private Handler handler;
	private MusicPlayer mp;
	
	private MainMenu mainM = new MainMenu();
	private InfoMenu iMenu = new InfoMenu();
	
	private Spawner spawner;
	
	Camera camera = new Camera(0, 0);;
	
	public Game() {
		new Window("PieEngine", WIDTH, HEIGHT, this);
		
		handler = new Handler();
		handler.addObj(new Player(100, 400, handler));
		handler.addObj(new Block(100, 420, handler));
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		spawner = new Spawner(handler);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		thread = new Thread(this);
		try {
			thread.join();
			running = false;
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
				if (running) 
					render();
					frames++;
					
					if (System.currentTimeMillis() - timer > 1000) {
						timer += 1000;
						System.out.println("FPS:" + frames);
						frames = 0;
					}
				
			}
			stop();
	}
	
	public void tick() {
		if(this.state == StateID.Play) {
		handler.tick();
		spawner.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		if(this.state == StateID.Play) g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//////////////////////////////////////////
		if(this.state == StateID.MainMenu)  {
			mainM.render(g);
		}
		if(this.state == StateID.InfoMenu) {
			iMenu.render(g);
		}
		if(this.state == StateID.Play) {
		handler.render(g);
		g.drawLine(-3, 420, 700, 420);
		}
		//////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game();

	}

}
