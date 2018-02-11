package models;

import java.io.File;
import java.io.FileNotFoundException;

public class OneShotHealthPot extends OneShotItem {
    private int healthToAdd;
    private String imageFile = "file:ArtAssets" + File.separator + "ItemImages" + File.separator + "HealthPot.png";

    public OneShotHealthPot() {
        this.healthToAdd = 5;
        this.setItemSpritePath(imageFile);
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For HealthPot: looking for -> " + imageFile);
        }
    }

    @Override
    public boolean onTouchAction(Character character) {
        character.updateHealth(healthToAdd);
        return true;
    }
}
