package domain.types;

import domain.values.IValue;

public interface IType {
    boolean equals(IType t);
    IValue defaultValue();
}
