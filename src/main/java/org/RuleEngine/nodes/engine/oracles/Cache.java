package org.RuleEngine.nodes.engine.oracles;

import java.util.LinkedHashSet;
import java.util.Set;

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
