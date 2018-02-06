package controllers;

import javafx.scene.input.KeyEvent;
import models.Character;
import models.GlobalLevel;
import models.Zone;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.GlobalGameplayView;


public class GlobalGameplayController {
    private Character character;
    private GlobalLevel map;
    private EventHandler<ActionEvent> localMovementListener;
    private GlobalGameplayView view;

    public GlobalGameplayController(GlobalGameplayView globalView, Character playerCharacter, GlobalLevel global) {
        this.character = playerCharacter;
        this.map = global;
        this.view = globalView;
        this.view.addKeyPressListener(new MovementHandler());
        this.view.addMenuButtonListener(new MenuButtonHandler());
        this.view.addChangeToLocalListener(new ChangeToLocalHandler());
    }

    class MovementHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            //Do Movement Stuff
            //move character around
            //if(e)
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

    class ChangeToLocalHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Do Menu stuff
            //switch into in-game menu
            System.out.println("Changing View To Local Level");
        }
    }
}
