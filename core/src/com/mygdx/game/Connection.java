package com.mygdx.game;

public class Connection implements com.badlogic.gdx.ai.pfa.Connection {
    public float cost;
    public Node from;
    public Node to;

    public Connection(Node from, Node to, float cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public Object getFromNode() {
        return from;
    }

    @Override
    public Object getToNode() {
        return to;
    }
}
