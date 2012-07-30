package bullets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Bullet;
import entities.Ship;


import states.GamePlayState;


public class SplitBullet extends Bullet {
	
	int direction;
	private Ship owner;
	private float start_y;
	
	public SplitBullet(float x, float y, int height, int width, int direction, Ship owner) {
		super(x, y,height, width, direction, owner);
		this.dy = 20;
		this.direction = direction;
		this.owner = owner;
		this.start_y = y;

	}
	
	
	public Ship getOwner() {
		return owner;
	}


	public void setOwner(Ship owner) {
		this.owner = owner;
	}


	public void updateLogic(int delta) {
		if(y + height < 0)
			GamePlayState.removeList.add(this);
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
