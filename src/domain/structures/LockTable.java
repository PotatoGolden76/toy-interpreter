package domain.structures;

import domain.values.IValue;

import java.util.Enumeration;
import java.util.Map;

public class LockTable implements IDictionary<Integer, Integer> {

    final java.util.Hashtable<Integer, Integer> internal = new java.util.Hashtable<>();

    @Override
    public int size() {
        return this.internal.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internal.isEmpty();
    }

    @Override
    public boolean isDefined(Integer key) {
        return this.internal.containsKey(key);
    }

    @Override
    public Enumeration<Integer> keys() {
        return this.internal.keys();
    }

    @Override
    public Enumeration<Integer> elements() {
        return this.internal.elements();
    }

    @Override
    public Integer get(Integer key) {
        return this.internal.get(key);
    }

    @Override
    public void put(Integer key, Integer value) {
        this.internal.put(key, value);
    }

    @Override
    public Integer remove(Integer key) {
        return this.internal.remove(key);
    }

    public LockTable clone() {
        LockTable clone = new LockTable();
        for (Integer key : this.internal.keySet()) {
            clone.put(key, this.internal.get(key));
        }
        return clone;
    }

    @Override
    public String toString() {
        String s = "Symbols:\n";
        Enumeration<Integer> e = this.internal.keys();
        while (e.hasMoreElements()) {
            Integer key = e.nextElement();
            s = s.concat("[" + key + ", " + this.internal.get(key) +  "]\n");
        }
        return s;
    }

    public Map<Integer, Integer> content() {
        return this.internal;
    }
}
