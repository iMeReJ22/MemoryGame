package com.example.javafxarchetypefxml;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class MyLabel extends Label {
    private int seconds;
    private int minutes;
    private int hours;

    private final Thread timer;

    public MyLabel() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        timer = new Thread(() -> {
            boolean go = true;
            while (go){
                try {
                    Thread.sleep(1000);
                    seconds++;
                    updateText();
                } catch (InterruptedException e) {
                    go = false;
                }
            }
        });
        updateText();
    }
    private void updateText(){
        Platform.runLater(() -> {
            checkTime();
            setText(getNice(hours) + ":" + getNice(minutes) + ":" + getNice(seconds));
        });
    }
    private void checkTime(){
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }
        if (minutes == 60) {
            minutes = 0;
            hours++;
        }
    }
    public String stopTimer(){
        timer.interrupt();
        return getNice(hours)+":"+getNice(minutes)+":"+getNice(seconds);
    }
    public void startTimer(){
        timer.start();
    }

    private String getNice(int time){
        if(time < 10)
            return "0"+time;
        else
            return Integer.toString(time);
    }
}
