package com.example.javafxarchetypefxml;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainGameController{
    private int guesses = 0;
    private int correctGuesses = 0;
    private int firstGuesses = 0;
    private int vertical;
    private int horizontal;
    private int cardCount;
    private boolean firstClick = true;
    private GridPane gridPane;
    private VBox vBox;
    private MyLabel timer;
    @FXML
    private AnchorPane anchorPane;
    private int cardsShown = 0;
    private final List<MyImageView> shownImages = new ArrayList<>();
    private final List<MyImage> images = new ArrayList<>();
    private void initImages(){
            for(int i = 1; i <= cardCount; i++){
                String imageSource = "/Images/" + i + ".png";
                images.add(new MyImage(getClass().getResourceAsStream(imageSource), i));
                images.add(new MyImage(getClass().getResourceAsStream(imageSource), i));
            }
    }
    private void initBoard(){
        gridPane= new GridPane();
        timer = new MyLabel();
        timer.setPrefHeight(60);
        timer.setStyle("-fx-text-fill: red; -fx-font-size: 30");
        timer.setVisible(true);
        timer.setAlignment(Pos.CENTER);
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.prefWidth(anchorPane.getScene().getWidth());
        vBox.prefHeight(anchorPane.getScene().getHeight());
        vBox.getChildren().add(0, gridPane);
        vBox.getChildren().add(1, timer);
        vBox.setStyle("-fx-background-color: black");
        anchorPane.getChildren().add(vBox);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(true);
        gridPane.setStyle("-fx-background-color: red");
        for(int h = 0; h < horizontal; h++){
            ColumnConstraints cc = new ColumnConstraints(150);
            gridPane.getColumnConstraints().add(cc);
        }
        for(int v = 0; v < vertical; v++){
            RowConstraints rc = new RowConstraints(150);
            gridPane.getRowConstraints().add(rc);
        }
    }
    private void hideAllCards(){
        gridPane.getChildren()
                .forEach(x -> {
                    if(x instanceof MyImageView)
                        ((MyImageView) x).switchImages();
                });
    }
    private void initCards(){
        for(int h = 0; h < horizontal; h++){
            for(int v = 0; v < vertical; v++) {
                int rand = (int) (Math.random() * images.size());
                MyImageView myCard = new MyImageView(images.remove(rand));
                EventHandler eventHandler = event -> {
                    try{
                        if(cardsShown == 2)
                            throw new ThirdClickException();
                        if(firstClick)
                            throw new FirstClickExcetpion();
                        if(myCard.isNowClicked())
                            throw new AlreadyClickedException();

                        myCard.switchImages();
                        myCard.click();
                        cardsShown++;
                        shownImages.add(myCard);

                        if(cardsShown == 2 && (shownImages.get(0).getCardID() == shownImages.get(1).getCardID())) {
                            if (shownImages.get(0).getClicks() == 1 && shownImages.get(1).getClicks() == 1)
                                firstGuesses++;
                            correctGuesses++;
                            shownImages.clear();
                            guesses++;
                            cardsShown = 0;
                        }

                        if(correctGuesses == cardCount){
                            gameEnd();
                        }
                    } catch (FirstClickExcetpion e){
                        System.out.println("Caught FCE");
                        hideAllCards();
                        firstClick = false;
                        timer.startTimer();
                    } catch (AlreadyClickedException e){
                        System.out.println("Caught ACE");
                    } catch (ThirdClickException e){
                        System.out.println("Caught TCE");
                        if (shownImages.get(0).getCardID() != shownImages.get(1).getCardID()) {
                            shownImages.forEach(MyImageView::unClick);
                            shownImages.forEach(MyImageView::switchImages);
                        }
                        shownImages.clear();
                        guesses++;
                        cardsShown = 0;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                };
                myCard.setOnMouseClicked(eventHandler);
                gridPane.add(myCard, h, v);

            }
        }
    }
    private void gameEnd() throws IOException {
        System.out.println("firstGuesses: " + firstGuesses + "\nGuesses: " + guesses);
        Stage thisStage = (Stage) anchorPane.getScene().getWindow();
        thisStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddScore-view.fxml"));
        Stage newStage = new Stage();
        Scene addScore = new Scene(loader.load());
        newStage.setScene(addScore);
        AddScoreController scoreController = loader.getController();
        scoreController.initialize(cardCount, timer.stopTimer(), guesses, firstGuesses);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
    }

    public void initDimensions(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        cardCount = vertical * horizontal / 2;
        initImages();
        initBoard();
        initCards();
    }
}
