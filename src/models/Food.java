package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Food extends TakeableItem{

    private String itemName;
    private String itemSpritePath;
    private Image itemSprite;

    public Food()throws FileNotFoundException {
        this.setName("Food");
        //itemSpritePath = "";
                //"file: ArtAssets" + File.separator + "ItemImages" + File.separator + "Food.png";
        //itemSprite = new Image(new FileInputStream((itemSpritePath)));
    }


   /* @Override
    public boolean onTouchAction(Character character) {
        return character.getInventory().addItem(this);
    }*/
}
