package PieMonsterEater.Engine.LevelLoader;

import java.util.Random;

import PieMonsterEater.Engine.Entities.Bird;
import PieMonsterEater.Engine.Entities.Cactus;
import PieMonsterEater.Engine.Entities.Sand;
import PieMonsterEater.Engine.EntityCore.Handler;

public class Spawner {

	private Handler handler;
	private int timer = 50, interval = 0;
	private Random r = new Random();
	
	public Spawner(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		timer--;
		
		interval = 0;
		
		int cluster;
		
		if(timer <= 0) {
			cluster = r.nextInt(((3 - 1) + 1) + 1);
			switch(cluster) {
			case 0: handler.addObj(new Bird(600, 320, handler));
				break;
			case 1: handler.addObj(new Cactus(600, 360, handler));
				break;
			case 2: for(int i = 0; i < 2; i++) {
				handler.addObj(new Cactus(600+interval, 360, handler));
				interval += 40;
			}
				break;
			case 3: for(int i = 0; i < 3; i++) {
				handler.addObj(new Cactus(600+interval, 360, handler));
				interval += 40;
			}
				break;
			}
			timer = 50;
		}
		
		handler.addObj(new Sand(650, r.nextInt(((500 - 450) + 1) + 1) + 420, handler));
	}
	
}
