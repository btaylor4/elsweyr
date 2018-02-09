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

import java.io.File;
import java.util.Date;


public class LoadGameView extends Parent {   //

    //Declare all the buttons and assets the Load Game view will need

    private Button backToMainButton = new Button("Back To Main Menu");
    private Button loadButton = new Button("Load Selected Game");
    private TableView<SaveFile> savedGamesTable = new TableView<>();
    Group root = new Group();
    private ObservableList<SaveFile> saves = FXCollections.observableArrayList();

    public LoadGameView(){
        //TODO: Check files and find any loaded games and add them to a dropdown menu
        setUpSaves();

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        //Back To Main Menu Button
        grid.add(backToMainButton,4,1);

        loadButton.setDisable(true);
        grid.add(loadButton,4,4);

        //Setting up the table view for the saved games
        savedGamesTable.setEditable(false);
        savedGamesTable.setMinWidth(450);

        TableColumn savedGameNames = new TableColumn("Saves");
        savedGameNames.setCellValueFactory( new PropertyValueFactory<>("fileName"));

        TableColumn lastPlayedDate = new TableColumn("Last Played");
        lastPlayedDate.setCellValueFactory( new PropertyValueFactory<>("dateLastPlayed"));

        TableColumn pathToFile = new TableColumn("Map File Path");
        pathToFile.setCellValueFactory( new PropertyValueFactory<>("pathToMapFile"));

        savedGamesTable.setItems(saves);
        savedGamesTable.getSelectionModel().selectFirst();
        savedGamesTable.getColumns().addAll(savedGameNames, lastPlayedDate, pathToFile);

        grid.add(savedGamesTable,4,0);
        this.getChildren().add(grid);
    }


    public void addBackToMainListener(EventHandler<ActionEvent> handlerForBackButton){
        backToMainButton.setOnAction(handlerForBackButton);
    }

    public void addTableClickListener(EventHandler<MouseEvent> handlerForTableMouseClick){
        savedGamesTable.setOnMouseClicked(handlerForTableMouseClick);
    }

    public void enableLoadButton(){
        loadButton.setDisable(false);
    }

    public SaveFile getSelectedFile(){
        return savedGamesTable.getSelectionModel().getSelectedItem();
    }

    public void addLoadGameListener(EventHandler<ActionEvent> handlerForLoadGame){
        loadButton.setOnAction(handlerForLoadGame);
    }

    private void setUpSaves() {
        File folder;
        File [] files;
        String saveSlot = "SaveSlot";

        for (int i = 1; i < 4; ++i) {
            folder = new File (saveSlot + i + File.separator);
            files = folder.listFiles();

            if (files.length == 2) {
                Date lastPlayed = new Date (files[0].lastModified());
                // file name = playerName + Map + .txt -> ignore "Map.txt"
                if (files[0].getName().contains("Map")) {
                    saves.add(new SaveFile("save" + i, "" + lastPlayed.toString(), files[1].getName(), files[0].getName()));
                } else {
                    saves.add(new SaveFile("save" + i, "" + lastPlayed.toString(), files[0].getName(), files[1].getName()));
                }
            } else {
                saves.add(new SaveFile("Empty", "0/0/0 00:00", "No File", "No File"));
            }
        }
    }


}
