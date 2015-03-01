package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Weapon {

	
	private Image img;
	private int heatLoad = 100;
	private int heatIncrease = 20;
	private float heatDecay = 0.001f;
	private int heat = 0;
	protected boolean blocked = false;
	
	public Weapon() {
		try {
			img = new Image("res/weapon.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public void shoot(Ship owner) {
		
		if(!blocked)
		{
			owner.getBulletLoader().add(new Bullet((int) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner));
//		bullets.add(new Bullet((int ) owner.getX()+((owner.getWidth()/ 2)), owner.getY() - 10, 2, 10, 1, owner));
			increaseHeat();
		}
	}

	
	public void increaseHeat() {
		
		if(heat+heatIncrease <= heatLoad)
			heat += heatIncrease;
		else if(heat+heatIncrease > heatLoad)
			heat = heatLoad;
		
		//TODO: block weapon if overheated
		if(((heatLoad/100)*heat) > 50)
			blocked = true;
	}
	

	public Image getImg() {
		return img;
	}


	public void setImg(String path) {
		try {
			img = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public int getHeatLoad() {
		return heatLoad;
	}


	public void setHeatLoad(int heatLoad) {
		this.heatLoad = heatLoad;
	}


	public int getHeatIncrease() {
		return heatIncrease;
	}


	public void setHeatIncrease(int heatIncrease) {
		this.heatIncrease = heatIncrease;
	}


	public int getHeat() {
		return heat;
	}


	public void setHeat(int heat) {
		this.heat = heat;
	}
	
	public void decreaseHeat() {
		heat -= heatDecay;
		
		if(((heatLoad/100)*heat) < 50)
			blocked = false;
		
		if(heat < 0)
			heat = 0;
	}

	
	
	
	
}
