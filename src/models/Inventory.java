package models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private ArrayList<Item> items;
    private int maxSize;

    public Inventory(){
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean addItem(Item item){
        //If the inventory is full the item cannot be added
        if(items.size() == maxSize) // if number of item types < max then user can have infinite of one or more types?
            return false;
        else{
            items.add(item);
        }
        return true;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public void updateCapactiy(int newMaxSize) {
        maxSize = newMaxSize;
    }

    public boolean hasItem(Item item){
        for(Item i : this.items){
            if(item.getName().equals(i.getName())){
                return true;
            }
        }
        return false;
    }
}