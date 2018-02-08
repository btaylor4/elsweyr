package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.GlobalLevel;
import views.GlobalGameplayView;
import views.MainMenuView;
import views.NewGameView;
import models.Character;


public class NewGameController {


    private Character character;
    private NewGameView view;
    private GlobalLevel global;

    public NewGameController(NewGameView newView) {
        this.character = new Character();
        this.view = newView;
        this.global = new GlobalLevel();
        this.view.addBackToMainListener(new NewGameController.backToMainButtonHandler());
        this.view.addStartGaneListener(new NewGameController.startNewGameButtonHandler());
    }


    class startNewGameButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){

            //Set up custom character settings
            character.setCharacterSprite(view.getSelectedImage());
            character.setCharacterName(view.getSelectedName());

            //TODO: send view to Global gameplay view
            System.out.println("Go to global gameplay");
            GlobalGameplayView globalView = new GlobalGameplayView();
            GlobalGameplayController globalController = new GlobalGameplayController(globalView,character,global);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene globalScene = new Scene(globalView, 500, 500);

            window.setScene(globalScene);

        }


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
}
