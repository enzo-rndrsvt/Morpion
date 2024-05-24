package com.example.ryndersvitu_enzo_tpjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connection-view.fxml"));
        Parent root = loader.load();

        ConnectionController connectionController = loader.getController();
        connectionController.setStage(primaryStage);

        primaryStage.setTitle("Page de connection");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}