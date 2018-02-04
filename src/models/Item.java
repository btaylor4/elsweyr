package models;

import java.awt.*;


public abstract class Item {
    private ItemType itemType;
    private Image itemSprite;
    private String name;

    public String getName() {
        return name;
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
