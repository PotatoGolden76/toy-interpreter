package domain.structures;

import domain.values.IValue;

import java.util.HashMap;

public interface IHeap {
    int allocate(IValue value);
    void update(int address, IValue value);
    IValue get(int address);
    boolean isDefined(int address);
    String toString();
    void setContent(HashMap<Integer, IValue> newContent);
    HashMap<Integer, IValue> getContent();
}
