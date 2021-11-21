package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

import engine.GameState;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Savable {

	static class Projects{
		private ArrayList<Project> projects;
		
		Projects(){
			projects = new ArrayList<Project>();
		}

		public ArrayList<Project> getProjects() {
			return projects;
		}

		public void setProjects(ArrayList<Project> projects) {
			this.projects = projects;
		}
	}
	
	private static Projects projects;
	
	private static File getGlobalFile() {
		String path = Paths.get("").toAbsolutePath().toString();
		File f =  new File(path + File.separator + "res" + File.separator);
		f.mkdirs();
		return f;
	}
	
	private static void dump(Object obj, File f) {
		Yaml yaml = new Yaml();
		yaml.setBeanAccess(BeanAccess.FIELD);
	    String output = yaml.dumpAsMap(obj);
	    try {
			Files.write(Paths.get(f.getAbsolutePath()), output.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Projects loadProjects(File f) {
		Yaml yaml = new Yaml();
		yaml.setBeanAccess(BeanAccess.FIELD);
		Projects pro = null;
		try {
			pro = yaml.loadAs(new FileInputStream(f),Projects.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	
	public static boolean intitDB() {
		File f = new File(getGlobalFile().getPath() + File.separator+ "projects.yml");
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projects = loadProjects(f);
		if (projects == null ) {
			projects = new Projects();
		}
		return true;
	}

	public static Project createProject(String name) {
		Project p = new Project(name);
		projects.getProjects().add(p);
		return p;
	}
	
	public static ArrayList<Project> getProjects() {
		return projects.getProjects();
	}
	
	public static boolean closeDB() {
		dump(projects,new File(getGlobalFile().getPath() + File.separator+ "projects.yml"));
		return true;
	}
}



