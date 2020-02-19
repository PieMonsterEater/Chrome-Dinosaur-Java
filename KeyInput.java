package PieMonsterEater.Engine.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import PieMonsterEater.Engine.Core.Game;
import PieMonsterEater.Engine.EntityCore.GameObject;
import PieMonsterEater.Engine.EntityCore.Handler;
import PieMonsterEater.Engine.EntityCore.ID;
import PieMonsterEater.Engine.Menus.StateID;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private Player player;
	
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
		if(tempObject.getID() == ID.Player) {
			Player p = (Player) tempObject;
			
			if(key == KeyEvent.VK_SPACE) {
				if(p.jumping == false) {
				p.setVelY(-15);
				p.jumping = true;
				}
			}
			if(key == KeyEvent.VK_DOWN) {
				p.crouching = true;
				if(p.alSet == false) {
					p.setAnimation(p.crouch, 10);
					p.alSet = true;
				}
			}
			if(key == KeyEvent.VK_L) {
				p.setX(100);
				p.setY(100);
				p.jumping = true;
			}
		}
	}
		
		if(key == KeyEvent.VK_ESCAPE) Game.state = StateID.MainMenu;
}
	
	public void keyReleased(KeyEvent e) {
int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
		
			
		if(tempObject.getID() == ID.Player) {
			Player p = (Player) tempObject;
			
			if(key == KeyEvent.VK_DOWN) {
				p.crouching = false;
				p.setAnimation(p.images, 10);
				p.alSet = false;
			}
		}
	}
		
		
	}
	
	
}
