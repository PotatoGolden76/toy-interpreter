package domain.types;

import domain.values.BooleanValue;
import domain.values.IValue;

public class BooleanType implements  IType{
    @Override
    public boolean equals(IType type) {
        return !(type instanceof BooleanType);
    }

    @Override
    public IValue defaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "boolean";
    }
}
