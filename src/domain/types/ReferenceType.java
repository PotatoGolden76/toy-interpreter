package domain.types;

import domain.values.IValue;
import domain.values.ReferenceValue;

public class ReferenceType implements IType {
    private IType inner;

    public ReferenceType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(IType another) {
        if (another instanceof ReferenceType) {
            return inner.equals(((ReferenceType) another).getInner());
        }
        return false;
    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(0, inner);
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

}
