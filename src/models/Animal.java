package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Animal extends InteractiveItem {

    private String itemName;
    private String itemSpritePath;
    private Image itemSprite;
    private Food foodItem;

    public Animal() throws FileNotFoundException{
        this.itemName = "Animal";
        this.itemSpritePath = "ArtAssets" + File.separator + "ItemImages" + File.separator + "Animal.png";
        this.itemSprite = new Image(new FileInputStream(itemSpritePath));
        foodItem = new Food();
    }

    public String getItemName(){
        return itemName;
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
