package objects;

import java.io.File;
import java.io.IOException;

public class Project extends Savable {
	private String projectName;
	private String projectPath;

	Project() {
		this.projectName = "";
		this.projectPath = "";
	}

	Project(String name, String directory) {
		this.projectName = name;
		directory.replace("\\", File.separator);
		directory.replace("/", File.separator);
		this.projectPath = directory + File.separator + name;
		File file = new File(this.projectPath);
		file.mkdirs();
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

	public void setProjectPath(String path) {
		this.projectPath = path;
	}

}