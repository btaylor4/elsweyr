package controllers;


import models.Character;
import models.GlobalLevel;
import models.Tile;
import models.Zone;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;


public class TestLocalGameplayController {
    LocalMovementListener movementListener = new LocalMovementListener();

    @Test
    public void testingMovement() {
        Point origin = new Point(0,0);
        Zone  map = new Zone();
        GlobalLevel globalLevel = new GlobalLevel();
        Zone[][] zone = new Zone[4][4];
        Tile[][] tiles = new Tile[4][4];
        Character character = new Character();

        character.updateLocalPos(origin);

        Assert.assertTrue(movementListener.checkValidMove("END", character, map));
        //character.updateGlobalPos(origin);

        //movementListener.updateCharacterPosition(“UP”, character, map);

    }

}
