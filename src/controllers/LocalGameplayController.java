package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import models.Character;
import models.GlobalLevel;
import models.Zone;
import views.GlobalGameplayView;
import views.LocalGameplayView;

import java.awt.*;
import java.io.IOException;


public class LocalGameplayController {
    private Character character;
    private GlobalLevel globalMap;
    private EventHandler<ActionEvent> localMovementListener;
    private EventHandler<ActionEvent> inventoryListener;
    private LocalGameplayView view;

    public LocalGameplayController(LocalGameplayView localView, Character playerCharacter, GlobalLevel global) {
        this.character = playerCharacter;
        this.globalMap = global;
        //TODO: make sure the correct map is loaded from the global map during this constructor
        this.view = localView;
        this.view.addKeyPressListener(new MovementHandler());
        this.view.addMenuButtonListener(new MenuButtonHandler());
        this.view.addInventoryButtonListener(new InvButtonHandler());
        this.view.addChangeToGlobalListener(new ChangeToGlobalHandler());
    }
    public LocalGameplayController(){};

    class MovementHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            //Do Movement Stuff
            //move character around
            String keyPressed = event.getCode().toString();
            System.out.println(keyPressed);

            //Attempt to move character on local map. We have to get the local map the user is on based on the global position he was in.
            int globalCharacterXPos = (int)character.getGlobalPos().getX();
            int globalCharacterYPos = (int)character.getGlobalPos().getY();

            Zone localMap = globalMap.getGlobalMap()[globalCharacterXPos][globalCharacterYPos];

            moveCharacter(keyPressed,character,localMap);

            Point localPos = character.getLocalPos();

            //Gets local map by accessing the global position of character and getting the zone he's on
//            localMap = globalMap.getGlobalMap()[(int)character.getGlobalPos().getX()][(int)character.getGlobalPos().getY()];


            //CHECK IF THERE IS AN ITEM IN TILE, IF IT'S INTERACTIVE ACTIVATE IT
            //IF IT'S TAKEABLE TAKE IT

            if(localMap.getLocalMap()[(int)localPos.getX()][(int)localPos.getY()].getItem() != null) {
                Tile tile = localMap.getLocalMap()[(int) localPos.getX()][(int) localPos.getY()];
                Item itemOnTile = tile.getItem();
                boolean shouldBeRemoved = itemOnTile.onTouchAction(character);
                switch (itemOnTile.getItemType()) {
                    case TAKEABLE:
                        if (shouldBeRemoved)
                            tile.removeItem();
                        break;
                    case ONESHOT:
                        tile.removeItem();
                        break;
                }
            }


            //CHECK IF THERE'S AN EXIT TILE


            if(localPos.getX() == localMap.getExitTile().getX() && localPos.getY() == localMap.getExitTile().getY() ){

                GlobalGameplayView globalView = new GlobalGameplayView();
                Scene scene = new Scene(globalView,500,500);
                //Set the characters position to be in the global map when stepping on the exit tile
                character.setOnLocal(false);
                GlobalGameplayController globalGameplay = new GlobalGameplayController(globalView,character,globalMap);
                Stage window = (Stage)(((Scene)event.getSource()).getWindow());

                window.setScene(scene);
            }


        }
    }

    class MenuButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do Menu stuff
            //switch into in-game menu
            System.out.println("menu Buttonstuff");
        }
    }

    class InvButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do inv menu stuff
            //switch to inventory view
            System.out.println("InvButton Stuff");
        }
    }


    boolean outOfMapBounds(Point userLocation,Point userMove, int mapRows,int mapCols){
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;


        if(predictedMove.x < 0 || predictedMove.y < 0 || predictedMove.x > mapRows || predictedMove.y > mapCols ){
            return true;
        }

        else{
            return false;
        }

    }

    boolean obstacleOrTerrainBlocking(Point userLocation,Point userMove, Zone map){
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;

        //Check if there's an obstacle item on tile where character wants to move
        if(map.getLocalMap()[predictedMove.x][predictedMove.y].getItem().getItemType().equals(ItemType.OBSTACLE)){
            return true;
        }
        //Check if the terrain where character is moving is of type grass
        if(!map.getLocalMap()[predictedMove.x][predictedMove.y].getTerrain().equals(Terrain.GRASS)){
            return true;
        }

        return false;

    }

    //Determines if move in map is a valid one
    void moveCharacter(String numKeyPressed, Character character, Zone localMap){

        int mapRows = (localMap.getLocalMap().length)-1; //get rows of map
        int mapCols = (localMap.getLocalMap()[0].length)-1; //get cols of map
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

        // If charachter isn't out of bounds and there isn't an obstacle item or impassable terrain, update his position
        if(!outOfMapBounds(characterPositionInMap,moveDirection,mapRows,mapCols) && !obstacleOrTerrainBlocking(characterPositionInMap,moveDirection,localMap)){

            Point newCharacterPosition = new Point((int)characterPositionInMap.getX()+(int)moveDirection.getX(),(int)characterPositionInMap.getY()+(int)moveDirection.getY());
            character.updateLocalPos(newCharacterPosition);

        }

        if(localMap.getLocalMap()[character.getLocalPos().x][character.getLocalPos().y].getAreaEffect().getEffectType() != EffectType.NONE) {
            localMap.getLocalMap()[character.getLocalPos().x][character.getLocalPos().y].getAreaEffect().applyEffect(character);
        }


    }

    void interactWithAreaEffects(Character character, Zone localMap){
        //TODO: implement similiar way as above method
    }



    class ChangeToGlobalHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do Menu stuff
            //switch into in-game menu


//            System.out.println("Changing View To Global Level");
//
//            GlobalGameplayView globalView = new GlobalGameplayView();
//            GlobalGameplayController controller = new GlobalGameplayController(globalView,character,globalMap);
//            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//            window = globalView;
//            window.show();

        }
    }
}
