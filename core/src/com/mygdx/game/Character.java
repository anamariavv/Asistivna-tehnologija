package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class Character {
    public Texture characterTex;
    public String spriteDir;
    public Array<Item> inventory;
    public Image characterImg;
    private int id;
    public String name;
    public Vector2 currentPosition;
    public float orientation;
    public Vector2 linearVelocity;

    public Character(String name, String spriteDir, int width, int height, Vector2 currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
        this.spriteDir = spriteDir;
        characterTex = new Texture(Gdx.files.internal(this.spriteDir));
        characterImg = new Image(this.characterTex);
        characterImg.setWidth(width);
        characterImg.setHeight(height);
        orientation = 0;
        linearVelocity = new Vector2(0,0);
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public float getOrientation() {
        return orientation;
    }
}
