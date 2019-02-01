package jfk_MAK.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Entity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditItemController implements Initializable {
    @FXML TextField nameTextfield;
    @FXML TextField surnameTextfield;
    @FXML Slider ageSlider;
    @FXML CheckBox isEmployedCheckbox;
    @FXML CheckBox isMarriedCheckbox;
    @FXML TextField monthSalaryTextfield;
    @FXML TextField numberOfChildrenTextfield;
    @FXML Button submitButton;
    @FXML Label resultLabel;
    @FXML Label ageLabel;

    private Entity chosenEntity;
    private int entityIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("jfk_MAK/View/tableTabView.fxml"));
        TableTabController tableTab = loader.getController();
        entityIndex = TableTabController.getEntityIndex();
        System.out.println(entityIndex);

        chosenEntity = CsvFile.getInstance().entities.get(entityIndex);
        System.out.println(chosenEntity.toString());

        fillFields();
        submitButton.setOnAction(this::submit);
        ageSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ageLabel.textProperty().setValue("Age: "+(int)ageSlider.getValue());
            }
        });
    }

    private void submit(ActionEvent actionEvent){
        if(getFieldsStatus()){
            passEntity();
        }
    }

    private void fillFields(){
        nameTextfield.setText(chosenEntity.getName());
        surnameTextfield.setText(chosenEntity.getSurname());
        ageLabel.setText("Age: " + Integer.toString(chosenEntity.getAge()));
        ageSlider.setValue(chosenEntity.getAge());
        isEmployedCheckbox.setSelected(chosenEntity.isEmployed());
        isMarriedCheckbox.setSelected(chosenEntity.isMarried());
        monthSalaryTextfield.setText(""+chosenEntity.getMonthSalary());
        numberOfChildrenTextfield.setText(""+chosenEntity.getNumOfChildren());
    }

    private void passEntity(){
        CsvFile.getInstance().entities.set(entityIndex,new Entity(
                nameTextfield.getText(),
                surnameTextfield.getText(),
                (int)ageSlider.getValue(),
                isEmployedCheckbox.isSelected(),
                isMarriedCheckbox.isSelected(),
                Integer.parseInt(monthSalaryTextfield.getText()),
                Integer.parseInt(numberOfChildrenTextfield.getText())));
        resultLabel.setStyle("-fx-text-fill: 66ff66;");
        resultLabel.setText("Submission successful!");
    }

    private boolean getFieldsStatus(){
        boolean status = true;
        resultLabel.setStyle("-fx-text-fill: ff0000; ");
        if(!nameTextfield.getText().matches("[A-z]+")){
            resultLabel.setText("Name must be a single word!");
            status=false;
        } else if(!surnameTextfield.getText().matches("[A-z]+")){
            resultLabel.setText("Surname must be a single word!");
            status=false;
        } else if(!monthSalaryTextfield.getText().matches("^[0-9]+((.|,)[0-9]*)?$")){
            resultLabel.setText("Monthly salary must be a decimal number!");
            status=false;
        } else if(!numberOfChildrenTextfield.getText().matches("[0-9]+")){
            resultLabel.setText("Number of children must be an integer number!");
            status=false;
        }
        return status;
    }
}
