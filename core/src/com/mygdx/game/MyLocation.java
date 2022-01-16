package com.mygdx.game;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MyLocation implements Location<Vector2> {
    Vector2 position;
    float orientation;

    public MyLocation(Vector2 position, float orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) MathUtils.atan2(vector.y, vector.x);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return new Vector2(-(float)MathUtils.sin(angle), (float)MathUtils.cos(angle));
    }

    @Override
    public Location<Vector2> newLocation() {
        return new MyLocation(this.position, this.orientation);
    }
}
