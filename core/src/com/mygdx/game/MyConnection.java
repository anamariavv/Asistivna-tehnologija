package com.mygdx.game;

public class MyConnection<N> implements com.badlogic.gdx.ai.pfa.Connection<N> {
    private float cost;
    private N from;
    private N to;

    public MyConnection(N from, N to, float cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public N getFromNode() {
        return from;
    }

    @Override
    public N getToNode() {
        return to;
    }
}
