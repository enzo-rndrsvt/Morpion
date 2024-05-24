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

    public WhoPlayFirstController2player(){
    }

    @FXML
    void startActionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view2player.fxml"));
        Parent root = loader.load();

        GameController2player gameController2player = loader.getController();

        if(event.getSource() == startButtonPlayerOne){
            gameController2player.setPlayerOneName(playerOneName);
            gameController2player.setPlayerTwoName(playerTwoName);
            gameController2player.setCurrentPlayer('X');
            gameController2player.setWhoPlayFirst("X");
            gameController2player.setCurrentPlayerName(playerOneName);
        } if(event.getSource() == startButtonPlayerTwo) {
            gameController2player.setPlayerOneName(playerTwoName);
            gameController2player.setPlayerTwoName(playerOneName);
            gameController2player.setCurrentPlayer('O');
            gameController2player.setWhoPlayFirst("O");
            gameController2player.setCurrentPlayerName(playerTwoName);
        } else {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 0) {
                gameController2player.setPlayerOneName(playerOneName);
                gameController2player.setPlayerTwoName(playerTwoName);
                gameController2player.setCurrentPlayer('X');
                gameController2player.setWhoPlayFirst("X");
                gameController2player.setCurrentPlayerName(playerOneName);
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
        this.playerOneName = playerOneName;
        startButtonPlayerOne.setText(playerOneName);
    }

    public void setPlayerTwoName(String playerTwoName){
        this.playerTwoName = playerTwoName;
        startButtonPlayerTwo.setText(playerTwoName);
    }

}