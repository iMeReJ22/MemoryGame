package com.example.javafxarchetypefxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class AddScoreController {
    private int points;
    private int pairs;
    private String time;
    private int guesses;
    private int fGuesses;
    private String nickname;
    @FXML
    private Label nicknameErrorLabel;
    @FXML
    private TextField nicknameField;
    @FXML
    private Label pointsLabel;
    @FXML
    private Label pairsLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label guessesLabel;
    @FXML
    private Label fGuessesLabel;

    public void initialize(int pairs, String time, int guesses, int fGuesses){
        this.pairs = pairs;
        this.time = time;
        this.guesses = guesses;
        this.fGuesses = fGuesses;
        String [] parts = time.split(":");
        int seconds = 0;
        seconds += Integer.parseInt(parts[0])*3600;
        seconds += Integer.parseInt(parts[1]) * 60;
        seconds += Integer.parseInt(parts[2]);
        points = pairs * 200 + (seconds * -5 + guesses * -30 + fGuesses * 30);
        pointsLabel.setText(Integer.toString(points));
        pairsLabel.setText(Integer.toString(pairs));
        timeLabel.setText(time);
        guessesLabel.setText(Integer.toString(guesses));
        fGuessesLabel.setText(Integer.toString(fGuesses));
    }

    private void saveScore() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/com/example/javafxarchetypefxml/allTimeScores", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
//        PrintWriter saveFile = new PrintWriter(file);
        String toSave = points + ";" + nickname + ";" + pairs + ";" + time + ";" + guesses + ";" + fGuesses;
        printWriter.println(toSave);
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

    public void yes() throws IOException {
        this.nickname = nicknameField.getText();
        boolean stop = false;
        if(nickname.length() <= 0){
            nicknameErrorLabel.setText("ADD A NICKNAME");
            nicknameErrorLabel.setVisible(true);
        }else {
            for(int i = 0; i < nickname.length(); i++)
                if (nickname.charAt(i) == ';') {
                    stop = true;
                    break;
                }
            if(stop){
                nicknameErrorLabel.setText("NICKNAME CANT HAVE ';'");
                nicknameErrorLabel.setVisible(true);
            }
            else {
                saveScore();
                close();
            }
        }
    }

    public void close(){
        Stage thisStage = (Stage) nicknameField.getScene().getWindow();
        thisStage.close();
    }
}
