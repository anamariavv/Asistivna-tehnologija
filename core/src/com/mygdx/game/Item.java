package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Item {
    public String name;
    public String spriteDir;
    public Rectangle itemRect;
    public Texture itemTex;
    public String description;

    public Item(String name, String spriteDir, String description, int width, int height) {
        this.name = name;
        this.description = description;
        this.spriteDir = spriteDir;
        this.itemRect = new Rectangle();
        this.itemRect.width = width;
        this.itemRect.height = height;
        this.itemRect.x = 100;
        this.itemRect.y = 100;
        this.itemTex = new Texture(Gdx.files.internal(this.spriteDir));
    }

}
