package models;



public class OneShotItem extends Item {

    public OneShotItem(){
        setItemType(ItemType.ONESHOT);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }
}
