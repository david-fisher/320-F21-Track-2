package objects;

import java.util.ArrayList;

public abstract class Savable {
	
	public ArrayList<Object> load(ArrayList<Object> data, String file) {
		return null ;
	}

	public boolean save(ArrayList<Object> data, String file) {
		return false ;
	}
	
	public boolean delete() {
		return false ;
	}
	
}
