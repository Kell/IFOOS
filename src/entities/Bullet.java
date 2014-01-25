package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


import states.GamePlayState;


public class Bullet extends Entity {
	
	int direction;
	private Ship owner;
	private boolean destroyed = false;
	protected int damage = 10;
	
	public Bullet(float x, float y, int height, int width, int direction, Ship owner) {
		super(x, y,height, width);
		this.dy = 20;
		this.direction = direction;
		this.owner = owner;
	}
	
	
	public Ship getOwner() {
		return owner;
	}


	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	
	public void setOwner(Ship owner) {
		this.owner = owner;
	}
	
	
	public int getDamage() {
		return damage;
	}
	

	public void updateLogic(int delta, GameContainer gc) {
		
		if(y + height < 0 || y + height >  gc.getHeight()) {
			this.getOwner().getBullets().remove(this);
			this.setDestroyed(true);
		}
		else
			move(delta, direction);
		
	}
	
	public void move(long delta, int direction) {
		// update the location of the entity based on move speeds
		if(direction == 1)
			y -= (delta * dy) / 100;
		else
			y += (delta * dy) / 100;
	}
	
	
	
}
