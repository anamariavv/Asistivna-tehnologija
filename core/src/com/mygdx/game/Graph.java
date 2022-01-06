package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class Graph implements IndexedGraph {


    @Override
    public Array<Connection> getConnections(Object fromNode) {
        //i need to implement this myself, maybe remove this class and implement pfa.graph in graph generator??
        return null;
    }

    @Override
    public int getIndex(Object node) {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }
}
