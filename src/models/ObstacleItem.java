package models;


public class ObstacleItem extends Item {

    public ObstacleItem(){
        setItemType(ItemType.OBSTACLE);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }
}
