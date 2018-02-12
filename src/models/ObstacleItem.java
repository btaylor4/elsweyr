package models;


import views.StatusView;

public class ObstacleItem extends Item {

    public ObstacleItem(){
        setItemType(ItemType.OBSTACLE);
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
