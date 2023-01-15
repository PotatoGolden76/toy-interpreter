package domain.structures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Queue implements IQueue<String>{

    final ArrayDeque<String> internal = new ArrayDeque<>();

    @Override
    public void push(String o) {
        this.internal.push(o);
    }

    @Override
    public String pop() {
        return this.internal.pop();
    }

    @Override
    public String toString() {
        return "Output: " + this.internal.stream().toList();
    }

    public List<String> getList() {

        return new ArrayList<>(this.internal);

    }
}
