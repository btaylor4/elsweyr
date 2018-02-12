package models;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;

public class BedOfSpikes extends ObstacleItem {

    private String itemName;
    private String itemSpritePath;
    private Image imageSprite;

    public BedOfSpikes() throws FileNotFoundException {
        this.setName("BedOfSpikes");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "BedOfSpikes.png");
        this.createItemImage();
    }

}
