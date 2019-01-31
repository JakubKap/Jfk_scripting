package jfk_MAK.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Engine.Nashorn;
import jfk_MAK.Model.OutputWriter;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

public class NashornTabController implements Initializable {

    @FXML private TextArea inputArea;
    @FXML private TextArea outputArea;
    @FXML private TextArea errOutputArea;
    @FXML private Button runButton;
    @FXML private Button clearButton;

    private Nashorn nashornEngine;

    OutputWriter outWriter, errWriter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final CsvFile csv = CsvFile.getInstance();
        outWriter = new OutputWriter(outputArea);
        errWriter = new OutputWriter(errOutputArea);

        nashornEngine = new Nashorn(csv.entities);
        nashornEngine.setStdout(outWriter);
        nashornEngine.setStderr(errWriter);

        runButton.setOnMouseClicked(mouseEvent -> {

            String result = nashornEngine.run(inputArea.getText());
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
