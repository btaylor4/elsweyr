package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Character;
import models.GlobalLevel;
import models.ReadFiles;
import models.SaveFile;
import views.GlobalGameplayView;
import views.MainMenuView;
import views.NewGameView;

import java.io.IOException;

public class NewGameController {

    private NewGameView view;
    private  String newMap = "TheMap.txt";
    private  String newCharacter = "TheCharacter.txt";

    public NewGameController(NewGameView newView) {
        view = newView;
        this.view.addBackToMainListener(new NewGameController.backToMainButtonHandler());
        this.view.addCreateGameListener(new NewGameController.CreateGameHandler());
        this.view.addTableClickListener(new NewGameController.tableClickedEventHandler());
    }


    class backToMainButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //TODO: Send view back to main menu
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
            view.enableCreateButton();
        }
    }

    class CreateGameHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            SaveFile file = view.getSelectedFile();
            Character character;
            GlobalLevel map;

            //copyAndRenameFiles(view.getName());

            try {
                character = ReadFiles.loadCharacter(newCharacter);
                map = ReadFiles.loadGame(newMap);
            }
            catch(IOException e){
                System.out.println("Default game files not found.");
                return;
            }

            System.out.println("Create Game Button Clicked");




            GlobalGameplayView globalView = new GlobalGameplayView();

            System.out.println("Loading... " + newMap);
            System.out.println("Loading... " + newCharacter);
            GlobalGameplayController globalController = new GlobalGameplayController(globalView, character, map);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene globalScene = new Scene(globalView, 500, 500);
            window.setTitle("Global Level");
            window.setScene(globalScene);

        }
    }

}
