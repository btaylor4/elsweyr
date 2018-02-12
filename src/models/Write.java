package models;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Write {

    private FileWriter characterSaveFile;
    private FileWriter mapSaveFile;

    private static String FILE_PATH;

    public void writeCharacterFile( String filePath, Character characterBeingSaved) throws IOException
    {
        String newString = new String();

        characterSaveFile = new FileWriter(filePath + "DefaultCharacter.txt");

        characterSaveFile.write(newString.format("%s%n", characterBeingSaved.getCharacterName()));
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

        ArrayList<Item> items = characterBeingSaved.getInventory().getItems();

        for(int i = 0; i < items.size(); i++)
        {
            characterSaveFile.write(newString.format("%s %d%n",items.get(i).getName() ,1));
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

    public void writeMapFile(String filePath, GlobalLevel mapBeingSaved) throws IOException
    {
        mapSaveFile = new FileWriter(filePath + "DefaultMap.txt");
        String newString = new String();

        Zone[][] globalMap = mapBeingSaved.getGlobalMap();

        //row
        //global map size
        mapSaveFile.write(newString.format("%d %d%n", globalMap.length, globalMap[0].length));
        //game time
        mapSaveFile.write(newString.format("%d%n", mapBeingSaved.getGameTime()));


        for(int i = 0; i<globalMap.length; i++)
        {
            for(int j = 0; j<globalMap[i].length; j++)
            {
                //each zone
                Zone zone = globalMap[i][j];
                if(!zone.getHasLevel()){
                    //zone size
                    mapSaveFile.write(newString.format("%d %d%n", 0, 0));
                    //exit tile
                    mapSaveFile.write(newString.format("%d %d%n", (int)zone.getExitTile().getX(), (int)zone.getExitTile().getY()));
                    //start tile
                    mapSaveFile.write(newString.format("%d %d%n", (int)zone.getStartTile().getX(), (int)zone.getStartTile().getY()));
                    //zone sprite
                    mapSaveFile.write(newString.format("%s%n", zone.getZoneSpritePath()));
                    //is passable
                    if(!zone.isPassable()) {
                        mapSaveFile.write(newString.format("%d%n", 0));
                    }
                    else{
                        mapSaveFile.write(newString.format("%d%n", 1));
                    }
                }
                else {
                    Tile[][] zoneLocalMap = zone.getLocalMap();
                    //zone size
                    mapSaveFile.write(newString.format("%d %d%n", zoneLocalMap.length, zoneLocalMap[0].length));
                    //exit tile
                    mapSaveFile.write(newString.format("%d %d%n", (int)zone.getExitTile().getX(), (int)zone.getExitTile().getY()));
                    //start tile
                    mapSaveFile.write(newString.format("%d %d%n", (int)zone.getStartTile().getX(), (int)zone.getStartTile().getY()));
                    //zone sprite
                    mapSaveFile.write(newString.format("%s%n", zone.getZoneSpritePath()));
                    //is passable
                    if(!zone.isPassable()) {
                        mapSaveFile.write(newString.format("%d%n", 0));
                    }
                    else {
                        mapSaveFile.write(newString.format("%d%n", 1));
                    }

                    for(int u = 0; u<zoneLocalMap.length; u++)
                    {
                        for(int h = 0; h<zoneLocalMap[u].length; h++)
                        {
                            //each tile
                            Tile tile = zoneLocalMap[u][h];
                            int numberOfLinesForEachTile = 0;
                            if(!tile.getAreaEffect().getEffectType().equals(EffectType.NONE)) {
                                numberOfLinesForEachTile++;
                            }
                            if(!tile.getItem().getItemType().equals(ItemType.NONE)) {
                                numberOfLinesForEachTile++;
                            }
                            if(tile.getDecalSpritePath() != null) {
                                numberOfLinesForEachTile++;
                            }
                            //tile terrain
                            mapSaveFile.write(newString.format("%s %d %n", tile.getTerrain(), numberOfLinesForEachTile));

                            //AreaEffects

                            //new stuff
                            if(!tile.getAreaEffect().getEffectType().equals(EffectType.NONE)) {
                                //area effect
                                mapSaveFile.write(newString.format("Effect %s ", tile.getAreaEffect().getEffectType()));
                                if (tile.getAreaEffect().getEffectType().equals(EffectType.HEALTHEFFECT)) {
                                    HealthEffect tileEffect = (HealthEffect) tile.getAreaEffect();
                                    mapSaveFile.write(newString.format("%d %d %s%n", tileEffect.getTimeInterval(), tileEffect.getHealthChange(), tileEffect.getEffectId()));
                                } else if (tile.getAreaEffect().getEffectType().equals(EffectType.LEVELUPEFFECT)) {
                                    LevelUpEffect tileEffect = (LevelUpEffect) tile.getAreaEffect();
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
                                    //Takeable Item
                                    mapSaveFile.write(newString.format("Item TAKEABLE %s%n", tileItem.getName()));
                                } else if (tile.getItem().getItemType().equals(ItemType.INTERACTIVE)) {
                                    InteractiveItem tileItem = (InteractiveItem) tile.getItem();
                                    //Interactive Item
                                    mapSaveFile.write(newString.format("Item INTERACTIVE %s%n", tileItem.getName()));
                                } else if (tile.getItem().getItemType().equals(ItemType.OBSTACLE)) {
                                    ObstacleItem tileItem = (ObstacleItem) tile.getItem();
                                    //Obstacle Item
                                    mapSaveFile.write(newString.format("Item OBSTACLE %s%n", tileItem.getName()));
                                } else if (tile.getItem().getItemType().equals(ItemType.ONESHOT)) {
                                    OneShotItem tileItem = (OneShotItem) tile.getItem();
                                    //OneShot Item
                                    mapSaveFile.write(newString.format("Item ONESHOT %s%n", tileItem.getName()));
                                }
                            }
                            if(tile.getDecalSpritePath() != null) {
                                //Decal
                                mapSaveFile.write(newString.format("Decal %s%n", tile.getDecalSpritePath()));

                            }
                        }
                    }
                }

            }
        }

        mapSaveFile.close();
    }
}

