package controllers;

import models.Zone;
import models.Character;
import models.ItemType;
import models.Terrain;

import java.awt.*;

import static models.ItemType.OBSTACLE;

public class LocalMovementListener {

    //FOR ALL BOOLEANS CURRENTLY RETURNING FALSE
    //THIS ARE ALL METHOD STUBS

    boolean outOfMapBounds(Point userLocation,Point userMove, int mapRows,int mapCols){
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;


        if(predictedMove.x < 0 || predictedMove.y < 0 || predictedMove.x > mapRows || predictedMove.y > mapCols ){
            return false;
        }

        else{
            return true;
        }

    }

    boolean obstacleOrTerrainBlocking(Point userLocation,Point userMove, Zone map){
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;

        //Check if there's an obstacle item on tile where character wants to move
        if(map.getLocalMap()[predictedMove.x][predictedMove.y].getItem().equals(ItemType.OBSTACLE)){
            return false;
        }
        //Check if the terrain where character is moving is of type grass
        if(!map.getLocalMap()[predictedMove.x][predictedMove.y].getTerrain().equals(Terrain.GRASS)){
            return false;
        }

        return true;

    }
    boolean checkValidMove(String numKeyPressed, Character character, Zone localMap){

        int mapRows = localMap.getLocalMap().length; //get rows of map
        int mapCols = localMap.getLocalMap()[0].length; //get cols of map
        Point characterPositionInMap = character.getLocalPos();
        Point moveDirection; //Store the direction associated with the key pressed ex: UP = (0,1), DOWN = (0,-1)


        switch (numKeyPressed){
            case "HOME" :
                moveDirection = new Point(-1,-1);
                break;
            case "UP" :
                moveDirection = new Point(-1,0);
                break;
            case "PAGE_UP" :
                moveDirection = new Point(-1,1);
                break;
            case "RIGHT" :
                moveDirection = new Point(0,1);
                break;
            case "END" :
                moveDirection = new Point(1,-1);
                break;
            case "DOWN" :
                moveDirection = new Point(1,0);
                break;
            case "PAGE_DOWN" :
                moveDirection = new Point(1,1);
                break;
            case "LEFT" :
                moveDirection = new Point(0,-1);
                break;
            default:
                moveDirection = new Point(0,0);

        }

        if(outOfMapBounds(characterPositionInMap,moveDirection,mapRows,mapCols)){
            return false;
        }

        return true;
    }

    boolean checkForItem(){
        return false;
    }
    void interactWithItem(){

    }
    void checkForAreaEffects(){

    }
    boolean interactWithAreaEffects(){

        return false;
    }
    boolean checkForLocalExitTile(){
        return false;

    }
    void passControlToGlobalGamePlay(){

    }
}
