package models;

public class OneShotHealthPot extends OneShotItem {
    private int healthToAdd;

    public OneShotHealthPot(int healthToAdd) {
        this.healthToAdd = healthToAdd;
    }

    @Override
    public boolean onTouchAction(Character character) {
        character.updateHealth(healthToAdd);
        return true;
    }
}
