package entities;

public class PlayerShip extends Ship {
	

	public PlayerShip(int x, int y, int height, int width) {
		super(x, y, height, width);
	}
	
	
	public void makeDamage(int damage) {
		if(health > 0)
			health -= damage;
	}
	
}
