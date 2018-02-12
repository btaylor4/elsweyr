package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Character;
import models.GlobalLevel;
import views.GlobalGameplayView;
import views.InGameMenuView;
import views.LocalGameplayView;

import java.awt.*;


public class GlobalGameplayController {
    private Character character;
    private GlobalLevel map;
    private EventHandler<ActionEvent> localMovementListener;
    private GlobalGameplayView view;

    public GlobalGameplayController(GlobalGameplayView globalView, Character playerCharacter, GlobalLevel global) {
        this.character = playerCharacter;
        this.map = global;
        this.view = globalView;
        this.view.addKeyPressListener(new GlobalMovementListener());
        this.view.addMenuButtonListener(new MenuButtonHandler());
        this.view.addChangeToLocalListener(new ChangeToLocalHandler());
        this.view.setCharacterSpritesPath(character.getCharacterSpritePath());

        populateView();
    }

    public void populateView()
    {
        int mapHeight = map.getGlobalMap().length;
        int mapWidth = map.getGlobalMap()[0].length;
        character.setCharacterSprite(new Image(character.getCharacterSpritePath()+ "Character_Front.png"));
        Image characterImage = character.getCharacterSprite();
        Image[][] zoneImages = new Image[mapHeight][mapWidth];
        for(int i = 0; i < mapHeight; i++)
            for(int j = 0; j < mapWidth; j++)
                zoneImages[i][j] = map.getGlobalMap()[i][j].getZoneSprite();


        view.createTileViews(zoneImages);
        view.createCharacterView(characterImage);
        view.setGlobalCharacterPrevPos(character.getGlobalPos());
        view.updateCharacterPos(character.getGlobalPos());


    }

    public class GlobalMovementListener implements EventHandler<KeyEvent> {
        public boolean checkValidMove(Point projectedMove) {
            Point characterPosition = character.getGlobalPos();
            int xPositionChange = characterPosition.x + projectedMove.x;
            int yPositionChange = characterPosition.y + projectedMove.y;

            if(xPositionChange < 0 || xPositionChange > map.getGlobalMap().length - 1)
                return false;

            else if(yPositionChange < 0 || yPositionChange > map.getGlobalMap()[0].length - 1)
                return false;
            //Checks if the zone is passable if it is not passable returns false.
            else if(!map.getGlobalMap()[xPositionChange][yPositionChange].isPassable())
                return false;

            else
                return true;
        }

        public boolean updateCharacterPosition(String move) {
            Point projectedMove;

            switch (move) {
                case "UP": // 8
                    projectedMove = new Point(-1, 0);
                    break;

                case "DOWN": // 2
                    projectedMove = new Point(1, 0);
                    break;

                case "LEFT": // 4
                    projectedMove = new Point(0, -1);
                    break;

                case "RIGHT": //6
                    projectedMove = new Point(0, 1);
                    break;

                case "END": // 1 DOWN_LEFT
                    projectedMove = new Point(1, -1);
                    break;

                case "PAGE_DOWN":  // DOWN_RIGHT
                    projectedMove = new Point(1, 1);
                    break;

                case "HOME":  // UP_LEFT
                    projectedMove = new Point(-1, -1);
                    break;


                case "PAGE_UP": // UP_RIGHT
                    projectedMove = new Point(-1, 1);
                    break;

                default:
                    return false;
            }
            //Sends the view the characters move.
            view.updateMove(move);


            if(checkValidMove(projectedMove)) {
                Point newPosition = new Point(character.getGlobalPos().x + projectedMove.x,
                        character.getGlobalPos().y + projectedMove.y);
                character.updateGlobalPos(newPosition);
                //Sends the character position to the view
                view.updateCharacterPos(newPosition);
                return true;
            }

            return false;
        }

        public boolean checkForLocalLevel() {
            Point globalPos = character.getGlobalPos();
            if(map.getGlobalMap()[globalPos.x][globalPos.y].getHasLevel())
                return true;

            else
                return false;
        }

        @Override
        public void handle(KeyEvent event) {

            String move = event.getCode().toString();
            System.out.println(move);
            if(updateCharacterPosition(move) && checkForLocalLevel()) {
                System.out.println("Changing View To Local Level");
                character.updateLocalPos(map.getStartPosOfTile(character.getGlobalPos()));
                //Set the characters location to be in the Local view when changing to it
                character.setOnLocal(true);
                LocalGameplayView localGameplayView = new LocalGameplayView(character.getCharacterSpritePath());

                Point startTile = map.getGlobalMap()[character.getGlobalPos().x][character.getGlobalPos().y].getStartTile();
                character.updateLocalPos(startTile);
                Scene globalScene = new Scene(localGameplayView, 500, 500);
                LocalGameplayController localGameplayController = new LocalGameplayController(localGameplayView, character, map);
                Stage window = (Stage)(((Scene)event.getSource()).getWindow());
                window.setTitle("Local Level");
                window.setScene(globalScene);
            }
        }
    }

    class MenuButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            // go to in game menu
            System.out.println("menu Buttonstuff");

            InGameMenuView inGameMenuView = new InGameMenuView();
            Scene globalScene = new Scene(inGameMenuView, 500, 500);
            InGameMenuController inGameController = new InGameMenuController(inGameMenuView, character, map);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(globalScene);
        }
    }

    class ChangeToLocalHandler implements EventHandler<ActionEvent> {

        //TODO: Clean thi
        @Override
        public void handle(ActionEvent event) {
            // load local map
            System.out.println("Changing View To Local Level");

            LocalGameplayView localGameplayView = new LocalGameplayView(character.getCharacterSpritePath());
            Scene globalScene = new Scene(localGameplayView, 500, 500);
            LocalGameplayController localGameplayController = new LocalGameplayController(localGameplayView, character, map);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(globalScene);
        }
    }
}
