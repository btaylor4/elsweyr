package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LoadGameView extends Parent {   //

    //Declare all the buttons and assets the Load Game view will need

    private Button backToMainButton = new Button("Back To Main Menu");
    Group root = new Group();
    Scene loadGameScene = new Scene(root,800,800);

    public LoadGameView(){


        //TODO: Check files and find any loaded games and add them to a dropdown menu
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        grid.add(backToMainButton,3,6);

        this.getChildren().add(grid);
    }


    public void addBackToMainListener(EventHandler<ActionEvent> handlerForBackButton){
        backToMainButton.setOnAction(handlerForBackButton);
    }

}
