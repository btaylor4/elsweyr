package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainMenuView;
import views.LoadGameView;

public class MainMenuController {

    private MainMenuView view;

    public MainMenuController() {
        view = new MainMenuView();
        this.view.addLoadGameButtonListener(new MainMenuController.loadButtonHandler());
        this.view.addNewGameButtonListener(new MainMenuController.newButtonHandler());
        this.view.addExitListener(new MainMenuController.exitButtonHandler());

    }


    class loadButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Switch View To Load Game Menu
            System.out.println("Load Game Buttonstuff");
            LoadGameView loadView = new LoadGameView();
            LoadGameController loadController = new LoadGameController();
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            Scene loadScene = new Scene(loadView,500,500);
            window.setScene(loadScene);
        }
    }

    class newButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Switch View To New Game Menu
            System.out.println("New Game Buttonstuff");
        }
    }

    class exitButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Exit the Game
            System.out.println("Exit Game Buttonstuff");
        }
    }
}
