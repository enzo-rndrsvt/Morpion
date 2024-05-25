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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class GameController2player {
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

    public GameController2player(){
        // On instaure une matrice de 3/3 pour le jeu
        board = new char[3][3];
        gameEnd = false;
        initiateBoard();

    }

    private Button getButtonByIndex(int index){
        // Fonction pour récuper les boutons plus facilement
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

    private void initiateBoard(){
        // Initialisation de la matrice de jeu avec des '-' à chaque case
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = '-';

            }
        }
    }

    private void winButtons(int... indices){
        // Fonction pour mettre en évidence les boutons gagnants à l'aide de la fonction getButtonByIndex
        for(int index : indices){
            Button button = getButtonByIndex(index);
            if(button != null){
                button.setTextFill(javafx.scene.paint.Color.RED);
            }
        }
    }

    private void resetWinButtons(int... indices){
        // Fonction pour remettre les boutons gagnants à leur état initial à l'aide de la fonction getButtonByIndex
        for(int index : indices){
            Button button = getButtonByIndex(index);
            if(button != null){
                button.setTextFill(javafx.scene.paint.Color.valueOf("#03045e"));
                button.setText("");
            }
        }
    }

    @FXML
    void gameButtonAction(ActionEvent event) {
        // Déroulement du jeu tant que la partie n'est pas terminée
        if(gameEnd) return;

        // On récupère l'id du bouton cliqué
        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        // On récupère les coordonnées du bouton cliqué à l'aide de son id (exemple : bouton 0_0) pour permettre de l'identifier plus facilement dans la matrice
        int row = Character.getNumericValue(buttonId.charAt(6));
        int col = Character.getNumericValue(buttonId.charAt(8));

        // On vérifie si la case est vide
        if(board[row][col] == '-'){

            // Si elle est vide alors on la rempli à l'aide du symbole du joueur actuel
            board[row][col] = currentPlayer;

            // On affiche le symbole du joueur actuel sur le bouton cliqué
            button.setText(String.valueOf(currentPlayer));

            // On vérifie si le joueur actuel a gagné
            checkWin();

            // On change de joueur
            switchPlayer();

            // Tant que la partie n'est pas terminée, on affiche un messqge pour indiquer le joueur actuel
            if (gameEnd == false) {
                whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );
            }
        }
    }

    @FXML
    void restartButton(ActionEvent event) {
        // On remet la matrice de jeu à zéro
        initiateBoard();
        gameEnd = false;

        // On remet les boutons à leur état initial
        resetWinButtons(0, 1, 2, 3, 4, 5, 6, 7, 8);
        whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );

        // On cache le bouton de restart
        restartButtonId.setVisible(false);
    }


    @FXML
    void aboutButton(ActionEvent event) {
        // Fonctionnalité du bouton "À propos" (à implémenter)
        return;
    }

    @FXML
    void leaveButton(ActionEvent event) {
        // Quitter le jeu
        Platform.exit();
    }
    @FXML
    void rulesButton(ActionEvent event) throws IOException {
        // Affiche la page de règles
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
        // On affiche le bouton pour recommencer la partie
        restartButtonId.setVisible(true);

        // On regarde qui à commencer en premier pour savoir a qui incrémenter le nombre de victoires
        if(Objects.equals(whoPlayFirst, "X")){
            // Si le joueur actuel est le joueur 1, on incrémente son nombre de victoires
            if(currentPlayer == 'X'){
                playerOneWins++;
            } else {
                // Sinon on incrémente le nombre de victoires du joueur 2
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

        // On verifier si il y a 3 symboles identiques sur une ligne ou une colonne
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)) {
                gameEnd = true;

                // On met en évidence les boutons gagnants
                winButtons(i*3, i*3+1, i*3+2);
                whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
                addWins();

                // On met à jour le classement
                updateRanking();
                return;
            }
            if( (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)){
                gameEnd = true;

                // On met en évidence les boutons gagnants
                winButtons(i, i+3, i+6);
                whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
                addWins();

                // On met à jour le classement
                updateRanking();
                return;
            }
        }

        // On verifier si il y a 3 symboles identiques sur une diagonale
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)) {
            gameEnd = true;

            // On met en évidence les boutons gagnants
            winButtons(0, 4, 8);
            whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
            addWins();

            // On met à jour le classement
            updateRanking();
            return;
        }
        if((board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)){
            gameEnd = true;

            // On met en évidence les boutons gagnants
            winButtons(2, 4, 6);
            whoToPlayLabel.setText("Le joueur (" + currentPlayer + ") - " + currentPlayerName + " a gagné !");
            addWins();

            // On met à jour le classement
            updateRanking();
            return;
        }

        // On vérifie si il y a match nul
        boolean full = true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j] == '-'){
                    full = false;
                    break;
                }
            }
        }

        // Si il y a match nul, on affiche un message et on stop le jeu
        if(full){
            gameEnd = true;
            whoToPlayLabel.setText("Match nul !");
            restartButtonId.setVisible(true);
        }
    }

    private void updateRanking(){
        if(playerOneWins > playerTwoWins){
            // On vérifie qui a le plus de victoires pour mettre à jour le classement
            rank1Label.setText(" 1er " + playerOneName + " : " + playerOneWins + " victoire(s)");
            rank2Label.setText(" 2ème " + playerTwoName + " : " + playerTwoWins + " victoire(s)");
        } else {
            rank1Label.setText(" 1er " + playerTwoName + " : " + playerTwoWins + " victoire(s)");
            rank2Label.setText(" 2ème " + playerOneName + " : " + playerOneWins + " victoire(s)");
        }
    }

    private void switchPlayer(){
        // On change de joueur en prenant en compte son symbole et son nom
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        currentPlayerName = (currentPlayerName == playerOneName) ? playerTwoName : playerOneName;

    }

    public void setCurrentPlayer(char currentPlayer){
        // On récupère le joueur actuel
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentPlayerName(String currentPlayerName){
        // On récupère le nom du joueur actuel
        this.currentPlayerName = currentPlayerName;
        whoToPlayLabel.setText("Au tour de : (" + currentPlayer + ") - " + currentPlayerName );
    }

    public void setWhoPlayFirst(String currentPlayer){
        // On récupère qui doit jouer en premier
        this.whoPlayFirst = currentPlayer;
    }
    public void setPlayerOneName(String playerOneName){
        // On récupère le nom du joueur 1
        this.playerOneName = playerOneName;
    }

    public void setPlayerTwoName(String playerTwoName){
        // On récupère le nom du joueur 2
        this.playerTwoName = playerTwoName;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}
