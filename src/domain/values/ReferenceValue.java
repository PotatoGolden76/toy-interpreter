package domain.values;

import domain.exceptions.ValueException;
import domain.types.IType;
import domain.types.ReferenceType;

public class ReferenceValue implements IValue{
    private int address;
    private IType type;

    public ReferenceValue(int address, IType type) {
        this.address = address;
        this.type = type;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "(" + address + ", " + type.toString() + ")";
    }

    @Override
    public IType getType() {
        return new ReferenceType(type);
    }

    @Override
    public BooleanValue equals(IValue another) {
        if (another instanceof ReferenceValue) {
            return new BooleanValue(address == ((ReferenceValue) another).getAddress());
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue greaterThan(IValue other) throws ValueException {
        return null;
    }

    @Override
    public BooleanValue lessThan(IValue other) throws ValueException {
        return null;
    }

    @Override
    public BooleanValue greaterThanOrEqual(IValue other) throws ValueException {
        return null;
    }

    @Override
    public BooleanValue lessThanOrEqual(IValue other) throws ValueException {
        return null;
    }

    @Override
    public BooleanValue notEqual(IValue other) {
        return null;
    }

}
