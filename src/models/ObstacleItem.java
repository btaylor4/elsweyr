package models;


public class ObstacleItem extends Item {

    ObstacleItem(){
        setItemType(ItemType.Obstacle);
    }

    @Override
    public boolean onTouchAction(Character character) {
        return true;
    }
}
