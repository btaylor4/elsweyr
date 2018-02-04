package models;

public class HealthEffect extends AreaEffect {

    private int timeInterval;
    private int healthChange;
    private String effectId;

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
}
