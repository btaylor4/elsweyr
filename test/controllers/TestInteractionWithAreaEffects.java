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
    private Zone[][] zones;
    private Tile[][] tiles;
    private Scene localScene;
    private Stage primaryWindow;

    @Override
    public void start(Stage stage) throws Exception {
        primaryWindow = new Stage();

        localview = new LocalGameplayView();
        character = new Character();
        Zone localLevel = new Zone();

        LocalGameplayController localGameplayController = new LocalGameplayController(localview, character, globalLevel);

        localScene = new Scene(localview, 500, 500);
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
            }
        }

        zones[0][0].setLocalMap(tiles);
        globalLevel.setGlobalMap(zones);
    }

    @Test
    public void testAreaEffectAppliesAfterMove() throws InterruptedException {
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
        Thread.sleep(2000);

        assertEquals(character.getCurrentHP(), 196);
    }
}