package domain.structures;

import domain.values.IValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IHeap {
    int allocate(IValue value);
    void update(int address, IValue value);
    IValue get(int address);
    boolean isDefined(int address);
    String toString();
    void setContent(Map<Integer, IValue> newContent);
    Map<Integer, IValue> getContent();

    List<IValue> getValues();
}
