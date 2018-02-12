package models;

import javafx.scene.image.Image;
import views.StatusView;

public class NoneItem extends Item{

    public NoneItem() {
        setName("NONE");
        setItemType(ItemType.NONE);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return false;
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view) {
        return false;
    }
}
