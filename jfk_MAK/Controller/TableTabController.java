package jfk_MAK.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Entity;

import javafx.scene.Node;

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

    private void loadCsv(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to open");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        CsvFile.getInstance().load(file.getPath());

        entityTable.getItems().clear();
        entityTable.setItems(CsvFile.getInstance().entities);
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
            deleteButton.setVisible(false);
        } else {
            deleteButton.setDisable(false);
            deleteButton.setVisible(true);
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
    }
}
