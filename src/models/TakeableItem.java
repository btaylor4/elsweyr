package models;

import views.StatusView;

public class TakeableItem extends Item{

    public TakeableItem()
    {
        setItemType(ItemType.TAKEABLE);
    }

    @Override
    public boolean onTouchAction(Character character) {

        return character.getInventory().addItem(this);

    }

    @Override
    public boolean onTouchAction(Character character, StatusView view) {
        return character.getInventory().addItem(this);
    }
}
