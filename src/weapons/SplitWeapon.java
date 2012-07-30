package weapons;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Bullet;
import entities.Ship;
import entities.Weapon;

import bullets.SplitBullet;



public class SplitWeapon extends Weapon {

	
	public SplitWeapon() {
		super.setImg("res/splitweapon.png");
	}
	
	
	
	public ArrayList<Bullet> shoot(Ship owner) {
		
		SplitBullet b = new SplitBullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner);
		
//		super.bullets.add(b);
		owner.bullets.add(b);
		
		return owner.bullets; 
		
//		return super.bullets; 
	}
	
}
