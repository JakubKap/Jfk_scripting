package jfk_MAK.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Entity;

public class TableTabController{

    @FXML private Button loadButton;
    @FXML private TableView<Entity> entityTable;
    /*@FXML private TableColumn<Entity, String> nameColumn;
    @FXML private TableColumn<Entity, String> surnameColumn;
    @FXML private TableColumn<Entity, Integer> ageColumn;
    @FXML private TableColumn<Entity, Boolean> employedColumn;
    @FXML private TableColumn<Entity, Boolean> marriedColumn;
    @FXML private TableColumn<Entity, Integer> salaryColumn;
    @FXML private TableColumn<Entity, Integer> childrenColumn;*/

    public void loadButtonClicked(ActionEvent event){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String path = new File(dialog.getFile()).getAbsolutePath();
        System.out.println(path + " chosen.");

        entityTable.setItems(getInitialTableData(path));

    }

    public ObservableList<Entity> getInitialTableData(String path) {

        ObservableList<Entity> observableEntities = FXCollections.observableArrayList();
        List<Entity> entities = new CsvFile().readFromCsvFile(path);
        observableEntities.addAll(entities);

        return FXCollections.observableArrayList(entities);
    }


}
