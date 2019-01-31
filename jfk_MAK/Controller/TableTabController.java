package jfk_MAK.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
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

    private Entity chosenEntity;
    private int entityIndex;

    public Entity getChosenEntity(){
        return chosenEntity;
    }
    public int getEntityIndex(){
        return entityIndex;
    }

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
            editButton.setDisable(true);
        } else {
            deleteButton.setDisable(false);
            editButton.setDisable(false);
        }
    }

    private void editItem(ActionEvent actionEvent){
        chosenEntity=entityTable.getSelectionModel().getSelectedItem();
        entityIndex=entityTable.getSelectionModel().getSelectedIndex();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("jfk_MAK/View/editItemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Editing "+chosenEntity.getName()+" "+chosenEntity.getSurname());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
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
