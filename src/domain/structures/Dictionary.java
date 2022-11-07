package domain.structures;

import domain.types.IValue;

import java.util.Enumeration;

public class Dictionary implements IDictionary<String, IValue> {

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
    public IValue put(String key, IValue value) {
        return this.internal.put(key, value);
    }

    @Override
    public IValue remove(String key) {
        return this.internal.remove(key);
    }
}
