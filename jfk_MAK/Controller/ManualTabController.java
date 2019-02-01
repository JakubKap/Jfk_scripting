package jfk_MAK.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jfk_MAK.Model.CsvFile;
import jfk_MAK.Model.Entity;

import java.net.URL;
import java.util.ResourceBundle;

public class ManualTabController implements Initializable {

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

    private Entity entity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    private void passEntity(){
        entity = new Entity(
                nameTextfield.getText(),
                surnameTextfield.getText(),
                (int)ageSlider.getValue(),
                isEmployedCheckbox.isSelected(),
                isMarriedCheckbox.isSelected(),
                Integer.parseInt(monthSalaryTextfield.getText()),
                Integer.parseInt(numberOfChildrenTextfield.getText())
        );

        resultLabel.setStyle("-fx-text-fill: #66ff66;;");
        resultLabel.setText("Submission successful!");
        clearFields();
        CsvFile.getInstance().entities.add(entity);

    }

    private void clearFields(){
        nameTextfield.setText("");
        surnameTextfield.setText("");
        ageSlider.setValue(18);
        isEmployedCheckbox.setSelected(false);
        isMarriedCheckbox.setSelected(false);
        monthSalaryTextfield.setText("");
        numberOfChildrenTextfield.setText("");
    }

    private boolean getFieldsStatus(){
        boolean status = true;
        resultLabel.setStyle("-fx-text-fill: #ff0000; ");
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
