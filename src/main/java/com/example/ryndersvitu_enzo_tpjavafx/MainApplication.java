package com.example.ryndersvitu_enzo_tpjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Chargement du fichier FXML pour la vue de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connection-view.fxml"));
        Parent root = loader.load();

        // Récupération du contrôleur associé et initialisation du stage principal
        ConnectionController connectionController = loader.getController();
        connectionController.setStage(primaryStage);

        // Configuration de la fenêtre principale
        primaryStage.setTitle("Page de connexion");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Ajout de l'icône à la fenêtre principale
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));

        // Affichage de la fenêtre principale
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lancement de l'application JavaFX
        launch(args);
    }
}
