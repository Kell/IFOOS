package stuff;

import org.newdawn.slick.geom.Rectangle;

import entities.Bullet;
import entities.Entity;
import entities.PlayerShip;
import entities.Ship;
import ships.AlienShip;
import states.GamePlayState;


public class CollisionDetection {

	
	public static void checkCollision(Bullet b, Entity colideWithEntity) {
		
//		contains doesnt work without it O_o
		Rectangle rec = new Rectangle(colideWithEntity.getX(), colideWithEntity.getY(), colideWithEntity.getWidth(), colideWithEntity.getHeight());
		if(
		   rec.contains(b.getX(), b.getY())
		   ||
		   rec.contains(b.getX()+b.getWidth(), b.getY())
		   ||
		   rec.contains(b.getX(), b.getY()+b.getHeight())
		   ||
		   rec.contains(b.getX()+b.getWidth(), b.getY()+b.getHeight())
		   ) {
		
			
			Ship bulletOwnerShip = b.getOwner();
			
			// BULLETS FROM PLAYER SHIP
//			if (!( bulletOwnerShip instanceof AlienShip)) {
			if (bulletOwnerShip instanceof PlayerShip) {
				colideWithEntity.setHealth(0);
				b.setHealth(0);
				b.setDestroyed(true);
				GamePlayState.score += colideWithEntity.getScore();
			}
			// BULLETS FROM ALIEN SHIP (NORMAL && COMMANDER)
			else if ((bulletOwnerShip instanceof AlienShip)) {
				// ALIEN BULLETS COLLIDES WITH PLAYER SHIP
//				if(!(colideWithEntity instanceof AlienShip)) {
				if(colideWithEntity instanceof PlayerShip) {
					((PlayerShip) colideWithEntity).makeDamage(b.getDamage());
					b.setHealth(0);
					b.setDestroyed(true);
					
//					GamePlayState.lostLive = true;
				}
			}
		}
		else
		{
		}
	}
}
