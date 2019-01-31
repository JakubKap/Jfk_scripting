package jfk_MAK.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void submit(){

    }
}
