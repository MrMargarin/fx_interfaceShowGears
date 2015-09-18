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
import javafx.stage.Stage;
import mai_n.Logic_main;
import mai_n.MyException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Rimskii on 17.09.2015.
 */
public class AutController {
    public TextField loginField;
    public PasswordField passField;
    public Button connBtn;
    public Label welcomLabel;



    private Logic_main mainThread;





    public void connectAction(ActionEvent event){
        try {
            mainThread = new Logic_main(loginField.getText(), passField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        //((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainWin.fxml"));

    try {
        Parent parent = loader.load();
        //mainController = loader.<MainController>getController();
        //mainController = new MainController(mainThread);





        //Parent parent = FXMLLoader.load(getClass().getResource());
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("ShowGears");
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}
