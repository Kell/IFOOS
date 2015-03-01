package weapons;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Bullet;
import entities.Ship;
import entities.Weapon;

import bullets.SplitBullet;



public class SplitWeapon extends Weapon {

	private int heatLoad = 200;
	private int heatIncrease = 60;
	private float heatDecay = 0.011f;
	private int heat = 0;
	
	public SplitWeapon() {
		super.setImg("res/splitweapon.png");
	}
	
	
	
	public void shoot(Ship owner) {
		
		if(!blocked)
		{
			SplitBullet b = new SplitBullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner);
			owner.getBulletLoader().add(b);
			increaseHeat();
		}
		
	}
	
}
