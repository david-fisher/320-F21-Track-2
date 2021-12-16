package org.GameObjects.objects;

import java.util.ArrayList;

import org.RuleEngine.engine.GameState;

public class Project{
	
	static class GameStates{
		private ArrayList<GameState> gamestates;
		
		GameStates(){
			setGamestates(new ArrayList<GameState>());
		}

		public ArrayList<GameState> getGamestates() {
			return gamestates;
		}

		public void setGamestates(ArrayList<GameState> gamestates) {
			this.gamestates = gamestates;
		}

	}
	
	private String projectName;
//	private String projectPath;
	private GameState initGS;
	private GameState savedGS;
	
	public GameState getInitGS() {
		return initGS;
	}

	public void setInitGS(GameState initGS) {
		this.initGS = initGS;
	}

	public GameState getSavedGS() {
		return savedGS;
	}

	public void setSavedGS(GameState savedGS) {
		this.savedGS = savedGS;
	}

	public Project() {
	}

	public Project(String name) {
		this.projectName = name;
//		this.projectPath = directory + File.separator + name;
//		new File(this.projectPath).mkdirs();
		this.initGS = new GameState();
		this.savedGS = null;
	}
	
//	public File getResourceFile(boolean init) {
//		String path = this.projectPath + File.separator+ "res" + File.separator;
//		new File(this.projectPath).mkdirs();
//		if(!init) {
//			path += "savedGames" + File.separator;
//			new File(this.projectPath).mkdirs();
//		}
//		return new File(path);
//	}

	public String getProjectName() {
		return this.projectName;
	}

//	public String getProjectPath() {
//		return this.projectPath;
//	}

	public void setProjectName(String name) {
		this.projectName = name;
	}

//	public void setProjectPath(String f) {
//		this.projectPath = f;
//	}

}
