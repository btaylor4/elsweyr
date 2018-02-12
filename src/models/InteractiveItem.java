package models;


import views.StatusView;

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

    @Override
    public boolean onTouchAction(Character character, StatusView view) {
        return false;
    }

    public boolean checkRequirements(Character character){

        return true;
    }
}
