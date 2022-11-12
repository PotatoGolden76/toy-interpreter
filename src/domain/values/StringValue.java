package domain.values;

import domain.types.IType;
import domain.types.IntType;
import domain.types.StringType;

import java.util.Objects;

public class StringValue implements IValue {
    private final String n;

    public StringValue(String val) {
        this.n = val;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    public String getValue() {
        return this.n;
    }

    @Override
    public String toString() {
        return this.n;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StringValue) {
            return Objects.equals(this.n, ((StringValue) object).getValue());
        }
        return false;
    }
}
