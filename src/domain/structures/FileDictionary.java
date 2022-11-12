package domain.structures;

import domain.values.StringValue;

import java.io.BufferedReader;
import java.util.Enumeration;

public class FileDictionary implements IDictionary<StringValue, BufferedReader> {

    final java.util.Hashtable<StringValue, BufferedReader> internal = new java.util.Hashtable<>();

    @Override
    public int size() {
        return this.internal.size();
    }

    @Override
    public boolean isEmpty() {
        return this.internal.isEmpty();
    }

    @Override
    public boolean isDefined(StringValue key) {
        return this.internal.containsKey(key);
    }

    @Override
    public Enumeration<StringValue> keys() {
        return this.internal.keys();
    }

    @Override
    public Enumeration<BufferedReader> elements() {
        return this.internal.elements();
    }

    @Override
    public BufferedReader get(StringValue key) {
        return this.internal.get(key);
    }

    @Override
    public BufferedReader put(StringValue key, BufferedReader value) {
        return this.internal.put(key, value);
    }

    @Override
    public BufferedReader remove(StringValue key) {
        return this.internal.remove(key);
    }

    @Override
    public String toString() {
        String s = "Files:\n";
        Enumeration<StringValue> e = this.internal.keys();
        while (e.hasMoreElements()) {
            StringValue key = e.nextElement();
            s = s.concat("[" + key + ", " + this.internal.get(key).toString() +  "]\n");
        }
        return s;
    }
}
