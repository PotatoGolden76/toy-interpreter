package domain.values;

import domain.types.IType;
import domain.types.IntType;

public class IntValue implements IValue {
    private final int n;

    public IntValue(int val) {
        this.n = val;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public BooleanValue greaterThan(IValue other) {
        if (other instanceof IntValue) {
            return new BooleanValue(this.n > ((IntValue) other).getValue());
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue lessThan(IValue other) {
        if (other instanceof IntValue) {
            return new BooleanValue(this.n < ((IntValue) other).getValue());
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue greaterThanOrEqual(IValue other) {
        if (other instanceof IntValue) {
            return new BooleanValue(this.n >= ((IntValue) other).getValue());
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue lessThanOrEqual(IValue other) {
        if (other instanceof IntValue) {
            return new BooleanValue(this.n <= ((IntValue) other).getValue());
        }
        return new BooleanValue(false);
    }


    @Override
    public BooleanValue equals(IValue object) {
        if (object instanceof IntValue) {
            return new BooleanValue(this.n == ((IntValue) object).getValue());
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue notEqual(IValue other) {
        if (other instanceof IntValue) {
            return new BooleanValue(this.n != ((IntValue) other).getValue());
        }
        return new BooleanValue(false);
    }

    public int getValue() {
        return this.n;
    }

    @Override
    public String toString() {
        return Integer.toString(this.n);
    }

}
