package models;

import org.junit.Before;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bryan on 2/6/18.
 */
public class TestHealthEffect {

    private Character character;
    private HealthEffect healthEffect;

    @Before
    public void initializeVariables() {
        character = new Character();
        healthEffect = new HealthEffect();
    }

    @Test
    public void testPositiveEffectIsBeingApplied() throws InterruptedException {
        character.setBaseHP(100);
        character.setCurrentHP(100);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(1);
        healthEffect.applyEffect(character);

        Thread.sleep(2000);
        assertEquals(character.getCurrentHP(), 104);
    }

    @Test
    public void testNegativeEffectIsBeingApplied() throws InterruptedException {
        character.setBaseHP(100);
        character.setCurrentHP(100);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(-1);
        healthEffect.applyEffect(character);

        Thread.sleep(2000);
        assertEquals(character.getCurrentHP(), 96);
    }

    @Test
    public void testNegativeHealth() throws InterruptedException {
        character.setBaseHP(0);
        character.setCurrentHP(0);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(-1);
        healthEffect.applyEffect(character);

        Thread.sleep(2000);
        assertEquals(character.getCurrentHP(), 0);
    }

    @Test
    public void testHealthOverrageRestsCharacterToMaxHealth() throws InterruptedException {
        character.setBaseHP(200);
        character.setCurrentHP(200);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(1);
        healthEffect.applyEffect(character);

        Thread.sleep(2000);
        assertEquals(character.getCurrentHP(), 200);
    }

    @Test
    public void testTimerGetsStopped() throws InterruptedException {
        character.setBaseHP(200);
        character.setCurrentHP(200);
        character.setTotalHP(200);

        healthEffect.setTimeInterval(500);
        healthEffect.setHealthChange(-1);
        healthEffect.applyEffect(character);
        Thread.sleep(490);
        healthEffect.stopTimer();

        Thread.sleep(2000);
        assertEquals(character.getCurrentHP(), 199);
    }
}
