package states;


import stuff.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOptionState extends BasicGameState {

	private int id;
	private int screenWidth;
	private int screenHeight;
	private Image options = null;

	public GameOptionState(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame state)
			throws SlickException {
		screenWidth = gc.getScreenWidth();
		screenHeight = gc.getScreenHeight();
		
		options = new Image("res/options.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g)
			throws SlickException {

		g.setColor(Color.black);
        g.fillRect(0,0, screenWidth, screenHeight);
        
        
        options.draw(50, 10);
        g.setColor(Color.white);
        g.drawLine(0, 80, 400, 80);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int delta)
			throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			state.enterState(Game.GAMEMENUSTATE);
		}
	}

	@Override
	public int getID() {
		return this.id;
	}

}
