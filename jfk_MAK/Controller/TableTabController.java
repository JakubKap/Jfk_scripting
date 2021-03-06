package jfk_MAK.Controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Modality;
import javafx.stage.Screen;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Entity;
import javafx.stage.Stage;

public class TableTabController implements Initializable{

    @FXML private TableView<Entity> entityTable;
    @FXML private TableColumn<Entity, String> nameColumn;
    @FXML private TableColumn<Entity, String> surnameColumn;
    @FXML private TableColumn<Entity, Integer> ageColumn;
    @FXML private TableColumn<Entity, Boolean> employedColumn;
    @FXML private TableColumn<Entity, Boolean> marriedColumn;
    @FXML private TableColumn<Entity, Integer> salaryColumn;
    @FXML private TableColumn<Entity, Integer> childrenColumn;

    @FXML private GridPane tableGridPane;

    @FXML private Button loadButton;
    @FXML private Button deleteButton;
    @FXML private Button editButton;

    private static int entityIndex;

    public static int getEntityIndex(){
        return entityIndex;
    }

    private void loadCsv(ActionEvent event){
        disableModifyBtns();
        FileChooser fileChooser = new FileChooser();
        try {
            File file = new File(new File(".").getCanonicalPath());
            fileChooser.setInitialDirectory(file);
        }catch(IOException e){
            e.printStackTrace();
        }
        fileChooser.setTitle("Select a file to open");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        try {
            CsvFile.getInstance().load(file.getPath());
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wybierz plik csv.");
            alert.setHeaderText("Wybierz plik csv z dowolnej lokalizacji.");

            alert.showAndWait();
        }
    }

    private void deleteItem(ActionEvent actionEvent){
        int index = entityTable.getSelectionModel().getSelectedIndex();
        CsvFile.getInstance().remove(index);
    }

    private void manageVisibility(){
        if(CsvFile.getInstance().entities.isEmpty()){
            deleteButton.setDisable(true);
            editButton.setDisable(true);
        }
    }


    private void editItem(ActionEvent actionEvent){
        entityIndex = entityTable.getSelectionModel().getSelectedIndex();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("jfk_MAK/View/editItemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableGridPane.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
}

    private void enableModifyBtns(){
        editButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    private void disableModifyBtns(){
        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        employedColumn.setCellValueFactory(new PropertyValueFactory<>("employed"));
        marriedColumn.setCellValueFactory(new PropertyValueFactory<>("married"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("monthSalary"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<>("numOfChildren"));

        loadButton.setOnAction(this::loadCsv);
        deleteButton.setOnAction(this::deleteItem);
        editButton.setOnAction(this::editItem);

        CsvFile csv = CsvFile.getInstance();
        entityTable.setItems(csv.entities);

        csv.addChangeListener(entities -> {
            entityTable.refresh();
            manageVisibility();
        });

        entityTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(entityTable.getSelectionModel().getSelectedIndex() + " | " + CsvFile.getInstance().entities.size());
                if(!CsvFile.getInstance().entities.isEmpty()
                        && entityTable.getSelectionModel().getSelectedIndex() >= 0)
                    enableModifyBtns();
            }
        });

    }
}
