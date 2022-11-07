package domain.types;

public class IntType implements IType{
    @Override
    public boolean equals(IType type) {
        return type instanceof IntType;
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }
}
