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

public class WhoPlayFirstController {

    @FXML
    private Button startButtonPlayerOne;

    @FXML
    private Button startButtonPlayerTwo;

    @FXML
    private Button startButtonRandom;

    private Stage stage;

    private String playerOneName;
    private String playerTwoName;

    public WhoPlayFirstController(){
    }

    @FXML
    void startActionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = loader.load();

        GameController gameController = loader.getController();

        if(event.getSource() == startButtonPlayerOne){
            gameController.setPlayerOneName(playerOneName);
            gameController.setPlayerTwoName(playerTwoName);
            gameController.setCurrentPlayer('X');
            gameController.setWhoPlayFirst("X");
            gameController.setCurrentPlayerName(playerOneName);
        } if(event.getSource() == startButtonPlayerTwo) {
            gameController.setPlayerOneName(playerTwoName);
            gameController.setPlayerTwoName(playerOneName);
            gameController.setCurrentPlayer('O');
            gameController.setWhoPlayFirst("O");
            gameController.setCurrentPlayerName(playerTwoName);
        } else {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 0) {
                gameController.setPlayerOneName(playerOneName);
                gameController.setPlayerTwoName(playerTwoName);
                gameController.setCurrentPlayer('X');
                gameController.setWhoPlayFirst("X");
                gameController.setCurrentPlayerName(playerOneName);
            } else {
                gameController.setPlayerOneName(playerTwoName);
                gameController.setPlayerTwoName(playerOneName);
                gameController.setCurrentPlayer('O');
                gameController.setWhoPlayFirst("O");
                gameController.setCurrentPlayerName(playerTwoName);
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
        this.playerOneName = playerOneName;
        startButtonPlayerOne.setText(playerOneName);
    }

    public void setPlayerTwoName(String playerTwoName){
        this.playerTwoName = playerTwoName;
        startButtonPlayerTwo.setText(playerTwoName);
    }

}