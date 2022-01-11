package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraTools {

    public static void lockOnPlayer(Camera camera, Vector2 player) {
        Vector3 position = camera.position;
        position.x = player.x;
        position.y = player.y;
        camera.position.set(position);
        camera.update();
    }

    public static void lerpToPlayer(Camera camera, Vector2 player) {
        Vector3 position = camera.position;
        position.x = camera.position.x + (player.x - camera.position.x) * .1f;
        position.y = camera.position.y + (player.y - camera.position.y) * .1f;
        camera.position.set(position);
        camera.update();
    }

    public static void boundary(Camera camera, float startX, float startY, float width, float height) {

        Vector3 position = camera.position;
        if(position.x < startX) {
            position.x = startX;
        }
        if(position.y < startY) {
            position.y = startY;
        }
        if(position.x > startX + width) {
            position.x = startX + width;
        }
        if(position.y > height) {
            position.y = startY + height;
        }

        camera.position.set(position);
        camera.update();
    }
}
