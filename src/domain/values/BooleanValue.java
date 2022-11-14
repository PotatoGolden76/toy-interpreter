package domain.values;

import domain.exceptions.ValueException;
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

    @Override
    public BooleanValue greaterThan(IValue other) throws ValueException {
        throw new ValueException(" > is not defined for boolean values.");
    }

    @Override
    public BooleanValue lessThan(IValue other) throws ValueException {
        throw new ValueException(" < is not defined for boolean values.");
    }

    @Override
    public BooleanValue greaterThanOrEqual(IValue other) throws ValueException {
        throw new ValueException(" >= is not defined for boolean values.");
    }

    @Override
    public BooleanValue lessThanOrEqual(IValue other) throws ValueException {
        throw new ValueException(" <= is not defined for boolean values.");
    }


    @Override
    public BooleanValue equals(IValue other) {
        if (other instanceof BooleanValue) {
            return new BooleanValue(this.v == ((BooleanValue)other).getValue());
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue notEqual(IValue other) {
        if (other instanceof BooleanValue) {
            return new BooleanValue(this.v != ((BooleanValue)other).getValue());
        }
        return new BooleanValue(false);
    }

    public boolean getValue() {
        return v;
    }

    @Override
    public String toString() {
        return this.v ? "True" : "False";
    }

}
