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


public class MainMenuView extends Parent {   //

    //Initialize all the buttons and assets the main menu view will need
    private Button loadGameButton = new Button("Load Game");
    private Button newGameButton = new Button("New Game");
    private Button exitGameButton = new Button("Quit to Desktop");
    Group root = new Group();

    public MainMenuView(){

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));


        grid.add(loadGameButton,3,1);
        grid.add(newGameButton, 3,2);
        grid.add(exitGameButton,3,4);

        this.getChildren().add(grid);
    }

    public void addLoadGameButtonListener(EventHandler<ActionEvent> handlerForLoadButton){
        loadGameButton.setOnAction(handlerForLoadButton);
    }

    public void addNewGameButtonListener(EventHandler<ActionEvent> handlerForNewGameButton){
        newGameButton.setOnAction(handlerForNewGameButton);
    }

    public void addExitListener(EventHandler<ActionEvent> handlerForExitButton){
        exitGameButton.setOnAction(handlerForExitButton);
    }


}
