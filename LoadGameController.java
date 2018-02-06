package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainMenuView;
import views.LoadGameView;

public class LoadGameController {

    private LoadGameView view;

    public LoadGameController() {
        view = new LoadGameView();
        this.view.addBackToMainListener(new LoadGameController.backToMainButtonHandler());
    }




    class backToMainButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //TODO: Send view back to main menu
            System.out.println("Back To Main Menu Buttonstuff");
        }
    }
}
