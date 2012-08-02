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
	
	
	
	public void shoot(Ship owner) {
		System.out.println("SplitWeapon shoot");
		SplitBullet b = new SplitBullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner);
		
		owner.getBulletLoader().add(b);
		
	}
	
}
