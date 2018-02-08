package views;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;



public class GlobalGameplayView extends Parent {   //

    private Button inGameMenuButton= new Button("In-Game Menu");
    private Button changeToLocal = new Button("Change To Local View");
    private Image charachterSprite;
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
    private int globalMapSize;
    Group root = new Group();
    Scene localScene = new Scene(root,800,800);

    public GlobalGameplayView(){

        globalCharacterPrevPos.setLocation(15,15);
        globalCharacterPos.setLocation(15,15);
        globalMapSize = 30;
        tileSprites = new Image[globalMapSize][globalMapSize];
        viewableTilesNum = 9;
        tileImageView = new ImageView[globalMapSize][globalMapSize];


        viewableGlobalMap = new GridPane();
        viewableGlobalMap.setVgap(0);
        viewableGlobalMap.setHgap(0);

        intializeSprites();

        GlobalDisplay gd = new GlobalDisplay(this);
        gd.intializeMap();

        //TODO: Remove the hardcoding of the characters, and tiles width and height
        //TODO: Animate Character Movement along with the direction the character is facing
        //TODO: Figure out an appropariate method for moving the characters based on changing user input, and all directions.

    }
    //Creates ImageViews for the character and map tiles.
    public void intializeSprites(){
        //Intializes the characterSprite with an Image
        charachterSprite =  charachterSprite = new Image("PlaceHolderForImages/Character.png", 80.,80.,true,true);
        //Creates a characterView
        characterView = new ImageView(charachterSprite);
        //Character height and width must be smaller than tile's height and width.
        characterView.setFitHeight(30);
        characterView.setFitWidth(30);

        for(int i = 0; i < globalMapSize; i++) {
            for (int j = 0; j < globalMapSize; j++) {
                int temp = (int)(Math.random() * 3);
                if(temp == 2)
                    tileSprites[i][j] = new Image("PlaceHolderForImages/Water.png",100.,100., true,true);
                else if( temp == 1 )
                    tileSprites[i][j] = new Image("PlaceHolderForImages/GRASS.png",100.,100., true,true);
                else
                    tileSprites[i][j] = new Image("PlaceHolderForImages/MOUNTAIN.png",100.,100., true,true);
            }
        }

        //Creates imageViews of each tile
        for(int i = 0; i < globalMapSize; i++)
            for (int j = 0; j < globalMapSize; j++) {
                tileImageView[i][j] = new ImageView(tileSprites[i][j]);
                //Note the width and height are dependent on the number of viewable tiles and the window size
                // squareroot(500*500/81)
                //squareroot(windowWidth*windowHeight)/(tilesWidth * tilesHeight))
                tileImageView[i][j].setFitWidth(55.555555555);
                tileImageView[i][j].setFitHeight(55.55555555);
            }

    }


    public void addMenuButtonListener(EventHandler<ActionEvent> handlerForMenuButton){
        inGameMenuButton.setOnAction(handlerForMenuButton);
    }

    public void addKeyPressListener(EventHandler<KeyEvent> handlerForKeypress){
        this.localScene.setOnKeyPressed(handlerForKeypress);
    }

    public void addChangeToLocalListener(EventHandler<ActionEvent> handlerForChangeToLocal){
        this.changeToLocal.setOnAction(handlerForChangeToLocal);
    }

    public void updateCharacterPos(Point updatedPos){
        globalCharacterPos = updatedPos;
    }



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
                if(now - elapsedTime < 1_000_000_000)
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
            {
                return "LEFT";
            }
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
        if(DisplayRowEnd > globalMapSize - 1)
        {
            DisplayRowEnd = globalMapSize;
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
        if(DisplayColEnd > globalMapSize - 1){
            DisplayColEnd = globalMapSize;
        }
        return DisplayColEnd;
    }




}




