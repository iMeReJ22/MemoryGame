package com.example.javafxarchetypefxml;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyImageView extends ImageView {
    private final int cardID;
    private int clicks;
    private boolean nowClicked;
    private final Image front;
    private final Image back;

    private boolean showsFront;

    public MyImageView(MyImage image) {
        super(image);
        this.cardID = image.getImageID();
        clicks = 0;
        nowClicked = false;
        front = image;
        back = new Image(getClass().getResourceAsStream("/Images/back.png"));
        showsFront = true;
    }

    public int getCardID() {
        return cardID;
    }
    public int getClicks() {
        return clicks;
    }
    public boolean isNowClicked() {
        return nowClicked;
    }
    public void click(){
        clicks++;
        nowClicked = true;
    }
    public void unClick(){
        nowClicked = false;
    }

    public void switchImages(){
        if(showsFront)
            super.setImage(back);
        else
            super.setImage(front);
        showsFront = !showsFront;
    }
}
