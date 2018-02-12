package models;

import views.StatusView;

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
    public boolean checkRequirements(Character character) {
        for(Item item: character.getInventory().getItems()) {
            if(item.getName().equalsIgnoreCase("food")) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onTouchAction(Character character){
        //check to make sure the inventory contains food
        return checkRequirements(character);
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view){
        //check to make sure the inventory contains food
       return  checkRequirements(character);
    }

}
