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
import models.SaveFile;

import java.util.Date;


public class LoadGameView extends Parent {   //

    //Declare all the buttons and assets the Load Game view will need

    private Button backToMainButton = new Button("Back To Main Menu");
    private Button loadButton = new Button("Load Selected Game");
    private TableView<SaveFile> savedGamesTable = new TableView<>();
    Group root = new Group();
    private ObservableList<SaveFile> saves =
        FXCollections.observableArrayList(
            new SaveFile("dummyFile", "2018/12/5 12:00","SomePath"),
            new SaveFile("otherDummyFile", "2017/12/3-12:00)","SomeOtherPath"),
            new SaveFile("anotherDummyFile", "1999/12/31-12:00","SomeOtherOtherPath")
        );

    public LoadGameView(){
        //TODO: Check files and find any loaded games and add them to a dropdown menu
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        //Back To Main Menu Button
        grid.add(backToMainButton,4,1);
        //TODO: Set button to disabled until a table item is clicked
//        loadButton.setDisable(true);
        grid.add(loadButton,4,4);

        //Setting up the table view for the saved games
        savedGamesTable.setEditable(false);
        savedGamesTable.setMinWidth(400);

        TableColumn savedGameNames = new TableColumn("Saves");
        savedGameNames.setCellValueFactory( new PropertyValueFactory<>("fileName"));

        TableColumn lastPlayedDate = new TableColumn("Last Played");
        lastPlayedDate.setCellValueFactory( new PropertyValueFactory<>("dateLastPlayed"));

        TableColumn pathToFile = new TableColumn("File Path");
        pathToFile.setCellValueFactory( new PropertyValueFactory<>("pathToFile"));

        savedGamesTable.setItems(saves);
        savedGamesTable.getColumns().addAll(savedGameNames, lastPlayedDate,pathToFile);

        grid.add(savedGamesTable,4,0);
        this.getChildren().add(grid);
    }


    public void addBackToMainListener(EventHandler<ActionEvent> handlerForBackButton){
        backToMainButton.setOnAction(handlerForBackButton);
    }

    public void addTableClickListener(EventHandler<MouseEvent> handlerForTableMouseClick){

    }

    public SaveFile getSelectedFile(){
        return savedGamesTable.getSelectionModel().getSelectedItem();
    }


    public void addLoadGameListener(EventHandler<ActionEvent> handlerForLoadGame){
        loadButton.setOnAction(handlerForLoadGame);
    }



}
