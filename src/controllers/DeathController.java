package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.DeathView;
import views.MainMenuView;
//Controls the deathview, and return to the main menu button.
public class DeathController {
    DeathView view;
    public DeathController(DeathView deathView){
        view = deathView;
        this.view.addExitListener(new DeathController.exitButtonHandler());
    }

    //Handles a check on the main menu bottom. Sends the user to the main menu.
    public class exitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            MainMenuView mainMenuView = new MainMenuView();
            Scene loadScene = new Scene(mainMenuView,500,500);
            MainMenuController mainMenuController = new MainMenuController(mainMenuView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Main Menu");
            window.setScene(loadScene);
        }
    }
}
