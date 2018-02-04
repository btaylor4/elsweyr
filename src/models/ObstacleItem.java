package models;


public class ObstacleItem extends Item {

    ObstacleItem(){
        setItemType(ItemType.OBSTACLE);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }
}
