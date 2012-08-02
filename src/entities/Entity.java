package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


public class Entity extends Rectangle {

	protected int height;
	protected int width;
	protected float x;
	protected float y;
	protected int dx = 10;
	protected int dy = 10;
	protected Image img;
	private int lives = 1;
	private int score = 0;
	
	protected boolean moveRight = false;
	protected boolean moveLeft 	= false;
	protected boolean moveUp 	= false;
	protected boolean moveDown 	= false;
	


	public Entity(float x, float y, int height, int width) {
		super(x, y, width, height);
		this.height = height;
		this.width = width;
		this.x = (int) x;
		this.y = (int) y;
	}

	public float getHeight() {
		return (int) height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean isMoveUp() {
		return moveUp;
	}

	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	public boolean isMoveDown() {
		return moveDown;
	}

	
	
	
	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}

	
	
	
	public void draw(Graphics g, int x, int y)
	{
		g.drawRect(x, y, width, height);
	}

	
	
	public void updateLogic(int delta) {
		// TODO Auto-generated method stub
		
	}
	
}
