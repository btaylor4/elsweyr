package models;



public class OneShotItem extends Item {

    OneShotItem(){
        setItemType(ItemType.OneShot);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }
}
