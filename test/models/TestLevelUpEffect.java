package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bryan on 2/7/18.
 */
public class TestLevelUpEffect {

    private Character character;
    private LevelUpEffect levelUpEffect;

    @Before
    public void initializeVariables() {
        character = new Character();
        levelUpEffect = new LevelUpEffect();
    }

    @Test
    public void testLevelEffectUpdatesCharacterLevel() {
        character.setLevel(1);
        character.setCurrExp(1);
        character.setExpToNextLevel(10);
        levelUpEffect.applyEffect(character);
        assertEquals(2, character.getLevel());
        assertEquals(10, character.getCurrExp());
        assertEquals(20, character.getExpToNextLevel());
    }
}
