import controller.Controller;
import domain.ProgramState;
import domain.exceptions.InterpreterException;
import domain.exceptions.TypeException;
import domain.statements.IStatement;
import domain.structures.Stack;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import repository.Repository;
import view.Programs;

import java.io.IOException;

public class ListController {
    private ProgramController programController;

    public void setProgramController(ProgramController programController) {
        this.programController = programController;
    }

    @FXML
    private ListView<IStatement> statements;

    @FXML
    private Button displayButton;

    @FXML
    public void initialize() {
        statements.setItems(FXCollections.observableArrayList(Programs.statements));
        displayButton.setOnAction(actionEvent -> {
            int index = statements.getSelectionModel().getSelectedIndex();
            if (index < 0)
                return;

            Controller controller;
            try {
               controller = new Controller(new Stack(Programs.statements[index]), true, "log.txt");
            } catch (IOException | TypeException e) {
                throw new RuntimeException(e);
            }

            programController.setController(controller);
        });
    }
}