package PieMonsterEater.Engine.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import BuildingBlocks.Block;
import PieMonsterEater.Engine.EntityCore.GameObject;
import PieMonsterEater.Engine.EntityCore.Handler;
import PieMonsterEater.Engine.EntityCore.ID;

public class Player extends GameObject {
	
	private Handler handler;
	public BufferedImage[] images = new BufferedImage[2];
	public BufferedImage[] crouch = new BufferedImage[2];
	public boolean jumping = true, crouching = false, alSet = false;
	public static boolean dead = false;
	public int gravity = 1;
	private int score = 0;
	public static Rectangle retry = new Rectangle(100, 160, 110, 30);
	private int MAXHEIGHT = 30;
	
	public Player(int x, int y, Handler handler) {
		super(x, y, handler);
		this.handler = handler;
		this.setID(ID.Player);
		this.setSpriteSheet("res/SpriteSheet.png");
		
		images[0] = ss.grabSprite(0, 0, 32, 32);
		images[1] = ss.grabSprite(32, 0, 32, 32);
		
		crouch[0] = ss.grabSprite(128, 0, 32, 32);
		crouch[1] = ss.grabSprite(160, 0, 32, 32);
		
		this.setAnimation(images, 10);
	}

	public void tick() {
		if(jumping == true) y += velY;
		
		if(y <= MAXHEIGHT) y = MAXHEIGHT;
		
		if(dead == false)velY += gravity;
		
		ani.tick();
		this.setSpr(ani.getImage());
		
		if(dead == false) {
		collision();
		}
		
		//System.out.println(dead);
	}

	public void render(Graphics g) {
		Font fnt = new Font("ComicSans", Font.BOLD, 50);
		Font fnt1 = new Font("ComicSans", Font.BOLD, 40);
		
		
		Graphics2D g2d = (Graphics2D) g;
		if(dead == false) {
		score++;
		g.setColor(Color.BLUE);
		//g2d.draw(getBounds());
		g.setColor(new Color(64, 64, 64));
		g.drawString("" + score, 530, 25);
		g.drawImage(sprite, x, y, 32, 64, null);
		} else if(dead == true) {
			g.setColor(new Color(64, 64, 64));
			g.setFont(fnt);
			g.drawString("YOU DIED!", 100, 100);
			g.setFont(fnt1);
			g.drawString("Score: " + score, 100, 140);
			g.drawString("Retry?", retry.x, retry.y + 30);
			g2d.draw(retry);
		}
	}
	
	public void collision() {
		System.out.println("colliding");
		
	for(int i = 0; i < handler.object.size(); i++) {
		GameObject tempObject = handler.object.get(i);
		
		if(tempObject.getID() == ID.Block) {
			Block b = (Block) tempObject;
			if(getBounds().intersects(b.getBoundsTop())) {
				y = b.getY() - 65;
				jumping = false;
			}
			if(getBounds().intersects(b.getBoundsBottom())) y = b.getY() + 30;
			if(getBounds().intersects(b.getBoundsRight())) x = b.getX() + 32;
			if(getBounds().intersects(b.getBoundsLeft())) x = b.getX() - 31;
		}
		
		if(tempObject.getID() == ID.Cactus) {
			if(getBounds().intersects(tempObject.getBounds())) dead = true;
		}
	}
	}
	
	public void reset() {
		dead = false;
		score = 0;
	}

	public Rectangle getBounds() {
		int height = 0, posY = 0;
		if(crouching == false) {
			height = 64;
			posY = 0;
		}
		if(crouching == true)  {
			height = 32;
			posY = 28;
		}
		return new Rectangle(x, y + posY, 32, height);
	}
	
	

}
