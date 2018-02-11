package models;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Zone {

    private Tile[][] localMap;
    private Point exitTile = new Point(0,0);
    private Point startTile = new Point(0,0);
    private Image zoneSprite;
    private String zoneSpritePath;
    private boolean isPassable;
    private boolean hasLevel;

    public Zone() {
    }

    public Zone(int row, int col)  {
        localMap = new Tile[row][col];
    }

    public Tile[][] getLocalMap() {
        return localMap;
    }

    public void setZoneSpritePath(String zoneSpritePath)
    {
        this.zoneSpritePath = zoneSpritePath;
    }

    public void createZoneImage() throws FileNotFoundException
    {
        zoneSprite = new Image(new FileInputStream(zoneSpritePath));
    }

    public String getZoneSpritePath()
    {
        return zoneSpritePath;
    }

    public void setLocalMap(Tile[][] localMap) {
        this.localMap = localMap;
    }

    public Point getExitTile() {
        return exitTile;
    }

    public void setExitTile(Point exitTile) {
        this.exitTile = exitTile;
    }

    public Point getStartTile() {
        return startTile;
    }

    public void setStartTile(Point startTile) {
        this.startTile = startTile;
    }

    public Image getZoneSprite() {
        return zoneSprite;
    }

    public void setZoneSprite(Image zoneSprite) {
        this.zoneSprite = zoneSprite;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public boolean getHasLevel() {
        return hasLevel;
    }

    public void setHasLevel(boolean hasLevel) {
        this.hasLevel = hasLevel;
    }
}
