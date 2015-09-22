package show_gears_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mai_n.Logic_main;
import show_gears_gui.controllers.MainController;
import show_gears_gui.login.ProfileController;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {




    Stage window;

    TextField username, password;
    Button connect;
    AnchorPane autpane;
    Scene sceneM, sceneA;

    Logic_main mainThread;
    private String usr;
    private String psw;

    @Override
    public void start(Stage stage){

        window = stage;
        username = new TextField();
        password = new TextField();
        username.setPromptText("Имя пользователя");
        password.setPromptText("Пароль");
        connect = new Button("Войти");

        connect.setOnAction(e -> {
        setUsr(username.getText());
        setPsw(password.getText());



            try {
                MainController profile = (MainController) replaceSceneContent("views/mainWin.fxml");

                //profile.setApp(this);
            } catch (Exception e1) {
                e1.printStackTrace();
            }


            //Parent root = FXMLLoader.load(getClass().getResource("views/mainWin.fxml"));

            //sceneM = new Scene(root);
            window.setScene(sceneA);


        });


            //Parent root = FXMLLoader.load(getClass().getResource("views/mainWin.fxml"));
            //Scene scene = new Scene(root);
            autpane = new AnchorPane();

        try {
            autpane.getChildren().setAll((Node[]) FXMLLoader.load(getClass().getResource("views/autWin.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        sceneA = new Scene(autpane);
            window.setTitle("sometxt");
            window.setScene(sceneA);
            window.show();


    }






    public static void main(String[] args) {
        launch(args);


    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    private Node replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        // Store the stage width and height in case the user has resized the window
        double stageWidth = window.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (window.getWidth() - window.getScene().getWidth());
        }

        double stageHeight = window.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (window.getHeight() - window.getScene().getHeight());
        }

        Scene scene = new Scene(page);
        if (!Double.isNaN(stageWidth)) {
            page.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            page.setPrefHeight(stageHeight);
        }

        window.setScene(scene);
        window.sizeToScene();
        return (Node) loader.getController();
    }
}
