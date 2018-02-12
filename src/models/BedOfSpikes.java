package models;

import java.io.File;
import java.io.FileNotFoundException;

public class BedOfSpikes extends ObstacleItem {

    public BedOfSpikes() throws FileNotFoundException {
        this.setName("Bed Of Spikes");
        this.setItemSpritePath("ArtAssets" + File.separator + "ItemImages" + File.separator + "BedOfSpikes.png");
        this.createItemImage();
    }

}
