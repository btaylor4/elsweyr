package models;


public class InteractiveItem extends Item {

    InteractiveItem(){
        setItemType(ItemType.Interactive);
    }
    @Override
    public boolean onTouchAction(Character character) {

        if(checkRequirements(character))
        {
            return true;
        }
        return false;
    }

    public boolean checkRequirements(Character character){

        return true;
    }
}
