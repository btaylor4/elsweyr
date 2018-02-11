package views;

import controllers.InventoryController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.*;
import models.Character;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class InventoryViewTest extends ApplicationTest {

    private InventoryView view;
    private InventoryController controller;

    private Character character;
    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();
        view = new InventoryView();
        Scene localScene = new Scene(view, 500, 500);
        controller = new InventoryController(view, character, new GlobalLevel());
        window.setScene(localScene);
        window.show();
    }

    @Before
    public void init() {
        character = new Character();
        Inventory inventory = new Inventory();
        ArrayList<Item> items = new ArrayList<>();
        TakeableItem item0 = new TakeableItem();
        item0.setName("sword");
        item0.setItemSprite(new Image("file:PlaceHolderForImages/Takeable.png",100.,100., true,true));
        items.add(item0);
        TakeableItem item1 = new TakeableItem();
        item1.setName("dagger");
        item1.setItemSprite(new Image("file:PlaceHolderForImages/Takeable.png",100.,100., true,true));
        items.add(item1);
        inventory.setItems(items);
        character.setInventory(inventory);
    }

    @Test
    public void tryEquippingItem() {
        view.setSelectedItemIndex(1);
        view.getEquipButton().fire();
        assertTrue(controller.getCharacter().getEquippedItem().getName().equals("dagger"));
    }

    @Test
    public void tryUnEquippingItem() {
        view.setSelectedItemIndex(1);
        view.getEquipButton().fire();
        view.getUnEquipButton().fire();
        assertNull(controller.getCharacter().getEquippedItem());
    }

    @Test
    public void tryDroppingItem() {
        view.setSelectedItemIndex(1);
        view.getDropButton().fire();
        assertTrue(controller.getCharacter().getInventory().getItems().size() == 1);
    }

    @Test
    public void tryDroppingFirstItemAndEquippingSecondItem() {
        view.setSelectedItemIndex(0);
        view.getDropButton().fire();
        view.setSelectedItemIndex(0);
        view.getEquipButton().fire();
        assertTrue(controller.getCharacter().getEquippedItem().getName().equals("dagger"));
    }

    @Test
    public void tryExitingInventory() {
        view.getBackToGameButton().fire();
    }

}