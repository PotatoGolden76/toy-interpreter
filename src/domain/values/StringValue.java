package domain.values;

import domain.exceptions.ValueException;
import domain.types.IType;
import domain.types.StringType;

public class StringValue implements IValue {
    private final String n;

    public StringValue(String val) {
        this.n = val;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public BooleanValue equals(IValue other) {
        if (other instanceof StringValue) {
            return new BooleanValue(this.n.equals(((StringValue) other).getValue()));
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue greaterThan(IValue other) throws ValueException {
        throw new ValueException(" > is not defined for string values.");
    }

    @Override
    public BooleanValue lessThan(IValue other) throws ValueException {
        throw new ValueException(" < is not defined for string values.");
    }

    @Override
    public BooleanValue greaterThanOrEqual(IValue other) throws ValueException {
        throw new ValueException(" >= is not defined for string values.");
    }

    @Override
    public BooleanValue lessThanOrEqual(IValue other) throws ValueException {
        throw new ValueException(" <= is not defined for string values.");
    }

    @Override
    public BooleanValue notEqual(IValue other) {
        if (other instanceof StringValue) {
            return new BooleanValue(!this.n.equals(((StringValue) other).getValue()));
        }
        return new BooleanValue(false);
    }

    public String getValue() {
        return this.n;
    }

    @Override
    public String toString() {
        return this.n;
    }

}
