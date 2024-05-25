package com.example.ryndersvitu_enzo_tpjavafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectionController {

    private static Stage stage;

    @FXML
    private Label beforeStartLabel;

    @FXML
    private TextField playerOneNameField;

    @FXML
    private TextField playerTwoNameField;

    private String playerOneName;
    private String playerTwoName;

    @FXML
    void aboutButton(ActionEvent event) {
        // Fonctionnalité du bouton "À propos" (à implémenter)
        return;
    }

    @FXML
    void leaveButton(ActionEvent event) {
        // Quitter l'application depuis la barre de menu
        Platform.exit();
    }

    @FXML
    void rulesButton(ActionEvent event) throws IOException {
        // Afficher la fenêtre des règles
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rules-view.fxml"));
        Parent root = loader.load();

        Stage rulesStage = new Stage();
        rulesStage.setScene(new Scene(root));
        rulesStage.setResizable(false);
        rulesStage.setTitle("Règles du jeu");
        rulesStage.initStyle(StageStyle.UNDECORATED);
        rulesStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
        rulesStage.show();
    }

    @FXML
    void startActionButton2player(ActionEvent event) throws IOException {
        // Démarrer le jeu pour 2 joueurs

        // On récupère les noms des joueurs
        playerOneName = playerOneNameField.getText();
        playerTwoName = playerTwoNameField.getText();

        // Vérifier si les noms des joueurs sont vides
        if (playerOneName.isEmpty() || playerTwoName.isEmpty()) {

            // Si c'est vide on met le message en rouge pour alerter
            beforeStartLabel.setTextFill(Color.RED);

            // On restaure la couleur après 1 seconde
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> beforeStartLabel.setTextFill(Paint.valueOf("#0077B6")));
                }
            }, 1000);
        } else {
            // Si tout est bon alors on charge la vue pour le jeu à 2 joueurs
            FXMLLoader loader = new FXMLLoader(getClass().getResource("whoplay-view2player.fxml"));
            Parent root = loader.load();

            // On envoie les noms des joueurs à la vue suivante
            WhoPlayFirstController2player whoPlayFirstController2player = loader.getController();
            whoPlayFirstController2player.setPlayerOneName(playerOneName);
            whoPlayFirstController2player.setPlayerTwoName(playerTwoName);

            Stage whoPlayFirstStage = new Stage();
            whoPlayFirstStage.setScene(new Scene(root));
            whoPlayFirstStage.setResizable(false);
            whoPlayFirstStage.initStyle(StageStyle.UNDECORATED);
            whoPlayFirstStage.setTitle("Tic - Tac - Toe");
            whoPlayFirstStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
            whoPlayFirstStage.show();
        }
    }

    @FXML
    void startActionButton1player(ActionEvent event) throws IOException {
        // Démarrer le jeu pour 1 joueur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view1player.fxml"));
        Parent root = loader.load();

        GameController1player gameController1player = loader.getController();
        gameController1player.setCurrentPlayer('X');
        gameController1player.setCurrentPlayerName("Vous");

        // On charge la vue du jeu et on l'ouvre
        Stage gameStage = new Stage();
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.setTitle("Tic - Tac - Toe");
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
        gameController1player.setStage(gameStage);
        gameStage.show();

        // On ferme la page de connection
        stage.close();
    }

    public void setStage(Stage stage) {
        ConnectionController.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}
