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
		
		System.out.println("NEW SPLITBULLET");

	}
	
	@Override
	public void updateLogic(int delta) {
		
		//SPLIT UP
		System.out.println("SPLIT BULLET Y:"+y+" start_y:"+start_y);
		if(y - start_y >= 100)
		{
			System.out.println("SPLIT BULLET!!!!!");
			owner.getBulletLoader().add(new SplitedBullet(1, x, y, direction, owner));
			owner.getBulletLoader().add(new SplitedBullet(2, x, y, direction, owner));
			this.setDestroyed(true);
			owner.getBullets().remove(this);
		}
		
		if(y + height < 0)
			GamePlayState.removeList.add(this);
		else
			move(delta, direction);
		
	}
}
