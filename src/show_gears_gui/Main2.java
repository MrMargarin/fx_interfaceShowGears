/*
 * Copyright (c) 2012, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package show_gears_gui;

import java.awt.*;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mai_n.Logic_main;
import mai_n.MyException;
import show_gears_gui.controllers.AutController;
import show_gears_gui.controllers.MainController;
import show_gears_gui.login.LoginController;
import show_gears_gui.login.model.User;
import show_gears_gui.login.security.Authenticator;


/**
 * Main Application. This class handles navigation and user session.
 */
public class Main2 extends Application {

    private Stage stage;
    private User loggedUser;

    private Logic_main mainThread;

    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main2.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
       mainThread = new Logic_main();
        try {
            stage = primaryStage;
            //stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            //stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.setTitle("Login Sample");
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean userLogging(String userId, String password){
        System.out.println("got user id " + userId + " password " + password);
        if (Authenticator.validate(userId, password)) {
            System.out.println("OK");
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            System.out.println("NOT OK");
            return false;
        }
    }

    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    public void logIn(String u, String p){
        try {
            mainThread = new Logic_main(u, p);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        gotoProfile();
    }

    public void logOut(){
        mainThread = null;
        gotoLogin();
    }

    private void gotoProfile() {
        try {

            MainController mnControll = (MainController) replaceSceneContent("views/mainWin.fxml");
            mnControll.setApp(this, mainThread);
        } catch (Exception ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            AutController login = (AutController) replaceSceneContent("views/autWin.fxml");
            login.setApp(this, mainThread);
        } catch (Exception ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Node replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main2.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main2.class.getResource(fxml));

        Pane page;
        try {
        if(fxml == "views/mainWin.fxml"){
            //BorderPane page;
            page = (BorderPane) loader.load(in);
        }
        else
            {
                //AnchorPane page;
                page = (AnchorPane) loader.load(in);
            }

        } finally {
            in.close();
        }

        // Store the stage width and height in case the user has resized the window
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
        }

        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
        }

        Scene scene = new Scene(page);
        if (!Double.isNaN(stageWidth)) {
            page.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            page.setPrefHeight(stageHeight);
        }

        stage.setScene(scene);
        stage.sizeToScene();
        return (Node) loader.getController();
    }

    public Logic_main getMainThread() {
        return mainThread;
    }

    public void setMainThread(Logic_main mainThread) {
        this.mainThread = mainThread;
    }

    public String getUsr() {
        return null;
    }
}
