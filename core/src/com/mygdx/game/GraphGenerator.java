package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;


public class GraphGenerator {

    //creates graph from tiledmap
    public static MyGraph createGraph(TiledMap map) {
        MyGraph graph;
        Array<Node> nodes = new Array<Node>();
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
        int mapHeight = MapManager.lvlTileHeight;
        int mapWidth = MapManager.lvlTileWidth;

        for(int i = 0; i < mapHeight; i++) { //i je y, j je x
            for(int j = 0; j < mapWidth; j++) {
                Node node = new Node(1.0f);
                nodes.add(node);
            }
        }


        for(int i = 0; i < mapHeight; i++) {
            for(int j = 0; j < mapWidth; j++) {
                TiledMapTileLayer.Cell current = layer.getCell(i,j);
                TiledMapTileLayer.Cell up = layer.getCell(i+1, j);
                TiledMapTileLayer.Cell down = layer.getCell(i-1, j);
                TiledMapTileLayer.Cell left = layer.getCell(i, j-1);
                TiledMapTileLayer.Cell right = layer.getCell(i, j+1);

                Node currentNode = nodes.get(mapWidth*i+j);
                //add collision here by looking at properties
                if(current != null) {
                    if(i != 0 && down!= null) {
                        Node downNode = nodes.get(mapWidth*(i-1)+j);
                        currentNode.createConnection(downNode,1);
                    }
                    if(i != mapHeight-1 && up != null) {
                        Node upNode = nodes.get(mapHeight*(i+1)+j);
                        currentNode.createConnection(upNode, 1);
                    }
                    if(j != 0 && left != null) {
                        Node leftNode = nodes.get(mapWidth*i+(j-1));
                        currentNode.createConnection(leftNode,1);
                    }
                    if(j != mapWidth-1 && right != null) {
                        Node rightNode = nodes.get(mapWidth*i+(j+1));
                        currentNode.createConnection(rightNode,1);
                    }
                }
            }
        }

        graph = new MyGraph(nodes);
        return graph;
    }


}
