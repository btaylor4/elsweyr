package models;

import java.io.File;
import java.io.FileNotFoundException;

public class Animal extends InteractiveItem {

    private Food foodItem;

    public Animal() throws FileNotFoundException{
        this.setName("Animal");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "Animal.png");
        this.createItemImage();
        foodItem = new Food();
    }

    @Override
    public boolean onTouchAction(Character character){
        //check to make sure the inventory contains food
        Inventory inventory = character.getInventory();
        if(inventory.hasItem(foodItem)) {
            return true;
        }
        return false;
    }

}
