package states;



import java.util.ArrayList;

import ships.AlienMotherShip;
import ships.AlienShip;
import stuff.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Bullet;
import entities.Entity;
import entities.Ship;
import entities.Weapon;

import stuff.CollisionDetection;
import stuff.ShipContainer;

public class GamePlayState extends BasicGameState {

	private int id;

	private Ship playerEnt;
	private AlienMotherShip alienMotherShip;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Rectangle> removeList;
	public static ArrayList<Ship> entityList;
	public static ArrayList<Rectangle> starsList;
	private String scoreLabel = "Score";
	public static int score = 0;
	public static boolean gameOver = false;
	private boolean win = false;
	private boolean gameStarted;
	private boolean isCanonReady = true;
	public static boolean lostLive = false;
	public int lostLiveDrawCounter = 0;
	private boolean drawWeapons = false;
	
	Image entityImage = null;
	ShipContainer shipContainer;
	Rectangle shipContainerBorders;
	
	public GamePlayState(int gameplaystate) {
		this.id = gameplaystate;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		gc.setVSync(true);
		gc.setTargetFrameRate(60);
		gc.setShowFPS(true);
		
		gameStarted = false;

		//INITIALIZE LISTS
		bullets = new ArrayList<Bullet>();
		entityList = new ArrayList<Ship>();
		
		//ADD PLAYER SHIP
		playerEnt = new Ship((gc.getWidth() / 2) - 15, 550, 32, 32);
		playerEnt.setLives(3);
		entityList.add(playerEnt);
		
		//CREATE ALIEN SHIP CONTAINER (4 ROWS WITH 5 SHIPS);
		shipContainer = new ShipContainer(4, 8);
		shipContainerBorders = shipContainer.borders;
		entityList.addAll(shipContainer.alienShips);
		
		
		//=============================================================================
		// START: CREATING SOME INITIAL STARS 
		//=============================================================================
		starsList = new ArrayList<Rectangle>();
		//=============================================================================
		// END: CREATING SOME INITIAL STARS 
		//=============================================================================
		
		removeList = new ArrayList();
	}

	
	
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, final Graphics g) throws SlickException {
		
		g.setLineWidth(1);
		//BACKGROUND ==================================================================
		g.setColor(Color.black);
		g.fillRect(0,  0, 800, 600);
		
		// STARS =======================================================================
		g.setColor(Color.white);
		for(Rectangle r: starsList) {
			g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		}
		
		if(gameStarted)
		{
			//============================================================================================
			// START: ALIEN SHIP CONTAINER 
			//============================================================================================
			float tmp_x = 0;
			float tmp_y = 0;
			float tmp_max_x = 0;
			int tmp_row = 0;
			for(Ship e: entityList) {
				if(e instanceof AlienShip) {
					g.setColor(((AlienShip) e).getColor());
					
					if(tmp_x == 0)
						tmp_x = e.getX();
					else {
						if(tmp_x > e.getX())
							tmp_x = e.getX();
					}
					
					if(tmp_y == 0)
						tmp_y = e.getY();
					else {
						if(tmp_y > e.getY())
							tmp_y = e.getY();
					}
					if(((AlienShip) e).getRow() > tmp_row)
						tmp_row = ((AlienShip) e).getRow();
					
					if(e.getX() > tmp_max_x)
						tmp_max_x = e.getX();
					
				} //END: if (e instanceof AlienShip)
				else
					g.setColor(Color.white);
				
	//			if(!(e instanceof AlienShip)) {
	//				e.getImg().draw(e.getX(), e.getY());
	//			} else
					e.draw(g, (int) e.getX(), (int) e.getY());
					
					
			}//END: (Entity e: entityList)
			
			shipContainerBorders.setX(tmp_x);
			shipContainerBorders.setY(tmp_y);
			shipContainerBorders.setHeight((int) (tmp_row*40)-10);
			shipContainerBorders.setWidth((int)(tmp_max_x-tmp_x)+30);
			
			if(((shipContainerBorders.getY()+ shipContainerBorders.getHeight()) > 350) && shipContainer.getDx() != 15) {
				System.out.println("increase speed!");
				shipContainer.setDx(15);
				shipContainer.setDy(15);
			}
			
			
			if(tmp_y >= 100 && alienMotherShip == null) {
				alienMotherShip = new AlienMotherShip(null, -80, 20, 30, 90, 0);
				entityList.add(alienMotherShip);
			}
			
	//		if(alienMotherShip != null)
	//		{
	//			g.setColor(alienMotherShip.getColor());
	//			alienMotherShip.draw(g, (int) alienMotherShip.getX(), (int) alienMotherShip.getY());
	//		}
			//============================================================================================
			// END: ALIEN SHIP CONTAINER 
			//============================================================================================
		
			
			// BULLETS =================================================
			g.setColor(Color.yellow);
			for(Bullet b: bullets) {
				System.out.println("draw Bullet!:"+b.getX()+"|"+b.getY());
	//			entityImage = b.getImg();
	//			if(entityImage != null)
	//				entityImage.draw(b.getX(), b.getY());
				g.drawRect(b.getX(), b.getY(), b.getHeight(), b.getWidth());
			}
			
			
			
			//Weapons
			if(drawWeapons) {
				int w_x = 10;
				int w_y = gc.getHeight() - 60;
				int w_cntr = 0;
				g.setLineWidth(3);
				
				for (Weapon w : playerEnt.getWeapons()) {
					if(playerEnt.getSelectedWeaponIndex() == w_cntr)
						g.setColor(Color.white);
					else
						g.setColor(Color.gray);
					
					w.getImg().draw(w_x, w_y);
					g.drawRoundRect(w_x, w_y, 45, 45, 4, 4);
					
					
					if(playerEnt.getSelectedWeaponIndex() != w_cntr) {
						Color trans = new Color(0f,0f,0f,0.7f);
				        g.setColor(trans);
				        g.fillRect(w_x+3, w_y+3, 40, 40);
					}
					
					w_cntr++;
					
					w_y -= 60;
				}
			}
		}
		else
		{
			g.drawString("Are you ready?" , (gc.getWidth() / 2) - 60, (gc.getHeight() / 2) - 10);
			g.drawString("Press return to start the game" , (gc.getWidth() / 2) - 120, (gc.getHeight() / 2) + 30);
		}
		
		
		// SCORE ====================================================
		g.setColor(Color.white);
		g.drawString(scoreLabel, 650, 10);
		g.drawString(Integer.toString(score), 700, 10);
		
		// LIVES ====================================================
		g.setColor(Color.white);
		g.drawString("LIVES", 30, 20);
		int lives_x = 70;
		for (int i = 1; i <= playerEnt.getLives(); i++) {
			lives_x += 20; 
			g.fillRect(lives_x, 25, 10, 10);
		}
		
		if(lostLive) {
			int gcWidth = gc.getWidth(); 
			int gcHeight = gc.getHeight(); 
			
			Color trans = new Color(1f,0f,0f,0.5f);
	        g.setColor(trans);
	        g.fillRect(0,0, gcWidth, gcHeight);
	        if(lostLiveDrawCounter == 6) {
	        	lostLive = false;
	        	lostLiveDrawCounter = 0;
	        }
	        
	        lostLiveDrawCounter++;
	        
		}
		
		
		
		// GAME OVER ================================================
		if(gameOver) {
			g.setColor(new Color(221, 72, 20));
			String gameOverString = "GAME OVER";
			if(win)
				gameOverString = "YOU WON!";
			else
				gameOverString = "YOU LOST!";
				
			g.drawString(gameOverString , (gc.getWidth() / 2) - 20, (gc.getHeight() / 2) - 10);
		}
	}

	
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		//==================================================================
		// START: RANDOM STARS
		//==================================================================
		int rand = (int) (Math.random() * (500 - 1) + 1);
		if(rand <= 40)
		{
			int stars_x = (int) (Math.random() * (800 - 1) + 1);
			int width =	3;
			if(rand < 20)
				width = 1;
			starsList.add(new Rectangle(stars_x, -12,	width, 7));
		}
		
		//CHECK WHETHER STARS ARE OUT OF SCREEN => REMOVE OBJECT
		for(Rectangle r: starsList) {
			r.setY((float) (r.getY()+((delta *9.5) / 100)));
			if(r.getY() > 650)
				removeList.add(r);
		}
		//==================================================================
		// END: RANDOM STARS
		//==================================================================

		
		handleInput(gc, playerEnt, delta);
		
		if(!gameOver && gameStarted) {
			
			boolean enemiesAlive = false;

			for(Ship s: entityList) {
				s.updateLogic(delta);
				
				if (s instanceof AlienShip && !(s instanceof AlienMotherShip))
					enemiesAlive = true;
				
				if(s.bulletLoader.size() > 0)
				{
					System.out.println("ADD BULLETS FROM LOADER");
					bullets.addAll(s.bulletLoader);
					s.bullets.addAll(s.bulletLoader);
					s.bulletLoader.clear();
				}
			}
			
			
			//Bullets
			isCanonReady = true;
			for(Bullet b: bullets) {
				b.updateLogic(delta, gc);
				
				for (Entity ship: entityList) {
					
					CollisionDetection.checkCollision(b, ship);
					boolean removed = false;
					if(b.getLives() <= 0 || b.isDestroyed())
					{
						removeList.add(b);
						removed = true;
					}
					if(ship.getLives() <= 0)
						removeList.add(ship);
					
					if(!removed && !(b.getOwner() instanceof AlienShip))
						isCanonReady = false;
				}
			}
			
			
			if(playerEnt.getLives() <= 0) {
				gameOver = true;
				win = false;
			}
			else if (!enemiesAlive) {
				gameOver = true;
				win = true;
			}
			
			
			if(alienMotherShip != null)
				alienMotherShip.updateLogic(delta);
			
			
			// REMOVE NOT NEEDED OBJECTS ==========================================
			if(removeList.size() > 0) {
				entityList.removeAll(removeList);
				bullets.removeAll(removeList);
				starsList.removeAll(removeList);
				removeList.clear();
			}
		}
		
		
		
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Game.GAMEMENUSTATE);
		}
		
	}

	
	@Override
	public int getID() {
		return this.id;
	}
	
	
	
	public void handleInput(GameContainer gc, Ship entity, int delta) {
		
		if(gameStarted && !gameOver) {
			if (gc.getInput().isKeyDown(gc.getInput().KEY_RIGHT)) {
				entity.setMoveRight(true);
			}
			else if (gc.getInput().isKeyDown(gc.getInput().KEY_LEFT)) {
				entity.setMoveLeft(true);
			}
			
			if (!gc.getInput().isKeyDown(gc.getInput().KEY_RIGHT)) {
				entity.setMoveRight(false);
			}
			if (!gc.getInput().isKeyDown(gc.getInput().KEY_LEFT)) {
				entity.setMoveLeft(false);
			}
			
			
			if (gc.getInput().isKeyPressed(gc.getInput().KEY_C)) {
				if(drawWeapons)
					drawWeapons = false;
				else
					drawWeapons = true;
			}
			
			
			if (drawWeapons) {
				if (gc.getInput().isKeyPressed(gc.getInput().KEY_TAB)) {
					System.out.println("change Weapon");
					int w_count = playerEnt.getWeaponsCount();
					int w_cur_index = playerEnt.getSelectedWeaponIndex();
					
					if(w_cur_index >= (w_count - 1))
						playerEnt.setSelectedWeaponIndex(0);
					else
						playerEnt.setSelectedWeaponIndex(w_cur_index+1);
				}
			}
			
			
			if(gc.getInput().isKeyPressed(gc.getInput().KEY_SPACE) && isCanonReady)
			{
//				bullets.addAll(entity.getSelectedWeapon().shoot(entity));
//				entity.bullets.addAll(entity.getSelectedWeapon().shoot(entity));
				entity.bulletLoader.addAll(entity.getSelectedWeapon().shoot(entity));
			}
			
			entity.move(delta);
		}
		else
		{
			if (gc.getInput().isKeyPressed(gc.getInput().KEY_RETURN)) {
				System.out.println("PRESSED");
				gameStarted =  true;
			}
		}
		

	}
	
	
}