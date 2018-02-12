package models;


import views.StatusView;

public class OneShotItem extends Item {

    public OneShotItem(){
        setItemType(ItemType.ONESHOT);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view) {
        return false;
    }
}
