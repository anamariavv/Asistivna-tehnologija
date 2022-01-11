package com.mygdx.game;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SteeringEntity implements Steerable<Vector2> {
    Vector2 characterPosition;
    Vector2 linearVelocity;

    boolean tagged;
    float characterRadius;
    float orientation;
    float maxLinearSpeed, maxLinearAcceleration;
    float maxAngularSpeed, maxAngularAcceleration;
    float zeroLinearSpeedThreshold;

    SteeringBehavior<Vector2> behavior;
    SteeringAcceleration<Vector2> steeringOutput;

    public SteeringEntity(Vector2 characterPosition, float characterRadius, float orientation) {
        this.characterPosition = characterPosition;
        this.characterRadius = characterRadius;
        this.orientation = orientation;
        this.maxLinearSpeed = 20;
        this.maxLinearAcceleration = 0;
        this.maxAngularSpeed = 10;
        this.maxAngularAcceleration = 0;
        this.zeroLinearSpeedThreshold = 0.5f;
        this.linearVelocity = new Vector2(10,10);
        this.steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
    }

    public void update(float delta) {
        if(behavior != null) {
            behavior.calculateSteering(steeringOutput);
            applySteering(steeringOutput, delta);
        }
    }

    private void applySteering(SteeringAcceleration<Vector2> steeringOutput, float delta) {
        this.characterPosition.mulAdd(this.linearVelocity, delta);
        this.linearVelocity.mulAdd(steeringOutput.linear, delta);
        System.out.println("Velocity: " + this.linearVelocity);
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float)MathUtils.atan2(vector.y, vector.x);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return new Vector2(-(float)MathUtils.sin(angle), (float)MathUtils.cos(angle));
    }

    @Override
    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    @Override
    public float getAngularVelocity() {
        return 10;
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
        return zeroLinearSpeedThreshold;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {
        this.zeroLinearSpeedThreshold = value;
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
    public Vector2 getPosition() {
        return characterPosition;
    }

    @Override
    public float getOrientation() {
        return 0; //get the angle of the character
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
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
