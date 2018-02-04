package models;

public class TakeableItem extends Item{

    TakeableItem()
    {
        setItemType(ItemType.TAKEABLE);
    }
    @Override
    public boolean onTouchAction(Character character) {

        return character.getInventory().addItem(this);

    }
}
