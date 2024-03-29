package domain.structures;

import domain.values.IValue;

import java.util.Enumeration;
import java.util.Map;

public class SymbolDictionary implements IDictionary<String, IValue> {

    final java.util.Hashtable<String, IValue> internal = new java.util.Hashtable<>();

    @Override
    public int size() {
        return this.internal.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internal.isEmpty();
    }

    @Override
    public boolean isDefined(String key) {
        return this.internal.containsKey(key);
    }

    @Override
    public Enumeration<String> keys() {
        return this.internal.keys();
    }

    @Override
    public Enumeration<IValue> elements() {
        return this.internal.elements();
    }

    @Override
    public IValue get(String key) {
        return this.internal.get(key);
    }

    @Override
    public void put(String key, IValue value) {
        this.internal.put(key, value);
    }

    @Override
    public IValue remove(String key) {
        return this.internal.remove(key);
    }

    public SymbolDictionary clone() {
        SymbolDictionary clone = new SymbolDictionary();
        for (String key : this.internal.keySet()) {
            clone.put(key, this.internal.get(key));
        }
        return clone;
    }

    @Override
    public String toString() {
        String s = "Symbols:\n";
        Enumeration<String> e = this.internal.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            s = s.concat("[" + key + ", " + this.internal.get(key) +  "]\n");
        }
        return s;
    }

    public Map<String, IValue> content() {
        return this.internal;
    }
}
