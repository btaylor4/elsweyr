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

    /*
    Creates Image objects for the character, and zoneTiles, and passes them to the GlobalGamePlayView to make ImageViews.
     */
    public void populateView()
    {
        int mapHeight = map.getGlobalMap().length;
        int mapWidth = map.getGlobalMap()[0].length;

        character.setCharacterSprite(new Image(character.getCharacterSpritePath() + "Character_Front.png"));
        Image characterImage = character.getCharacterSprite();

        Image[][] zoneImages = new Image[mapHeight][mapWidth];
        for(int i = 0; i < mapHeight; i++)
            for(int j = 0; j < mapWidth; j++)
                //map.getGlobalMap()[][].getZoneSprite() returns the imageSprite stored on the specific zone
                zoneImages[i][j] = map.getGlobalMap()[i][j].getZoneSprite();


        view.createTileViews(zoneImages);
        view.createCharacterView(characterImage);

        //Intializes the characterPosition in the view, this is used to display the ZoneImageViews
        view.setGlobalCharacterPrevPos(character.getGlobalPos());
        view.updateCharacterPos(character.getGlobalPos());


    }

    public class GlobalMovementListener implements EventHandler<KeyEvent> {
        /*
        Checks if the character move is not out of bounds.
        Or if the character is moving onto an impassable tile.
         */
        public boolean checkValidMove(Point projectedMove) {
            Point characterPosition = character.getGlobalPos();
            int xPositionChange = characterPosition.x + projectedMove.x;
            int yPositionChange = characterPosition.y + projectedMove.y;

            //Checks if character is out of bounds in the rows
            if(xPositionChange < 0 || xPositionChange > map.getGlobalMap().length - 1)
                return false;

            //Checks if the character is out of bounds in the columns
            else if(yPositionChange < 0 || yPositionChange > map.getGlobalMap()[0].length - 1)
                return false;

            //Checks if the zone is passable if it is not passable returns false.
            else if(!map.getGlobalMap()[xPositionChange][yPositionChange].isPassable())
                return false;

            else
                return true;
        }

        /*
        Position is stored in terms of a matrix, so decreasing the x point is moving up, and decreasing the y is moving left
         */
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

            //Sends the view the characters move, to display a sprite showing the direction of movement
            view.updateMove(move);

            //Updates the characters global position
            if(checkValidMove(projectedMove)) {
                Point newPosition = new Point(character.getGlobalPos().x + projectedMove.x,
                        character.getGlobalPos().y + projectedMove.y);
                character.updateGlobalPos(newPosition);

                //Sends the character position to the view, to update the map
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

            //Sending the character to local level.
            if(updateCharacterPosition(move) && checkForLocalLevel()) {

                //Set the characters location to be in the Local view when changing to it
                character.updateLocalPos(map.getStartPosOfTile(character.getGlobalPos()));

                character.setOnLocal(true);
                LocalGameplayView localGameplayView = new LocalGameplayView(character.getCharacterSpritePath());

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

            InGameMenuView inGameMenuView = new InGameMenuView();
            Scene globalScene = new Scene(inGameMenuView, 500, 500);
            InGameMenuController inGameController = new InGameMenuController(inGameMenuView, character, map);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(globalScene);
        }
    }

    //Handles sending the character to localView
    class ChangeToLocalHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            LocalGameplayView localGameplayView = new LocalGameplayView(character.getCharacterSpritePath());
            Scene globalScene = new Scene(localGameplayView, 500, 500);
            LocalGameplayController localGameplayController = new LocalGameplayController(localGameplayView, character, map);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(globalScene);
        }
    }
}
