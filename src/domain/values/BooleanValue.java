package domain.values;

import domain.types.BooleanType;
import domain.types.IType;

public class BooleanValue implements IValue {
    private final boolean v;

    public BooleanValue(boolean v) {
        this.v = v;
    }

    @Override
    public IType getType() {
        return new BooleanType();
    }

    public boolean getValue() {
        return this.v;
    }

    @Override
    public String toString() {
        return this.v ? "True" : "False";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof BooleanValue) {
            return this.v == ((BooleanValue)object).getValue();
        }
        return false;
    }
}