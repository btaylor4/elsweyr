package models;

import java.awt.*;

public class GlobalLevel {

    private Zone[][] globalMap;
    private int gameTime;

    public GlobalLevel()
    {
        globalMap = new Zone[10][10];
    }

    public GlobalLevel(int rows, int cols)   {
        globalMap = new Zone[rows][cols];
    }

    public Zone[][] getGlobalMap() {
        return globalMap;
    }

    public void setGlobalMap(Zone[][] globalMap) {
        this.globalMap = globalMap;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public Point getStartPosOfTile(Point levelCoord){
        return globalMap[levelCoord.x][levelCoord.y].getStartTile();
    }
}
