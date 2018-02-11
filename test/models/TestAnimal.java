package models;

import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestAnimal {

    private static Character testCharacter;
    private static Inventory testInventory;
    private static Food testFood1;
    private static Food testFood2;
    private static TakeableItem testItem1;

    @BeforeClass
    public static void setUpCharacter() throws FileNotFoundException{
        testCharacter = new Character();
        testInventory =  new Inventory();
        testFood1 = new Food();
        testFood2 = new Food();
        testItem1 = new TakeableItem();
        testItem1.setName("phone");

        testInventory.addItem(testFood1);
        //System.out.println(testFood1.getName());
        testInventory.addItem(testFood2);
        //System.out.println(testFood1.getName());
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem1);
        testCharacter.setInventory(testInventory);
    }

    @Test
    public void testAnimalInteractiveItemReturnsTrue() throws IOException {

        testCharacter = new Character();
        testInventory =  new Inventory();
        testFood1 = new Food();
        testFood2 = new Food();
        testItem1 = new TakeableItem();
        testItem1.setName("phone");

        testInventory.setMaxSize(12);

        testInventory.addItem(testFood1);
        //System.out.println(testFood1.getName());
        testInventory.addItem(testFood2);
        //System.out.println(testFood1.getName());
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem1);
        testCharacter.setInventory(testInventory);

        Animal testAnimal = new Animal();
        System.out.println("TeStOnE");
        //System.out.println(testAnimal.getName());
        Assert.assertEquals(true, testAnimal.onTouchAction(testCharacter));
    }

    @Test
    public void testAnimalInteractiveItemReturnsFalse() throws IOException  {
        Animal testAnimal = new Animal();
        testInventory.removeItem(testFood1);
        testInventory.removeItem(testFood2);

        testCharacter.setInventory(testInventory);

        Assert.assertEquals(false, testAnimal.onTouchAction(testCharacter));
    }

}
