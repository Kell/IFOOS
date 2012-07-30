package ships;
import org.newdawn.slick.Color;

import stuff.ShipContainer;



public class AlienCommanderShip extends AlienShip {

	public AlienCommanderShip(ShipContainer container, int x, int y, int height, int width, int row) {
		super(container, x, y, height, width, row);
		super.setColor(Color.red);
		super.setScore(20);
	}

}
