package models;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.*;

public class ReadFiles {

    private static final String IMAGE_PATH = "PlaceHolderForImages" + File.separator;

    private static String loadedSlotDetails;


    public static GlobalLevel loadGame(String gameFile) throws IOException {
        String currentLine;
        int currentNum;
        FileReader inStream;
        BufferedReader reader;
        Zone [][] local;
        Tile [][] tiles;
        Point size;

        inStream = new FileReader(gameFile);
        reader = new BufferedReader(inStream);

        currentLine = reader.readLine();
       // System.out.println(currentLine);
        size = getMatrixSize(currentLine.split(" "));

        local = new Zone[size.x][size.y];

        currentLine = reader.readLine();
        //System.out.println(currentLine);
        currentNum = Integer.parseInt(currentLine);

        //build map structure
        GlobalLevel loadedMap = new GlobalLevel();
        loadedMap.setGlobalMap(local);
        loadedMap.setGameTime(currentNum);

        // build zones
        for (int i = 0; i < local.length; ++i) {
            for (int r = 0; r < local[0].length; ++r) {

                Zone currentZone = new Zone();

                currentLine = reader.readLine();
                 //System.out.println(currentLine);
                size = getMatrixSize(currentLine.split(" "));
                tiles = new Tile[size.x][size.y];
                currentZone.setLocalMap(tiles);

                currentLine = reader.readLine();
                //System.out.println(currentLine);
                size = getMatrixSize(currentLine.split(" "));
                currentZone.setExitTile(size);

                currentLine = reader.readLine();
                //System.out.println(currentLine);
                size = getMatrixSize(currentLine.split(" "));
                currentZone.setStartTile(size);

                currentLine = reader.readLine();
                //System.out.println(currentLine);
                currentZone.setZoneSpritePath(currentLine);
                currentZone.createZoneImage();

                currentLine = reader.readLine();
                //System.out.println(currentLine);
                currentZone.setPassable(trueOrFalse(currentLine, gameFile));


                for (int j = 0; j < tiles.length; ++j) {
                    for (int l = 0; l < tiles[0].length; ++l) {

                        Tile tile = new Tile();
                        String[] line;

                        currentLine = reader.readLine();
                        //System.out.println(currentLine);
                        line = currentLine.split(" ");
                        tile.setTerrain(getTerrain(line[0]));
                        tile.setTileSpritePath(IMAGE_PATH + line[0] + ".png");
                        tile.createTileImage();

                        int tileAttributes = Integer.parseInt(line[1]);
                        for (int k = 0; k < tileAttributes; ++k) {
                            currentLine = reader.readLine();
                            //System.out.println(currentLine);
                            line = currentLine.split(" ");
                            setUpTile(tile, line, gameFile);
                        }

                        if (tile.getItem() == null) {
                            tile.setItem(new NoneItem());
                        }
                        if (tile.getAreaEffect() == null) {
                            tile.setEffectType(new NoneEffect());
                        }

                        //System.out.println(i + " i:r " + r);
                        //System.out.println(j + " j:l " + l);
                        tiles[j][l] = tile;
                    }
                }

                local[i][r] = currentZone;
            }
        }

        reader.close();
        inStream.close();

        return loadedMap;
    }

