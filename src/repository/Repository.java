package repository;

import domain.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    List<ProgramState> states = new ArrayList<>();
    final String logFilePath;
    PrintWriter logFile;

    public Repository(ProgramState state, String log) throws IOException {
        this.states.add(state);
        this.logFilePath = log;
        this.logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
    }

    public Repository(ArrayList<ProgramState> states, String log) throws IOException {
        this.states = states;
        this.logFilePath = log;

        this.logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
    }

    public Repository clone() {
        try {
            return new Repository(new ArrayList<>(this.states), this.getLog());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProgramState> getState() {
        return states;
    }

    public void setState(List<ProgramState> state) {
        this.states = state;
    }

    public void logProgramState(ProgramState state) throws IOException {
        this.logFile.println(state);
        this.logFile.println("\n<--- STEP --->\n");
        this.logFile.flush();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ProgramState state : states) {
            sb.append(state.toString());
        }
        return sb.toString();
    }

    public String getLog() {
        return this.logFilePath;
    }
}

