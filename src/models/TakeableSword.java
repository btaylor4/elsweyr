package models;

import views.StatusView;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class TakeableSword extends TakeableItem {

    private String imageFile = "ArtAssets" + File.separator + "ItemImages" + File.separator + "Sword.png";

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
        return character.getInventory().addItem(this);
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view) {
        return character.getInventory().addItem(this);
    }

}
