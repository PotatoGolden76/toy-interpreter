package domain.structures;

import domain.values.IValue;

import java.util.HashMap;

public class Heap implements IHeap{
    private HashMap<Integer, IValue> content;

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
    public void setContent(HashMap<Integer, IValue> newContent) {
        content = newContent;
    }

    @Override
    public HashMap<Integer, IValue> getContent() {
        return content;
    }
}
