package objects;

import java.io.File;
import java.nio.file.Path;

public class Project extends Savable {
	private String projectName;
	private String projectPath;
	private Path projectPathObj;

	Project() {
		this.projectName = "";
		this.projectPath = "";
	}

	Project(String name, String directory) {
		this.projectName = name;
		directory.replace("\\", File.separator);
		directory.replace("/", File.separator);
		this.projectPath = directory + File.separator + name;
//		this.projectPathObj = Paths.get(this.projectPath);
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