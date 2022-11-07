package domain.types;

public interface IType {
    public boolean equals(IType t);
    public IValue defaultValue();
}
