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
import javafx.scene.layout.GridPane;

import java.awt.*;



public class GlobalGameplayView extends Parent {   //

    private Button inGameMenuButton= new Button("In-Game Menu");
    private Button changeToLocal = new Button("Change To Local View");
    private Image characterSprite;
    private Image [][] tileSprites;
    private Image[][] itemSprites;
    private Image [][] terrainSprites;
    private Image [][] decalSprites;
    //Previous position allows for the ability to update the characters position while not changing the map.
    private Point globalCharacterPrevPos = new Point();
    private Point globalCharacterPos = new Point();
    //Contains the section of the globalmap that the character can view.
    private GridPane viewableGlobalMap;
    private ImageView characterView;
    private ImageView [][] tileImageView;
    private ImageView [][] itemView;
    //Max number of tiles display in each row and column
    private int viewableTilesNum;
    private int globalMapHeight;
    private int globalMapWidth;
    //Displays the position of the character
    private String characterDirection;
    private StringBuffer characterSpritePath;
    private StringBuffer path = new StringBuffer("file:PlaceHolderForImages/");

    Group root = new Group();

    public GlobalGameplayView(String spritePath){

        this.characterSpritePath = new StringBuffer(spritePath);
        globalCharacterPrevPos.setLocation(15,15);
        globalCharacterPos.setLocation(15,15);
        globalMapHeight = 30;
        globalMapWidth = 20;
        tileSprites = new Image[globalMapHeight][globalMapWidth];
        viewableTilesNum = 9;
        tileImageView = new ImageView[globalMapHeight][globalMapWidth];


        viewableGlobalMap = new GridPane();
        viewableGlobalMap.setVgap(0);
        viewableGlobalMap.setHgap(0);

        initializeSprites();
        characterDirection = "DOWN";

        GlobalDisplay gd = new GlobalDisplay(this);
        gd.intializeMap();

        //TODO: Remove the hardcoding of the characters, and tiles width and height
        //TODO: Animate Character Movement along with the direction the character is facing
        //TODO: Figure out an appropariate method for moving the characters based on changing user input, and all directions.
    }
    //Creates ImageViews for the character and map tiles.
    public void initializeSprites(){
        //Intializes the characterSprite with an Image
        System.out.println(characterSpritePath);
        if (characterSpritePath == null)
            characterSpritePath = path;

        characterSprite = new Image(characterSpritePath + "Character.png", 80.,80.,true,true);
        //Creates a characterView
        characterView = new ImageView(characterSprite);
        //Character height and width must be smaller than tile's height and width.
        characterView.setFitHeight(30);
        characterView.setFitWidth(30);

        updateCharacterImageView(characterSpritePath + "Character_Front.png");

        for(int i = 0; i < globalMapHeight; i++) {
            for (int j = 0; j < globalMapWidth; j++) {
                int temp = (int)(Math.random() * 3);
                if(temp == 2)
                    tileSprites[i][j] = new Image(path + "WATER.png",100.,100., true,true);
                else if( temp == 1 )
                    tileSprites[i][j] = new Image(path + "GRASS.png",100.,100., true,true);
                else
                    tileSprites[i][j] = new Image(path + "MOUNTAIN.png",100.,100., true,true);
            }
        }

        //Creates imageViews of each tile
        for(int i = 0; i < globalMapHeight; i++) {
            for (int j = 0; j < globalMapWidth; j++) {
                tileImageView[i][j] = new ImageView(tileSprites[i][j]);
                //Note the width and height are dependent on the number of viewable tiles and the window size
                // squareroot(500*500/81)
                //squareroot(windowWidth*windowHeight)/(tilesWidth * tilesHeight))
                tileImageView[i][j].setFitWidth(55.555555555);
                tileImageView[i][j].setFitHeight(55.55555555);
            }
        }
    }

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

    public void addKeyPressListener(EventHandler<KeyEvent> handlerForKeypress){
        this.getScene().setOnKeyPressed(handlerForKeypress);
    }

