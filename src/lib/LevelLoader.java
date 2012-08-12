package lib;

import stuff.ShipContainer;

public class LevelLoader {

	public static ShipContainer shipContainer;
	public static int currentLevel = 0;
	
	public static void initLevel(int level) {
		if(level > 0)
		{
			currentLevel = level;
			System.out.println("LEVEL:"+level);
			int[][] loadLevel = LevelHolder.levels[level-1];
			if(loadLevel != null)
				shipContainer = new ShipContainer(loadLevel);
		}
	}
	
	
	public static boolean changeToNextLevel() {
		currentLevel++;
		if(LevelHolder.levels[currentLevel-1] != null) {
			initLevel(currentLevel);
			return true;
		}
		
		return false;
	}
	
	
	public static boolean nextLevelExists() {
		
		System.out.println("current_level:"+currentLevel);
		if(LevelHolder.levels[currentLevel] != null)
		{
			System.out.println("next Level exists:"+currentLevel+1);
			return true;
		}
		
		return false;
	}
	
	
	

			
}
