package show_gears_gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mai_n.Logic_main;
import mai_n.MyException;
import show_gears_gui.Main;
import show_gears_gui.Main2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Rimskii on 17.09.2015.
 */
public class AutController extends AnchorPane{
    public TextField loginField;
    public PasswordField passField;
    public Button connBtn;
    public Label welcomLabel;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    private Logic_main mainThread;
    private Main2 application;


    public void connectAction(ActionEvent event){

            //mainThread.connect_to_data_base(loginField.getText(), passField.getText());
            application.logIn(loginField.getText(), passField.getText());

    }

    public void setApp(show_gears_gui.Main2 application, Logic_main mainThread) {
        this.application = application;
        this.mainThread = mainThread;
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    }
}
