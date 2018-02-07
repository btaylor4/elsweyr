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
        characterSaveFile.write(newString.format("%d ",(int)characterBeingSaved.getGlobalPos().getX()));
        characterSaveFile.write(newString.format("%d%n",(int)characterBeingSaved.getGlobalPos().getY()));
        characterSaveFile.write(newString.format("%s%n", characterBeingSaved.getCharacterSpritePath()));
        if(characterBeingSaved.getEquippedItem() == null)
        {
            characterSaveFile.write(newString.format("Equipped N/A%n"));
        }
        else
        {
            characterSaveFile.write(newString.format("Equipped %s%n",characterBeingSaved.getEquippedItem().getName()));

        }
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getInventory().getMaxSize()));
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getInventory().getItems().size()));

        ArrayList<String> itemNamesArrayList = characterBeingSaved.getInventory().getHashMapNameArray();

        for(int i = 0; i < characterBeingSaved.getInventory().getItems().size(); i++)
        {
            String nameOfItem = itemNamesArrayList.get(i);
            List<Item> temp = characterBeingSaved.getInventory().getItems().get(nameOfItem);
            characterSaveFile.write(newString.format("%s %d%n",nameOfItem ,temp.size()));

        }


        ArrayList<Buffs> characterBuffs = characterBeingSaved.getActiveBuffs();
        characterSaveFile.write(newString.format("%d%n",characterBeingSaved.getActiveBuffs().size()));

        for(Buffs buff : characterBuffs)
        {
            characterSaveFile.write(newString.format("%s ", buff.getHealthEffect().getEffectType()));
            characterSaveFile.write(newString.format("%d ", buff.getHealthEffect().getTimeInterval()));
            characterSaveFile.write(newString.format("%d ", buff.getHealthEffect().getHealthChange()));
            characterSaveFile.write(newString.format("%s%n", buff.getHealthEffect().getEffectId()));
            //characterSaveFile
        }
        characterSaveFile.close();
    }

    public void writeMapFile(GlobalLevel mapBeingSaved, int selectedSaveSlot) throws IOException
    {
        mapSaveFile = new FileWriter("mapSaveFile.txt");
        String newString = new String();

        Zone[][] globalMap = mapBeingSaved.getGlobalMap();

        //row
        mapSaveFile.write(newString.format("Map Size: %d ", globalMap.length));
        mapSaveFile.write(newString.format("%d%n", globalMap[0].length));
        mapSaveFile.write(newString.format("Game Time %d%n", mapBeingSaved.getGameTime()));


        /*Zone[] zone : globalMap
        for(int i = 0; i<globalMap.length; i++)
        {
            for(int j = 0)
            Tile[][] tile2DArray = zone.getLocalMap();

            mapSaveFile.write(newString.format("Zone Size: %d ", tile2DArray.length));
            mapSaveFile.write(newString.format("%d%n", globalMap[0].length));

            /*for(Tile tile: tile2DArray)
            {

            }*/
        //}

        mapSaveFile.close();
    }
}

