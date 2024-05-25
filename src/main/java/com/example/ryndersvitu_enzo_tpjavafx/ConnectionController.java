package com.example.ryndersvitu_enzo_tpjavafx;

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

    }

    @FXML
    void leaveButton(ActionEvent event) {
        ((Stage) stage.getScene().getWindow()).close();
    }

    @FXML
    void rulesButton(ActionEvent event) throws IOException {
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

        playerOneName = playerOneNameField.getText();
        playerTwoName = playerTwoNameField.getText();

        if (Objects.equals(playerOneName, "") || Objects.equals(playerTwoName, "")) {
            beforeStartLabel.setTextFill(Color.RED);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    beforeStartLabel.setTextFill(Paint.valueOf("#0077B6"));
                }
            }, 1000);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("whoplay-view2player.fxml"));
            Parent root = loader.load();


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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view1player.fxml"));
        Parent root = loader.load();


        GameController1player gameController1player = loader.getController();
        gameController1player.setCurrentPlayer('X');
        gameController1player.setCurrentPlayerName("Vous");



        Stage gameStage = new Stage();
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.setTitle("Tic - Tac - Toe");
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/morpion.png"))));
        gameController1player.setStage(gameStage);
        gameStage.show();

        stage.close();
    }


    public String getPlayerOneName(){
        return playerOneName;
    }

    public String getPlayerTwoName(){
        return  playerTwoName;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public static Stage getStage(){
        return stage;
    }
}