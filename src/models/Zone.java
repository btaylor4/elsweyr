package models;

import javafx.scene.image.Image;

import java.awt.*;

public class Zone {

    private Tile[][] localMap;
    private Point exitTile;
    private Point startTile;
    private Image zoneSprite;
    private boolean isPassable;

    public Tile[][] getLocalMap() {
        return localMap;
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
}
