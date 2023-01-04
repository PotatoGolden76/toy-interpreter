package domain.structures;

import domain.types.IType;
import domain.values.IValue;

import java.util.Enumeration;

public class TypeTable implements IDictionary<String, IType> {
    final java.util.Hashtable<String, IType> internal = new java.util.Hashtable<>();

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
    public Enumeration<IType> elements() {
        return this.internal.elements();
    }

    @Override
    public IType get(String key) {
        return this.internal.get(key);
    }

    @Override
    public void put(String key, IType value) {
        this.internal.put(key, value);
    }

    @Override
    public IType remove(String key) {
        return this.internal.remove(key);
    }

    public TypeTable clone() {
        TypeTable clone = new TypeTable();
        for (String key : this.internal.keySet()) {
            clone.put(key, this.internal.get(key));
        }
        return clone;
    }
}
