package models;

import views.StatusView;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class OneShotBananaPeel extends OneShotItem{

    private  int healthIncrease;
    private String imageFile = "ArtAssets" + File.separator + "ItemImages" + File.separator + "BananaPeel.png";

    public OneShotBananaPeel() {
        this.healthIncrease = -3;
        this.setName("Banana Peel");
        this.setItemSpritePath(imageFile);
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For Banana Peel, it must have slipped away: looking for -> " + imageFile);
        }
    }

    @Override
    public boolean onTouchAction(Character character) {
        character.updateHealth(healthIncrease);
        return true;
    }

    @Override
    public boolean onTouchAction(Character character, StatusView view) {
        character.updateHealth(healthIncrease);
        view.updateCharacterHealth(healthIncrease);
        return true;
    }

    public void setHealthIncrease(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }

    public int getHealthIncrease() {
        return this.healthIncrease;
    }
}
