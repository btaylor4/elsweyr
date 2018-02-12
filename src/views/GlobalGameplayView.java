package views;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;


public class GlobalGameplayView extends Parent {   //

    private Button inGameMenuButton= new Button("In-Game Menu");
    private Button changeToLocal = new Button("Change To Local View");
    private Image characterSprite;

    //Previous position allows for the ability to update the characters position while not changing the map.
    private Point globalCharacterPrevPos = new Point();
    private Point globalCharacterPos = new Point();
    //Contains the section of the globalmap that the character can view.
    private GridPane viewableGlobalMap;

    private BorderPane globalView;
    private ImageView characterView;
    private ImageView [][] tileImageView;

    //Max number of tiles displayed in each row and column
    private int viewableTilesRow;
    private int viewableTilesCol;
    private int globalMapHeight;
    private int globalMapWidth;
    //Displays the position of the character
    private String characterDirection;
    private StringBuffer characterSpritePath;
    private StringBuffer path = new StringBuffer("file:PlaceHolderForImages/");

    Group root = new Group();

    public GlobalGameplayView(String spritePath){

        this.characterSpritePath = new StringBuffer(spritePath);
        viewableTilesRow = 9;
        viewableTilesCol = 9;

        viewableGlobalMap = new GridPane();
        viewableGlobalMap.setVgap(0);
        viewableGlobalMap.setHgap(0);

        globalView = new BorderPane();
        globalView.setCenter(viewableGlobalMap);
        globalView.setBottom(inGameMenuButton);

        characterDirection = "DOWN";

        GlobalDisplay gd = new GlobalDisplay(this);
        gd.intializeMap();


        //TODO: Remove the hardcoding tiles width and height
    }

    //Creates ImageViews for the character
    public void createCharacterView(Image characterImage) {

        characterSprite = characterImage;
        characterView = new ImageView(characterImage);
        characterView.setFitHeight(40);
        characterView.setFitWidth(40);
    }


    public void createTileViews(Image[][] tileSprites){

        globalMapHeight = tileSprites.length;
        globalMapWidth = tileSprites[0].length;

        //Intializes the imageview
        tileImageView = new ImageView[globalMapHeight][globalMapWidth];

        //Creates imageViews of each tile
        for(int i = 0; i < globalMapHeight; i++) {
            for (int j = 0; j < globalMapWidth; j++) {
                tileImageView[i][j] = new ImageView(tileSprites[i][j]);
                //Note the width and height are dependent on the number of viewable tiles and the window size

                //squareroot(windowWidth*windowHeight)/(displayabletile^2))
                // squareroot(500*500/81)

                //HardCodeWidth and Height
                tileImageView[i][j].setFitWidth(45.55555);
                tileImageView[i][j].setFitHeight(45.5555);

            }
        }

    }


    //Updates the character ImageView based on the direction the character is facing.
    private void updateCharacterImageView(String image){
        //Intializes the characterSprite with an Image
        characterSprite = new Image(image);
        //Creates a characterView
        characterView = new ImageView(characterSprite);
        //Character height and width must be smaller than tile's height and width.
        characterView.setFitHeight(40);
        characterView.setFitWidth(40);

    }


    public void addMenuButtonListener(EventHandler<ActionEvent> handlerForMenuButton){
            inGameMenuButton.setOnAction(handlerForMenuButton);
    }

    public void addKeyPressListener(EventHandler<javafx.scene.input.KeyEvent> handlerForKeypress){
        //This gets around buttons listening to keypresses.
        this.getScene().setOnKeyReleased(handlerForKeypress);
    }

    public void addChangeToLocalListener(EventHandler<ActionEvent> handlerForChangeToLocal){
        this.changeToLocal.setOnAction(handlerForChangeToLocal);
    }

    public void updateCharacterPos(Point updatedPos){
        globalCharacterPos = updatedPos;
    }
    public void setGlobalCharacterPrevPos(Point globalCharacterPrevPos) {this.globalCharacterPrevPos = globalCharacterPrevPos; }

    public void updateMove(String move){
        characterDirection = move;
        updateCharacterImageView();
    }


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


    public class GlobalDisplay extends AnimationTimer{

        //The outerclass GlobalGamePlayView need to add the JavaFX viewable contents to the view.
        private GlobalGameplayView globalGameplayView;

        public GlobalDisplay(GlobalGameplayView a){
            globalGameplayView = a;
        }

        @Override
        public void handle(long now) {

            //Checks if the character has been moved.
            if(globalCharacterPos.x != globalCharacterPrevPos.x || globalCharacterPos.y != globalCharacterPrevPos.y)
            {

                viewableGlobalMap.getChildren().clear();
                globalCharacterPrevPos.setLocation(globalCharacterPos.x, globalCharacterPos.y);
                //Sets the map the correct position after the characters movement
                //TODO:Make it so the tile the character is on will always be the center, no matter the map.
                updateViewAfterMovement();
                //Places the character into the center of the map
                viewableGlobalMap.add(characterView,viewableTilesCol/2,viewableTilesRow/2);
                viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                viewableGlobalMap.setHalignment(characterView, HPos.CENTER);

            }

            //Keeps the maps display when their is no input.
            else {
                viewableGlobalMap.getChildren().clear();
                updateViewAfterMovement();
                //Places the character into the center of the map
                viewableGlobalMap.add(characterView,viewableTilesCol/2,viewableTilesRow/2);
                viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
            }

        }

        public void intializeMap(){
            globalGameplayView.getChildren().addAll(globalView);
            start();
        }

    }

    //Updates the map based on the characters previous position
    public void updateViewAfterMovement(){

        int row = globalCharacterPrevPos.x;
        int col = globalCharacterPrevPos.y;

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
        //Adds the rows and the columns that are viewable to the globalmap gridpane

        for(int i = 0; i < numRows; i++) {
            temp = displayColStart;
            for (int j = 0; j < numCols; j++) {
                //If the tile is not in the map.
                viewableGlobalMap.add(tileImageView[displayRowStart][temp++], j, i);
            }
            displayRowStart++;
        }
    }

    //Cannot display a row that is before the 0th row.
    private int getDisplayStart(int row){
        int displayRowStart = row - viewableTilesRow / 2;
        if(displayRowStart < 0)
            return 0;
        return displayRowStart;
    }

    //Cannot display a row that is not in the map.
    private int getDisplayRowEnd(int row){
        int displayRowEnd = row + viewableTilesRow / 2 + 1;
        if(displayRowEnd > globalMapHeight - 1)
            return globalMapHeight - 1;
        return displayRowEnd;
    }

    private int getDisplayColStart(int col){
        int displayColStart = col - viewableTilesCol / 2;
        if(displayColStart < 0)
            return 0;
        return displayColStart;
    }

    //Retruns
    private int getDisplayColEnd(int col){
        //The plus one is for the for loop
        int displayColEnd = col + viewableTilesCol / 2 + 1;
        if(displayColEnd > globalMapWidth - 1)
            return globalMapWidth - 1;
        return displayColEnd;
    }
}




