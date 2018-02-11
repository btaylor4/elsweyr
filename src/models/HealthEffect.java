package models;

import views.StatusView;

import java.util.Timer;
import java.util.TimerTask;

public class HealthEffect extends AreaEffect {

    private int timeInterval;
    private int healthChange;
    private String effectId;
    private Timer buf;

    public HealthEffect(){
        setEffectType(EffectType.HEALTHEFFECT);
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getHealthChange() {
        return healthChange;
    }

    public void setHealthChange(int healthChange) {
        this.healthChange = healthChange;
    }

    public String getEffectId() {
        return effectId;
    }

    public void setEffectId(String effectId) {
        this.effectId = effectId;
    }

    @Override
    public void applyEffect(Character character) {
        buf = new Timer();
        character.addEffect(this);
        buf.schedule(new TimerTask() {
            @Override
            public void run() {
                character.updateHealth(healthChange);
            }
        }, 0, timeInterval);
    }

    @Override
    public void applyEffect(Character character, StatusView view) {
        buf = new Timer();
        character.addEffect(this);
        buf.schedule(new TimerTask() {
            @Override
            public void run() {
                character.updateHealth(healthChange);
                view.updateCharacterHealth(healthChange);
            }
        }, 0, timeInterval);
    }

    public void stopTimer() {
        buf.cancel();
    }
}
