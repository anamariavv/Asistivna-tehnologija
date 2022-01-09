package com.mygdx.game;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class SteeringEntity implements Steerable {
    Vector2 characterPosition;
    boolean tagged;
    float characterRadius;
    float orientation;
    float maxLinearSpeed, maxLinearAcceleration;
    float maxAngularSpeed, maxAngularAcceleration;

    SteeringBehavior<Vector2> behavior;
    SteeringAcceleration<Vector2> steeringOutput;

    public SteeringEntity(Vector2 characterPosition, float characterRadius, float orientation) {
        this.characterPosition = characterPosition;
        this.characterRadius = characterRadius;
        this.orientation = orientation;
    }

    public void update(float delta) {

    }

    @Override
    public Vector getLinearVelocity() {
        return new Vector2(1,1);
    }

    @Override
    public float getAngularVelocity() {
        return 1;
    }

    @Override
    public float getBoundingRadius() {
        return characterRadius;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return 0;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {

    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector getPosition() {
        return characterPosition;
    }

    @Override
    public float getOrientation() {
        return 0; //get the angle of the character
        //Todo implement angle tracking (maybe a vector or just a field with a value in the character class)
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public float vectorToAngle(Vector vector) {
        //what the hell
        return (float)MathUtils.atan2(this.characterPosition.x, this.characterPosition.y);
    }

    @Override
    public Vector angleToVector(Vector outVector, float angle) {
        return new Vector2(-(float)MathUtils.sin(angle), (float)MathUtils.cos(angle));
    }

    @Override
    public Location newLocation() {
        return newLocation();
    }

    public void setBehavior(SteeringBehavior<Vector2> behavior) {
        this.behavior = behavior;
    }

    public SteeringBehavior<Vector2> getBehavior() {
        return behavior;
    }
}
