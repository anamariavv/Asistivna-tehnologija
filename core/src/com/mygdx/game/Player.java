package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Player extends Character {
    private int health;
    public float speed = 5;
    public Animation<TextureRegion> walk_down, walk_up, walk_left, walk_right;
    private IndexedAStarPathFinder<Node> indexedPathFinder;
    private DefaultGraphPath<Node> resultPath;
    private Array<Vector2> waypointsArray;
    public int waypointNum = 0;
    private boolean finalWaypoint = false;
    float tolerance = 5;

    public Player() {
        super("Mate Matko", "Sprites/Player/player_front_2.png", 128, 128, new Vector2(0,0));
        health = 50;
        walk_down = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_down.gif").read());
        walk_up = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_up.gif").read());
        walk_left = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_left.gif").read());
        walk_right = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Sprites/Player/player_walk_right.gif").read());
        indexedPathFinder = new IndexedAStarPathFinder<Node>(MapManager.graph, false);
    }

    public void findPath(Vector3 endCoords) {
        resultPath = new DefaultGraphPath<Node>();
        Node startNode = MapManager.graph.getNodeXY((int)currentPosition.x, (int)currentPosition.y);
        Node endNode = MapManager.graph.getNodeXY((int) endCoords.x, (int) endCoords.y);
        indexedPathFinder.searchNodePath(startNode, endNode, new MyHeuristic(), resultPath);

        waypointNum = 0;
        finalWaypoint = false;
        waypointsArray = new Array<>();
        for(int i = 0; i < resultPath.nodes.size; i++) {
            Vector2 currentWaypoint = new Vector2(resultPath.nodes.get(i).worldx, resultPath.nodes.get(i).worldy);
            waypointsArray.add(currentWaypoint);
        }
    }

    public void update(float delta) {
        if(waypointsArray != null && finalWaypoint == false) {
            //Todo draw moving animation based on movement angle
            float angle = (float) Math.atan2(waypointsArray.get(waypointNum).y - currentPosition.y, waypointsArray.get(waypointNum).x - currentPosition.x);
            System.out.print("Angle: " + angle * MathUtils.radiansToDegrees);
            linearVelocity.set((float) MathUtils.cos(angle) * speed, (float) MathUtils.sin(angle) * speed);
            currentPosition.set(currentPosition.x + linearVelocity.x * delta, currentPosition.y + linearVelocity.y * delta);

            if(waypointReached(delta)) {
               currentPosition.set(waypointsArray.get(waypointNum));
                if(waypointNum + 1 >= waypointsArray.size) {
                    waypointNum = 0;
                    finalWaypoint = true;
                } else {
                    waypointNum++;
                }
            }
        }
    }

    private boolean waypointReached(float delta) {
        return waypointsArray.get(waypointNum).x - currentPosition.x <= speed / tolerance * delta &&
                waypointsArray.get(waypointNum).y - currentPosition.y <= speed/ tolerance * delta;
    }

}
