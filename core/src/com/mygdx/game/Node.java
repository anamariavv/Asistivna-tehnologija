package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;

public class Node extends BinaryHeap.Node {
    public Array<Connection> connections = new Array<Connection>();

    public Node(float value) {
        super(value);
    }


    public Array<Connection> getConnections () {
        return connections;
    }

    public void createConnection(Node to, float cost) {
            connections.add(new Connection(this, to, cost));
    }

    private static class Indexer {
        private static int index = 0;

        public static int getIndex() {
            return index++;
        }
    }
}
