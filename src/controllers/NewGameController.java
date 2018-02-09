package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.MainMenuView;
import views.NewGameView;

public class NewGameController {

    private NewGameView view;

    public NewGameController(NewGameView newView) {
        view = newView;
        this.view.addBackToMainListener(new NewGameController.backToMainButtonHandler());
        this.view.addCreateGameListener(new NewGameController.CreateGameHandler());
        this.view.addTableClickListener(new NewGameController.tableClickedEventHandler());
        this.view.addImageClickListener(new NewGameController.imageClickedEventHandler());
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

    class imageClickedEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            ImageView imageClicked = (ImageView) event.getSource();
            System.out.println(imageClicked.getAccessibleHelp());
        }
    }

    class CreateGameHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
/*            SaveFile file = view.getSelectedFile();
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
*/
            System.out.println("Create Game Button Clicked");
            System.out.println(view.getName());

/*
            GlobalGameplayView globalView = new GlobalGameplayView();

            System.out.println("Loading... " + file.getPathToMapFile());
            System.out.println("Loading... " + file.getPathToCharacterFile());
            GlobalGameplayController globalController = new GlobalGameplayController(globalView, character, map);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene globalScene = new Scene(globalView, 500, 500);
            window.setTitle("Global Level");
            window.setScene(globalScene); */

        }
    }
}
