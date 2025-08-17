package com.example.javafxarchetypefxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController{
    @FXML
    private Button newGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button scoreButton;

    public void newGame() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("NewGame-view.fxml"));

        Stage stage = (Stage) newGameButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("NewGame");
    }

    public void scoreBoard() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ScoreBoard-view.fxml"));

        //Stage window = (Stage) scoreButton.getScene().getWindow();
        Stage scores = new Stage();
        Scene scene = new Scene(parent);
        scores.setScene(scene);
        scores.setTitle("ScoreBoard");
        scores.setResizable(false);
        scores.initModality(Modality.APPLICATION_MODAL);
        scores.show();
    }

    public void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
