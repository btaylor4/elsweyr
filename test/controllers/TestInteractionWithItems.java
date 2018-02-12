package controllers;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.*;
import models.Character;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import views.LocalGameplayView;

import java.awt.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bryan on 2/6/18.
 */
public class TestInteractionWithItems extends ApplicationTest {

    private LocalGameplayController.MovementHandler movementHandler;
    private LocalGameplayController controller;
    private LocalGameplayView localview;
    private GlobalLevel globalLevel;
    private Character character;
    private Zone[][] zones;
    private Tile[][] tiles;
    private Scene localScene;
    private Stage primaryWindow;
    private Item item;
    private HealthEffect effect;

    @Override
    public void start(Stage stage) throws Exception {
        primaryWindow = new Stage();

        localview = new LocalGameplayView();
        character = new Character();
        Zone localLevel = new Zone();

        localScene = new Scene(localview, 500, 500);
        LocalGameplayController localGameplayController = new LocalGameplayController(localview, character, globalLevel);

        primaryWindow.setScene(localScene);
        primaryWindow.show();
    }

    @Before
    public void init() {
        character = new Character();
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
            }
        }

        zones[0][0].setLocalMap(tiles);
        globalLevel.setGlobalMap(zones);
        effect = new HealthEffect();
        effect.setEffectType(EffectType.NONE);

        character.updateGlobalPos(new Point(0,0));
        character.updateLocalPos(new Point(0,0));
        zones[0][0].getLocalMap()[0][1].setEffectType(effect);
    }

    @Test
    public void testTakeableItemIsAddedAfterMove() throws InterruptedException {
        Inventory inventory = new Inventory();
        inventory.setMaxSize(10);

        item = new TakeableItem();
        item.setName("sword");

        character.setInventory(inventory);

        zones[0][0].getLocalMap()[0][1].setItem(item);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        movementHandler.handle(event);
        assertTrue(character.getInventory().hasItem(item));
        assertTrue(character.getInventory().getItems().contains(item));
        assertNull(zones[0][0].getLocalMap()[0][1].getItem());
    }

    @Test
    public void testOneShotItemIsActivatedAfterMove() {
        item = new OneShotHealthPot();
        item.setName("health pot");

        character.setTotalHP(50);
        character.setCurrentHP(40);

        zones[0][0].getLocalMap()[0][1].setItem(item);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false, false, false);

        movementHandler.handle(event);
        assertTrue(character.getCurrentHP() == 50);
        assertNull(zones[0][0].getLocalMap()[0][1].getItem());
    }

    @Test
    public void testInteractiveItemIsTriggeredAfterMove() throws InterruptedException {
        Inventory inventory = new Inventory();
        inventory.setMaxSize(10);

        Door lockedDoor = new Door();
        lockedDoor.setName("door");

        item = new TakeableItem();
        item.setName("key");

        character.setInventory(inventory);
        character.getInventory().addItem(item);

        zones[0][0].getLocalMap()[0][1].setItem(lockedDoor);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        movementHandler.handle(event);
        assertTrue(lockedDoor.isDoorOpen());
    }

    @Test
    public void testInteractiveItemRequirementsNotMetAfterMove() throws InterruptedException {
        Inventory inventory = new Inventory();
        inventory.setMaxSize(10);

        Door lockedDoor = new Door();
        lockedDoor.setName("door");

        item = new TakeableItem();
        item.setName("sword");

        character.setInventory(inventory);
        character.getInventory().addItem(item);

        zones[0][0].getLocalMap()[0][1].setItem(lockedDoor);

        controller = new LocalGameplayController(localview, character, globalLevel);
        movementHandler = controller.new MovementHandler();

        KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false,
                false,false,false);

        movementHandler.handle(event);
        assertFalse(lockedDoor.isDoorOpen());
    }
}
