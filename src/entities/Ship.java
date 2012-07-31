package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import weapons.DualWeapon;
import weapons.SplitWeapon;




public class Ship extends Entity {

	
	ArrayList<Weapon> weapons;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Bullet> bulletLoader;
	int selectedWeapon = 0;
	
	public Ship(int x, int y, int height, int width) {
		super(x, y, height, width);
		
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon());
		weapons.add(new DualWeapon());
		weapons.add(new SplitWeapon());
		
		bullets = new ArrayList<Bullet>();
		bulletLoader = new ArrayList<Bullet>();
	}
	
	
	public void move(long delta) {
		// update the location of the entity based on move speeds
		if(moveRight && x < (790 - width))
			x += (delta * dx) / 100;
		else if (moveLeft && x > 10) 
			x -= (delta * dx) / 100;
		else if (moveUp)
			y -= (delta * dy) / 100;
		else if (moveUp) 
			y += (delta * dy) / 100;
		
	}
	
	
	
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
	
	public Weapon getSelectedWeapon() {
		return weapons.get(selectedWeapon);
	}
	
	public int getSelectedWeaponIndex() {
		return selectedWeapon;
	}
	
	public void setSelectedWeaponIndex(int index) {
		selectedWeapon = index;
	}
	
	public int getWeaponsCount() {
		return weapons.size();
	}
}
