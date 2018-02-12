package models;

import javafx.scene.image.Image;
import views.StatusView;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public abstract class Item {
    private ItemType itemType;
    private Image itemSprite;
    private String itemSpritePath;
    private String name;

    public String getName() {
        return name;
    }

    public void setItemSpritePath(String itemSpritePath) {
        this.itemSpritePath = itemSpritePath;
    }

    public void createItemImage() throws FileNotFoundException {
        itemSprite = new Image(new FileInputStream(itemSpritePath));
    }

    public String getItemSpritePath()
    {
        return itemSpritePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Image getItemSprite() {
        return itemSprite;
    }

    public void setItemSprite(Image itemSprite) {
        this.itemSprite = itemSprite;
    }

    public abstract boolean onTouchAction(Character character);

    public abstract boolean onTouchAction(Character character, StatusView view);

    public boolean equals(Item i){
        if(i.getName().equals(this.getName()) && i.getItemType().equals(this.getItemType())){
            return true;
        }
        else{
            return false;
        }
    }

}
