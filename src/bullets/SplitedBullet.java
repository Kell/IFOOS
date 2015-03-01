package bullets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Bullet;
import entities.Ship;


import states.GamePlayState;


public class SplitedBullet extends Bullet {
	
	int direction;
	private Ship owner;
	private float start_y;
	//left/right bullet
	private int type;
	
	public SplitedBullet(int type, float x, float y, int direction, Ship owner) {
		super(x, y, 5, 5, direction, owner);
		this.dy = 20;
		this.direction = direction;
		this.owner = owner;
		this.start_y = y;
		this.type = type;
	}
	
	
	public void move(long delta, int direction) {
		// update the location of the entity based on move speeds
		if(direction == 1) {
			y -= (delta * dy) / 100;
			if(type == 1)	
				x -= (delta * dy) / 300;
			else
				x += (delta *dy) / 300;
		}
		else {
			y += (delta * dy) / 100;
			if(type == 1)	
				x += (delta * dy) / 100;
			else
				x -= (delta *dy) / 100;
		}
			
		
	}
}
