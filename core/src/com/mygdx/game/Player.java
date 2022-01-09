package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player extends Character {
    private int health;
    public static final float speed = 150.0f;
    public Animation<TextureRegion> walk_down, walk_up, walk_left, walk_right;
    private IndexedAStarPathFinder<Node> indexedPathFinder;
    private DefaultGraphPath<Node> resultPath;

    public Player() {
        super("Mate Matko", "Sprites/Player/player_front_2.png", 128, 128, new Vector2(0,0));
        health = 50;
        walk_down = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_down.gif").read());
        walk_up = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_up.gif").read());
        walk_left = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_left.gif").read());
        walk_right = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_right.gif").read());
        resultPath = new DefaultGraphPath<Node>();
        indexedPathFinder = new IndexedAStarPathFinder<Node>(MapManager.graph, false);
    }

    public void findPath(Vector3 endCoords) {
        Node startNode = MapManager.graph.getNodeXY((int) this.characterRect.getX(), (int) this.characterRect.getY());
        Node endNode = MapManager.graph.getNodeXY((int) endCoords.x, (int) endCoords.y);
        indexedPathFinder.searchNodePath(startNode, endNode, new MyHeuristic(), resultPath);
    }



}
