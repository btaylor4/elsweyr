package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.GlobalLevel;
import models.SaveFile;
import models.Character;
import views.GlobalGameplayView;
import views.MainMenuView;
import views.LoadGameView;

public class LoadGameController {

    private LoadGameView view;

    public LoadGameController(LoadGameView loadView) {
        view = loadView;
        this.view.addBackToMainListener(new LoadGameController.backToMainButtonHandler());
        this.view.addLoadGameListener(new LoadGameController.loadGameHandler());
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

        }
    }

    class loadGameHandler implements EventHandler<ActionEvent> {
    // TODO: Figure out whether to load into local gameplay or global gameplay,right now its just loading into global
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Load into game Button");
            GlobalGameplayView globalView = new GlobalGameplayView();

            //TODO: load the character and map from the file corresponding to the selected object in the tableview
            SaveFile file = view.getSelectedFile();
//            Character character = loadCharacter(file.getPathToFile());
//            GlobalLevel map = loadMap(file.getPathToFile());

            //TODO: remove this once loading is set up
            Character character = new Character();
            GlobalLevel globalMap = new GlobalLevel();


            System.out.println("Loading... " + file.getFileName());
            GlobalGameplayController globalController = new GlobalGameplayController(globalView,character,globalMap);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene globalScene = new Scene(globalView,500,500);
            window.setTitle("Global Level");
            window.setScene(globalScene);
        }
    }

}
