package models;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;
import java.awt.Point;


public class Write {

    private FileWriter characterSaveFile;
    private FileWriter mapSaveFile;

    public void writeCharacterFile( Character characterBeingSaved, int selectedSaveSlot) throws IOException
    {
        String newString = new String();

        characterSaveFile = new FileWriter("characterSaveFile.txt");

        characterSaveFile.write(newString.format("%d%n", characterBeingSaved.getBaseHP()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getCurrentHP()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getBonusHP()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getCurrExp()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getExpToNextLevel()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getLevel()));
        characterSaveFile.write(newString.format("%d ",(int)characterBeingSaved.getLocalPos().getX()));
        characterSaveFile.write(newString.format("%d%n",(int)characterBeingSaved.getLocalPos().getY()));
        characterSaveFile.write(newString.format("Equipped:%s%n",characterBeingSaved.getEquippedItem().getName()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getInventory().getMaxSize()));
        //characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getInventory().size()));
        //HashMap<String, List<Item>> itemsHashMap = characterBeingSaved.getInventory().getItems();

        //list the items in the hash map TBD

        /*//*List<Item> orangeItems  = itemsHashMap.get("oranges");
        List<Item> appleItems = itemsHashMap.get("apples");

        if(orange != NULL)   //if there are items in the inventory
        {
            characterSaveFile.write(orangeItems.size(), " oranges");
        }
        if(appleItems != NULL) {
            characterSaveFile.write(appleItems.size(), " apples");
        }*//*

        Buffs[] characterBuffs = characterBeingSaved.getActiveBuffs();

        characterSaveFile.write(characterBuffs.length);
        for(Buffs buff: characterBuffs)
        {
            characterSaveFile.write(buff.getHealthEffect.getEffectID());
            //characterSaveFile
        }*/
        characterSaveFile.close();
    }

    public void writeMapFile() throws IOException
    {
        mapSaveFile = new FileWriter("mapSaveFile.txt");
        mapSaveFile.close();
    }
}

