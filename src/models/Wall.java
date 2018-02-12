package models;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;

public class Wall extends ObstacleItem{

    private String itemSpritePath;
    private Image itemSprite;
    private String itemName;

    public Wall() throws FileNotFoundException{
        this.setName("Wall");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "Wall.png");
        this.createItemImage();
    }
}
