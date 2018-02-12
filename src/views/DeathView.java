package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DeathView extends Parent
{
    private Text deathMessage;
    private Button exitGameButton = new Button("Quit to Main Menu");
    public DeathView(){
        GridPane gridPane = new GridPane();
        deathMessage = new Text(100,100,"You died, sorry : (");

        gridPane.add(deathMessage,0,0);
        gridPane.add(exitGameButton,0,1);
        this.getChildren().add(gridPane);
    }

    public void addExitListener(EventHandler<ActionEvent> handlerForExitButton){
        exitGameButton.setOnAction(handlerForExitButton);
    }

}
