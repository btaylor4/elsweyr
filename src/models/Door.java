package models;

/**
 * Created by Bryan on 2/9/18.
 */
public class Door extends InteractiveItem {
    private boolean isOpen;

    public Door() {
        isOpen = false;
    }

    @Override
    public boolean checkRequirements(Character character) {
        for(Item item: character.getInventory().getItems()) {
            if(item.getName().equalsIgnoreCase("key")) {
                isOpen = true;
                return true;
            }
        }

        return false;
    }

    public boolean isDoorOpen() {
        return isOpen;
    }
}
