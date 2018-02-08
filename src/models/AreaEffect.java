package models;

public abstract class AreaEffect {
    private EffectType effectType;

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public abstract void applyEffect(Character character);
}
