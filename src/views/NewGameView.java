package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.SaveFile;

import java.io.File;
import java.util.Date;

//TODO PASS CHARACHTER IMAGE INTO NEW CHARACTER CLASS
public class NewGameView extends Parent {

    //Declare all the buttons and assets the Load Game view will need

    private Button backToMainButton = new Button("Back To Main Menu");
    private Button startGameButton = new Button("Start Game");
    private Label nameLabel = new Label("Name: ");
    private TextField nameInput = new TextField();
    private String selectedCharacterFilePath;
    private TableView<SaveFile> savedGamesTable = new TableView<>();
    private ObservableList<SaveFile> saves = FXCollections.observableArrayList();


    Group root = new Group();

    public NewGameView(){

        //TODO: Add options for which slot to save to
        final ToggleGroup group = new ToggleGroup();


        GridPane grid = new GridPane();
        BorderPane borderpane = new BorderPane();

        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(nameLabel,nameInput);

        HBox returnMenuBox = new HBox();
        returnMenuBox.getChildren().addAll(backToMainButton,startGameButton);
        startGameButton.setDisable(true);


        //Setting up the table view for the saved games
        setUpSaves();

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

        grid.add(savedGamesTable,5,5, 15, 1);


        String imageFile = "src" + File.separator + "views" + File.separator + "briefcase.png";
        Image image = new Image(getClass().getResourceAsStream("briefcase.png"));
        //set toggles with character images
        //TODO Add different character image sprites

        ToggleButton tb1 = new ToggleButton("Press me", new ImageView(image));
        ToggleButton tb2 = new ToggleButton("Press me", new ImageView(image));
        ToggleButton tb3 = new ToggleButton ("Press me", new ImageView(image));

        tb1.setSelected(true);
        selectedCharacterFilePath = imageFile; // this is the image path of tb1's image!!!
        //Assign toggles to one group
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        tb3.setToggleGroup(group);

        //Set user data

        tb1.setUserData(new Image(getClass().getResourceAsStream("briefcase.png")));
        tb1.setAccessibleHelp(imageFile);
        tb2.setUserData(new Image(getClass().getResourceAsStream("briefcase.png")));
        tb2.setAccessibleHelp(imageFile);
        tb3.setUserData(new Image(getClass().getResourceAsStream("briefcase.png")));
        tb3.setAccessibleHelp(imageFile);

        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,20,10));
        //Set toggles in grid
        grid.add(tb1,5,20);
        grid.add(tb2,10,20);
        grid.add(tb3,15,20);


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(newValue != null){
                    selectedCharacterFilePath = ((ToggleButton) group.getSelectedToggle()).getAccessibleHelp();
                }
            }
        });


        //Anchor down the Hbox for the return to main menu button
        AnchorPane anchorpane = new AnchorPane();
        anchorpane.getChildren().addAll(returnMenuBox);   // Add grid from Example 1-5
        AnchorPane.setBottomAnchor(returnMenuBox, 30.0);
        AnchorPane.setLeftAnchor(returnMenuBox, 5.0);

        borderpane.setTop(nameBox);
        borderpane.setCenter(grid);
        borderpane.setBottom(anchorpane);

        this.getChildren().add(borderpane);
    }

    public String getSelectedCharacterFilePath() {return selectedCharacterFilePath; }

    public String getSelectedName(){
        return nameInput.getText();
    }

    public void addBackToMainListener(EventHandler<ActionEvent> handlerForBackButton){
        backToMainButton.setOnAction(handlerForBackButton);
    }

    public void addTableClickListener(EventHandler<MouseEvent> handlerForTableMouseClick){
        savedGamesTable.setOnMouseClicked(handlerForTableMouseClick);
    }

    public void enableCreateButton(){
        startGameButton.setDisable(false);
    }

    public SaveFile getSelectedFile(){
        return savedGamesTable.getSelectionModel().getSelectedItem();
    }

    public void addStartGameListener(EventHandler<ActionEvent> handlerForBackButton){
        startGameButton.setOnAction(handlerForBackButton);
    }

    private void setUpSaves() {
        File folder;
        File [] files;

        String saveSlot = "SaveSlot";

        for (int i = 1; i < 4; ++i) {
            folder = new File (saveSlot + i + File.separator);
            files = folder.listFiles();

            if (files != null && files.length > 1) {
                Date lastPlayed = new Date(0);
                String mapFilePath = "";
                String characterFileNamePath = "";

                for (int j = 0; j < files.length; ++j) {
                    if (files[j].getName().equals("DefaultMap.txt")) {
                        lastPlayed = new Date (files[j].lastModified());
                        mapFilePath = saveSlot + i + File.separator + files[j].getName();
                    } else if (files[j].getName().equals("DefaultCharacter.txt")) {
                        characterFileNamePath = saveSlot + i + File.separator + files[j].getName();
                    }
                }
                if (!mapFilePath.equals("") && !characterFileNamePath.equals("")) {
                    saves.add(new SaveFile("save " + i, lastPlayed.toString(), characterFileNamePath, mapFilePath));
                } else {
                    saves.add(new SaveFile("Empty", "0/0/0 00:00", "No File SaveSlot" + i, "No File SaveSlot" + i));
                }

            } else {
                saves.add(new SaveFile("Empty", "0/0/0 00:00", "No File SaveSlot" + i, "No File SaveSlot" + i));
            }
        }

    }
}