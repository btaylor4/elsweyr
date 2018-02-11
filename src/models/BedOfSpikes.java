package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class BedOfSpikes extends ObstacleItem {

    private String itemName;
    private String itemSpritePath;
    private Image imageSprite;

    public BedOfSpikes() throws FileNotFoundException {
        this.itemName = "Bed Of Spikes";
        this.itemSpritePath = "ArtAssets" + File.separator + "ItemImages" + File.separator + "BedOfSpikes.png";
        this.imageSprite = new Image(new FileInputStream(itemSpritePath));
    }

    public String getItemName(){
        return itemName;
    }
}
