package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Character;
import models.GlobalLevel;
import models.HealthEffect;
import views.*;

/**
 * Created by dontf on 2/9/2018.
 */
public class InGameMenuController {
    private InGameMenuView view;
    private Character character;
    private GlobalLevel map;

    public InGameMenuController(InGameMenuView inGameMenuView, Character character, GlobalLevel map) {
        view = inGameMenuView;
        this.view.addResumeGameButtonListener(new InGameMenuController.resumeButtonHandler());
        this.view.addSaveGameButtonListener(new InGameMenuController.saveButtonHandler());
        this.view.addExitListener(new InGameMenuController.exitButtonHandler());

        this.character = character;
        this.map = map;
    }


    class resumeButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Switch View To game play, either local or global
            System.out.println("Resume Game Buttonstuff");

            if (character.isOnLocal()) {
                LocalGameplayView localView = new LocalGameplayView(character.getCharacterSpritePath());
                Scene loadScene = new Scene(localView,500,500);
                LocalGameplayController localGameplayController = new LocalGameplayController(localView, character, map);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

                window.setTitle("Local Game");
                window.setScene(loadScene);
            } else {
                GlobalGameplayView globalView = new GlobalGameplayView(character.getCharacterSpritePath());
                Scene loadScene = new Scene(globalView,500,500);
                GlobalGameplayController globalGameplayController = new GlobalGameplayController(globalView, character, map);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Global Game");
                window.setScene(loadScene);
            }

        }
    }

    class saveButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Switch View To Save Game Menu
            System.out.println("Save Game Buttonstuff");


            SaveGameView saveView = new SaveGameView(character, map);
            Scene loadScene = new Scene(saveView,500,500);
            SaveGameController saveGameController = new SaveGameController(saveView, character, map);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Save Menu");
            window.setScene(loadScene);
        }
    }

    class exitButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Exit to main
            System.out.println("Exit Game Buttonstuff");

            MainMenuView mainMenuView = new MainMenuView();
            Scene loadScene = new Scene(mainMenuView,500,500);
            MainMenuController mainMenuController = new MainMenuController(mainMenuView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Main Menu");
            window.setScene(loadScene);
        }
    }
}
