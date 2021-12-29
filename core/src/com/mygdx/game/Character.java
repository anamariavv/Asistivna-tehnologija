package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class Character {
    public Texture characterTex;
    public String spriteDir;
    public Rectangle characterRect;
    public Array<Item> inventory;
    public Image characterImg;
    private int id;
    public String name;

    public Character(String name, String spriteDir, int width, int height) {
        this.name = name;
        this.spriteDir = spriteDir;
        this.characterTex = new Texture(Gdx.files.internal(this.spriteDir));
        this.characterImg = new Image(this.characterTex);
        this.characterRect = new Rectangle();
        this.characterImg.setWidth(width);
        this.characterImg.setHeight(height);
        this.characterRect.setX(500);
        this.characterRect.setWidth(128);
        this.characterRect.setHeight(128);
        this.characterRect.setY(500);
    }


    public void move(float x_destination, float y_destination) {
        characterRect.setX(x_destination);
        characterRect.setY(y_destination);
    }

    //generate character id
    //add methods for adding and removing from inventory
    //add movement methods
    //add animations
    //add method to change spawn location
}