    public static Character loadCharacter(String characterFile) throws IOException {
        String currentLine;
        int currentNum;
        Point loc;
        FileReader inStream;
        BufferedReader reader;

        inStream = new FileReader(characterFile);
        reader = new BufferedReader(inStream);

        Character loadedCharacter = new Character();

        //character name
        currentLine = reader.readLine();
        loadedCharacter.setCharacterName(currentLine);

        //base hp
        currentLine = reader.readLine();
        loadedCharacter.setBaseHP(Integer.parseInt(currentLine));

        //current hp
        currentLine = reader.readLine();
        loadedCharacter.setCurrentHP(Integer.parseInt(currentLine));

        //bonus hp
        currentLine = reader.readLine();
        loadedCharacter.setBonusHP(Integer.parseInt(currentLine));

        //current exp
        currentLine = reader.readLine();
        loadedCharacter.setCurrExp(Integer.parseInt(currentLine));

        //next exp to level
        currentLine = reader.readLine();
        loadedCharacter.setExpToNextLevel(Integer.parseInt(currentLine));

        //level
        currentLine = reader.readLine();
        loadedCharacter.setLevel(Integer.parseInt(currentLine));

        //is player on local?
        currentLine = reader.readLine();
        loadedCharacter.setOnLocal(trueOrFalse(currentLine, characterFile));

        //local pos
        currentLine = reader.readLine();
        loc = getMatrixSize(currentLine.split(" "));
        loadedCharacter.updateLocalPos(loc);

        //global pos
        currentLine = reader.readLine();
        loc = getMatrixSize(currentLine.split(" "));
        loadedCharacter.updateGlobalPos(loc);

        //player image
        currentLine = reader.readLine();
        loadedCharacter.setCharacterSpritePath(currentLine);
        loadedCharacter.createCharacterImage();

        //equipped item
        currentLine = reader.readLine();
        loadedCharacter.setEquippedItem(getItem(currentLine.split(" ")[1]));

        //max inventory size
        currentLine = reader.readLine();
        Inventory in = new Inventory();
        in.setMaxSize(Integer.parseInt(currentLine));

        //fill inventory
        currentNum = Integer.parseInt(reader.readLine());
        for (int i = 0; i < currentNum; ++i) {
            String [] itemLine = reader.readLine().split(" ");
            int num = Integer.parseInt(itemLine[itemLine.length - 1]);

            for (int j = 0; j < num; ++j) {
                TakeableItem TI = new TakeableItem();

                TI.setName(getID(itemLine, 0, itemLine.length - 1));
                TI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));
                in.addItem(TI);
            }
        }

        //buff array size
        currentNum = Integer.parseInt(reader.readLine());

        for (int i = 0; i < currentNum; ++i) {
            currentLine = reader.readLine();
            loadedCharacter.addActiveBuff(addBuffsToChar(currentLine.split(" "), characterFile));
        }

        reader.close();
        inStream.close();

        return loadedCharacter;
    }

    private static Point getMatrixSize(String [] size) {
        assert size.length == 2;
        return new Point(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    }

    private static Terrain getTerrain(String terrain) {
        switch (terrain) {
            case "GRASS":
                return Terrain.GRASS;
            case "WATER":
                return Terrain.WATER;
            case "MOUNTAIN":
                return Terrain.MOUNTAIN;
            default:
                return Terrain.NONE;
        }
    }

    private static void setUpTile(Tile tile, String [] line, String gameFile) throws FileNotFoundException {

        if (line[0].equals("Effect")) {
            if (line[1].equals(EffectType.HEALTHEFFECT.name())) {
                HealthEffect HE = new HealthEffect();
                HE.setTimeInterval(Integer.parseInt(line[2]));
                HE.setHealthChange(Integer.parseInt(line[3]));
                HE.setEffectId(getID (line, 4));
                tile.setEffectType(HE);
            } else if (line[1].equals(EffectType.LEVELUPEFFECT.name())) {
                LevelUpEffect LE = new LevelUpEffect();
                LE.setHasBeenActivated(trueOrFalse(line[2], gameFile));
                tile.setEffectType(LE);
            } else {
                System.out.println("Improper Area Effect Property @ " + gameFile + "should be Health or Level");
                assert false;
            }
        } else if (line[0].equals("Item")) {
            if (line[1].equals(ItemType.TAKEABLE.name())) {
                TakeableItem TI = new TakeableItem();
                TI.setName(getID(line, 2, line.length - 1));
                TI.setItemSpritePath(line[line.length - 1]);
                TI.createItemImage();
                tile.setItem(TI);
            } else if (line[1].equals(ItemType.OBSTACLE.name())) {
                ObstacleItem OI = new ObstacleItem();
                OI.setName(getID(line, 2, line.length - 1));
                OI.setItemSpritePath(line[line.length - 1]);
                OI.createItemImage();
                tile.setItem(OI);
            } else if (line[1].equals(ItemType.ONESHOT.name())) {
                OneShotItem OSI = new OneShotItem();
                OSI.setName(getID(line, 2, line.length - 1));
                OSI.setItemSpritePath(line[line.length - 1]);
                OSI.createItemImage();
                tile.setItem(OSI);
            } else if (line[1].equals(ItemType.INTERACTIVE.name())) {
                InteractiveItem II = new InteractiveItem();
                II.setName(getID(line, 2, line.length - 1));
                II.setItemSpritePath(line[line.length - 1]);
                II.createItemImage();
                tile.setItem(II);
            } else {
                System.out.println("Improper Item Property @ " + gameFile + "should be Obstacle, Interactive, OneShot, or Takeable");
                assert false;
            }
        } else if (line[0].equals("Decal")) {
            tile.setDecalSpritePath(line[1]);
            tile.createDecalImage();
        } else {
            System.out.println("Improper Tile Property @ " + gameFile + "should be Effect, Item, or Decal");
            assert false;
        }

    }

    private static String getID(String [] id, int startPos) {
        String realID = "";

        for (int i = startPos; i < id.length; ++i) {
            if (i != id.length - 1) {
                realID += id[i] + " ";
            } else {
                realID += id[i];
            }
        }

        return realID;
    }

    private static String getID(String [] id, int startPos, int endPos) {
        String realID = "";

        for (int i = startPos; i < id.length && i < endPos; ++i) {
            if (i != id.length - 1 && i != endPos - 1) {
                realID += id[i] + " ";
            } else {
                realID += id[i];
            }
        }

        return realID;
    }

    private static boolean trueOrFalse(String line, String gameFile) {
        if (line.equals("0")) {
            return false;
        } else if (line.equals("1")) {
            return true;
        } else {
            System.out.println("Improper File Format @ " + gameFile + ", should be 0 or 1, but is: " + line);
            assert false;
        }
        return false;
    }

    private static Item getItem(String item) throws FileNotFoundException {
        if (item.equals(ItemType.NONE.name())) {
            return new NoneItem();
        } else {
            TakeableItem TI = new TakeableItem();
            TI.setItemSprite(new Image(new FileInputStream(IMAGE_PATH + "Takeable.png")));
            TI.setName(item);
            return TI;
        }
    }

    private static Buffs addBuffsToChar(String [] effect, String charcterFile) {
        Buffs B = new Buffs();
        if (effect[0].equals(EffectType.HEALTHEFFECT.name())) {
            HealthEffect HE = new HealthEffect();
            HE.setTimeInterval(Integer.parseInt(effect[1]));
            HE.setHealthChange(Integer.parseInt(effect[2]));
            HE.setEffectId(getID(effect, 3));
            B.setHealthEffect(HE);
            return  B;
        } else {
            System.out.println("Improper Effect @ " + charcterFile + " should be Health, but is: " + effect[0]);
            assert false;
        }
        return null;
    }
}
