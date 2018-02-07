package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.GlobalLevel;
import models.ReadFiles;
import models.SaveFile;
import models.Character;
import views.GlobalGameplayView;
import views.MainMenuView;
import views.LoadGameView;

import java.io.IOException;

public class LoadGameController {

    private LoadGameView view;

    public LoadGameController(LoadGameView loadView) {
        view = loadView;
        this.view.addBackToMainListener(new LoadGameController.backToMainButtonHandler());
        this.view.addLoadGameListener(new LoadGameController.loadGameHandler());
        this.view.addTableClickListener(new tableClickedEventHandler());
    }




    class backToMainButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Back To Main Menu Buttonstuff");
            MainMenuView mainView = new MainMenuView();
            MainMenuController mainController = new MainMenuController(mainView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(mainView, 500, 500);
            window.setTitle("Main Menu");
            window.setScene(mainScene);
        }
    }

    class tableClickedEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            view.enableLoadButton();
        }
    }

    class loadGameHandler implements EventHandler<ActionEvent> {
    // TODO: Figure out whether to load into local gameplay or global gameplay,right now its just loading into global
        @Override
        public void handle(ActionEvent event) {
            SaveFile file = view.getSelectedFile();
            Character character;
            GlobalLevel map;
            try {
                character = ReadFiles.loadCharacter(file.getPathToCharacterFile());
                map = ReadFiles.loadGame(file.getPathToMapFile());
            }
            catch(IOException e){
                System.out.println("No game associated with this file name");
                return;
            }

            System.out.println("Load into game Button");
            GlobalGameplayView globalView = new GlobalGameplayView();

            System.out.println("Loading... " + file.getPathToMapFile());
            System.out.println("Loading... " + file.getPathToCharacterFile());
            GlobalGameplayController globalController = new GlobalGameplayController(globalView,character,map);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene globalScene = new Scene(globalView,500,500);
            window.setTitle("Global Level");
            window.setScene(globalScene);
        }
    }

}