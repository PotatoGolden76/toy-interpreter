package repository;

import domain.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository {

    ProgramState state;
    String logFilePath;
    PrintWriter logFile;

    public Repository(ProgramState state, String log) throws IOException {
        this.state = state;
        this.logFilePath = log;
        this.logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
    }

    public ProgramState getState() {
        return state;
    }

    public void setState(ProgramState state) {
        this.state = state;
    }

    public void logProgramState() {
        this.logFile.println(this);
        this.logFile.println("\n<--- STEP --->\n");
        this.logFile.flush();
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}

