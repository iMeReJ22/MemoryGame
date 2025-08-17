package com.example.javafxarchetypefxml;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class NewGameController {
    @FXML
    private Spinner<Integer> verticalSpinner;
    @FXML
    private Spinner<Integer> horizontalSpinner;
    @FXML
    private Label evenLable;
    @FXML
    private Button startGameButton;
    @FXML
    private Button backButton;
    private int currentHor, currentVer;

    public void initialize() {

        SpinnerValueFactory<Integer> vfVer = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 5);
        SpinnerValueFactory<Integer> vfHor = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10);
        vfVer.setValue(1);
        verticalSpinner.setValueFactory(vfVer);
        currentVer = verticalSpinner.getValue();
        vfHor.setValue(1);
        horizontalSpinner.setValueFactory(vfHor);
        currentHor = horizontalSpinner.getValue();

        verticalSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentVer = verticalSpinner.getValue();
                checkEven();
            }
        });

        horizontalSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentHor  = horizontalSpinner.getValue();
                checkEven();
            }
        });

    }


    private boolean checkEven(){
        if((currentHor * currentVer)% 2 == 1){
            evenLable.setText("Can't start the game, number of cards isn't even.");
            evenLable.setStyle("-fx-text-fill: RED");
            return false;
        }
        else{
            evenLable.setText("Can start the game, number of cards is even.");
            evenLable.setStyle("-fx-text-fill: GREEN");
            return true;
        }
    }
    public void startGame() throws IOException, InterruptedException {
        if(checkEven()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame-view.fxml"));

            Stage gameStage = new Stage();

//            Scene scene = new Scene(loader.load(), 120 * currentHor + 5 * (currentHor-1), 120 * currentVer + 5 * (currentVer-1) +60);

            Scene scene = new Scene(loader.load(), 150 * currentHor + 10 * (currentHor-1), 150 * currentVer + 10 * (currentVer-1) +60);

            gameStage.setResizable(false);

            gameStage.setScene(scene);


            MainGameController mainGameController = loader.getController();
            mainGameController.initDimensions(currentVer, currentHor);

            Stage currentStage = (Stage) startGameButton.getScene().getWindow();
//            currentStage.close();
            gameStage.setTitle("its gamin time");
            gameStage.show();
        }else{
            TranslateTransition shake1 = new TranslateTransition();
            shake1.setNode(startGameButton);
            shake1.setFromX(0);
            shake1.setToX(10);
            shake1.setCycleCount(2);
            shake1.setAutoReverse(true);
            shake1.setDuration(Duration.seconds(0.05));
            shake1.play();
        }
    }

    public void goBack() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Menu-view.fxml"));

        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Menu");
    }
}
