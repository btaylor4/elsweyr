package models;

public class LevelUpEffect extends AreaEffect {
    private boolean hasBeenActivated;

    public LevelUpEffect(){
        setEffectType(EffectType.LEVELUPEFFECT);
        hasBeenActivated = false;
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
        int nextLevelExperience = character.getExpToNextLevel();
        hasBeenActivated = true;
        character.setCurrExp(nextLevelExperience);
        character.updateLevel();
    }
}
