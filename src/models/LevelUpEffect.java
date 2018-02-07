package models;

public class LevelUpEffect extends AreaEffect {
    private boolean hasBeenActivated;

    public LevelUpEffect(){
        setEffectType(EffectType.LEVELUPEFFECT);
    }

    public void levelUp(Character character){
        character.updateLevel();
    }
    public boolean hasBeenActivated() {
        return hasBeenActivated;
    }

    public void setHasBeenActivated(boolean hasBeenActivated) {
        this.hasBeenActivated = hasBeenActivated;
    }

    @Override
    public void applyEffect(Character character) {

    }
}
