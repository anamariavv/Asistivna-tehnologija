package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class MyGraph implements IndexedGraph<Node> {
    Array<Node> nodes = new Array<Node>();

    public MyGraph(Array<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        return fromNode.connections;
    }

    @Override
    public int getIndex(Node node) {
        int index = nodes.indexOf(node, false);
        return index;
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    public Node getNodeXY(int x, int y) {
        int i = x / MapManager.tilePixelWidth;
        int j = y / MapManager.tilePixelHeight;

        return nodes.get(MapManager.lvlTileWidth * i + j);
    }

}
