package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;

public class Node extends BinaryHeap.Node {
    public Array<Connection<Node>> connections = new Array<Connection<Node>>();
    int index;

    public Node(float value) {
        super(value);
        index = Node.Indexer.getIndex();
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
