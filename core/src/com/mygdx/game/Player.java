package com.mygdx.game;

public class Player extends Character {
    private int health;

    public Player() {
        super("Mate Matko", "Sprites/Player/player_front_2.png", 64, 64);
        this.health = 50;
    }

}
