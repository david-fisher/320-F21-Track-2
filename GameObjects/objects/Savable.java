package objects;

import java.util.*;
import java.awt.Desktop;  
import java.io.*;

public abstract class Savable {
	
	public ArrayList<Object> load(ArrayList<Object> data, String file) {
		return null ;
	}

	public boolean save(ArrayList<Object> data, String file) {
		//get file and make a ObjectMapper mapper for YAML
		FileInputStream readFile = getFile(file);
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.writeValue(new File(file), data);
		return true;
	}
	
	public boolean delete(ArrayList<Object> data, String file) {
		return false ;
	}

	private FileInputStream getFile(String path) {
		//returns file in readable form for easy use in Java
		return FileInputStream(new File(path));
	}
	
}
