package com.example.ryndersvitu_enzo_tpjavafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameController1player {
    @FXML
    private Button button0_0;

    @FXML
    private Button button0_1;

    @FXML
    private Button button0_2;

    @FXML
    private Button button1_0;

    @FXML
    private Button button1_1;

    @FXML
    private Button button1_2;

    @FXML
    private Button button2_0;

    @FXML
    private Button button2_1;

    @FXML
    private Button button2_2;

    @FXML
    private Label rank1Label;

    @FXML
    private Label rank2Label;

    @FXML
    private Button restartButtonId;


    @FXML
    private Label whoToPlayLabel;

    private Stage stage;

    private char[][] board;
    private char currentPlayer;
    private boolean gameEnd;

    private String playerOneName;
    private String playerTwoName;
    private String currentPlayerName;

    private int playerOneWins = 0;
    private int playerTwoWins = 0;
    private String whoPlayFirst;

    public GameController1player(){
        board = new char[3][3];
        gameEnd = false;
        initiateBoard();

    }

    private Button getButtonByIndex(int index){
        switch (index){
            case 0: return button0_0;
            case 1: return button0_1;
            case 2: return button0_2;
            case 3: return button1_0;
            case 4: return button1_1;
            case 5: return button1_2;
            case 6: return button2_0;
            case 7: return button2_1;
            case 8: return button2_2;
            default: return null;
        }
    }



    private void winButtons(int... indices){
        for(int index : indices){
            Button button = getButtonByIndex(index);
            if(button != null){
                button.setTextFill(javafx.scene.paint.Color.RED);
            }
        }
    }

    private void resetWinButtons(int... indices){
        for(int index : indices){
            Button button = getButtonByIndex(index);
            if(button != null){
                button.setTextFill(javafx.scene.paint.Color.valueOf("#03045e"));
                button.setText("");
            }
        }
    }

    private void initiateBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = '-';

            }
        }
    }

    @FXML
    void gameButtonAction(ActionEvent event) {
        if(gameEnd) return;

        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        // button0_0
        int row = Character.getNumericValue(buttonId.charAt(6));
        int col = Character.getNumericValue(buttonId.charAt(8));

        if(board[row][col] == '-'){
            board[row][col] = currentPlayer;
            button.setText(String.valueOf(currentPlayer));
            checkWin();
            switchPlayer();
            if (gameEnd == false) {
                whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );
                robotMove();
            }
        }

    }

    private Random random = new Random();

    private void robotMove(){
        if(gameEnd) return;
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != '-');

        board[row][col] = currentPlayer;
        String buttonId = "button" + row + '_' + col;
        Button button = (Button) stage.getScene().lookup("#" + buttonId);
        button.setText(String.valueOf(currentPlayer));
        checkWin();
        switchPlayer();
        if(gameEnd == false){
            whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );
        }
    }


    @FXML
    void restartButton(ActionEvent event) {
        initiateBoard();
        switchPlayer();
        gameEnd = false;
        resetWinButtons(0, 1, 2, 3, 4, 5, 6, 7, 8);
        whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );
        restartButtonId.setVisible(false);

    }


    @FXML
    void aboutButton(ActionEvent event) {}

    @FXML
    void leaveButton(ActionEvent event) {
        Platform.exit();
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

    private void addWins(){
        restartButtonId.setVisible(true);
        if(Objects.equals(whoPlayFirst, "X")){
            if(currentPlayer == 'X'){
                playerOneWins++;
            } else {
                playerTwoWins++;
            }
        } else {
            if(currentPlayer == 'X'){
                playerTwoWins++;
            } else {
                playerOneWins++;
            }
        }




    }

    private void checkWin(){

        // LIGNES ET COLONNES
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)) {
                gameEnd = true;
                winButtons(i*3, i*3+1, i*3+2);
                whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
                addWins();
                updateRanking();
                return;
            }
            if( (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)){
                gameEnd = true;
                winButtons(i, i+3, i+6);
                whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
                addWins();
                updateRanking();
                return;
            }
        }

        // DIAGONALES
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)) {
            gameEnd = true;
            winButtons(0, 4, 8);
            whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
            addWins();
            updateRanking();
            return;
        }
        if((board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)){
            gameEnd = true;
            winButtons(2, 4, 6);
            whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
            addWins();
            updateRanking();
            return;
        }

        // MATCH NUL
        boolean full = true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j] == '-'){
                    full = false;
                    break;
                }
            }
        }
        if(full){
            gameEnd = true;
            whoToPlayLabel.setText("Match nul !");
            restartButtonId.setVisible(true);
        }
    }

    private void updateRanking(){
        if(playerOneWins > playerTwoWins){
            rank1Label.setText(" 1er " + playerOneName + " : " + playerOneWins + " victoire(s)");
            rank2Label.setText(" 2ème " + playerTwoName + " : " + playerTwoWins + " victoire(s)");
        } else {
            rank1Label.setText(" 1er " + playerTwoName + " : " + playerTwoWins + " victoire(s)");
            rank2Label.setText(" 2ème " + playerOneName + " : " + playerOneWins + " victoire(s)");
        }
    }

    private void switchPlayer(){

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        currentPlayerName = (currentPlayerName == playerOneName) ? playerTwoName : playerOneName;

    }

    public void setCurrentPlayer(char currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentPlayerName(String currentPlayerName){
        this.currentPlayerName = currentPlayerName;
        whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );
    }

    public void setWhoPlayFirst(String currentPlayer){
        this.whoPlayFirst = currentPlayer;
    }
    public void setPlayerOneName(String playerOneName){
        this.playerOneName = playerOneName;
    }

    public void setPlayerTwoName(String playerTwoName){
        this.playerTwoName = playerTwoName;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
