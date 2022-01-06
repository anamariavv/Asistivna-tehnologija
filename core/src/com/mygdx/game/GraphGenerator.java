package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;


public class GraphGenerator {

    //creates graph from tiledmap
    public static Graph createGraph(TiledMap map) {
        Graph graph;
        Array<BinaryHeap.Node> nodes = new Array<BinaryHeap.Node>();
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

                //create connections-> implement collision here??
                Node targetNode = (Node) nodes.get(mapWidth*i+j);
                if(targetNode == null) {
                    if(i != 0 && down == null) {
                        Node downNode = (Node) nodes.get(mapWidth * (i-1) + j);
                        targetNode.createConnection(downNode, 1);
                    }
                    if(i != mapHeight-1 && up == null) {
                        Node upNode = (Node) nodes.get(mapWidth * (i+1) + j);
                        targetNode.createConnection(upNode, 1);
                    }
                    if(j != 0 && left == null) {
                        Node leftNode = (Node) nodes.get(mapWidth * i + (j-1));
                        targetNode.createConnection(leftNode, 1);
                    }
                    if(j != mapWidth-1 && right == null) {
                        Node rightNode = (Node) nodes.get(mapWidth * i + (j+1));
                        targetNode.createConnection(rightNode, 1);
                    }
                }
            }
        }

        graph = new Graph(nodes);

        return graph;
    }


}
