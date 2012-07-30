package ships;

import org.newdawn.slick.Color;

import entities.Bullet;
import entities.Ship;

import states.GamePlayState;
import stuff.ShipContainer;


public class AlienShip extends Ship {
	
	private boolean rightEdge = false;
	private boolean leftEdge = false;
	private int tmpY;
	private int score = 10;
	private int row;
	private Color color = Color.green;
	private ShipContainer shipContainer;
	
	
	public AlienShip(ShipContainer container, int x, int y, int height, int width, int row) {
		super( x, y, height, width);
		shipContainer = container;
		moveRight = true;
		this.row = row;
		
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	
	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public void updateLogic(int delta) {
		
		//MOVEMENT TO THE RIGHT
		if(x < (790 - width) && moveLeft == false)
		{
			
			if(!leftEdge) {
				leftEdge = true;
				rightEdge = false;
				tmpY = y;
			}
			
			if(!moveRight) 
				moveDown = true;
		}
		else
			moveRight = false;
		
		//MOVEMENT TO THE LEFT
		if(x > 10 && moveRight == false)
		{
			if (!rightEdge) {
				rightEdge = true;
				leftEdge = false;
				tmpY = y;
			}
			
			if(!moveLeft)
				moveDown = true;
		}
		else
			moveLeft = false;
		
		
		
		//DOWN MOVEMENT
		if(moveDown && y >= (tmpY + 35)) {
			moveDown = false;
			
			if(rightEdge) {
				moveLeft = true;
				rightEdge = false;
			}
			else if(leftEdge) {
				moveRight = true;
				leftEdge = false;
			}
		}
		move(delta);
		
		int rand = (int) (Math.random() * (3500 - 1) + 1);
		if(rand <= 3)
		{
//			if(GamePlayState.bullets.size() == 0){
//				Bullet b = new Bullet(x+((width/2) -5), y+ height, 10, 10, 2, this);
//				if(b != null) {
//					GamePlayState.bullets.add(b);
//				}
//			}
			if(super.bulletLoader.size() == 0){
				Bullet b = new Bullet(x+((width/2) -5), y+ height, 10, 10, 2, this);
				if(b != null) {
					super.bulletLoader.add(b);
				}
			}
		}
		
		if(y+height >= 550)
			GamePlayState.gameOver = true;
	}
	
	public void move(long delta) {
		// update the location of the entity based on move speeds
		dx = (int) shipContainer.getDx();
		dy = (int) shipContainer.getDy();
		
		if(moveRight)
			x += (delta * dx) / 100;
		else if (moveLeft) 
			x -= (delta * dx) / 100;
		else if (moveUp)
			y -= (delta * dy) / 100;
		else if (moveDown) 
			y += (delta * dy) / 100;
		
	}
}
