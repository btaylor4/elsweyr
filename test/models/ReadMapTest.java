package models;


import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadMapTest {

    private static final String IMAGE_PATH = "PlaceHolderForImages"  + File.separator;

    static GlobalLevel actualGL;

    @BeforeClass
    public static void setUpMap() throws FileNotFoundException {
        Zone[][] zones = new Zone[3][1];
        Tile[][] tiles;
        actualGL = new GlobalLevel();

        tiles = new Tile[2][2];
        //first local map
        zones[0][0] = new Zone();
        zones[0][0].setLocalMap(tiles);
        zones[0][0].setExitTile(new Point(0, 1));
        zones[0][0].setStartTile(new Point (1, 1));
        zones[0][0].setPassable(true);
        zones[0][0].setZoneSprite(new Image(new FileInputStream(IMAGE_PATH + "mapImage.png")));

        // first local map tiles
        tiles[0][0] = new Tile();
        tiles[0][0].setTerrain(Terrain.GRASS);
        HealthEffect HE = new HealthEffect();
        HE.setEffectId("1015");
        HE.setEffectType(EffectType.HEALTHEFFECT);
        HE.setTimeInterval(10);
        HE.setHealthChange(-5);
        tiles[0][0].setEffectType(HE);
        InteractiveItem II = new InteractiveItem();
        II.setName("bob");
        II.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Interactive.png")));
        tiles[0][0].setItem(II);
        tiles[0][0].setDecal(new Image(new FileInputStream(IMAGE_PATH + "Death.png")));
        tiles[0][0].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "GRASS.png")));

        // second local map tiles
        tiles[0][1] = new Tile();
        tiles[0][1].setTerrain(Terrain.MOUNTAIN);
        LevelUpEffect LE = new LevelUpEffect();
        LE.setHasBeenActivated(false);
        LE.setEffectType(EffectType.LEVELUPEFFECT);
        tiles[0][1].setEffectType(LE);
        ObstacleItem OI = new ObstacleItem();
        OI.setName("blob");
        OI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Obstacle.png")));
        tiles[0][1].setItem(OI);
        tiles[0][1].setDecal(new Image(new FileInputStream(IMAGE_PATH + "Level.png")));
        tiles[0][1].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "MOUNTAIN.png")));

        // third local map tiles
        tiles[1][0] = new Tile();
        tiles[1][0].setTerrain(Terrain.WATER);
        tiles[1][0].setEffectType(new NoneEffect());
        OneShotItem OSI = new OneShotItem();
        OSI.setName("one");
        OSI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "OneShot.png")));
        tiles[1][0].setItem(OSI);
        tiles[1][0].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "WATER.png")));

        // fourth local map tiles
        tiles[1][1] = new Tile();
        tiles[1][1].setTerrain(Terrain.WATER);
        tiles[1][1].setEffectType(new NoneEffect());
        TakeableItem TI = new TakeableItem();
        TI.setName("me");
        TI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));
        tiles[1][1].setItem(TI);
        tiles[1][1].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "GRASS.png")));

        tiles = new Tile[1][2];
        //second local map
        zones[1][0] = new Zone();
        zones[1][0].setLocalMap(tiles);
        zones[1][0].setExitTile(new Point(0, 1));
        zones[1][0].setStartTile(new Point (0, 0));
        zones[1][0].setPassable(false);
        zones[1][0].setZoneSprite(new Image(new FileInputStream(IMAGE_PATH + "mapImage.png")));

        // first local map tile
        tiles[0][0] = new Tile();
        tiles[0][0].setTerrain(Terrain.GRASS);
        LE = new LevelUpEffect();
        LE.setHasBeenActivated(true);
        LE.setEffectType(EffectType.LEVELUPEFFECT);
        tiles[0][0].setEffectType(LE);
        tiles[0][0].setItem(new NoneItem());
        tiles[0][0].setDecal(new Image(new FileInputStream(IMAGE_PATH + "Level.png")));
        tiles[0][0].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "GRASS.png")));

        // second local map tiles
        tiles[0][1] = new Tile();
        tiles[0][1].setTerrain(Terrain.WATER);
        tiles[0][1].setEffectType(new NoneEffect());
        tiles[0][1].setItem(new NoneItem());
        tiles[0][1].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "WATER.png")));

        tiles = new Tile[2][2];
        // third local map
        zones[2][0] = new Zone();
        zones[2][0].setLocalMap(tiles);
        zones[2][0].setExitTile(new Point(0, 0));
        zones[2][0].setStartTile(new Point (0, 1));
        zones[2][0].setPassable(true);
        zones[2][0].setZoneSprite(new Image(new FileInputStream(IMAGE_PATH + "mapImage.png")));

        // first local map tiles
        tiles[0][0] = new Tile();
        tiles[0][0].setTerrain(Terrain.WATER);
        tiles[0][0].setEffectType(new NoneEffect());
        tiles[0][0].setItem(new NoneItem());
        tiles[0][0].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "WATER.png")));

        // second local map tiles
        tiles[0][1] = new Tile();
        tiles[0][1].setTerrain(Terrain.WATER);
        tiles[0][1].setEffectType(new NoneEffect());
        tiles[0][1].setItem(new NoneItem());
        tiles[0][1].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "WATER.png")));

        // third local map tiles
        tiles[1][0] = new Tile();
        tiles[1][0].setTerrain(Terrain.GRASS);
        HE = new HealthEffect();
        HE.setEffectType(EffectType.HEALTHEFFECT);
        HE.setEffectId("101");
        HE.setTimeInterval(5);
        HE.setHealthChange(5);
        tiles[1][0].setEffectType(HE);
        tiles[1][0].setItem(new NoneItem());
        tiles[1][0].setDecal(new Image(new FileInputStream(IMAGE_PATH + "Health.png")));
        tiles[1][0].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "GRASS.png")));

        // fourth local map tiles
        tiles[1][1] = new Tile();
        tiles[1][1].setTerrain(Terrain.MOUNTAIN);
        HE = new HealthEffect();
        HE.setEffectType(EffectType.HEALTHEFFECT);
        HE.setEffectId("1420");
        HE.setTimeInterval(0);
        HE.setHealthChange(-1000);
        tiles[1][1].setEffectType(HE);
        tiles[1][1].setItem(new NoneItem());
        tiles[1][1].setDecal(new Image(new FileInputStream(IMAGE_PATH + "Death.png")));
        tiles[1][1].setTileSprite(new Image(new FileInputStream(IMAGE_PATH + "MOUNTAIN.png")));

        actualGL.setGlobalMap(zones);
        actualGL.setGameTime(15);

    }

    @Test
    public void readMap() {

        String mapFile = "TheMap.txt";
        GlobalLevel GL = null;

        try {
            GL = ReadFiles.loadGame(mapFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        if (GL != null) {
            // test the map components based on file.
            checkGlobalAttributes(GL);

            for (int i = 0; i < GL.getGlobalMap().length * GL.getGlobalMap()[0].length; ++i) {
                Zone current = GL.getGlobalMap()[i / GL.getGlobalMap().length][i % GL.getGlobalMap()[0].length];
                Zone actualZone = actualGL.getGlobalMap()[i / GL.getGlobalMap().length][i % GL.getGlobalMap()[0].length];
                //check each zone
                checkZoneAttributes(current, actualZone);
                for (int j = 0; j < current.getLocalMap().length * current.getLocalMap()[0].length; ++j) {
                    Tile tile = current.getLocalMap()[i / current.getLocalMap().length] [i % current.getLocalMap()[0].length];
                    Tile actualTile = actualZone.getLocalMap()[i / current.getLocalMap().length] [i % current.getLocalMap()[0].length];
                    //check each tile
                    checkTilesAttributes(tile, actualTile);
                }
            }

        } else {
            System.out.println("GL is still null, but was set!");
            Assert.assertTrue(false);
        }
    }

    private void checkGlobalAttributes(GlobalLevel GL) {
        if (GL != null && actualGL != null) {
            Assert.assertEquals(GL.getGameTime(), actualGL.getGameTime());
            Assert.assertEquals(GL.getGlobalMap().length, actualGL.getGlobalMap().length);
            Assert.assertEquals(GL.getGlobalMap()[0].length, actualGL.getGlobalMap()[0].length);
        } else {
            System.out.println("one or both objects are null");
            Assert.assertTrue(false);
        }
    }

    private void checkZoneAttributes(Zone expected, Zone actual) {
        if (expected != null && actual != null) {
            // test zone
            Assert.assertEquals(expected.getExitTile(), actual.getExitTile());
            Assert.assertEquals(expected.getStartTile(), actual.getStartTile());
            Assert.assertEquals(expected.isPassable(), actual.isPassable());
            Assert.assertEquals(expected.getLocalMap().length, actual.getLocalMap().length);
            Assert.assertEquals(expected.getLocalMap()[0].length, actual.getLocalMap()[0].length);
        } else {
            System.out.println("one or both objects are null");
            Assert.assertTrue(false);
        }
    }

    private void checkTilesAttributes(Tile expected, Tile actual) {
        if (expected != null && actual != null) {
            // test tile
            Assert.assertEquals(expected.getTerrain(), actual.getTerrain());
            checkItem(expected.getItem(), actual.getItem());
            checkEffect(expected.getAreaEffect(), actual.getAreaEffect());
        } else {
            System.out.println("one or both objects are null");
            Assert.assertTrue(false);
        }
    }

    private void checkItem(Item expected, Item actual) {
        Assert.assertEquals(expected.getItemType(), actual.getItemType());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    private void checkEffect(AreaEffect expected, AreaEffect actual) {

        Assert.assertEquals(expected.getEffectType(), actual.getEffectType());

        if (expected.getEffectType().equals(EffectType.HEALTHEFFECT)) {
            HealthEffect exp = (HealthEffect) expected;
            HealthEffect act = (HealthEffect) actual;

            Assert.assertEquals(exp.getEffectId(), act.getEffectId());
            Assert.assertEquals(exp.getHealthChange(), act.getHealthChange());
            Assert.assertEquals(exp.getTimeInterval(), act.getTimeInterval());
        } else if (expected.getEffectType().equals(EffectType.LEVELUPEFFECT)) {
            LevelUpEffect exp = (LevelUpEffect) expected;
            LevelUpEffect act = (LevelUpEffect) actual;

            Assert.assertEquals(exp.hasBeenActivated(), act.hasBeenActivated());
        } else if (expected.getEffectType().equals(EffectType.NONE)) {
            NoneEffect exp = (NoneEffect) expected;
            NoneEffect act = (NoneEffect) actual;

            Assert.assertEquals(exp.getEffectType(), act.getEffectType());
        } else {
            System.out.println("unknown effect");
            Assert.assertTrue(false);
        }
    }
}
