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

public class WhoPlayFirstController1player {

    @FXML
    private Button startButtonPlayerOne;

    @FXML
    private Button startButtonPlayerTwo;

    @FXML
    private Button startButtonRandom;

    private Stage stage;

    private String playerOneName;
    private String playerTwoName;

    public WhoPlayFirstController1player(){
    }

    @FXML
    void startActionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view1player.fxml"));
        Parent root = loader.load();

        GameController1player gameController1player = loader.getController();

        if(event.getSource() == startButtonPlayerOne){
            gameController1player.setPlayerOneName(playerOneName);
            gameController1player.setPlayerTwoName(playerTwoName);
            gameController1player.setCurrentPlayer('X');
            gameController1player.setWhoPlayFirst("X");
            gameController1player.setCurrentPlayerName(playerOneName);
        } if(event.getSource() == startButtonPlayerTwo) {
            gameController1player.setPlayerOneName(playerTwoName);
            gameController1player.setPlayerTwoName(playerOneName);
            gameController1player.setCurrentPlayer('O');
            gameController1player.setWhoPlayFirst("O");
            gameController1player.setCurrentPlayerName(playerTwoName);
        } else {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 0) {
                gameController1player.setPlayerOneName(playerOneName);
                gameController1player.setPlayerTwoName(playerTwoName);
                gameController1player.setCurrentPlayer('X');
                gameController1player.setWhoPlayFirst("X");
                gameController1player.setCurrentPlayerName(playerOneName);
            } else {
                gameController1player.setPlayerOneName(playerTwoName);
                gameController1player.setPlayerTwoName(playerOneName);
                gameController1player.setCurrentPlayer('O');
                gameController1player.setWhoPlayFirst("O");
                gameController1player.setCurrentPlayerName(playerTwoName);
            }
        }



        Stage gameStage = new Stage();
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.setTitle("Tic - Tac - Toe");
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
        gameController1player.setStage(gameStage);
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