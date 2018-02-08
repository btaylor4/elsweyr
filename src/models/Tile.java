package models;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tile {

    private Terrain terrain;
    private Image tileSprite;
    private AreaEffect effectType;
    private Item item;
    private Image decal;
    private String tileSpritePath;
    private Image decalSprite;
    private String decalSpritePath;

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

    public AreaEffect getAreaEffect() {
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

    public void setDecalSpritePath(String decalSpritePath) {
        this.decalSpritePath = decalSpritePath;
    }

    public void createDecalImage() throws FileNotFoundException {
        decalSprite = new Image(new FileInputStream(decalSpritePath));
    }

    public String getDecalSpritePath() {
        return decalSpritePath;
    }

    public void setTileSpritePath(String tileSpritePath) {
        this.tileSpritePath = tileSpritePath;
    }

    public void createTileImage() throws FileNotFoundException {
        tileSprite = new Image(new FileInputStream(tileSpritePath));
    }

    public String getTileSpritePath() {
        return tileSpritePath;
    }

}
