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
    private Image [][] tileSprites;
    private Image[][] itemSprites;
    private Image [][] decalSprites;
    private ImageView characterImageView;
    private ImageView [][] tileImageView;
    private ImageView [][] decalImageView;
    private ImageView [][] itemImageView;
    private int localMapSize;
    private int viewableTilesNum;

    private Point localCharacterPos;
    private Point localCharacterPrevPos;
    private StringBuffer path = new StringBuffer("file:PlaceHolderForImages/");

    Group root = new Group();
    Scene localScene = new Scene(root,800,800);

    public LocalGameplayView(){

        /*
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));


        grid.add(inventoryButton,1,1);
        grid.add(inGameMenuButton, 1,0);
        grid.add(changeToGlobal,4,4);
        this.getChildren().add(grid);
        */

        localMapSize = 30;
        viewableTilesNum = 9;
        localCharacterPrevPos = new Point(15,15);

        intializeImageViews();
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
        this.localScene.setOnKeyPressed(handlerForKeypress);
    }

    public void addChangeToGlobalListener(EventHandler<ActionEvent> handlerForChangeToGlobal){
        this.changeToGlobal.setOnAction(handlerForChangeToGlobal);
    }

    public void intializeImageViews(){

        intializeCharacterImageView();
        intializeTileImageView();
        intializeItemImageView();
        intializeDecalView();
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

    private void intializeTileImageView(){
        double tileHeight = 55.555555555;
        double tileWidth = 55.555555555;
        tileSprites = new Image[localMapSize][localMapSize];
        tileImageView = new ImageView[localMapSize][localMapSize];

        for(int i = 0; i < localMapSize; i++)
            for (int j = 0; j < localMapSize; j++) {
                int temp = (int) (Math.random() * 3);
                if (temp == 2)
                    tileSprites[i][j] = new Image(path +"WATER.png", 100., 100., true, true);
                else if (temp == 1)
                    tileSprites[i][j] = new Image(path+"GRASS.png", 100., 100., true, true);
                else
                    tileSprites[i][j] = new Image(path+"MOUNTAIN.png", 100., 100., true, true);
            }

        //Creates imageViews of each tile
        for(int i = 0; i < localMapSize; i++)
            for (int j = 0; j < localMapSize; j++) {
                tileImageView[i][j] = new ImageView(tileSprites[i][j]);
                //Note the width and height are dependent on the number of viewable tiles and the window size
                // squareroot(500*500/81)
                //squareroot(windowWidth*windowHeight)/(tilesWidth * tilesHeight))
                tileImageView[i][j].setFitWidth(tileWidth);
                tileImageView[i][j].setFitHeight(tileHeight);
            }

    }


    private void intializeItemImageView(){
        double imageWidth = 20;
        double imageHeight = 20;
        itemSprites = new Image[localMapSize][localMapSize];
        itemImageView = new ImageView[localMapSize][localMapSize];

        for(int i = 0; i < localMapSize; i++)
            for(int j = 0; j < localMapSize; j++){
                if(Math.random() > .8)
                    itemSprites[i][j] = new Image(path+"Takeable.png");

            }

        for(int i = 0; i < localMapSize; i++)
            for(int j = 0; j < localMapSize; j++){
                if(itemSprites[i][j] != null){
                    itemImageView[i][j] = new ImageView(itemSprites[i][j]);
                    itemImageView[i][j].setFitWidth(imageWidth);
                    itemImageView[i][j].setFitHeight(imageHeight);

                }
            }
    }

    private void intializeDecalView(){
        //Note the width and height are dependent on the number of viewable tiles and the window size
        // squareroot(500*500/81)
        //squareroot(windowWidth*windowHeight)/(tilesWidth * tilesHeight))
        double decalHeight = 55.555555555;
        double decalWidth = 55.555555555;
        decalSprites = new Image[localMapSize][localMapSize];
        decalImageView = new ImageView[localMapSize][localMapSize];

        for(int i = 0; i < localMapSize; i++)
            for(int j = 0; j < localMapSize; j++){
                if(Math.random() > .8)
                    decalSprites[i][j] = new Image(path+"Death.png");

            }

        for(int i = 0; i < localMapSize; i++)
            for(int j = 0; j < localMapSize; j++){
                if(decalSprites[i][j] != null){
                    decalImageView[i][j] = new ImageView(decalSprites[i][j]);
                    decalImageView[i][j].setFitWidth(decalWidth);
                    decalImageView[i][j].setFitHeight(decalHeight);

                }
            }
    }


    public class LocalDisplay extends AnimationTimer {
        private LocalGameplayView localGameplayView;
        private GridPane localMap;
        private long elapsedTime;

        public LocalDisplay(LocalGameplayView view){
            localGameplayView = view;
            localMap = new GridPane();
            localMap.setHgap(0);
            localMap.setVgap(0);
            elapsedTime = 0;
        }

        @Override
        public void handle(long now) {

            //if(now - elapsedTime > 2_000_000_000){
                localMap.getChildren().clear();
                displayMapAndContents();
                elapsedTime = now;
            //}


        }

        public void intializeMap(){

            displayMapAndContents();
            localGameplayView.getChildren().addAll(localMap);
            start();
        }




        private void displayMapAndContents(){

            int row = localCharacterPrevPos.x;
            int col = localCharacterPrevPos.y;

            //The first row to be displayed
            int DisplayRowStart = getDisplayStart(row);
            int DisplayRowEnd = getDisplayRowEnd(row);
            //The first column to be displayed.
            int DisplayColStart = getDisplayColStart(col);
            int DisplayColEnd = getDisplayColEnd(col);


            //Number of rows to be displayed
            int numRows = DisplayRowEnd - DisplayRowStart;
            //Number of Columnstobecisplayed
            int numCols = DisplayColEnd - DisplayColStart;
            int temp = DisplayColStart;
            for(int i = 0; i < numRows; i++) {
                temp = DisplayColStart;
                for (int j = 0; j < numCols; j++) {
                    localMap.add(tileImageView[DisplayRowStart][temp],j, i);
                    if(decalImageView[DisplayRowStart][temp] != null){
                        localMap.add(decalImageView[DisplayRowStart][temp], j, i);
                    }
                    if(itemImageView[DisplayRowStart][temp] != null){
                        localMap.add(itemImageView[DisplayRowStart][temp], j, i);
                        localMap.setValignment(itemImageView[DisplayRowStart][temp], VPos.CENTER);
                        localMap.setHalignment(itemImageView[DisplayRowStart][temp], HPos.CENTER);

                    }

                    temp++;
                }
                DisplayRowStart++;
            }
        }

        //Cannot display a row that is before the 0th row.
        private int getDisplayStart(int row){
            int DisplayRowStart = row - viewableTilesNum / 2;
            if(DisplayRowStart < 0){
                DisplayRowStart = 0;
            }
            return DisplayRowStart;
        }
        //Cannot display a row that is not in the map.
        private int getDisplayRowEnd(int row){
            int DisplayRowEnd = row + viewableTilesNum / 2 + 1;
            if(DisplayRowEnd > localMapSize - 1)
            {
                DisplayRowEnd = localMapSize;
            }
            return DisplayRowEnd;
        }

        private int getDisplayColStart(int col){
            int DisplayColStart = col - viewableTilesNum /2;
            if(DisplayColStart < 0){
                DisplayColStart = 0;
            }
            return DisplayColStart;
        }

        private int getDisplayColEnd(int col){
            //The plus one is for the for loop
            int DisplayColEnd = col + viewableTilesNum / 2 + 1;
            if(DisplayColEnd > localMapSize - 1){
                DisplayColEnd = localMapSize;
            }
            return DisplayColEnd;
        }
    }
}
