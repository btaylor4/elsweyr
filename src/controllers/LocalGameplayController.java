package controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Character;
import models.*;
import views.*;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class LocalGameplayController {
    private Character character;
    private GlobalLevel globalMap;
    private LocalGameplayView view;
    Timer gameChecks = new Timer();

    public LocalGameplayController(LocalGameplayView localView, Character playerCharacter, GlobalLevel global) {
        this.character = playerCharacter;
        this.globalMap = global;
        //TODO: make sure the correct map is loaded from the global map during this constructor
        this.view = localView;
        this.view.addKeyPressListener(new MovementHandler());
        this.view.addMenuButtonListener(new MenuButtonHandler());
        this.view.addInventoryButtonListener(new InvButtonHandler());

        this.view.getStatusView().setDefaultHealth(character.getBaseHP());
        this.view.getStatusView().getCurrentHealth().setWidth(character.getCurrentHP());
        this.view.getStatusView().updateCharacterLevel(character.getLevel());
        this.view.setCharacterSpritesPath(character.getCharacterSpritePath());


        for(HealthEffect effect: character.getHealthEffects()) {
            effect.applyEffect(character, this.view.getStatusView());
        }

        populateView();

        gameChecks.schedule(new TimerTask() {

            @Override
            public void run() {
                globalMap.setGameTime(globalMap.getGameTime()+100);
                //check death
                if(playerCharacter.getCurrentHP() <= 0){
                    Platform.runLater(() -> {
                        System.out.println("Death");
                        DeathView deathView = new DeathView();
                        Scene scene = new Scene(deathView, 500, 500);
                        DeathController deathController = new DeathController(deathView);
                        Stage window = (Stage) view.getScene().getWindow();
                        window.setScene(scene);
                        window.setTitle("DEATH");
                        gameChecks.cancel();
                    });
                }
            }
        }, 0, 100);
    }

    public void populateView() {

        Point zoneLoc = character.getGlobalPos();
        Zone localLevel = globalMap.getGlobalMap()[zoneLoc.x][zoneLoc.y];
        Tile[][] tiles = localLevel.getLocalMap();
        int zoneHeight = tiles.length;
        int zoneWidth = tiles[0].length;

        Image characterSprite = character.getCharacterSprite();
        Image[][] tileSprites = new Image[zoneHeight][zoneWidth];
        Image[][] decalSprites = new Image[zoneHeight][zoneWidth];
        Image[][] itemSprites = new Image[zoneHeight][zoneWidth];

        for (int i = 0; i < zoneHeight; i++)
            for (int j = 0; j < zoneWidth; j++) {
                tileSprites[i][j] = tiles[i][j].getTileSprite();
                if (tiles[i][j].getDecal() != null) {
                    decalSprites[i][j] = tiles[i][j].getDecal();
                }
                if (tiles[i][j].getItem().getItemSprite() != null) {
                    itemSprites[i][j] = tiles[i][j].getItem().getItemSprite();
                }
            }

        view.createTileViews(tileSprites);
        view.createDecalViews(decalSprites);
        view.createItemViews(itemSprites);
        view.createCharacterView(characterSprite);
        view.setCharacterPrevPos(character.getLocalPos());
        view.updateCharacterPos(character.getLocalPos());

    }

    class MovementHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            //Do Movement Stuff
            //move character around
            String keyPressed = event.getCode().toString();
            System.out.println(keyPressed);

            //Attempt to move character on local map. We have to get the local map the user is on based on the global position he was in.
            int globalCharacterXPos = (int) character.getGlobalPos().getX();
            int globalCharacterYPos = (int) character.getGlobalPos().getY();

            Zone localMap = globalMap.getGlobalMap()[globalCharacterXPos][globalCharacterYPos];

            if(moveCharacter(keyPressed, character, localMap))  {

            }

            Point localPos = character.getLocalPos();

            //Gets local map by accessing the global position of character and getting the zone he's on
//            localMap = globalMap.getGlobalMap()[(int)character.getGlobalPos().getX()][(int)character.getGlobalPos().getY()];


            //CHECK IF THERE IS AN ITEM IN TILE, IF IT'S INTERACTIVE ACTIVATE IT
            //IF IT'S TAKEABLE TAKE IT

            if (localMap.getLocalMap()[(int) localPos.getX()][(int) localPos.getY()].getItem() != null) {
                Tile tile = localMap.getLocalMap()[(int) localPos.getX()][(int) localPos.getY()];
                Item itemOnTile = tile.getItem();
                boolean shouldBeRemoved = itemOnTile.onTouchAction(character, view.getStatusView());
                switch (itemOnTile.getItemType()) {
                    case TAKEABLE:
                        if (shouldBeRemoved) {
                            tile.removeItem();
                            tile.setItem(new NoneItem());
                            view.removeItemImageView(character.getLocalPos());
                        }
                        break;
                    case ONESHOT:
                        tile.removeItem();
                        tile.setItem(new NoneItem());
                        view.removeItemImageView(character.getLocalPos());
                        break;
                    case INTERACTIVE:
                        if (shouldBeRemoved) {
                            /*if (itemOnTile instanceof Animal) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Animal Interaction");
                                alert.setHeaderText("It is an Animal");
                                alert.setContentText("You may pet the animal now!");
                                alert.showAndWait();
                            } else if (itemOnTile instanceof Door) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Door Interaction");
                                alert.setHeaderText("It is a Door");
                                alert.setContentText("You may enter the door!");
                                alert.showAndWait();
                            }*/
                        } else {
                            /*if (itemOnTile instanceof Animal) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Animal Interaction");
                                alert.setHeaderText("It is an Animal");
                                alert.setContentText("You need food to pet the animal...");
                                alert.showAndWait();
                            } else if (itemOnTile instanceof Door) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Door Interaction");
                                alert.setHeaderText("It is a Door");
                                alert.setContentText("You need a key to enter...");
                                alert.showAndWait();
                            }*/
                        }
                }
            }


            //CHECK IF THERE'S AN EXIT TILE


            if (localPos.getX() == localMap.getExitTile().getX() && localPos.getY() == localMap.getExitTile().getY()) {
                gameChecks.cancel();
                GlobalGameplayView globalView = new GlobalGameplayView(character.getCharacterSpritePath());
                Scene scene = new Scene(globalView, 500, 500);
                //Set the characters position to be in the global map when stepping on the exit tile
                character.setOnLocal(false);
                GlobalGameplayController globalGameplay = new GlobalGameplayController(globalView, character, globalMap);
                Stage window = (Stage) (((Scene) event.getSource()).getWindow());
                window.setScene(scene);
            }


        }
    }

    class MenuButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            // go to in game menu
            InGameMenuView inGameMenuView = new InGameMenuView();
            Scene globalScene = new Scene(inGameMenuView, 500, 500);
            InGameMenuController inGameController = new InGameMenuController(inGameMenuView, character, globalMap);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            for(HealthEffect effect: character.getHealthEffects()) {
                effect.stopTimer();
            }
            gameChecks.cancel();

            window.setScene(globalScene);
            System.out.println("menu Buttonstuff");
        }
    }

    class InvButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do inv menu stuff
            //switch to inventory view