    public void addChangeToLocalListener(EventHandler<ActionEvent> handlerForChangeToLocal){
        this.changeToLocal.setOnAction(handlerForChangeToLocal);
    }

    public void updateCharacterPos(Point updatedPos){
        globalCharacterPos = updatedPos;
    }

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
        private GlobalGameplayView outer;
        //Determines the time between showing the character movement and map updating
        private long elapsedTime = 0;

        public GlobalDisplay(GlobalGameplayView a){
            outer = a;
        }

        @Override
        public void handle(long now) {

            //Checks if the character has been moved.
            if(globalCharacterPos.x != globalCharacterPrevPos.x || globalCharacterPos.y != globalCharacterPrevPos.y)
            {
                //Moves the character and causes a one second wait.
                viewableGlobalMap.getChildren().clear();
                if(now - elapsedTime < 500_000_000)
                {
                    updateViewAfterMovement();
                    moveCharacterView();
                }
                //Once the one second weight is over the map is updated.
                else
                {
                    globalCharacterPrevPos.setLocation(globalCharacterPos.x, globalCharacterPos.y);
                    //Sets the map the correct position after the characters movement
                    updateViewAfterMovement();
                    //Places the character into the center of the map
                    viewableGlobalMap.add(characterView,viewableTilesNum/2,viewableTilesNum/2);
                    viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                    viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
                    elapsedTime = now;
                }
            }
            //Keeps the maps display when their is no input.
            else {
                viewableGlobalMap.getChildren().clear();
                updateViewAfterMovement();
                //Places the character into the center of the map
                viewableGlobalMap.add(characterView,viewableTilesNum/2,viewableTilesNum/2);
                viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
                elapsedTime = now;
            }

        }

        public void intializeMap(){
            outer.getChildren().addAll(viewableGlobalMap);
            start();
        }

        //Displays the characters position based on the movement input.
        public void moveCharacterView(){
            String move = calculateMovementType();
            switch (move) {
                case "UP": // 8
                    viewableGlobalMap.add(characterView,viewableTilesNum/2,viewableTilesNum/2 - 1);
                    viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                    viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
                    break;

                case "DOWN": // 2
                    viewableGlobalMap.add(characterView,viewableTilesNum/2,viewableTilesNum/2 + 1);
                    viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                    viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
                    break;

                case "LEFT": // 4
                    viewableGlobalMap.add(characterView,viewableTilesNum/2 - 1,viewableTilesNum/2);
                    viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                    viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
                    break;

                case "RIGHT": //6
                    viewableGlobalMap.add(characterView,viewableTilesNum/2 + 1,viewableTilesNum/2);
                    viewableGlobalMap.setValignment(characterView, VPos.CENTER);
                    viewableGlobalMap.setHalignment(characterView, HPos.CENTER);
                    break;
            }
            }

        }

        //This only accounts for left right up down.
        public String calculateMovementType(){
            if(globalCharacterPrevPos.x > globalCharacterPos.x)
                return "UP";
            else if(globalCharacterPrevPos.x < globalCharacterPos.x)
                return "DOWN";
            else if(globalCharacterPrevPos.y > globalCharacterPos.y)
                return "LEFT";
            else
                return "RIGHT";
        }

    //Updates the map based on the characters previous position
    public void updateViewAfterMovement(){

        int row = globalCharacterPrevPos.x;
        int col = globalCharacterPrevPos.y;

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
        //Adds the rows and the columns that are viewable to the globalmap gridpane
        for(int i = 0; i < numRows; i++) {
            temp = DisplayColStart;
            for (int j = 0; j < numCols; j++) {
                viewableGlobalMap.add(tileImageView[DisplayRowStart][temp++],j, i);
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
        if(DisplayRowEnd > globalMapHeight - 1)
        {
            DisplayRowEnd = globalMapHeight;
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
        if(DisplayColEnd > globalMapWidth - 1){
            DisplayColEnd = globalMapWidth;
        }
        return DisplayColEnd;
    }
}




