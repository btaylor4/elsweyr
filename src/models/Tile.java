package models;

import java.awt.*;

public class Tile {

    private Terrain terrain;
    private Image tileSprite;
    private AreaEffect effectType;
    private Item item;
    private Image decal;

    public void removeItem(){
        item = null;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Image getTileSprite() {
        return tileSprite;
    }

    public void setTileSprite(Image tileSprite) {
        this.tileSprite = tileSprite;
    }

    public AreaEffect getEffectType() {
        return effectType;
    }

    public void setEffectType(AreaEffect effectType) {
        this.effectType = effectType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Image getDecal() {
        return decal;
    }

    public void setDecal(Image decal) {
        this.decal = decal;
    }
}
