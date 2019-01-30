package jfk_MAK.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import jfk_MAK.Model.Engine.JythonEngine;

public class JytonTabController {

    @FXML private TextArea inputArea;

    @FXML private TextArea outputArea;

    @FXML private TextArea errOutputArea;

    @FXML private Button runButton;

    JythonEngine jythonEngine;

    public JytonTabController() {

        runButton.setOnMouseClicked(mouseEvent -> {

        });
    }
}
