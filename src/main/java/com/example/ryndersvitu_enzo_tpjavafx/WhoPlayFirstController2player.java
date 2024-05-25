package com.example.ryndersvitu_enzo_tpjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class WhoPlayFirstController2player {

    @FXML
    private Button startButtonPlayerOne;

    @FXML
    private Button startButtonPlayerTwo;

    @FXML
    private Button startButtonRandom;

    private Stage stage;

    private String playerOneName;
    private String playerTwoName;

    @FXML
    void startActionButton(ActionEvent event) throws IOException {

        // On charge la page de jeu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view2player.fxml"));
        Parent root = loader.load();

        GameController2player gameController2player = loader.getController();

        // Si on choisi de commencer par le joueur 1 alors on envoye au controlleur du jeu les informations du joueur 1 (Nom, Symbole)
        if(event.getSource() == startButtonPlayerOne){
            gameController2player.setPlayerOneName(playerOneName);
            gameController2player.setPlayerTwoName(playerTwoName);
            gameController2player.setCurrentPlayer('X');
            gameController2player.setWhoPlayFirst("X");
            gameController2player.setCurrentPlayerName(playerOneName);

        // Si on choisi de commencer par le joueur 2 alors on envoye au controlleur du jeu les informations du joueur 2 (Nom, Symbole)
        } if(event.getSource() == startButtonPlayerTwo) {
            gameController2player.setPlayerOneName(playerTwoName);
            gameController2player.setPlayerTwoName(playerOneName);
            gameController2player.setCurrentPlayer('O');
            gameController2player.setWhoPlayFirst("O");
            gameController2player.setCurrentPlayerName(playerTwoName);
        } else {
        // Si on choisi de commencer par un joueur aléatoire alors on envoi au controlleur du jeu les informations du joueur aléatoire
            Random random = new Random();

            // On génère un nombre aléatoire entre 0 et 1
            int randomInt = random.nextInt(2);

            // Si le nombre généré est 0 alors on envoi les informations du joueur 1 au controlleur du jeu
            if (randomInt == 0) {
                gameController2player.setPlayerOneName(playerOneName);
                gameController2player.setPlayerTwoName(playerTwoName);
                gameController2player.setCurrentPlayer('X');
                gameController2player.setWhoPlayFirst("X");
                gameController2player.setCurrentPlayerName(playerOneName);

            // Si le nombre généré est 1 alors on envoi les informations du joueur 2 au controlleur du jeu
            } else {
                gameController2player.setPlayerOneName(playerTwoName);
                gameController2player.setPlayerTwoName(playerOneName);
                gameController2player.setCurrentPlayer('O');
                gameController2player.setWhoPlayFirst("O");
                gameController2player.setCurrentPlayerName(playerTwoName);
            }
        }


        Stage gameStage = new Stage();
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.setTitle("Tic - Tac - Toe");
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
        gameStage.show();

        ((Stage) startButtonPlayerOne.getScene().getWindow()).close();
        ConnectionController.getStage().close();
    }

    public void setPlayerOneName(String playerOneName) {
        // Pour récuperer le nom du joueur 1
        this.playerOneName = playerOneName;
        startButtonPlayerOne.setText(playerOneName);
    }

    public void setPlayerTwoName(String playerTwoName){
        // Pour récuperer le nom du joueur 2
        this.playerTwoName = playerTwoName;
        startButtonPlayerTwo.setText(playerTwoName);
    }

}