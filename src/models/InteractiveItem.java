package models;


public class InteractiveItem extends Item {

    public InteractiveItem(){
        setItemType(ItemType.INTERACTIVE);
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
