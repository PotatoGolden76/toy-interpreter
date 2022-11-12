package domain.types;

import domain.values.IValue;

public interface IType {
    public boolean equals(IType t);
    public IValue defaultValue();
}
