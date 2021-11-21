package objects;

import java.io.File;

class Project{
	private String projectName;
	private String projectPath;

	Project() {
		this.projectName = "";
		this.projectPath = "";
	}

	Project(String name, String directory) {
		this.projectName = name;
		this.projectPath = directory + File.separator + name;
		new File(this.projectPath).mkdirs();
	}
	
	public File getResourceFile(boolean init) {
		String path = this.projectPath + File.separator+ "res" + File.separator;
		new File(this.projectPath).mkdirs();
		if(!init) {
			path += "savedGames" + File.separator;
			new File(this.projectPath).mkdirs();
		}
		return new File(path);
	}

	public String getProjectName() {
		return this.projectName;
	}

	public String getProjectPath() {
		return this.projectPath;
	}

	public void setProjectName(String name) {
		this.projectName = name;
	}

	public void setProjectPath(String f) {
		this.projectPath = f;
	}

}
