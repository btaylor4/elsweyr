package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainMenuView;
import views.LoadGameView;
import views.NewGameView;

public class MainMenuController {

    private MainMenuView view;

    public MainMenuController(MainMenuView mainView) {
        view = mainView;
        this.view.addLoadGameButtonListener(new MainMenuController.loadButtonHandler());
        this.view.addNewGameButtonListener(new MainMenuController.newButtonHandler());
        this.view.addExitListener(new MainMenuController.exitButtonHandler());
    }


    class loadButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Switch View To Load Game Menu
            LoadGameView loadView = new LoadGameView();
            LoadGameController loadController = new LoadGameController(loadView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene loadScene = new Scene(loadView,500,500);
            window.setTitle("Load Game");
            window.setScene(loadScene);
        }
    }

    class newButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Switch View To Load Game Menu
            NewGameView newView = new NewGameView();
            NewGameController newController = new NewGameController(newView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene loadScene = new Scene(newView,500,500);
            window.setTitle("Create New Game");
            window.setScene(loadScene);
        }
    }

    class exitButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Exit the Game
            Platform.exit();
        }
    }
}
