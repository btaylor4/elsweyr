package models;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class TakeableKey extends TakeableItem{

    private String imageFile = "ArtAssets" + File.separator + "ItemImages" + File.separator + "Key.png";

    public TakeableKey() {
        this.setName("Key");
        this.setItemSpritePath(imageFile);
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For Key: looking for -> " + imageFile);
        }
    }

    @Override
    public boolean onTouchAction(Character character) {
       return character.getInventory().addItem(this);
    }
}
