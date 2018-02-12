package models;

import views.StatusView;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Bryan on 2/9/18.
 */
public class Door extends InteractiveItem {
    private boolean isOpen;
    private String imageFile = "ArtAssets" + File.separator + "ItemImages" + File.separator + "Door.png";

    public Door() {
        isOpen = false;
        this.setName("Door");
        this.setItemSpritePath(imageFile);
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For Door: looking for -> " + imageFile);
        }
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

    @Override
    public boolean onTouchAction(Character character, StatusView view){
        return checkRequirements(character);
    }

    public boolean isDoorOpen() {
        return isOpen;
    }
}
