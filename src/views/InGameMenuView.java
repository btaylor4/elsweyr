package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Created by dontf on 2/9/2018.
 */
public class InGameMenuView extends Parent {
    //Initialize all the buttons and assets the main menu view will need
    private Button resumeGameButton = new Button("Resume Game");
    private Button saveGameButton = new Button("New Game");
    private Button exitGameButton = new Button("Quit to Main Menu");
    Group root = new Group();

    public InGameMenuView(){

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));


        grid.add(resumeGameButton,3,1);
        grid.add(saveGameButton, 3,2);
        grid.add(exitGameButton,3,4);

        this.getChildren().add(grid);
    }

    public void addResumeGameButtonListener(EventHandler<ActionEvent> handlerForResumeButton){
        resumeGameButton.setOnAction(handlerForResumeButton);
    }

    public void addSaveGameButtonListener(EventHandler<ActionEvent> handlerForSaveGameButton){
        saveGameButton.setOnAction(handlerForSaveGameButton);
    }

    public void addExitListener(EventHandler<ActionEvent> handlerForExitButton){
        exitGameButton.setOnAction(handlerForExitButton);
    }
}
