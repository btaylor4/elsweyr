package views;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;


public class LocalGameplayView extends Parent {   //

    private Button inventoryButton = new Button("Inventory");
    private Button inGameMenuButton = new Button("In-Game Menu");
    private Button changeToGlobal = new Button("Change To Global View");
    /*
    Order of operation of sprite display.
    displayed 1st is tilesprite
    displayed 2nd is decal
    displayed 3rd is item
    displayed 4th/last is character
     */
    private Image characterSprite;
    private ImageView characterImageView;
    private ImageView [][] tileImageView;
    private ImageView [][] decalImageView;
    private ImageView [][] itemImageView;
    private ImageView surroundingTile;
    private int localMapHeight;
    private int localMapWidth;
    //The numbers of rows that can be viewed
    private int viewableTilesRow;
    //The number of columns that can be viewed
    private int viewableTilesCol;
    //The direction the character is facing.
    private String characterDirection;
    //Needed to display the character at the correct location.
    private Point localCharacterPos;
    //Used to detect if the character moved.
    private Point localCharacterPrevPos;
    private StringBuffer path = new StringBuffer("file:PlaceHolderForImages/");

    Group root = new Group();
    Scene localScene = new Scene(root,800,800);

    public LocalGameplayView(){

        viewableTilesRow = 9;
        viewableTilesCol = 9;
        characterDirection = "DOWN";

        LocalDisplay ld = new LocalDisplay(this);
        ld.intializeMap();
    }

    public void addInventoryButtonListener(EventHandler<ActionEvent> handlerForInvButton){
        inventoryButton.setOnAction(handlerForInvButton);
    }

    public void addMenuButtonListener(EventHandler<ActionEvent> handlerForMenuButton){
        inGameMenuButton.setOnAction(handlerForMenuButton);
    }

    public void addKeyPressListener(EventHandler<KeyEvent> handlerForKeypress){
        this.getScene().setOnKeyReleased(handlerForKeypress);
    }

    public void addChangeToGlobalListener(EventHandler<ActionEvent> handlerForChangeToGlobal){
        this.changeToGlobal.setOnAction(handlerForChangeToGlobal);
    }


    //Creates a characterImageView
    private void intializeCharacterImageView(){

        double characterHeight = 30;
        double characterWidth = 30;
        characterSprite = new Image(path + "Character.png");
        characterImageView = new ImageView(characterSprite);
        characterImageView.setFitHeight(characterHeight);
        characterImageView.setFitWidth(characterWidth);
    }



    //Creates a tile ImageView
    public void createTileViews(Image[][] tileSprites) {
        localMapHeight = tileSprites.length;
        localMapWidth = tileSprites[0].length;

        double tileHeight = 45.55555;
        double tileWidth = 45.55555;
        tileImageView = new ImageView[localMapHeight][localMapWidth];
        for(int i = 0; i < tileImageView.length; i++)
            for(int j = 0; j < tileImageView[0].length; j++) {
                tileImageView[i][j] = new ImageView(tileSprites[i][j]);
                tileImageView[i][j].setFitHeight(tileHeight);
                tileImageView[i][j].setFitWidth(tileWidth);
            }


        //Creates the Surrounding Tile
        Image temp = new Image(path + "MOUNTAIN.png");
        surroundingTile = new ImageView(temp);
        surroundingTile.setFitHeight(45.55555);
        surroundingTile.setFitWidth(45.555555);


    }

    //Creates a decal ImageView
    public void createDecalViews(Image[][] decalSprites) {

        double decalHeight = 45.55555;
        double decalWidth = 45.55555;
        decalImageView = new ImageView[localMapHeight][localMapWidth];

        for(int i = 0; i < decalImageView.length; i++)
            for(int j = 0; j < decalImageView[0].length; j++) {
            if(decalSprites[i][j] != null) {
                decalImageView[i][j] = new ImageView(decalSprites[i][j]);
                decalImageView[i][j].setFitHeight(decalHeight);
                decalImageView[i][j].setFitWidth(decalWidth);
            }
            }

    }

    //Creates an item ImageView
    public void createItemViews(Image[][] itemSprites) {
        double itemHeight = 45.55555;
        double itemWidth = 45.55555;
        itemImageView = new ImageView[localMapHeight][localMapWidth];

        for(int i = 0; i < itemImageView.length; i++)
            for(int j = 0; j < itemImageView[0].length; j++) {
                if(itemSprites[i][j] != null) {
                    itemImageView[i][j] = new ImageView(itemSprites[i][j]);
                    itemImageView[i][j].setFitHeight(itemHeight);
                    itemImageView[i][j].setFitWidth(itemWidth);
                }
            }
    }

