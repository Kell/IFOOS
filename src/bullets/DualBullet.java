package bullets;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Bullet;
import entities.Ship;

import states.GamePlayState;


public class DualBullet  {
	
	int direction;
	private Ship owner;
	
	ArrayList<Bullet> bullets;
	
	public DualBullet(float x, float y, int height, int width, int direction, Ship owner) {
		
		System.out.println("new DualBullet");
		
		bullets = new ArrayList<Bullet>();
		
		bullets.add(new Bullet(x-15, y, height, width, direction, owner));
		bullets.add(new Bullet(x+15, y, height, width, direction, owner));
		

	}
	
	
	public ArrayList<Bullet> getBullets() {
		System.out.println("DualBullet - getBullets");
		return bullets;
	}
}
