package controllers;

import models.*;
import models.Character;

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

        int mapRows = localMap.getLocalMap().length-1; //get rows of map
        int mapCols = localMap.getLocalMap()[0].length-1; //get cols of map
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

    void interactWithItem(Character character, Zone localMap){
        Point localPos = character.getLocalPos();
        //TODO: Possibly have different check for not null
        if(localMap.getLocalMap()[(int)localPos.getX()][(int)localPos.getY()].getItem() != null)
            localMap.getLocalMap()[(int)localPos.getX()][(int)localPos.getY()].getItem().onTouchAction(character);
    }

    void interactWithAreaEffects(Character character, Zone localMap){
        //TODO: implement similiar way as above method
    }

    boolean checkForLocalExitTile(Character character, Zone localMap){
        Point localPos = character.getLocalPos();

        if(localPos.getX() == localMap.getExitTile().getX() && localPos.getY() == localMap.getExitTile().getY() ){
            return true;

        }
        else{
            return false;
        }

    }
    void passControlToGlobalGamePlay(){

    }
}
