package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Character {
    private int health;
    public static final float speed = 150.0f;
    public Animation<TextureRegion> walk_down, walk_up, walk_left, walk_right;
    private IndexedAStarPathFinder<Node> indexedPathFinder;
    private DefaultGraphPath<Node> resultPath;

    public Player() {
        super("Mate Matko", "Sprites/Player/player_front_2.png", 128, 128);
        this.health = 50;

        this.walk_down = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_down.gif").read());
        this.walk_up = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_up.gif").read());
        this.walk_left = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_left.gif").read());
        this.walk_right = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_right.gif").read());

        resultPath = new DefaultGraphPath<Node>();
        indexedPathFinder = new IndexedAStarPathFinder<Node>(MapManager.graph, false);
    }

    public void move(float x, float y) {
        Node startNode = MapManager.graph.getNodeXY((int)this.characterRect.getX(), (int)this.characterRect.getY());
        Node endNode = MapManager.graph.getNodeXY((int)x, (int)y);

        indexedPathFinder.searchNodePath(startNode, endNode, new MyHeuristic(), resultPath);
        System.out.println("path found " + resultPath);

        /* Todo
            Implement movement based on the found path. Include animation based on the current direction
            the player is facing
        */
    }

}
