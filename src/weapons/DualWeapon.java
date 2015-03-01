package weapons;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import bullets.DualBullet;

import entities.Bullet;
import entities.Ship;
import entities.Weapon;



public class DualWeapon extends Weapon {

	private int heatLoad = 200;
	private int heatIncrease = 40;
	private float heatDecay = 0.011f;
	private int heat = 0;
	
	
	public DualWeapon() {
		super.setImg("res/dualweapon.png");
	}
	
	
	
	public void shoot(Ship owner) {
		
		if(!blocked)
		{
			DualBullet db = new DualBullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner);
			owner.getBulletLoader().addAll(db.getBullets());
			increaseHeat();
		}
	}
	
}
