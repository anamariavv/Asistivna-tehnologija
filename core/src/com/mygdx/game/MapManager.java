package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapManager {
    public static int lvlTileWidth;
    public static int lvlTileHeight;
    public static int lvlPixelWidth;
    public static int lvlPixelHeight;
    public static int tilePixelWidth;
    public static int tilePixelHeight;
    public static TiledMap currentMap;
    public static Graph graph;
    public static OrthogonalTiledMapRenderer mapRenderer;
    public static GraphGenerator graphGenerator;

    public static void loadMap(String dir) {
        currentMap = new TmxMapLoader().load(dir);
        mapRenderer = new OrthogonalTiledMapRenderer(currentMap);

        MapProperties properties = currentMap.getProperties();
        lvlTileWidth = properties.get("width", Integer.class);
        lvlTileHeight = properties.get("height", Integer.class);
        lvlPixelWidth = properties.get("tilewidth", Integer.class);
        lvlPixelHeight = properties.get("tileheight", Integer.class);
        tilePixelWidth = lvlTileWidth*tilePixelWidth;
        tilePixelHeight = lvlTileHeight*tilePixelHeight;
        GraphGenerator.createGraph(currentMap);
    }

    public static void renderMap(OrthographicCamera camera) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

}
