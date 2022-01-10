package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.steer.behaviors.FollowPath;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Player extends Character {
    private int health;
    public float speed = 50;
    public Animation<TextureRegion> walk_down, walk_up, walk_left, walk_right;
    private IndexedAStarPathFinder<Node> indexedPathFinder;
    private DefaultGraphPath<Node> resultPath;;

    public Player() {
        super("Mate Matko", "Sprites/Player/player_front_2.png", 128, 128, new Vector2(0,0));
        health = 50;
        walk_down = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_down.gif").read());
        walk_up = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_up.gif").read());
        walk_left = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_left.gif").read());
        walk_right = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_right.gif").read());
        //resultPath = new DefaultGraphPath<Node>();
        indexedPathFinder = new IndexedAStarPathFinder<Node>(MapManager.graph, false);
    }

    public void findPath(Vector3 endCoords) {
        resultPath = new DefaultGraphPath<Node>();
        Node startNode = MapManager.graph.getNodeXY((int)currentPosition.x, (int)currentPosition.y);
        Node endNode = MapManager.graph.getNodeXY((int) endCoords.x, (int) endCoords.y);
        indexedPathFinder.searchNodePath(startNode, endNode, new MyHeuristic(), resultPath);

        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(1,1,0,1);
        float oldx = resultPath.nodes.get(0).worldx;
        float oldy = resultPath.nodes.get(0).worldy;
        for(int i = 1; i < resultPath.nodes.size; i++) {
            shape.line(oldx, oldy, resultPath.nodes.get(i).worldx, resultPath.nodes.get(i).worldy);
            oldx = resultPath.nodes.get(i).worldx;
            oldy = resultPath.nodes.get(i).worldy;
        }
        shape.end();
        shape.dispose();

        Array<Vector2> waypointsArray = new Array<>();
        for(int i = 0; i < resultPath.nodes.size; i++) {
            Vector2 currentWaypoint = new Vector2(resultPath.nodes.get(i).worldx, resultPath.nodes.get(i).worldy);
            waypointsArray.add(currentWaypoint);
        }

        LinePath<Vector2> waypoints = new LinePath<>(waypointsArray, true);
        FollowPath<Vector2, LinePath.LinePathParam> followResultPath = new FollowPath<>(this,waypoints);
        followResultPath.setDecelerationRadius(100);
        followResultPath.setPathOffset(0);
        setBehavior(followResultPath);
    }



}
