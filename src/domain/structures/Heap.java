package domain.structures;

import domain.values.IValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heap implements IHeap{
    private Map<Integer, IValue> content;

    public int getNextFree() {
        return nextFree;
    }

    private int nextFree;

    public Heap() {
        content = new HashMap<>();
        nextFree = 1;
    }

    @Override
    public int allocate(IValue value) {
        content.put(nextFree, value);
        return nextFree++;
    }

    @Override
    public void update(int address, IValue value) {
        content.put(address, value);
    }

    @Override
    public IValue get(int address) {
        return content.get(address);
    }

    @Override
    public boolean isDefined(int address) {
        return content.containsKey(address);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Heap: \n");
        for (Integer key : content.keySet()) {
            result.append(key).append("->").append(content.get(key)).append("\n");
        }
        return result.toString();
    }

    @Override
    public void setContent(Map<Integer, IValue> newContent) {
        content = newContent;
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return content;
    }

    @Override
    public List<IValue> getValues() {
        return Arrays.asList(content.values().toArray(new IValue[0]));
    }
}
