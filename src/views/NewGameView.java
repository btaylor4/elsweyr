package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;


import java.awt.*;

//TODO PASS CHARACHTER IMAGE INTO NEW CHARACTER CLASS
public class NewGameView extends Parent {

    //Declare all the buttons and assets the Load Game view will need

    private Button backToMainButton = new Button("Back To Main Menu");
    private Button startGameButton = new Button("Start Game");
    private Label nameLabel = new Label("Name: ");
    private TextField nameInput = new TextField();
    private Image selectedCharacterImage;


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


        Image image = new Image(getClass().getResourceAsStream("briefcase.png"));
        //set toggles with character images
        //TODO Add different character image sprites

        ToggleButton tb1 = new ToggleButton("Press me", new ImageView(image));
        ToggleButton tb2 = new ToggleButton("Press me", new ImageView(image));
        ToggleButton tb3 = new ToggleButton ("Press me", new ImageView(image));

        tb1.setSelected(true);
        //Assign toggles to one group
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        tb3.setToggleGroup(group);

        //Set user data

        tb1.setUserData(new Image(getClass().getResourceAsStream("briefcase.png")));
        tb2.setUserData(new Image(getClass().getResourceAsStream("briefcase.png")));
        tb3.setUserData(new Image(getClass().getResourceAsStream("briefcase.png")));

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
                    selectedCharacterImage = (Image) group.getSelectedToggle().getUserData();
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

    public Image getSelectedImage(){
        return selectedCharacterImage;
    }

    public String getSelectedName(){
        return nameInput.getText();
    }


    public void addBackToMainListener(EventHandler<ActionEvent> handlerForBackButton){
        backToMainButton.setOnAction(handlerForBackButton);
    }
    public void addStartGaneListener(EventHandler<ActionEvent> handlerForBackButton){
        startGameButton.setOnAction(handlerForBackButton);
    }

}