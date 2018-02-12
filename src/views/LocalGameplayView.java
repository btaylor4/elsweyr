package views;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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

import java.awt.*;


public class LocalGameplayView extends Parent {   //

    private Button inventoryButton = new Button("Inventory");
    private Button inGameMenuButton = new Button("In-Game Menu");
    private Button changeToGlobal = new Button("Change To Global View");

    private StatusView statusView = new StatusView();
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
    private StringBuffer characterSpritePath;


    Group root = new Group();

    public LocalGameplayView(String spritePath){

        this.characterSpritePath = new StringBuffer(spritePath);
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

    //Updates the character ImageView based on the direction the character is facing.
    private void updateCharacterImageView(String image){
        //Intializes the characterSprite with an Image
        characterSprite = new Image(image);
        //Creates a characterView
        characterImageView = new ImageView(characterSprite);
        //Character height and width must be smaller than tile's height and width.
        characterImageView.setFitHeight(40);
        characterImageView.setFitWidth(40);

    }

    //Updates characterImageView based on character movement direction
    private void updateCharacterImageView(){
        switch(characterDirection){
            case "UP": // 8
                updateCharacterImageView(characterSpritePath + "Character_Back.png");
                break;

            case "DOWN": // 2
                updateCharacterImageView(characterSpritePath + "Character_Front.png");
                break;

            case "LEFT": // 4
                updateCharacterImageView(characterSpritePath + "Character_East.png");
                break;

            case "RIGHT": //6
                updateCharacterImageView(characterSpritePath + "Character_East.png");
                break;

            case "END": // 1 DOWN_LEFT

                break;

            case "PAGE_DOWN":  // DOWN_RIGHT
                updateCharacterImageView(characterSpritePath + "Character_South_East.png");
                break;

            case "HOME":  // UP_LEFT

                break;


            case "PAGE_UP": // UP_RIGHT

                break;

            default:

        }
    }

    public void setCharacterSpritesPath(String path){this.characterSpritePath = new StringBuffer(path);}

    public void updateMove(String move){
        characterDirection = move;
        updateCharacterImageView();
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
        characterSprite = characterSprite;
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

    public void removeItemImageView(Point locPos) {
        itemImageView[locPos.x][locPos.y].setImage(null);
    }

    public StatusView getStatusView() {
        return statusView;
    }

    public class LocalDisplay extends AnimationTimer {
        private LocalGameplayView localGameplayView;
        private GridPane localMap;
        private BorderPane localView;
        private long elapsedTime;

        public LocalDisplay(LocalGameplayView view) {
            localGameplayView = view;
            localMap = new GridPane();
            localMap.setHgap(0);
            localMap.setVgap(0);


            localView = new BorderPane();
            localView.setCenter(localMap);
            localView.setBottom(inGameMenuButton);
            localView.setTop(statusView);
            localView.setRight(inventoryButton);
        }

        @Override
        public void handle(long now) {

            //Detects when the character moves and moves the map
            if (localCharacterPos.x != localCharacterPrevPos.x || localCharacterPos.y != localCharacterPrevPos.y) {

                //Updates the position to be the position moved to
                setCharacterPrevPos(localCharacterPos);
                localMap.getChildren().clear();

                displayMapAndContents();

                localMap.add(characterImageView, viewableTilesCol / 2, viewableTilesRow / 2);
                localMap.setValignment(characterImageView, VPos.CENTER);
                localMap.setHalignment(characterImageView, HPos.CENTER);

            }
            else {

                localMap.getChildren().clear();

                displayMapAndContents();

                localMap.add(characterImageView, viewableTilesCol / 2, viewableTilesRow / 2);
                localMap.setValignment(characterImageView, VPos.CENTER);
                localMap.setHalignment(characterImageView, HPos.CENTER);
            }


        }

        public void intializeMap() {
            localGameplayView.getChildren().addAll(localView);
            start();
        }

        //Displays the map and contents (including tiles, decals, and items)
        private void displayMapAndContents() {

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
            for (int i = 0; i < numRows; i++) {
                temp = displayColStart;
                for (int j = 0; j < numCols; j++) {
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
                    temp++;
                }
                displayRowStart++;
            }
        }

        //Cannot display a row that is before the 0th row.
        private int getDisplayStart(int row) {
            int displayRowStart = row - viewableTilesRow / 2;
            if (displayRowStart < 0)
                return 0;
            return displayRowStart;
        }

        //Cannot display a row that is not in the map.
        private int getDisplayRowEnd(int row) {
            int displayRowEnd = row + viewableTilesRow / 2 + 1;
            if (displayRowEnd > localMapHeight - 1)
                return localMapHeight - 1;
            return displayRowEnd;
        }

        private int getDisplayColStart(int col) {
            int displayColStart = col - viewableTilesCol / 2;
            if (displayColStart < 0)
                return 0;
            return displayColStart;
        }

        private int getDisplayColEnd(int col) {
            //The plus one is for the for loop
            int displayColEnd = col + viewableTilesCol / 2 + 1;
            if (displayColEnd > localMapWidth - 1)
                return localMapWidth - 1;
            return displayColEnd;
        }
    }
}
