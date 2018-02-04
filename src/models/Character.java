package models;

import javafx.scene.image.Image;

import java.awt.*;

public class Character {

    private int baseHP;
    private int currentHP;
    private int bonusHP;
    private int totalHP;
    private int currExp;
    private int expToNextLevel;
    private int level;
    private Inventory inventory;
    private Point globalPos;
    private Point localPos;
    private Item equippedItem;
    private Image characterSprite;
    private Buffs activeBuffs;

    public void updateHealth(int healthChange){
        currentHP += healthChange;
        //Character max currentHP is his/her TotalHP
        if(currentHP > totalHP)
            currentHP = totalHP;
        //Character min currentHP is 0.
        else if(currentHP < 0)
            currentHP = 0;
    }

    public void updateExp(int exp){
        currExp += exp;
    }

    public void updateLevel(){
        currExp -= expToNextLevel;
        level++;
        updateExpToNextLevel();
    }

    public void equip(Item item){
        equippedItem = item;
    }

    public void unEquip(Item item){

    }


    public void useEquipped(){

    }

    private void updateExpToNextLevel(){
        expToNextLevel *= 2;
    }


    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getBonusHP() {
        return bonusHP;
    }

    public void setBonusHP(int bonusHP) {
        this.bonusHP = bonusHP;
    }

    public int getTotalHP() {
        return totalHP;
    }

    public void setTotalHP(int totalHP) {
        this.totalHP = totalHP;
    }

    public int getCurrExp() {
        return currExp;
    }

    public void setCurrExp(int currExp) {
        this.currExp = currExp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Point getGlobalPos() {
        return globalPos;
    }

    public void updateGlobalPos(Point globalPos) {
        this.globalPos = globalPos;
    }

    public Point getLocalPos() {
        return localPos;
    }

    public void updateLocalPos(Point localPos) {
        this.localPos = localPos;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    public Image getCharacterSprite() {
        return characterSprite;
    }

    public void setCharacterSprite(Image characterSprite) {
        this.characterSprite = characterSprite;
    }

    public Buffs getActiveBuffs() {
        return activeBuffs;
    }

    public void setActiveBuffs(Buffs activeBuffs) {
        this.activeBuffs = activeBuffs;
    }
}
