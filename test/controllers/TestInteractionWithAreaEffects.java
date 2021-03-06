package controllers;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.*;
import models.Character;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import views.GlobalGameplayView;
import views.LocalGameplayView;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bryan on 2/6/18.
 */
public class TestInteractionWithAreaEffects extends ApplicationTest {

    private LocalGameplayController.MovementHandler movementHandler;
    private LocalGameplayController controller;
    private LocalGameplayView localview;
    private GlobalLevel globalLevel;
    private Character character;

    private HealthEffect healthEffect;
    private LevelUpEffect levelUpEffect;
    private Zone[][] zones;
    private Tile[][] tiles;
    private Scene localScene;
    private Stage primaryWindow;

    @Override
    public void start(Stage stage) throws Exception {
        primaryWindow = new Stage();

        localview = new LocalGameplayView("file:PlaceHolderForImages/");
        character = new Character();
        character.setCurrentHP(100);
        character.setCharacterSpritePath("file:PlaceHolderForImages/");

        Zone localLevel = new Zone();
        localScene = new Scene(localview, 500, 500);

        LocalGameplayController localGameplayController = new LocalGameplayController(localview, character, globalLevel);
        primaryWindow.setScene(localScene);
        primaryWindow.show();
    }

    @Before
    public void init() {
        globalLevel = new GlobalLevel();
        zones = new Zone[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                zones[i][j] = new Zone();
                zones[i][j].setExitTile(new Point(1,1));
            }
        }

        tiles = new Tile[5][5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].setItem(new InteractiveItem());
                tiles[i][j].setTerrain(Terrain.GRASS);
                tiles[i][j].setEffectType(new NoneEffect());
            }
        }

        zones[0][0].setLocalMap(tiles);
        globalLevel.setGlobalMap(zones);
        levelUpEffect = new LevelUpEffect();
    }

    @Test
    public void testHealthEffectAppliesAfterMove() throws InterruptedException {
        healthEffect = new HealthEffect();
        character.setBaseHP(200);
        character.setCurrentHP(200);
        character.setTotalHP(200);
        character.setCharacterSpritePath("file:PlaceHolderForImages/");

        healthEffect.setTimeInterval(5);
        healthEffect.setHealthChange(-1);

        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        zones[0][0].getLocalMap()[0][1].setEffectType(healthEffect);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        movementHandler.handle(event);
        Thread.sleep(2000);

        assertEquals(character.getCurrentHP(), 196);
    }

    @Ignore
    public void testSameHealthEffectDoesNotStack() throws InterruptedException {
        healthEffect = new HealthEffect();
        character.setBaseHP(200);
        character.setCurrentHP(200);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(-1);

        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        zones[0][0].getLocalMap()[0][1].setEffectType(healthEffect);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);
        movementHandler.handle(event);

        event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false,
                false,false,false);
        movementHandler.handle(event);

        event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);
        movementHandler.handle(event);

        Thread.sleep(2000);

        assertEquals(character.getCurrentHP(), 196); //This will not work because of how timers work
    }

    @Test
    public void testHealthEffectIsNotAddedIfTileMovedToHasSameEffect() throws InterruptedException {
        healthEffect = new HealthEffect();
        character.setBaseHP(200);
        character.setCurrentHP(200);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(-1);

        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        zones[0][0].getLocalMap()[0][1].setEffectType(healthEffect);
        zones[0][0].getLocalMap()[0][0].setEffectType(healthEffect);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);
        movementHandler.handle(event);

        event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false,
                false,false,false);
        movementHandler.handle(event);

        event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);
        movementHandler.handle(event);

        assertEquals(character.getHealthEffects().size(), 1);
        Thread.sleep(2000);

//        assertEquals(character.getCurrentHP(), 195);
    }

    @Test
    public void testLevelUpEffectAppliesAfterMove() {
        character.setLevel(1);
        character.setCurrExp(1);
        character.setExpToNextLevel(10);
        character.setCharacterSpritePath("file:PlaceHolderForImages/");

        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        zones[0][0].getLocalMap()[0][1].setEffectType(levelUpEffect);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        movementHandler.handle(event);
        assertEquals(character.getLevel(), 2);
        assertEquals(character.getCurrExp(), 10);
        assertEquals(character.getExpToNextLevel(), 20);
    }

    @Test
    public void testLevelUpEffectCannotBeReactivated() throws InterruptedException {
        character.setLevel(1);
        character.setCurrExp(1);
        character.setExpToNextLevel(10);

        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        zones[0][0].getLocalMap()[0][1].setEffectType(levelUpEffect);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);
        movementHandler.handle(event);

        event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false,
                false,false,false);
        movementHandler.handle(event);

        event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);
        movementHandler.handle(event);

        assertEquals(character.getLevel(), 2);
    }
}
