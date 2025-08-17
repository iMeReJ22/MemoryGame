package com.example.javafxarchetypefxml;

import javafx.scene.image.Image;

import java.io.InputStream;

public class MyImage extends Image {
    private final int imageID;

    public MyImage(InputStream inputStream, int imageID) {
        super(inputStream);
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }
}
