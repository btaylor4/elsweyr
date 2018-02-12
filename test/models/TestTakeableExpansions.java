package models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class TestTakeableExpansions {

    private static Character testCharacter;
    private static Inventory testInv;
    private static TakeableKey testKey;
    private static TakeableSword testSword;

    @BeforeClass
    public static void setUpCharacter() throws FileNotFoundException {
        testCharacter = new Character();
        testInv = new Inventory();
        testKey = new TakeableKey();
        testKey.setItemType(ItemType.TAKEABLE);
        testSword = new TakeableSword();
        testSword.setItemType(ItemType.TAKEABLE);
        testCharacter.setInventory(testInv);
        testInv.setMaxSize(5);

    }

    @Test
    public void KeyTest() {
        testKey.onTouchAction(testCharacter);
        Assert.assertTrue(testCharacter.getInventory().hasItem(testKey));
        testInv.removeItem(testKey);
        Assert.assertFalse(testCharacter.getInventory().hasItem(testKey));
    }

    @Test
    public void SwordTest() {
        testSword.onTouchAction(testCharacter);
        Assert.assertTrue(testCharacter.getInventory().hasItem(testSword));
        testInv.removeItem(testSword);
        Assert.assertFalse(testCharacter.getInventory().hasItem(testSword));
    }
}
