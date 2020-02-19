package PieMonsterEater.Engine.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import PieMonsterEater.Engine.EntityCore.GameObject;
import PieMonsterEater.Engine.EntityCore.Handler;

public class Sand extends GameObject {

	private Handler handler;
	private int timer = 50;
	
	public Sand(int x, int y, Handler handler) {
		super(x, y, handler);
		this.handler = handler;
	}
	
	public void tick() {
		x += velX;
		
		velX += -3;
		
		timer--;
		if(timer <= 0) handler.removeObj(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(64, 64, 64));
		g.fillRect(x, y, 5, 5);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 1, 1);
	}
	
	

}
