package models;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Wall extends ObstacleItem{

    private String itemSpritePath;
    private Image itemSprite;
    private String itemName;

    public Wall() throws FileNotFoundException{
        itemName = "Wall";
        itemSpritePath = "file: ArtAssets" + File.separator + "ItemImages" + File.separator + "Wall.png";
        itemSprite = new Image(new FileInputStream(itemSpritePath));
    }

    public String getItemName(){
        return itemName;
    }
}
