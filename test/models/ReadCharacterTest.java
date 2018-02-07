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
import java.util.ArrayList;

public class ReadCharacterTest {

    private static final String IMAGE_PATH = "PlaceHolderForImages" + File.separator;

    private static Character actualChar;

    @BeforeClass
    public static void setUpChar() throws FileNotFoundException {
        actualChar = new Character();

        actualChar.setBaseHP(10);
        actualChar.setCurrentHP(5);
        actualChar.setBonusHP(3);
        actualChar.setCurrExp(0);
        actualChar.setExpToNextLevel(4);
        actualChar.setLevel(4);
        actualChar.setOnLocal(false);
        actualChar.updateLocalPos(new Point(4, 3));
        actualChar.updateGlobalPos(new Point (7, 8));
        actualChar.setCharacterSpritePath(IMAGE_PATH + "Character.png");
        actualChar.createCharacterImage();
        actualChar.setEquippedItem(new NoneItem());
        actualChar.setInventory(new Inventory());
        actualChar.getInventory().setMaxSize(5);

        TakeableItem TI = new TakeableItem();
        TI.setName("hula-hoop");
        TI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));

        actualChar.getInventory().addItem(TI);
        actualChar.getInventory().addItem(TI);

        TI = new TakeableItem();
        TI.setName("block");
        TI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));

        actualChar.getInventory().addItem(TI);

        TI = new TakeableItem();
        TI.setName("shoe");
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

    @Test
    public void testCharacter() throws IOException {
        Character expectedChar = ReadFiles.loadCharacter("TheCharacter.txt");

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
}
