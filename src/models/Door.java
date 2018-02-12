package models;

import javafx.scene.control.Alert;
import views.StatusView;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Bryan on 2/9/18.
 */
public class Door extends InteractiveItem {
    private boolean isOpen;
    private String imageFile = "ArtAssets" + File.separator + "ItemImages" + File.separator + "Door.png";
    private Alert requirementsNotMetAlert;
    private Alert requirementsMetAlert;

    public Door() {
        isOpen = false;
        this.setName("Door");
        this.setItemSpritePath(imageFile);
        this.requirementsMetAlert = new Alert(Alert.AlertType.INFORMATION);
        requirementsMetAlert.setTitle("Door Interaction");
        requirementsMetAlert.setHeaderText("It is a Door");
        requirementsMetAlert.setContentText("You may enter the door!");
        this.requirementsNotMetAlert = new Alert(Alert.AlertType.INFORMATION);
        requirementsNotMetAlert.setTitle("Door Interaction");
        requirementsNotMetAlert.setHeaderText("It is a Door");
        requirementsNotMetAlert.setContentText("You need a key to enter...");
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For Door: looking for -> " + imageFile);
        }
    }

    @Override
    public boolean checkRequirements(Character character) {
        return character.getEquippedItem().getName().equalsIgnoreCase("key");
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view){
        return checkRequirements(character);
    }

    @Override
    public boolean onTouchAction(Character character) {
        if (checkRequirements(character)) {
            requirementsMetAlert.showAndWait();
        } else {
            requirementsNotMetAlert.showAndWait();
        }
        return checkRequirements(character);
    }

    public boolean isDoorOpen() {
        return isOpen;
    }
}
