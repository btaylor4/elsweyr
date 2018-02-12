package models;

import views.StatusView;

import java.io.File;
import java.io.FileNotFoundException;

public class Food extends TakeableItem{

    public Food()throws FileNotFoundException {
        this.setName("Food");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "Food.png");
        this.createItemImage();
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
