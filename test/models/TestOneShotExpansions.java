package models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class TestOneShotExpansions {

    private static Character testCharacter;
    private static OneShotBananaPeel testBanana;
    private static OneShotBook testBook;

    @BeforeClass
    public static void setUpCharacter() throws FileNotFoundException {
        testCharacter = new Character();
        testBanana = new OneShotBananaPeel();
        testBook = new OneShotBook();

        testCharacter.setTotalHP(100);
        testCharacter.setBaseHP(100);
        testCharacter.setCurrentHP(testCharacter.getBaseHP());
        testCharacter.setCurrExp(100);
    }

    @Test
    public void bananaTest() {
        testBanana.onTouchAction(testCharacter);
        Assert.assertEquals(testCharacter.getBaseHP() + testBanana.getHealthIncrease(), testCharacter.getCurrentHP());
    }

    @Test
    public void bookTest() {
        testBook.onTouchAction(testCharacter);
        Assert.assertEquals(110, testCharacter.getCurrExp());
    }
}
