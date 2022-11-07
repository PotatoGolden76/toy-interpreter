package repository;

import domain.ProgramState;

public class Repository {

    ProgramState state;

    public Repository(ProgramState state) {
        this.state = state;
    }

    public ProgramState getState() {
        return state;
    }

    public void setState(ProgramState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}

