package models;

import javafx.scene.image.Image;

public class NoneItem extends Item{

    public NoneItem() {
        setName("NONE");
        setItemType(ItemType.NONE);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return false;
    }
}
