package views;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;



public class GlobalGameplayView extends Parent {   //

    private Button inGameMenuButton= new Button("In-Game Menu");
    private Button changeToLocal = new Button("Change To Local View");
    private Image charachterSprite;
    private Image [][] tileSprites;
    private Image[][] itemSprites;
    private Image [][] terrainSprites;
    private Image [][] decalSprites;
    private Point globalCharacterPos = new Point();
    Group root = new Group();
    Scene localScene = new Scene(root,800,800);

    public GlobalGameplayView(){

        globalCharacterPos.setLocation(0,0);
        tileSprites = new Image[5][5];

        charachterSprite = new Image("http://creationview.com/image/Birds4F.jpg", 80.,80.,true,true);

        for(int i = 0; i < tileSprites.length; i++) {
            for (int j = 0; j < tileSprites[0].length; j++) {
                tileSprites[i][j] = new Image("http://www.creationview.com/gallery/Recentwork5/of8b5305_std.jpg",100.,100., true,true);
            }
        }

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        grid.add(inGameMenuButton, 1,0);
        grid.add(changeToLocal,4,4);
        this.getChildren().addAll(grid);

        GlobalDisplay gd = new GlobalDisplay(this);
        gd.intializeCanvas();
        //TODO: Select the images to be displayed, create a method to display the images properly connected.
        //TODO: Correctly update the map tiles based on character movement.

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

        private GraphicsContext gc;
        private Canvas canvas;
        private Point center;
        private GlobalGameplayView outer;
        public GlobalDisplay(GlobalGameplayView a){
            outer = a;
        }

        @Override
        public void handle(long now) {

            double timeDifference;

            //Draws the tileSprites
            for (int i = 0; i < tileSprites.length; i++) {
                for (int j = 0; j < tileSprites[0].length; j++) {
                    //if(closeEnoughToCharacter())
                    gc.drawImage(tileSprites[i][j], convertToGraphicPosCol(j), convertToGraphicPosRow(i),100,100);
                }
            }
            //Draws the characterSprite
            gc.drawImage(charachterSprite, center.x, center.y, charachterSprite.getWidth(),charachterSprite.getHeight());


        }

        public void intializeCanvas(){

            canvas = new Canvas( 500, 500 );
            center = new Point(200,200);
            gc = canvas.getGraphicsContext2D();
            //Starts the display of the globalview
            outer.getChildren().addAll(canvas);
            start();


        }

        public double convertToGraphicPosRow(int r){
            double pos = canvas.getHeight()/tileSprites.length*r;
            return pos;
        }

        public double convertToGraphicPosCol(int c){
            double pos = canvas.getWidth()/tileSprites[0].length*c;
            return pos;
        }

    }
}
