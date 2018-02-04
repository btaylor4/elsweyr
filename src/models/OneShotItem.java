package models;



public class OneShotItem extends Item {

    OneShotItem(){
        setItemType(ItemType.ONESHOT);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }
}
