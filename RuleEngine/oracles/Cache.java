package oracles;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import engine.*; 
import nodes.*;

// WIP
public class Cache {
    private int size;
    private Set<Integer> cache;

    public Cache(int size) {
        this.size = size;
        this.cache = new LinkedHashSet<Integer>(size);
    }

    public boolean get(int key) { 
        return false;
    }

    public void put(int key) {

    }

    public void check() {

    }

}
