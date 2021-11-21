package objects;

import java.io.File;
import java.util.ArrayList;

import engine.GameState;

class Project{
	
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
	private GameState intiGS;
	private GameStates savedGS;
	
	public GameState getIntiGS() {
		return intiGS;
	}

	public void setIntiGS(GameState intiGS) {
		this.intiGS = intiGS;
	}

	public GameStates getSavedGS() {
		return savedGS;
	}

	public void setSavedGS(GameStates savedGS) {
		this.savedGS = savedGS;
	}

	Project() {
	}

	Project(String name) {
		this.projectName = name;
//		this.projectPath = directory + File.separator + name;
//		new File(this.projectPath).mkdirs();
		this.intiGS = new GameState();
		this.savedGS = new GameStates();
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
