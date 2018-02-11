package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;
import models.Character;
import views.*;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SaveGameController {

    private SaveGameView view;
    private Character character;
    private GlobalLevel map;
    private int saveSlot;
    private static String FILE_PATH;


    public SaveGameController(SaveGameView saveView, Character character, GlobalLevel map) {

        this.character = character;
        this.map = map;
        view = saveView;
        this.view.addBackToGameListener(new backToGameButtonHandler());
        this.view.addSaveSlotOneListener(new saveSlotOneHandler());
        this.view.addSaveSlotTwoListener(new saveSlotTwoHandler());
        saveSlot = 0;
    }

    class backToGameButtonHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Back to the Game!");
            if (character.isOnLocal()) {
                LocalGameplayView localGameplayView = new LocalGameplayView();
                Scene localScene = new Scene(localGameplayView, 500, 500);
                LocalGameplayController localGameplayController = new LocalGameplayController(localGameplayView, character, map);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(localScene);
            } else { //(!character.isOnLocal())
                GlobalGameplayView globalGameplayView = new GlobalGameplayView();
                GlobalGameplayController globalGameplayController = new GlobalGameplayController(globalGameplayView, character, map);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene globalScene = new Scene(globalGameplayView, 500, 500);
                window.setScene(globalScene);
            }

        }
    }

    class saveSlotOneHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            saveSlot = 1;
            view.enableSaveButton();
        }
    }


    class saveSlotTwoHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            saveSlot = 2;
            view.enableSaveButton();
        }
    }

    class saveSlotThreeHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            saveSlot = 3;
            view.enableSaveButton();
        }
    }

    class saveGameHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            Write writeObject = new Write();
            FILE_PATH = "SaveSlot" + saveSlot + File.separator;
            try {
                writeObject.writeMapFile(FILE_PATH, map);
                //view.addSaveSlotThreeListener(new saveSlotThreeHandler());
                //view.addSaveListener(new saveGameHandler());
            } catch (IOException ex) {
                System.out.println("Map cannot be saved" + ex);
            }

            try {
                writeObject.writeCharacterFile(FILE_PATH, character);
            } catch (IOException ex) {
                System.out.println("Character cannot be saved " + ex);
            }
            //After save, the user will have to click Go Back to Game to return to the game
        }
    }
}