package jfk_MAK.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Engine.JythonEngine;
import jfk_MAK.Model.OutputWriter;

import java.net.URL;
import java.util.ResourceBundle;

public class JytonTabController implements Initializable {

    @FXML private TextArea inputArea;
    @FXML private TextArea outputArea;
    @FXML private TextArea errOutputArea;
    @FXML private Button runButton;
    @FXML private Button clearButton;

    JythonEngine jythonEngine;

    OutputWriter outWriter, errWriter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final CsvFile csv = CsvFile.getInstance();
        outWriter = new OutputWriter(outputArea);
        errWriter = new OutputWriter(errOutputArea);

        jythonEngine = new JythonEngine(csv.entities);
        jythonEngine.setStdout(outWriter);
        jythonEngine.setStderr(errWriter);

        runButton.setOnMouseClicked(mouseEvent -> {

            String result = jythonEngine.run(inputArea.getText());
            if(result != null) {
                errOutputArea.setText(result);
            }

            csv.informChange();
        });

        clearButton.setOnMouseClicked(mouseEvent -> {
            outWriter.clear();
            errWriter.clear();
        });
    }
}
