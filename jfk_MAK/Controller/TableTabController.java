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
import javafx.scene.control.cell.PropertyValueFactory;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Entity;

public class TableTabController implements Initializable{

    @FXML private TableView<Entity> entityTable;
    @FXML private TableColumn<Entity, String> nameColumn;
    @FXML private TableColumn<Entity, String> surnameColumn;
    @FXML private TableColumn<Entity, Integer> ageColumn;
    @FXML private TableColumn<Entity, Boolean> employedColumn;
    @FXML private TableColumn<Entity, Boolean> marriedColumn;
    @FXML private TableColumn<Entity, Integer> salaryColumn;
    @FXML private TableColumn<Entity, Integer> childrenColumn;

    public void loadButtonClicked(ActionEvent event){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String path = dialog.getDirectory() + dialog.getFile();
        System.out.println(path+ " chosen.");

        entityTable.getItems().clear();
        entityTable.setItems(getInitialTableData(path));
    }

    private ObservableList<Entity> getInitialTableData(String path) {

        ObservableList<Entity> observableEntities = FXCollections.observableArrayList();
        List<Entity> entities = new CsvFile().readFromCsvFile(path);
        observableEntities.addAll(entities);

        return FXCollections.observableArrayList(entities);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Entity, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Entity, String>("surname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Entity, Integer>("age"));
        employedColumn.setCellValueFactory(new PropertyValueFactory<Entity, Boolean>("employed"));
        marriedColumn.setCellValueFactory(new PropertyValueFactory<Entity, Boolean>("married"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Entity, Integer>("monthSalary"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<Entity, Integer>("numOfChildren"));
    }


}
