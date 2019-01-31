package jfk_MAK.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import jfk_MAK.Model.Engine.JythonEngine;

import java.net.URL;
import java.util.ResourceBundle;

public class JytonTabController implements Initializable {

    @FXML private TextArea inputArea;

    @FXML private TextArea outputArea;

    @FXML private TextArea errOutputArea;

    @FXML private Button runButton;

    JythonEngine jythonEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        runButton.setOnMouseClicked(mouseEvent -> {

        });
    }
}