    public void createCharacterView(Image characterSprite) {
        characterImageView = new ImageView(characterSprite);
        characterImageView.setFitHeight(40);
        characterImageView.setFitWidth(40);
    }

    public void updateCharacterPos(Point startTile) {
        localCharacterPos = startTile;
    }

    public void setCharacterPrevPos(Point characterPrevPos) {
        this.localCharacterPrevPos = characterPrevPos;
    }

    public void setCharacterDirection(String characterDirection) {
        this.characterDirection = characterDirection;
    }


    public class LocalDisplay extends AnimationTimer {
        private LocalGameplayView localGameplayView;
        private GridPane localMap;
        private BorderPane localView;
        private long elapsedTime;

        public LocalDisplay(LocalGameplayView view){
            localGameplayView = view;
            localMap = new GridPane();
            localMap.setHgap(0);
            localMap.setVgap(0);


            localView = new BorderPane();
            localView.setCenter(localMap);
            localView.setBottom(inGameMenuButton);
            localView.setRight(inventoryButton);
            elapsedTime = 0;
        }

        @Override
        public void handle(long now) {

            //Detects when the character moves and moves the map
            if(localCharacterPos.x != localCharacterPrevPos.x || localCharacterPos.y != localCharacterPrevPos.y) {
                //Updates the position to be the position moved to
                setCharacterPrevPos(localCharacterPos);
                localMap.getChildren().clear();
                displayMapAndContents();
                elapsedTime = now;


                localMap.add(characterImageView, viewableTilesCol / 2, viewableTilesRow / 2);
                localMap.setValignment(characterImageView, VPos.CENTER);
                localMap.setHalignment(characterImageView, HPos.CENTER);
            }
            else{
                localMap.getChildren().clear();
                displayMapAndContents();

                localMap.add(characterImageView, viewableTilesCol / 2, viewableTilesRow / 2);
                localMap.setValignment(characterImageView, VPos.CENTER);
                localMap.setHalignment(characterImageView, HPos.CENTER);
            }


        }

        public void intializeMap(){
            localGameplayView.getChildren().addAll(localView);
            start();
        }



        //Displays the map and contents (including tiles, decals, and items)
        private void displayMapAndContents(){

            int row = localCharacterPrevPos.x;
            int col = localCharacterPrevPos.y;

            //The first row to be displayed
            int displayRowStart = getDisplayStart(row);
            int displayRowEnd = getDisplayRowEnd(row);
            //The first column to be displayed.
            int displayColStart = getDisplayColStart(col);
            int displayColEnd = getDisplayColEnd(col);


            //Number of rows to be displayed
            int numRows = displayRowEnd - displayRowStart;
            //Number of Columnstobecisplayed
            int numCols = displayColEnd - displayColStart;
            int temp = displayColStart;
            for(int i = 0; i < numRows; i++) {
                temp = displayColStart;
                for (int j = 0; j < numCols; j++) {

                    //If the tile is not in the map.
                    if(displayRowStart < 0)
                    {
                        localMap.add(surroundingTile,j,i);
                    }
                    else if(displayRowEnd > localMapHeight){
                        localMap.add(surroundingTile,j,i);
                    }
                    else if(temp < 0)
                    {
                        localMap.add(surroundingTile,j,i);
                        temp++;
                    }
                    else if(temp > localMapWidth)
                    {
                        localMap.add(surroundingTile,j,i);
                    }
                    else {
                        //When the tile is in the map
                        localMap.add(tileImageView[displayRowStart][temp], j, i);
                        if (decalImageView[displayRowStart][temp] != null) {
                            localMap.add(decalImageView[displayRowStart][temp], j, i);
                        }
                        if (itemImageView[displayRowStart][temp] != null) {
                            localMap.add(itemImageView[displayRowStart][temp], j, i);
                            localMap.setValignment(itemImageView[displayRowStart][temp], VPos.CENTER);
                            localMap.setHalignment(itemImageView[displayRowStart][temp], HPos.CENTER);

                        }
                    }

                    temp++;
                }
                displayRowStart++;
            }
    }

        //Cannot display a row that is before the 0th row.
        private int getDisplayStart(int row){
            int displayRowStart = row - viewableTilesRow / 2;
            return displayRowStart;
        }
        //Cannot display a row that is not in the map.
        private int getDisplayRowEnd(int row){
            int displayRowEnd = row + viewableTilesRow / 2 + 1;
            return displayRowEnd;
        }

        private int getDisplayColStart(int col){
            int displayColStart = col - viewableTilesCol /2;
            return displayColStart;
        }

        private int getDisplayColEnd(int col){
            //The plus one is for the for loop
            int displayColEnd = col + viewableTilesCol / 2 + 1;
            return displayColEnd;
        }
    }
}
