package models;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteTest extends ApplicationTest {

    private static final String IMAGE_PATH = "file:PlaceHolderForImages/";
    private static final String FILE_PATH = "SaveSlot" + File.separator;

    private static GlobalLevel actualMap;
    private static  Character actualChar;


    @Before
    public void setUpMap() throws IOException {
        actualMap = ReadFiles.loadGame("DefaultMap.txt");
    }

    @Before
    public void setUpChar() throws FileNotFoundException, IOException {
        actualMap = ReadFiles.loadGame("DefaultMap.txt");
        actualChar = new Character();

        actualChar.setBaseHP(10);
        actualChar.setCurrentHP(5);
        actualChar.setBonusHP(3);
        actualChar.setCurrExp(0);
        actualChar.setExpToNextLevel(4);
        actualChar.setLevel(4);
        actualChar.setOnLocal(false);
        actualChar.updateLocalPos(new Point(0, 0));
        actualChar.updateGlobalPos(new Point (0, 0));
        actualChar.setCharacterSpritePath(IMAGE_PATH + "Character.png");
        actualChar.createCharacterImage();
        actualChar.setEquippedItem(new NoneItem());
        actualChar.setInventory(new Inventory());
        actualChar.getInventory().setMaxSize(5);
        actualChar.setCharacterName("Bob");

        TakeableItem TI = new TakeableItem();
        TI.setName("key");
        TI.setItemSprite(new javafx.scene.image.Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));

        actualChar.getInventory().addItem(TI);

        TI = new TakeableItem();
        TI.setName("sword");
        TI.setItemSprite(new javafx.scene.image.Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));

        actualChar.getInventory().addItem(TI);

        TI = new TakeableItem();
        TI.setName("food");
        TI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));

        actualChar.getInventory().addItem(TI);

        HealthEffect HE = new HealthEffect();
        HE.setTimeInterval(1000);
        HE.setHealthChange(4);
        HE.setEffectId("poison");

        Buffs B = new Buffs();
        B.setHealthEffect(HE);

        actualChar.addActiveBuff(B);

        HE = new HealthEffect();
        HE.setTimeInterval(2000);
        HE.setHealthChange(6);
        HE.setEffectId("Chocolate cake");

        B = new Buffs();
        B.setHealthEffect(HE);

        actualChar.addActiveBuff(B);

        HE = new HealthEffect();
        HE.setTimeInterval(3000);
        HE.setHealthChange(3);
        HE.setEffectId("poison");

        B = new Buffs();
        B.setHealthEffect(HE);

        actualChar.addActiveBuff(B);

    }

    @Ignore
    public void testMap() throws IOException {
        Write write = new Write();
        String mapFile ="DefaultMap.txt";
        write.writeMapFile("", actualMap);

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

            for(int globRow = 0; globRow < GL.getGlobalMap().length; globRow++) {
                for (int globCol = 0; globCol < GL.getGlobalMap()[0].length; globCol++) {
                    Zone current = GL.getGlobalMap()[globRow][globCol];
                    Zone actualZone = actualMap.getGlobalMap()[globRow][globCol];
                    //check each zone
                    checkZoneAttributes(current, actualZone);
                    for (int locRow = 0; locRow < current.getLocalMap().length; locRow++) {
                        for (int locCol = 0; locCol < current.getLocalMap()[0].length; locCol++) {
                            Tile tile = current.getLocalMap()[locRow][locCol];
                            Tile actualTile = actualZone.getLocalMap()[locRow][locCol];
                            //check each tile
                            checkTilesAttributes(tile, actualTile);
                        }
                    }
                }
            }

        } else {
            System.out.println("GL is still null, but was set!");
            Assert.assertTrue(false);
        }
    }

    private void checkGlobalAttributes(GlobalLevel GL) {
        if (GL != null && actualMap != null) {
            Assert.assertEquals(GL.getGameTime(), actualMap.getGameTime());
            Assert.assertEquals(GL.getGlobalMap().length, actualMap.getGlobalMap().length);
            Assert.assertEquals(GL.getGlobalMap()[0].length, actualMap.getGlobalMap()[0].length);
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
            if (expected.getHasLevel() && actual.getHasLevel()) {
                Assert.assertEquals(expected.getLocalMap().length, actual.getLocalMap().length);
                Assert.assertEquals(expected.getLocalMap()[0].length, actual.getLocalMap()[0].length);
                Assert.assertEquals(expected.getZoneSpritePath(), actual.getZoneSpritePath());
            } else if(expected.getHasLevel() || actual.getHasLevel()) {
                Assert.assertTrue(false);
            }
        } else {
            System.out.println("one or both objects are null");
            Assert.assertTrue(false);
        }
    }

    private void checkTilesAttributes(Tile expected, Tile actual) {
        if (expected != null && actual != null) {
            // test tile
            Assert.assertEquals(expected.getTerrain(), actual.getTerrain());
            Assert.assertEquals(expected.getTileSpritePath(), actual.getTileSpritePath());
            Assert.assertEquals(expected.getDecalSpritePath(), actual.getDecalSpritePath());
            checkItem(expected.getItem(), actual.getItem());
            checkEffect(expected.getAreaEffect(), actual.getAreaEffect());
        } else {
            System.out.println("one or both objects are null");
            Assert.assertTrue(false);
        }
    }

    private void checkItem(Item expected, Item actual) {

        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            Assert.assertTrue(false);
        }

        Assert.assertEquals(expected.getItemType(), actual.getItemType());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getItemSpritePath(), actual.getItemSpritePath());
    }

    private void checkEffect(AreaEffect expected, AreaEffect actual) {

        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            Assert.assertTrue(false);
        }

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



    /*------------------------------------------------------------------------------------------------------------*/



    @Ignore
    public void testChar() throws IOException {
        Write write = new Write();
        Character expectedChar;
        write.writeCharacterFile("", actualChar);

        expectedChar = ReadFiles.loadCharacter("DefaultCharacter.txt");

        Assert.assertEquals(expectedChar.getBaseHP(), actualChar.getBaseHP());
        Assert.assertEquals(expectedChar.getCurrentHP(), actualChar.getCurrentHP());
        Assert.assertEquals(expectedChar.getBonusHP(), actualChar.getBonusHP());
        Assert.assertEquals(expectedChar.getCurrExp(), actualChar.getCurrExp());
        Assert.assertEquals(expectedChar.getExpToNextLevel(), actualChar.getExpToNextLevel());
        Assert.assertEquals(expectedChar.getLevel(), actualChar.getLevel());
        Assert.assertEquals(expectedChar.isOnLocal(), actualChar.isOnLocal());
        Assert.assertEquals(expectedChar.getLocalPos(), actualChar.getLocalPos());
        Assert.assertEquals(expectedChar.getGlobalPos(), actualChar.getGlobalPos());
        Assert.assertEquals(expectedChar.getCharacterSpritePath(), actualChar.getCharacterSpritePath());

        checkEquipped(expectedChar.getEquippedItem(), actualChar.getEquippedItem());
        checkBuffs(expectedChar.getActiveBuffs(), actualChar.getActiveBuffs());
    } // none in equipped

    @Ignore
    public void testCharWithEquipped() throws IOException {
        Write write = new Write();
        Character expectedChar;

        boolean found = true;
        actualChar.setCharacterName("Bobby");
        for (Item i : actualChar.getInventory().getItems()) {
            if (!found && i.getName().equals("hula-hoop")) {
                actualChar.setEquippedItem(i);
                found = true;
            }
        }

        write.writeCharacterFile("TheEquippedTest", actualChar);

        expectedChar = ReadFiles.loadCharacter("TheEquippedTestDefaultCharacter.txt");

        Assert.assertEquals(expectedChar.getBaseHP(), actualChar.getBaseHP());
        Assert.assertEquals(expectedChar.getCurrentHP(), actualChar.getCurrentHP());
        Assert.assertEquals(expectedChar.getBonusHP(), actualChar.getBonusHP());
        Assert.assertEquals(expectedChar.getCurrExp(), actualChar.getCurrExp());
        Assert.assertEquals(expectedChar.getExpToNextLevel(), actualChar.getExpToNextLevel());
        Assert.assertEquals(expectedChar.getLevel(), actualChar.getLevel());
        Assert.assertEquals(expectedChar.isOnLocal(), actualChar.isOnLocal());
        Assert.assertEquals(expectedChar.getLocalPos(), actualChar.getLocalPos());
        Assert.assertEquals(expectedChar.getGlobalPos(), actualChar.getGlobalPos());
        Assert.assertEquals(expectedChar.getCharacterSpritePath(), actualChar.getCharacterSpritePath());

        checkEquipped(expectedChar.getEquippedItem(), actualChar.getEquippedItem());
        checkBuffs(expectedChar.getActiveBuffs(), actualChar.getActiveBuffs());
    }

    private void checkBuffs(ArrayList<Buffs> expected, ArrayList<Buffs> actual) {
        Assert.assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); ++i) {
            checkHealthEffect(expected.get(i).getHealthEffect(), actual.get(i).getHealthEffect());
        }
    }

    private void checkHealthEffect(HealthEffect expected, HealthEffect actual) {
        Assert.assertEquals(expected.getEffectId(), actual.getEffectId());
        Assert.assertEquals(expected.getHealthChange(), actual.getHealthChange());
        Assert.assertEquals(expected.getEffectType(), actual.getEffectType());
        Assert.assertEquals(expected.getTimeInterval(), actual.getTimeInterval());
    }

    private void checkEquipped(Item expected, Item actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getItemType(), actual.getItemType());
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
