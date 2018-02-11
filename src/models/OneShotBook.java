package models;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dontf on 2/11/2018.
 */
public class OneShotBook extends OneShotItem{

    private  int expIncrease;
    private String imageFile = "ArtAssets" + File.separator + "ItemImages" + File.separator + "Book.png";

    public OneShotBook() {
        this.expIncrease = 10;
        this.setName("Book");
        this.setItemSpritePath(imageFile);
        try {
            this.createItemImage();
        } catch (FileNotFoundException e) {
            System.out.println("No Image File For Book: looking for -> " + imageFile);
        }
    }

    @Override
    public boolean onTouchAction(Character character) {
        character.updateExp(expIncrease);
        return true;
    }

    public void setExpIncrease(int expIncrease) {
        this.expIncrease = expIncrease;
    }

    public int getExpIncrease() {
        return this.expIncrease;
    }
}
