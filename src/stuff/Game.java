package stuff;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.GameMenuState;
import states.GamePlayState;


public class Game extends StateBasedGame {

	public static final int GAMEMENUSTATE 	= 0;
	public static final int GAMEPLAYSTATE 	= 1;
//	public static final int GAMEOPTIONSTATE = 2;
//	public static final int GAMEOVERSTATE 	= 3;
	
	public Game() {
		super("Invaders From Out Of Space");
		
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.addState(new GameMenuState(GAMEMENUSTATE));
//		this.addState(new GameOptionState(GAMEOPTIONSTATE));
//		this.addState(new GameOverState(GAMEOVERSTATE));
		this.enterState(GAMEMENUSTATE);

	}

	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Game(), 800, 600, false);
		container.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(GAMEMENUSTATE).init(container, this);
		this.getState(GAMEPLAYSTATE).init(container, this);
	}

}