package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.GlobalLevel;
import models.Character;
import models.SaveFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class SaveGameView extends Parent{

    private Button saveSlotOneButton = new Button("Save Game Slot 1");
    private Button saveSlotTwoButton = new Button("Save Game Slot 2");
    private Button saveSlotThreeButton = new Button("Save Game 3 Slot 3");
    private Button saveButton = new Button("Confirm Save");
    private Button backToGameButton = new Button("Return to Game");
    //private ArrayList<SaveFile> savedGamesList = new ArrayList<SaveFile>();
    private Character character;
    private GlobalLevel map;

    public SaveGameView(Character character, GlobalLevel map){
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(backToGameButton,4,1);

        grid.add(saveSlotOneButton,4,3);
        grid.add(saveSlotTwoButton,4,5);
        grid.add(saveSlotThreeButton,4,7);

        grid.add(saveButton, 1,9);
        saveButton.setDisable(true);
        this.getChildren().add(grid);
        this.character = character;
        this.map = map;
    }

    public void addBackToGameListener(EventHandler<MouseEvent> handlerForBackToGameButton){
        backToGameButton.setOnMouseClicked(handlerForBackToGameButton);
    }

    public void addSaveSlotOneListener(EventHandler<MouseEvent> handlerForSaveSlot1Button){
        saveSlotOneButton.setOnMouseClicked(handlerForSaveSlot1Button);
    }

    public void addSaveSlotTwoListener(EventHandler<MouseEvent> handlerForSaveSlot2Button){
        saveSlotTwoButton.setOnMouseClicked(handlerForSaveSlot2Button);
    }

    public void addSaveSlotThreeListener(EventHandler<MouseEvent> handlerForSaveSlot3Button){
        saveSlotThreeButton.setOnMouseClicked(handlerForSaveSlot3Button);
    }

    public void addSaveListener(EventHandler<MouseEvent> handlerForSaveButton){
        saveButton.setOnMouseClicked(handlerForSaveButton);
    }
    public void enableSaveButton(){ saveButton.setDisable(false);}

    public Character getCharacter(){
        return character;
    }
    public GlobalLevel getMap(){
        return map;
    }
}
