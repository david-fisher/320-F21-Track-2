package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public abstract class Savable {
	private static String filePath = File.separator + "res" + File.separator;
	private static Project project;
	private static String globalPath = new File(".").getAbsolutePath();
	private static boolean global = false;

	static HashMap<String, Constructor> constructorMap = new HashMap<String, Constructor>();
	static Constructor projectConstruct;

	public static HashMap<String, ArrayList<Savable>> objectMap = new HashMap<String, ArrayList<Savable>>();

	public Savable() {
		String className = this.getClass().getSimpleName();

		if (objectMap.containsKey(className)) {
			objectMap.get(className).add(this);
		} else {
			ArrayList<Savable> tempArray = new ArrayList<>();
			tempArray.add(this);
			objectMap.put(className, tempArray);
			if (!className.equals("Project")) {
				constructorMap.put(className, new Constructor(this.getClass()));
			} else {
				projectConstruct = new Constructor(this.getClass());
			}
		}
	}

	private static File getFile(String className) {
		String pre = "";
		if (global) {
			pre = globalPath;
		} else {
			pre = project.getProjectPath();
		}
		File file = new File(pre + filePath + className + ".yml");
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	private static void loadConstructors() {
		constructorMap.clear();
		InputStream inputStream;
		try {
			File file = getFile("StartupData");
			inputStream = new FileInputStream(file);
			Yaml yaml = new Yaml();
			HashMap<String, Constructor> x = yaml.load(inputStream);
			if (x != null) {
				Savable.constructorMap = x;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveConstructors() {
		File file = getFile("StartupData");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			Yaml yaml = new Yaml();
			yaml.dump(constructorMap, writer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static boolean saveProjects() {
		try {
			global = true;
			File file = getFile("ProjectConstruct");
			Yaml yaml = new Yaml();
			PrintWriter writer;
			writer = new PrintWriter(new FileWriter(file, false));
			yaml.dump(projectConstruct, writer);

			file = getFile("Project");
			yaml = new Yaml();
			writer = new PrintWriter(new FileWriter(file, false));
			yaml.dumpAll(objectMap.get("Project").iterator(), writer);
			global = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private static boolean loadProjects() {

		try {
			global = true;
			File file = getFile("ProjectConstruct");
			Yaml yaml = new Yaml();
			projectConstruct = yaml.load(new FileInputStream(file));

			file = getFile("Project");
			yaml = new Yaml(projectConstruct);
			for (Object object : yaml.loadAll(new FileInputStream(file))) {
				/*
				 * Create a Saveable object which calls the constructor to add it to the object
				 * map
				 */
			}
			global = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static void setProject(Project x) {
		project = x;
	}

	public static boolean closeDB() {
		saveProjects();
		Savable.saveConstructors();
		Yaml yaml;

		try {
			for (String s : constructorMap.keySet()) {
				File file = getFile(s);
				yaml = new Yaml();
				PrintWriter writer = new PrintWriter(new FileWriter(file, false));
				yaml.dumpAll(objectMap.get(s).iterator(), writer);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Savable> get(Class x) {
		return Savable.objectMap.get(x.getSimpleName());
	}

	public static boolean initDB() {
		Savable.loadConstructors();

		Yaml yaml;
		InputStream reader;
		try {
			for (String s : constructorMap.keySet()) {
				File file = getFile(s);
				reader = new FileInputStream(file);
				yaml = new Yaml(constructorMap.get(s));
				for (Object object : yaml.loadAll(reader)) {
					/*
					 * Create a Saveable object which calls the constructor to add it to the object
					 * map
					 */
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static ArrayList<Project> getProjects() {
		loadProjects();
		ArrayList<Project> x = new ArrayList<Project>();
		for (Savable s : objectMap.get("Project")) {
			x.add(Project.class.cast(s));
		}
		return x;
	}
}

