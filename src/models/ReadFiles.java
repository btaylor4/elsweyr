package models;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFiles {

    private static GlobalLevel loadedMap;
    private static Character loadedCharacter;
    private static String loadedSlotDetails;

    public static void loadGame(String gameFile) throws IOException {
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
        size = getMatrixSize(currentLine.split(" "));

        local = new Zone[size.x][size.y];

        currentLine = reader.readLine();
        currentNum = Integer.parseInt(currentLine);

        // build map structure
        loadedMap = new GlobalLevel();
        loadedMap.setGlobalMap(local);
        loadedMap.setGameTime(currentNum);

        // build zones
        int i = 0;
        while (i < size.x * size.y) {
            Zone currentZone = new Zone();

            currentLine = reader.readLine();
            size = getMatrixSize(currentLine.split(" "));
            tiles = new Tile[size.x][size.y];
            currentZone.setLocalMap(tiles);

            currentLine = reader.readLine();
            size = getMatrixSize(currentLine.split(" "));
            currentZone.setExitTile(size);

            currentLine = reader.readLine();
            size = getMatrixSize(currentLine.split(" "));
            currentZone.setStartTile(size);

            currentLine = reader.readLine();
            currentZone.setPassable(trueOrFalse(currentLine, gameFile));

            currentLine = reader.readLine();
            Image image = new Image(currentLine);
            currentZone.setZoneSprite(image);

            int j = 0;
            while (j < local.length * local[0].length) {
                Tile tile = new Tile();
                String [] line;

                line = reader.readLine().split(" ");
                tile.setTerrain(getTerrain(line[0]));
        //      tile.setTileSprite(new Image("thisTilesTerrain.png"));

                for (int k = 0; k < Integer.parseInt(line[1]); ++k) {
                    line = reader.readLine().split(" ");
                    setUpTile(tile, line, gameFile);
                }

                tiles[j / tiles.length][j % tiles[0].length] = tile;
                ++j;
            }

            local[i / local.length][i % local[0].length] = currentZone;
            ++i;
        }
    }

    public static void loadCharacter(String characterFile, String avatarFile) {

    }

    public static void loadSaveSlotDetails(String detailsFile) {

    }

    private static Point getMatrixSize(String [] size) {
        assert size.length == 2;
        return new Point(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    }

    private static Terrain getTerrain(String terrain) {
        switch (terrain) {
            case "Grass":
                return Terrain.GRASS;
            case "Water":
                return Terrain.WATER;
            case "Mountain":
                return Terrain.MOUNTAIN;
            default:
                return Terrain.NONE;
        }
    }

    private static void setUpTile(Tile tile, String [] line, String gameFile) {

        if (line[0].equals("Effect")) {
            if (line[1].equals("Health")) {
                HealthEffect HE = new HealthEffect();
                HE.setEffectType(EffectType.HEALTHEFFECT);
                HE.setTimeInterval(Integer.parseInt(line[2]));
                HE.setHealthChange(Integer.parseInt(line[3]));
                HE.setEffectId(line[4]);
                tile.setEffectType(HE);
            } else if (line[1].equals("Level")) {
                LevelUpEffect LE = new LevelUpEffect();
                LE.setEffectType(EffectType.LEVELUPEFFECT);
                LE.setHasBeenActivated(trueOrFalse(line[2], gameFile));
                tile.setEffectType(LE);
            } else {
                System.out.println("Improper Area Effect Property @ " + gameFile + "should be Health or Level");
                assert false;
            }
        } else if (line[0].equals("Item")) {
            if (line[1].equals("Takeable")) {
                TakeableItem TI = new TakeableItem();
                TI.setName(line[2]);
        //      TI.setItemSprite(new Image("imageFile.png"));
                tile.setItem(TI);
            } else if (line[1].equals("Obstacle")) {
                ObstacleItem OI = new ObstacleItem();
                OI.setName(line[2]);
        //      OI.setItemSprite(new Image("imageFile.png"));
                tile.setItem(OI);
            } else if (line[1].equals("OneShot")) {
                OneShotItem OSI = new OneShotItem();
                OSI.setName(line[2]);
        //      OSI.setItemSprite(new Image("imageFile.png"));
                tile.setItem(OSI);
            } else if (line[1].equals("Interactive")) {
                InteractiveItem II = new InteractiveItem();
                II.setName(line[2]);
        //      II.setItemSprite(new Image("imageFile.png"));
                tile.setItem(II);
            } else {
                System.out.println("Improper Item Property @ " + gameFile + "should be Obstacle, Interactive, OneShot, or Takeable");
                assert false;
            }
        } else if (line[0].equals("Decal")) {
            Image decal = new Image(line[1]);
            tile.setDecal(decal);
        } else {
            System.out.println("Improper Tile Property @ " + gameFile + "should be Effect, Item, or Decal");
            assert false;
        }
    }

    private static boolean trueOrFalse(String line, String gameFile) {
        if (line.equals("0")) {
            return false;
        } else if (line.equals("1")) {
            return true;
        } else {
            System.out.println("Improper File Format @ " + gameFile + ", should be 0 or 1");
            assert false;
        }
        return false;
    }

}
