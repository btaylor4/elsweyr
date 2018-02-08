package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class NewGameView extends Parent {

    //Declare all the buttons and assets the Load Game view will need

    private Button backToMainButton = new Button("Back To Main Menu");
    Group root = new Group();

    public NewGameView(){

        //TODO: Add options for which slot to save to
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