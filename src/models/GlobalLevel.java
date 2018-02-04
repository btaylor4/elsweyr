package models;

public class GlobalLevel {

    private Zone[][] globalMap;
    private int gameTime;

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
}
