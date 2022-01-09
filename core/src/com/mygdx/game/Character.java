package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class Character extends SteeringEntity {
    public Texture characterTex;
    public String spriteDir;
    public Rectangle characterRect;
    public Array<Item> inventory;
    public Image characterImg;
    private int id;
    public String name;
    public Vector2 currentPosition;
    public float orientation;

    public Character(String name, String spriteDir, int width, int height, Vector2 currentPosition) {
        super(currentPosition, 128, 0);
        this.name = name;
        this.currentPosition = currentPosition;
        this.spriteDir = spriteDir;
        characterTex = new Texture(Gdx.files.internal(this.spriteDir));
        characterImg = new Image(this.characterTex);
        characterRect = new Rectangle();
        characterImg.setWidth(width);
        characterImg.setHeight(height);
        characterRect.setX(0);
        characterRect.setWidth(128);
        characterRect.setHeight(128);
        characterRect.setY(0);
        orientation = 0;
    }

}
