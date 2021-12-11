package org.RuleEngine.oracles;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private class LRUHashMap<K,V> extends LinkedHashMap<K,V> {
        private int maxSize;
        public LRUHashMap(int size) {
            super(16, 0.75f, true);
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
            return this.size() > this.maxSize;
        }
    }

    private int size;

    private LRUHashMap<Integer, Integer> cache;

    public Cache(int size) {
        this.size = size;
        this.cache = new LRUHashMap<Integer, Integer>(size);
    }

    public Integer get(int key) {
        if (!this.cache.containsKey(key)){
            return null;
        }
        int value = this.cache.get(key);
        this.cache.remove(key);
        this.cache.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        this.cache.put(key, value);
    }

    public boolean contains(int key) {
        return this.cache.containsKey(key);
    }

    public boolean hasValue(int value) {
        return this.cache.containsValue(value);
    }
}
