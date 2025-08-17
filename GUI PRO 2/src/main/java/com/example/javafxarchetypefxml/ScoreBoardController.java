package com.example.javafxarchetypefxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ScoreBoardController {
    @FXML
    private TableView<Score> scoreTable;
    @FXML
    private TableColumn<Score, Integer> placeColumn;
    @FXML
    private TableColumn<Score, Integer> pointsColumn;
    @FXML
    private TableColumn<Score, String> timeColumn;
    @FXML
    private TableColumn<Score, Integer> guessesColumn;
    @FXML
    private TableColumn<Score, Integer> pairsColumn;
    @FXML
    private TableColumn<Score, Integer> fGuessesColumn;
    @FXML
    private TableColumn<Score, String> nicknameColumn;

    private ObservableList<Score> makeList() throws IOException {
        ObservableList<Score> scores = FXCollections.observableArrayList();
        File saves = new File("src/main/resources/com/example/javafxarchetypefxml/allTimeScores");
        Scanner fileScan = new Scanner(saves);
        List<tempScore> scoreList = new ArrayList<>();
        while (fileScan.hasNext()) {
            String line = fileScan.nextLine();
            String [] parts = line.split(";");
            int points = Integer.parseInt(parts[0]);
            String nickname = parts[1];
            int pairs = Integer.parseInt(parts[2]);
            String time = parts[3];
            int guesses = Integer.parseInt(parts[4]);
            int fGuesses = Integer.parseInt(parts[5]);
            scoreList.add(new tempScore(nickname, points, time, guesses, pairs, fGuesses));
        }
        scoreList.sort((o1, o2) -> o2.getPoints().compareTo(o1.getPoints()));
        int i = 1;
        for(tempScore score : scoreList){
            scores.add(new Score(i++, score));
        }
        return scores;
    }

    public void initialize() throws IOException {

        placeColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("place"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("points"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("time"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
        guessesColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("guesses"));
        fGuessesColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("fGuesses"));
        pairsColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("pairs"));

        scoreTable.setItems(makeList());
    }

}
