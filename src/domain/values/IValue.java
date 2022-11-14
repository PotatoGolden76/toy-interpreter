package domain.values;

import domain.exceptions.ValueException;
import domain.types.IType;

public interface IValue {
    IType getType();
    BooleanValue equals(IValue other);
    BooleanValue greaterThan(IValue other) throws ValueException;
    BooleanValue lessThan(IValue other) throws ValueException;
    BooleanValue greaterThanOrEqual(IValue other) throws ValueException;
    BooleanValue lessThanOrEqual(IValue other) throws ValueException;
    BooleanValue notEqual(IValue other);

}
