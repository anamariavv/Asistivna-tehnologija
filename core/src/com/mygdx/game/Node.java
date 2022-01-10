package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;

public class Node extends BinaryHeap.Node {
    public Array<Connection<Node>> connections = new Array<Connection<Node>>();
    int index;
    int x;
    int y;
    float worldx;
    float worldy;

    public Node(float value, int x, int y) {
        super(value);
        this.x = x;
        this.y = y;
        index = Node.Indexer.getIndex();
        worldx = x * MapManager.tilePixelWidth;
        worldy = y * MapManager.tilePixelHeight;
    }

    public Array<Connection<Node>> getConnections(Node node) {
        return connections;
    }

    public int getIndex() {
        return  index;
    }

    public void createConnection(Node to, float cost) {
            connections.add(new MyConnection(this, to, cost));
    }

    private static class Indexer {
        private static int index = 0;

        public static int getIndex() {
            return index++;
        }
    }
}