//            InGameMenuView inGameMenuView = new InGameMenuView();
            InventoryView inventoryView = new InventoryView();

            Scene inventoryScene = new Scene(inventoryView , 500, 500);
            InventoryController inventoryController = new InventoryController(inventoryView,character,globalMap);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            for(HealthEffect effect: character.getHealthEffects()) {
                effect.stopTimer();
            }
            gameChecks.cancel();
            window.setScene(inventoryScene);

            System.out.println("InvButton Stuff");
        }
    }


    boolean outOfMapBounds(Point userLocation, Point userMove, int mapRows, int mapCols) {
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;


        if (predictedMove.x < 0 || predictedMove.y < 0 || predictedMove.x > mapRows || predictedMove.y > mapCols) {
            return true;
        } else {
            return false;
        }

    }

    boolean obstacleOrTerrainBlocking(Point userLocation, Point userMove, Zone map) {
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;

        //Check if there's an obstacle item on tile where character wants to move
        if (map.getLocalMap()[predictedMove.x][predictedMove.y].getItem().getItemType().equals(ItemType.OBSTACLE)) {
            return true;
        }
        //Check if the terrain where character is moving is of type grass
        if (!map.getLocalMap()[predictedMove.x][predictedMove.y].getTerrain().equals(Terrain.GRASS)) {
            return true;
        }

        return false;
    }

    boolean hasInteractiveItem(Point userLocation, Point userMove, Zone map) {
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;

        //Check if there's an interactive item on tile where character wants to move
        if (map.getLocalMap()[predictedMove.x][predictedMove.y].getItem().getItemType().equals(ItemType.INTERACTIVE)) {
                return true;
        }

        return false;
    }

    boolean hasInteractiveItemShouldMove(Point userLocation, Point userMove, Zone map) {
        Point predictedMove = new Point();
        predictedMove.x = userLocation.x + userMove.x;
        predictedMove.y = userLocation.y + userMove.y;
        if (map.getLocalMap()[predictedMove.x][predictedMove.y].getItem().onTouchAction(character)) {
            return true;
        }
        return false;
    }

    //Determines if move in map is a valid one
    boolean moveCharacter(String numKeyPressed, Character character, Zone localMap) {

        int mapRows = (localMap.getLocalMap().length) - 1; //get rows of map
        int mapCols = (localMap.getLocalMap()[0].length) - 1; //get cols of map
        Point characterPositionInMap = character.getLocalPos();
        Point moveDirection; //Store the direction associated with the key pressed ex: UP = (0,1), DOWN = (0,-1)


        switch (numKeyPressed) {
            case "HOME":
                moveDirection = new Point(-1, -1);
                break;
            case "UP":
                moveDirection = new Point(-1, 0);
                break;
            case "PAGE_UP":
                moveDirection = new Point(-1, 1);
                break;
            case "RIGHT":
                moveDirection = new Point(0, 1);
                break;
            case "END":
                moveDirection = new Point(1, -1);
                break;
            case "DOWN":
                moveDirection = new Point(1, 0);
                break;
            case "PAGE_DOWN":
                moveDirection = new Point(1, 1);
                break;
            case "LEFT":
                moveDirection = new Point(0, -1);
                break;
            default:
                return false;


        }
        view.updateMove(numKeyPressed);


        // If charachter isn't out of bounds and there isn't an obstacle item or impassable terrain, update his position
        if (!outOfMapBounds(characterPositionInMap, moveDirection, mapRows, mapCols) &&
                !obstacleOrTerrainBlocking(characterPositionInMap, moveDirection, localMap)) {

            if (hasInteractiveItem(characterPositionInMap, moveDirection, localMap)) {
                if (hasInteractiveItemShouldMove(characterPositionInMap, moveDirection, localMap)) {
                    Point newCharacterPosition = new Point(characterPositionInMap.x + moveDirection.x,
                            characterPositionInMap.y + moveDirection.y);
                    character.updateLocalPos(newCharacterPosition);

                    character.updateLocalPos(newCharacterPosition);

                    //Updates the localViewsCharacterPosition
                    view.updateCharacterPos(newCharacterPosition);
                }
            } else {
                Point newCharacterPosition = new Point(characterPositionInMap.x + moveDirection.x,
                        characterPositionInMap.y + moveDirection.y);
                character.updateLocalPos(newCharacterPosition);

                character.updateLocalPos(newCharacterPosition);

                //Updates the localViewsCharacterPosition
                view.updateCharacterPos(newCharacterPosition);
            }
        }

        AreaEffect effect = localMap.getLocalMap()[character.getLocalPos().x][character.getLocalPos().y].getAreaEffect();

        switch (effect.getEffectType()) {
            case HEALTHEFFECT:

                for (HealthEffect effects : character.getHealthEffects()) {
                    effects.stopTimer(); //TODO: This applies one extra tick
                }

                character.getHealthEffects().clear();
                effect.applyEffect(character, view.getStatusView());
                //TODO: Logic below needs to be refactored when we have full areas with a bunch of aread effects of same
//                boolean hasEffectId = false;
//                if (!character.getHealthEffects().isEmpty()) {
//                    for (HealthEffect effects : character.getHealthEffects()) {
//                        effects.stopTimer();
//                    }
//                }
//
//                if (!character.hasEffect((HealthEffect) effect)) {
//                    for (HealthEffect myEffects : character.getHealthEffects()) {
//                        if (myEffects.getEffectId().equalsIgnoreCase(((HealthEffect) effect).getEffectId())) {
//                            hasEffectId = true;
//                        }
//                    }
//
//                    if (!hasEffectId) {
//                        for (HealthEffect effects : character.getHealthEffects()) {
//                            effects.stopTimer();
//                        }
//                        effect.applyEffect(character, view.getStatusView());
//                    }
//                }
                break;
            case LEVELUPEFFECT:
                for (HealthEffect effects : character.getHealthEffects()) {
                    effects.stopTimer(); //TODO: This applies one extra tick
                }

                character.getHealthEffects().clear();
                
                boolean activated = ((LevelUpEffect) effect).hasBeenActivated();
                if (!activated) {
                    effect.applyEffect(character, view.getStatusView());
                }
                break;
            case NONE:
                if (!character.getHealthEffects().isEmpty()) {
                    for (HealthEffect effects : character.getHealthEffects()) {
                        effects.stopTimer(); //TODO: This applies one extra tick
                    }

                    character.getHealthEffects().clear();
                }

        }

        return true;
    }
}
