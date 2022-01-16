package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class MyHeuristic implements Heuristic<Node> {

    @Override
    public float estimate(Node startNode, Node endNode) {
        int startIndex = startNode.getIndex();
        int endIndex = endNode.getIndex();

        int startX = startIndex % MapManager.lvlTileWidth;
        int startY = startIndex / MapManager.lvlTileHeight;

        int endX = endIndex % MapManager.lvlTileWidth;
        int endY = endIndex / MapManager.lvlTileHeight;

        float distance = (float) Math.sqrt(Math.pow(endX-startX,2) + Math.pow(endY-startY,2));
        return distance;
    }
}
