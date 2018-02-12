package models;

import java.util.Date;
import java.util.Timer;

public class Buffs {
    private HealthEffect healthEffect;
    private Date timeStarted;
    private int lastTick;
    private Timer buffTimer;


    public void startBuffTimer(){
    }

    public HealthEffect getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(HealthEffect healthEffect) {
        this.healthEffect = healthEffect;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    public int getLastTick() {
        return lastTick;
    }

    public void setLastTick(int lastTick) {
        this.lastTick = lastTick;
    }

    public Timer getBuffTimer() {
        return buffTimer;
    }

    public void setBuffTimer(Timer buffTimer) {
        this.buffTimer = buffTimer;
    }
}
