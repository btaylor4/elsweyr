package models;

public class LevelUpEffect extends AreaEffect {
    private boolean hasBeenActivated;

    public void levelUp(Character character){
        character.setLevel(character.getLevel() + 1);
    }
    public boolean hasBeenActivated() {
        return hasBeenActivated;
    }

    public void setHasBeenActivated(boolean hasBeenActivated) {
        this.hasBeenActivated = hasBeenActivated;
    }
}
