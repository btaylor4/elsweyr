package controllers;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.*;
import models.Character;
import org.junit.*;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import views.GlobalGameplayView;
import views.LocalGameplayView;


import java.awt.*;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;


public class TestLocalGameplayController extends ApplicationTest {

    private LocalGameplayController.MovementHandler localMovementHandler;
    private LocalGameplayController localController;

    private GlobalGameplayView globalView;
    private LocalGameplayView localView;
    private GlobalLevel globalLevel;
    private Zone[][] zones;
    private Tile[][] tiles;
    private Scene localScene;
    private Stage primaryWindow;


    @Before
    public void init() {
        HealthEffect healthEffect = new HealthEffect();
        healthEffect.setEffectType(EffectType.NONE);
        globalLevel = new GlobalLevel();
        zones = new Zone[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                zones[i][j] = new Zone();
                zones[i][j].setExitTile(new Point(1,1));
                zones[i][j].setZoneSprite(new Image("file:PlaceHolderForImages/GRASS.png"));
            }
        }

        tiles = new Tile[5][5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].setItem(new InteractiveItem());
                tiles[i][j].setTerrain(Terrain.GRASS);
                tiles[i][j].setEffectType(healthEffect);
                tiles[i][j].setTileSprite(new Image("file:PlaceHolderForImages/GRASS.png"));
                tiles[i][j].setDecal(new Image("file:PlaceHolderForImages/GRASS.png"));
                tiles[i][j].getItem().setItemSprite(new Image("file:PlaceHolderForImages/GRASS.png"));
            }
        }

        zones[0][0].setLocalMap(tiles);
        globalLevel.setGlobalMap(zones);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryWindow = new Stage();

//        globalView = new GlobalGameplayView();
        localView = new LocalGameplayView("file:PlaceHolderForImages/");

        Character playerCharacter = new Character();
        playerCharacter.setCharacterSpritePath("file:PlaceHolderForImages/");
        playerCharacter.setCurrentHP(100);

        Zone localLevel = new Zone();

        localScene = new Scene(localView, 500, 500);
        primaryWindow.setScene(localScene);

        //GlobalLevel global = new GlobalLevel();


        LocalGameplayController localGameplayController = new LocalGameplayController(localView, playerCharacter, globalLevel);


        primaryWindow.show();
    }


    @Test
    public void testValidDownMove() {
        Point expected = new Point(1,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testValidUpMove() {
        Point expected = new Point(0,0);
        Character character = new Character();
        character.setCharacterSprite(new Image("file:PlaceHolderForImages/GRASS.png"));
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(1,0));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);
    }

    @Test
    public void testValidRightMove() {
        Point expected = new Point(0,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testValidLeftMove() {
        Point expected = new Point(0,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,2));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }
    @Test
    public void testValidHomeMove() {
        Point expected = new Point(0,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(1,2));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.HOME, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }

    @Test
    public void testValidPgUpMove() {
        Point expected = new Point(0,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(1,2));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.PAGE_UP, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }
    @Test
    public void testValidEndMove() {
        Point expected = new Point(2,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(1,2));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.END, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testValidPgDownMove() {
        Point expected = new Point(2,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(1,2));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.PAGE_DOWN, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }

//    CHECK INVALID MOVE
//
//
//
//

    @Test
    public void testInvalidDownMove() {
        Point expected = new Point(4,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,0));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testInvalidUpMove() {
        Point expected = new Point(0,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);
    }

    @Test
    public void testInvalidRightMove() {
        Point expected = new Point(0,4);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,4));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testInvalidLeftMove() {
        Point expected = new Point(0,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }
    @Test
    public void testInvalidHomeMove() {
        Point expected = new Point(0,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.HOME, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }

    @Test
    public void testInvalidPgUpMove() {
        Point expected = new Point(0,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.PAGE_UP, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }
    @Test
    public void testInvalidEndMove() {
        Point expected = new Point(4,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,1));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.END, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testInvalidPgDownMove() {
        Point expected = new Point(4,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

//        controller = new GlobalGameplayController(globalView, character, globalLevel);

        localController = new LocalGameplayController(localView,character,globalLevel);

//        movementHandler = controller.new GlobalMovementListener();

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.PAGE_DOWN, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }



    @Test
    public void testValidObstacleMove() {

        ObstacleItem obstacleItem = new ObstacleItem();
        //Set an obstacle that character can't move on
        globalLevel.getGlobalMap()[0][0].getLocalMap()[4][4].setItem(obstacleItem);
        Point expected = new Point(4,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

        localController = new LocalGameplayController(localView,character,globalLevel);

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }

    @Test
    public void testInvalidObstacleMove() {

        //Set an obstacle that character can't move on
        Point expected = new Point(4,4);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

        localController = new LocalGameplayController(localView,character,globalLevel);

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testInvalidZoneMountainMove() {

        //Set an obstacle that character can't move on
        globalLevel.getGlobalMap()[0][0].getLocalMap()[4][4].setTerrain(Terrain.MOUNTAIN);
        Point expected = new Point(4,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

        localController = new LocalGameplayController(localView,character,globalLevel);

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }


    @Test
    public void testInvalidZoneWaterMove() {

        //Set an obstacle that character can't move on
        globalLevel.getGlobalMap()[0][0].getLocalMap()[4][4].setTerrain(Terrain.WATER);
        Point expected = new Point(4,3);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

        localController = new LocalGameplayController(localView,character,globalLevel);

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }



    @Test
    public void testValidZoneGrassMove() {

        //Set an obstacle that character can't move on
        globalLevel.getGlobalMap()[0][0].getLocalMap()[4][4].setTerrain(Terrain.GRASS);
        Point expected = new Point(4,4);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

        localController = new LocalGameplayController(localView,character,globalLevel);

        localMovementHandler = localController.new MovementHandler();


        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localMovementHandler.handle(event);

//        movementHandler.handle(event);
        assertEquals(expected.x, character.getLocalPos().x);
        assertEquals(expected.y, character.getLocalPos().y);

    }

    @Ignore
    public void testSceneSwapWhenTileIsExitTile() {
        //Set an obstacle that character can't move on
        globalLevel.getGlobalMap()[0][0].setExitTile(new Point(4,4));
        globalLevel.getGlobalMap()[0][0].getLocalMap()[4][4].setTerrain(Terrain.GRASS);
        Point expected = new Point(4,4);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(4,3));
        character.setCharacterSpritePath("file:PlaceHolderForImages/");
        character.setCurrentHP(100);

        localController = new LocalGameplayController(localView,character,globalLevel);

        localMovementHandler = localController.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localScene.setOnKeyPressed(localController.new MovementHandler());
        FxRobot robot = new FxRobot();
        robot.press(KeyCode.RIGHT);
    }

    @After
    public void cleanUp() throws TimeoutException {
        FxToolkit.hideStage();
    }
}
