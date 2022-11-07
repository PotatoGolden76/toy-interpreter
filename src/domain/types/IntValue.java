package domain.types;

public class IntValue implements IValue{
    private final int n;

    public IntValue(int val) {
        this.n = val;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    public int getValue() {
        return this.n;
    }

    @Override
    public String toString() {
        return Integer.toString(this.n);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof IntValue) {
            return this.n == ((IntValue)object).getValue();
        }
        return false;
    }
}
