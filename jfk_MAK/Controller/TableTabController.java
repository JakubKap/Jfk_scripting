package jfk_MAK.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML private Button loadButton;
    @FXML private Button deleteButton;
    @FXML private Button editButton;

    private void loadCsv(ActionEvent event){
        FileDialog dialog = new FileDialog((Frame)null, "Select a File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String path = dialog.getDirectory() + dialog.getFile();

        dialog.dispose();
        CsvFile.getInstance().entities.clear();
        entityTable.getItems().clear();

        CsvFile.getInstance().load(path);
        entityTable.setItems(CsvFile.getInstance().entities);
        entityTable.refresh();

        manageVisibility();
    }

    private void deleteItem(ActionEvent actionEvent){
        int index = entityTable.getSelectionModel().getSelectedIndex();
        CsvFile.getInstance().entities.remove(index);
        manageVisibility();
    }

    private void manageVisibility(){
        if(CsvFile.getInstance().entities.isEmpty()){
            deleteButton.setDisable(true);
        } else {
            deleteButton.setDisable(false);
        }
    }

    private void editItem(ActionEvent actionEvent){

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


        loadButton.setOnAction(this::loadCsv);
        deleteButton.setOnAction(this::deleteItem);
        editButton.setOnAction(this::editItem);

        entityTable.setItems(CsvFile.getInstance().entities);
    }
}
