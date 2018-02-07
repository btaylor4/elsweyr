package models;

import javafx.scene.image.Image;

import java.io.File;
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

    public void setItemSpritePath(String itemSpritePath)
    {
        this.itemSpritePath = itemSpritePath + File.separator;
    }

    public void createItemImage() throws FileNotFoundException
    {
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
}
