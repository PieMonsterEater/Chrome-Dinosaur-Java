package PieMonsterEater.Engine.Player;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import PieMonsterEater.Engine.Core.Game;
import PieMonsterEater.Engine.EntityCore.GameObject;
import PieMonsterEater.Engine.EntityCore.Handler;
import PieMonsterEater.Engine.EntityCore.ID;
import PieMonsterEater.Engine.Menus.StateID;

public class MouseInput implements MouseListener {
	public Random r;

	private Handler handler;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY(); 
		
		Rectangle m = new Rectangle(mx, my, 1, 1);
		
		if(Game.state == StateID.MainMenu) {
			if(mx >= Game.WIDTH/3 + 35 && mx <= Game.WIDTH/3 + 135) {
				if(my >= 150 && my <= 200) Game.state = StateID.Play;
			}
			if(mx >= Game.WIDTH/3 + 35 && mx <= Game.WIDTH/3 + 135) {
				if(my >= 250 && my <= 300) Game.state = StateID.InfoMenu;
			}
			if(mx >= Game.WIDTH/3 + 35 && mx <= Game.WIDTH/3 + 135) {
				if(my >= 350 && my <= 400) System.exit(1);
			}
		}
		
		if(Player.dead == true) {
		if(m.intersects(Player.retry)) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player) {
				Player p = (Player) tempObject;
				p.reset();
				}
			}
		}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}