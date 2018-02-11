package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;
import models.Character;
import views.GlobalGameplayView;
import views.InventoryView;
import views.LocalGameplayView;

import java.io.IOException;
import java.util.ArrayList;

public class InventoryController {

    private InventoryView view;
    private Character character;
    private GlobalLevel globalMap;


    public InventoryController(InventoryView invView, Character playerCharacter, GlobalLevel global) {
        this.character = playerCharacter;
        view = invView;
        invView.initializeSprites(getSprites());
        this.globalMap = global;

        this.view.addBackToGameListener(new InventoryController.backToGameButtonHandler());
        this.view.addEquipListener(new InventoryController.equipButtonHandler());
        this.view.addUnEquipListener(new InventoryController.unEquipButtonHandler());
        this.view.addDropListener(new InventoryController.dropButtonHandler());
    }

    public Character getCharacter() {
        return character;
    }

    class backToGameButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // TODO breaks tests can be fixed with platform runner
            System.out.println("Back To Game Buttonstuff");
            LocalGameplayView localView = new LocalGameplayView();
            Scene localScene = new Scene(localView, 500, 500);
            LocalGameplayController localController = new LocalGameplayController(localView, character, globalMap);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Local Level");
            window.setScene(localScene);
        }
    }

    class equipButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            character.setEquippedItem(character.getInventory().getItems().get(view.getSelectedItemIndex()));
        }
    }

    class unEquipButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            character.unEquip(character.getInventory().getItems().get(view.getSelectedItemIndex()));
        }
    }

    class dropButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            character.getInventory().getItems().remove(view.getSelectedItemIndex());
            // TODO breaks test can be fixed with platform runner
            view.getItemsFlow().getChildren().remove(view.getSelectedItemIndex());
            view.setSelectedItemIndex(-1);
            view.updateView(getSprites());
        }
    }

    private ArrayList<Image> getSprites() {
        ArrayList<Item> items = character.getInventory().getItems();
        ArrayList<Image> sprites = new ArrayList<>();
        for(Item item : items) {
            sprites.add(item.getItemSprite());
        }
        return sprites;
    }
}
