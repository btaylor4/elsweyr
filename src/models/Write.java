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
        if(characterBeingSaved.isOnLocal()) {
            characterSaveFile.write(newString.format("%d%n",1));
        }
        else{
            characterSaveFile.write(newString.format("%d%n",0));
        }
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
        mapSaveFile.write(newString.format("%d %d%n", globalMap.length, globalMap[0].length));
        mapSaveFile.write(newString.format("%d%n", mapBeingSaved.getGameTime()));


        for(int i = 0; i<globalMap.length; i++)
        {
            for(int j = 0; j<globalMap[i].length; j++)
            {
                //each zone
                Zone zone = globalMap[i][j];
                Tile[][] zoneLocalMap = zone.getLocalMap();
                mapSaveFile.write(newString.format("%d %d%n", zoneLocalMap.length, zoneLocalMap[0].length));
                mapSaveFile.write(newString.format("%d %d%n", (int)zone.getExitTile().getX(), (int)zone.getExitTile().getY()));
                mapSaveFile.write(newString.format("%d %d%n", (int)zone.getStartTile().getX(), (int)zone.getStartTile().getY()));
                mapSaveFile.write(newString.format("%s%n", zone.getZoneSpritePath()));
                if(!zone.isPassable()) {
                    mapSaveFile.write(newString.format("%d%n", 0));
                }
                else {
                    mapSaveFile.write(newString.format("%b%n", 1));
                }

                for(int u = 0; u<zoneLocalMap.length; u++)
                {
                    for(int h = 0; h<zoneLocalMap[u].length; h++)
                    {
                        //each tile
                        Tile tile = zoneLocalMap[u][h];
                        int numberOfLinesForEachTile = 0;
                        if(!tile.getEffectType().getEffectType().equals(EffectType.NONE)) {
                            numberOfLinesForEachTile++;
                        }
                        if(!tile.getItem().getItemType().equals(ItemType.NONE)) {
                            numberOfLinesForEachTile++;
                        }
                        if(tile.getDecalSpritePath() != null) {
                            numberOfLinesForEachTile++;
                        }

                        mapSaveFile.write(newString.format("%s %d %n", tile.getTerrain(), numberOfLinesForEachTile));

                        //AreaEffects
                        mapSaveFile.write(newString.format("%s ", tile.getEffectType().getEffectType()));
                        //new stuff
                        if(!tile.getEffectType().getEffectType().equals(EffectType.NONE)) {
                            if (tile.getEffectType().getEffectType().equals(EffectType.HEALTHEFFECT)) {
                                HealthEffect tileEffect = (HealthEffect) tile.getEffectType();
                                mapSaveFile.write(newString.format("%d %d %s%n", tileEffect.getTimeInterval(), tileEffect.getHealthChange(), tileEffect.getEffectId()));
                            } else if (tile.getEffectType().getEffectType().equals(EffectType.LEVELUPEFFECT)) {
                                LevelUpEffect tileEffect = (LevelUpEffect) tile.getEffectType();
                                if (tileEffect.hasBeenActivated()) {
                                    mapSaveFile.write(newString.format("%d%n", 1));
                                } else {
                                    mapSaveFile.write(newString.format("%d%n", 0));
                                }
                            }
                        }
                        if(!tile.getItem().getItemType().equals(ItemType.NONE)) {
                            //Item
                            if (tile.getItem().getItemType().equals(ItemType.TAKEABLE)) {
                                TakeableItem tileItem = (TakeableItem) tile.getItem();
                                mapSaveFile.write(newString.format("Takeable %s %n", tileItem.getName(), tileItem.getItemSpritePath()));
                            } else if (tile.getItem().getItemType().equals(ItemType.INTERACTIVE)) {
                                InteractiveItem tileItem = (InteractiveItem) tile.getItem();
                                mapSaveFile.write(newString.format("Interactive Item %s %n", tileItem.getName(), tileItem.getItemSpritePath()));
                            } else if (tile.getItem().getItemType().equals(ItemType.OBSTACLE)) {
                                ObstacleItem tileItem = (ObstacleItem) tile.getItem();
                                mapSaveFile.write(newString.format("Obstacle Item %s %n", tileItem.getName(), tileItem.getItemSpritePath()));
                            } else if (tile.getItem().getItemType().equals(ItemType.ONESHOT)) {
                                OneShotItem tileItem = (OneShotItem) tile.getItem();
                                mapSaveFile.write(newString.format("OneShot %s %n", tileItem.getName(), tileItem.getItemSpritePath()));
                            }
                        }
                        if(tile.getDecalSpritePath() != null) {
                            mapSaveFile.write(newString.format("%s%n", tile.getDecalSpritePath()));

                        }
                    }
                }
            }
        }

        mapSaveFile.close();
    }
}

