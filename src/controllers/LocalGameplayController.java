package controllers;

import javafx.scene.input.KeyEvent;
import models.Character;
import models.GlobalLevel;
import models.Zone;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.LocalGameplayView;


public class LocalGameplayController {
    private Character character;
    private Zone map;
    private EventHandler<ActionEvent> localMovementListener;
    private EventHandler<ActionEvent> inventoryListener;
    private LocalGameplayView view;

    public LocalGameplayController(LocalGameplayView localView, Character playerCharacter, Zone global) {
        this.character = playerCharacter;
        this.map = global;
        this.view = localView;
        this.view.addKeyPressListener(new MovementHandler());
        this.view.addMenuButtonListener(new MenuButtonHandler());
        this.view.addInventoryButtonListener(new InvButtonHandler());
    }

    class MovementHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            //Do Movement Stuff
            //move character around
//            if(e)
            System.out.println("Movement handler stuff");
            character.getLocalPos();

        }
    }

    class MenuButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do Menu stuff
            //switch into in-game menu
            System.out.println("menu Buttonstuff");
        }
    }

    class InvButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do inv menu stuff
            //switch to inventory view
            System.out.println("InvButton Stuff");
        }
    }
}
