package models;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class TakeableSword extends TakeableItem {

    private String imageFile = "file:ArtAssets" + File.separator + "ItemImages" + File.separator + "Sword.png";

    public TakeableSword() {
        this.setName("Sword");
        this.setItemSpritePath(imageFile);
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For Sword: looking for -> " + imageFile);
        }
    }

    @Override
    public boolean onTouchAction(Character character) {
        character.getInventory().addItem(this);
        return true;
    }

}
