package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.DeathView;
import views.MainMenuView;

public class DeathController {
    DeathView view;
    public DeathController(DeathView deathView){
        view = deathView;
        this.view.addExitListener(new DeathController.exitButtonHandler());
    }

    public

    class exitButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Exit to main


            MainMenuView mainMenuView = new MainMenuView();
            Scene loadScene = new Scene(mainMenuView,500,500);
            MainMenuController mainMenuController = new MainMenuController(mainMenuView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Main Menu");
            window.setScene(loadScene);
        }
    }
}
