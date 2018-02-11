package controllers;

import javafx.scene.Node;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Bryan on 2/6/18.
 */
public class TestGlobalGameplayController extends ApplicationTest {
    private GlobalGameplayController.GlobalMovementListener movementHandler;
    private GlobalGameplayController controller;
    private GlobalGameplayView globalView;
    private GlobalLevel globalLevel;
    private Zone[][] zones;
    private Tile[][] tiles;
    private Scene localScene;
    private Stage primaryWindow;

    @Before
    public void init() {
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
            }
        }

        zones[0][0].setLocalMap(tiles);
        globalLevel.setGlobalMap(zones);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryWindow = new Stage();

        globalView = new GlobalGameplayView();
        localScene = new Scene(globalView, 500, 500);
        Character playerCharacter = new Character();

        GlobalGameplayController globalGameplayController = new GlobalGameplayController(globalView, playerCharacter, globalLevel);

        primaryWindow.setScene(localScene);
        primaryWindow.show();
    }

    @Test
    public void testValidUPMove() {
        Point expected = new Point(0,0);
        Character character = new Character();
        character.setCharacterSprite(new Image("file:PlaceHolderForImages/GRASS.png"));
        character.updateGlobalPos(new Point(1,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
    }

    @Test
    public void testValidDownMove() {
        Point expected = new Point(2,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
    }

    @Test
    public void testValidLeftMove() {
        Point expected = new Point(1,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,1));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
    }

    @Test
    public void testValidRightMove() {
        Point expected = new Point(1,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
    }

    @Test
    public void testValidUpLeftMove() {
        Point expected = new Point(0,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,1));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.HOME, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
        assertEquals(expected.y, character.getGlobalPos().y);
    }

    @Test
    public void testValidUpRightMove() {
        Point expected = new Point(0,2);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,1));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.PAGE_UP, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
        assertEquals(expected.y, character.getGlobalPos().y);
    }

    @Test
    public void testValidDownLeftMove() {
        Point expected = new Point(2,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,1));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.END, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
        assertEquals(expected.y, character.getGlobalPos().y);
    }

    @Test
    public void testValidDownRightMove() {
        Point expected = new Point(2,2);
        Character character = new Character();
        character.updateGlobalPos(new Point(1,1));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.PAGE_DOWN, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(expected.x, character.getGlobalPos().x);
        assertEquals(expected.y, character.getGlobalPos().y);
    }

    @Test
    public void testInValidUPMove() {
        Point direction = new Point(-1,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidDownMove() {
        Point direction = new Point(10,0);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidLeftMove() {
        Point direction = new Point(0,-1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidRightMove() {
        Point direction = new Point(0,10);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidUpLeftMove() {
        Point direction = new Point(-1,-1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidUpRightMove() {
        Point direction = new Point(-1,1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidDownLeftMove() {
        Point direction = new Point(1,-1);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Test
    public void testInvalidDownRightMove() {
        Point direction = new Point(10,10);
        Character character = new Character();
        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        assertFalse(movementHandler.checkValidMove(direction));
    }

    @Ignore
    public void testSceneSwapWhenZoneHasLocalLevel() throws AWTException {
        Character character = new Character();
        character.updateGlobalPos(new Point(1,0));
        character.updateLocalPos(new Point(0,0));
        zones[1][1].setStartTile(new Point(0,0));

        controller = new GlobalGameplayController(globalView, character, globalLevel);
        movementHandler = controller.new GlobalMovementListener();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        localScene.setOnKeyPressed(controller.new GlobalMovementListener());
        FxRobot robot = new FxRobot();
        robot.press(event.getCode());
    }

    @After
    public void cleanUp() throws TimeoutException {
        FxToolkit.hideStage();
    }
}
