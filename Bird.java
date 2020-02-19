package PieMonsterEater.Engine.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import PieMonsterEater.Engine.EntityCore.GameObject;
import PieMonsterEater.Engine.EntityCore.Handler;
import PieMonsterEater.Engine.EntityCore.ID;

public class Bird extends GameObject {

	private Handler handler;
	private int timer = 50;
	
	public Bird(int x, int y, Handler handler) {
		super(x, y, handler);
		this.setSpriteSheet("res/SpriteSheet.png");
		this.setSprite(96, 0, 32, 32);
		this.setID(ID.Cactus);
		
		this.handler = handler;
	}
	
	public void tick() {
		x += velX;
		
		velX += -1;
		
		timer--;
		if(timer <= 0) handler.removeObj(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.GREEN);
		//g2d.draw(getBounds());
		
		g.drawImage(sprite, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y - 20, 32, 64);
	}
	
	

}
