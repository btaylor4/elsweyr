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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.SaveFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;


public class NewGameView extends Parent {

    //Declare all the buttons and assets the New Game view will need
    private final String AVATAR_PATH = "PlaceHolderForImages" + File.separator;

    private Button backToMainButton = new Button("Back To Main Menu");
    private Button createButton = new Button("Create New Game");
    private TextField name = new TextField("Player's Name");
    private ImageView [] avatar = new ImageView[3];
    private TableView<SaveFile> savedGamesTable = new TableView<>();
    Group root = new Group();
    private ObservableList<SaveFile> saves = FXCollections.observableArrayList();

    public NewGameView(){

        //TODO: Add options for which slot to save to
        setUpSaves();
        setUpAvaterImages();

        GridPane grid = new GridPane();
        grid.gridLinesVisibleProperty();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        if (avatar[0] != null) {
            for (int i = 0; i < 3; ++i) {
                avatar[i].setFitWidth(30);
                avatar[i].setFitHeight(30);
                grid.add(avatar[i], 4 + i, 2);
            }
        }

        grid.add(backToMainButton,4,5);

        name.setMaxWidth(150);
        grid.add(name, 5, 0);

        createButton.setDisable(true);
        grid.add(createButton,4,7);

        //Setting up the table view for the saved games
        savedGamesTable.setEditable(false);
        savedGamesTable.setMinWidth(400);
        savedGamesTable.setMaxHeight(100);

        TableColumn savedGameNames = new TableColumn("Saves");
        savedGameNames.setCellValueFactory( new PropertyValueFactory<>("fileName"));

        TableColumn lastPlayedDate = new TableColumn("Last Played");
        lastPlayedDate.setCellValueFactory( new PropertyValueFactory<>("dateLastPlayed"));

        TableColumn pathToFile = new TableColumn("Map File Path");
        pathToFile.setCellValueFactory( new PropertyValueFactory<>("pathToMapFile"));

        savedGamesTable.setItems(saves);
        savedGamesTable.getSelectionModel().selectFirst();
        savedGamesTable.getColumns().addAll(savedGameNames, lastPlayedDate,pathToFile);

        grid.add(savedGamesTable,4,4, 3, 1);

        this.getChildren().add(grid);
    }


    public void addBackToMainListener(EventHandler<ActionEvent> handlerForBackButton){
        backToMainButton.setOnAction(handlerForBackButton);
    }

    public void addTableClickListener(EventHandler<MouseEvent> handlerForTableMouseClick){
        savedGamesTable.setOnMouseClicked(handlerForTableMouseClick);
    }

    public void addImageClickListener(EventHandler<MouseEvent> handlerForImageMouseClick) {
        avatar[0].setOnMouseClicked(handlerForImageMouseClick);
        avatar[1].setOnMouseClicked(handlerForImageMouseClick);
        avatar[2].setOnMouseClicked(handlerForImageMouseClick);
    }

    public void enableCreateButton(){
        createButton.setDisable(false);
    }

    public String getName() { return name.getText(); }

    public SaveFile getSelectedFile(){
        return savedGamesTable.getSelectionModel().getSelectedItem();
    }

    public void addCreateGameListener(EventHandler<ActionEvent> handlerForCreateGame){
        createButton.setOnAction(handlerForCreateGame);
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

    private void setUpAvaterImages() {
        String avatarFile = "Avatar";

        for (int i = 0; i < 3; ++i) {
            try {
                avatar[i] = new ImageView(new Image(new FileInputStream(AVATAR_PATH + avatarFile + (i + 1) + ".png")));
                avatar[i].setAccessibleHelp(AVATAR_PATH + avatarFile + (i + 1) + ".png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}