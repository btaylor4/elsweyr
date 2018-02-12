package models;

import javafx.scene.control.Alert;
import views.StatusView;

import java.io.File;
import java.io.FileNotFoundException;

public class Animal extends InteractiveItem {

    private Food foodItem;
    private Alert requirementsNotMetAlert;
    private Alert requirementsMetAlert;

    public Animal() throws FileNotFoundException{
        this.setName("Animal");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "Animal.png");
        this.createItemImage();
        foodItem = new Food();
        this.requirementsNotMetAlert = new Alert(Alert.AlertType.INFORMATION);
        requirementsNotMetAlert.setTitle("Animal Interaction");
        requirementsNotMetAlert.setHeaderText("It is an Animal");
        requirementsNotMetAlert.setContentText("You need food to pet the animal...");
        this.requirementsMetAlert = new Alert(Alert.AlertType.INFORMATION);
        requirementsMetAlert.setTitle("Animal Interaction");
        requirementsMetAlert.setHeaderText("It is an Animal");
        requirementsMetAlert.setContentText("You may pet the animal now!");
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
        if (checkRequirements(character)) {
            requirementsMetAlert.showAndWait();
        } else {
            requirementsNotMetAlert.showAndWait();
        }
        return checkRequirements(character);
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view){
        //check to make sure the inventory contains food
       return  checkRequirements(character);
    }

}
