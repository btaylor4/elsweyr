package models;

import java.io.File;
import java.io.FileNotFoundException;

public class Wall extends ObstacleItem{

    public Wall() throws FileNotFoundException{
        this.setName("Wall");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "Wall.png");
        this.createItemImage();
    }

}
