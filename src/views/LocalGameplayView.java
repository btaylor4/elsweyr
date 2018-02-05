package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LocalGameplayView extends Stage {   //

    private Button inventoryButton = new Button("Inventory");
    private Button inGameMenuButton = new Button("In-Game Menu");
    private Image charachterSprite;
    private Image [][] tileSprites;
    private Image[][] itemSprites;
    private Image [][] terrainSprites;
    private Image [][] decalSprites;
    Group root = new Group();
    Scene localScene = new Scene(root,800,800);

    public LocalGameplayView(){
        this.setTitle("LOCAL GAMEPLAY VIEW");
        this.setScene(localScene);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));


        grid.add(inventoryButton,1,1);
        grid.add(inGameMenuButton, 1,0);

        root.getChildren().add(grid);
    }

    public void addInventoryButtonListener(EventHandler<ActionEvent> handlerForInvButton){
        inventoryButton.setOnAction(handlerForInvButton);
    }

    public void addMenuButtonListener(EventHandler<ActionEvent> handlerForMenuButton){
        inGameMenuButton.setOnAction(handlerForMenuButton);
    }

    public void addKeyPressListener(EventHandler<KeyEvent> handlerForKeypress){
        this.localScene.setOnKeyPressed(handlerForKeypress);
    }

}
