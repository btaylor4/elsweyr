package models;

public class TakeableItem extends Item{

    TakeableItem()
    {
        setItemType(ItemType.Takeable);
    }
    @Override
    public boolean onTouchAction(Character character) {

        return character.getInventory().addItem(this);

    }
}
