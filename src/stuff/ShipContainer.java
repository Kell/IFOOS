package stuff;

import java.awt.Color;
import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

import ships.AlienCommanderShip;
import ships.AlienShip;

import entities.Ship;


public class ShipContainer {

	
	private int x = 100;
	private int y = 30;
	private int width;
	private int height;
	private float dx = 10;
	private float dy = 10;
	private Color color = Color.blue;
	public static Rectangle borders;
	
	public static ArrayList<Ship> alienShips;
	
	public ShipContainer(int rows, int ships) {
		
		alienShips = new ArrayList<Ship>();
		//alien ship rows
		int tmp_y = y;
		for(int r = 1; r <= rows; r++) {
			//ships per row
			int tmp_x = x;
			for(int i = 1; i <= ships; i++) {
				if(r > 2)
					alienShips.add(new AlienShip(this, tmp_x, tmp_y, 30, 30, r));
				else
					alienShips.add(new AlienCommanderShip(this, tmp_x, tmp_y, 30, 30, r));
					
				tmp_x += 60;
				
			}
			tmp_y += 40;
		}
		//width  (ship*(ship_wdith+extra_space)-space_between_ships)
		//height
		borders = new Rectangle(x, y, ((ships*60)-30), (rows*70)-10);
	}
	
	public ShipContainer(int[][] levelShips) {
		
		alienShips = new ArrayList<Ship>();
		//alien ship rows
		int tmp_y = y;
		int tmpShips = 0;
		int tmpRows = 0;
		for(int r = 1; r < levelShips.length; r++) {
			//ships per row
			int tmp_x = x;
			tmpRows++;
			tmpShips = 0;
			for(int s = 1; s < levelShips[r].length; s++) {
				tmpShips++;	
				if(levelShips[r][s] == 1)
					alienShips.add(new AlienShip(this, tmp_x, tmp_y, 30, 30, r));
				else if(levelShips[r][s] == 2)
					alienShips.add(new AlienCommanderShip(this, tmp_x, tmp_y, 30, 30, r));
				
				tmp_x += 60;
				
			}
			tmp_y += 40;
		}
		borders = new Rectangle(x, y, ((tmpShips*60)-30), (tmpRows*70)-10);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public static Rectangle getBorders() {
		return borders;
	}

	public static void setBorders(Rectangle borders) {
		ShipContainer.borders = borders;
	}

	public static ArrayList<Ship> getAlienShips() {
		return alienShips;
	}

	public static void setAlienShips(ArrayList<Ship> alienShips) {
		ShipContainer.alienShips = alienShips;
	}
	
	
	
	

}
