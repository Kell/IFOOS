package ships;

import org.newdawn.slick.Color;

import stuff.ShipContainer;



public class AlienMotherShip extends AlienShip {

	private int dx = 11;
	
	public AlienMotherShip(ShipContainer container, int x, int y, int height, int width, int row) {
		super(container, x, y, height, width, row);
		super.setColor(Color.magenta);
		super.setScore(50);
	}
	
	
	public void updateLogic(int delta) {
		
		//MOVEMENT TO THE RIGHT
		if(x < (790 - width) && moveLeft == false)
		{
			if(!moveRight) 
				moveRight = true;
		}
		else
			moveRight = false;
		
		//MOVEMENT TO THE LEFT
		if(x > 10 && moveRight == false)
		{
			if(!moveLeft)
				moveLeft = true;
		}
		else
			moveLeft = false;
		
		move(delta);
	}
	
	
	public void move(long delta) {
		// update the location of the entity based on move speeds
		if(moveRight)
			x += (delta * dx) / 100;
		else if (moveLeft) 
			x -= (delta * dx) / 100;
		
	}

}
