package models;

import java.util.Timer;
import java.util.TimerTask;

public class HealthEffect extends AreaEffect {

    private int timeInterval;
    private int healthChange;
    private String effectId;
    private Timer buf;

    public HealthEffect(){
        setEffectType(EffectType.HEALTHEFFECT);
        buf = new Timer();
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
        buf.schedule(new TimerTask() {
            @Override
            public void run() {
                character.updateHealth(healthChange);
            }
        }, 0, timeInterval);
    }

    public void stopTimer() {
        buf.cancel();
    }
}
