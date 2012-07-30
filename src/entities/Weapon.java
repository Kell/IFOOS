package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Weapon {

	
	ArrayList<Bullet> bullets;
	private Image img;
	
	public Weapon() {
		bullets = new ArrayList<Bullet>();
		try {
			img = new Image("res/weapon.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Bullet> shoot(Ship owner) {
		
		owner.bullets.add(new Bullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner));
		bullets.add(new Bullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner));
		
		return bullets;
	}


	public Image getImg() {
		return img;
	}


	public void setImg(String path) {
		try {
			img = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
