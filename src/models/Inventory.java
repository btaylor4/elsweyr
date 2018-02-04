package models;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
public class Inventory {

    private HashMap<String, List<Item>> items;
    private int maxSize;

    public boolean addItem(Item item){
        //If the inventory is full the item cannot be added
        if(items.size() == maxSize)
            return false;
        else{
            //Returns a list of the same items
            List<Item> sameItem = items.get(item.getName());

            /*
            If the list is empty, i.e. there are none of this item in the inventory.
            Therefore a list of that item is created and added to the inventory.
            */
            if(sameItem == null) {
                List<Item> temp = new ArrayList<Item>();
                temp.add(item);
                items.put(item.getName(),temp);
            }
            //There already exists a list of that item.
            else{
                sameItem.add(item);
                items.put(item.getName(), sameItem);
            }
        }
        return true;

    }

    public boolean removeItem(Item item) {
        List<Item> sameItem = items.get(item.getName());
        if(sameItem == null){
            return false;
        }
        //The item is removed from the array list.
        if(sameItem.size() > 1){
            sameItem.remove(1);
            return true;
        }
        //The list of the item is cleared and the itemlist is removed from the hashmap.
        sameItem.clear();
        items.remove(item.getName());
        return true;

    }

    public void updateCapactiy(int newMaxSize) {
        maxSize = newMaxSize;
    }

    public boolean hasItem(Item item){
        return null != items.get(item.getName());
    }

}
