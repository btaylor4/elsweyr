package views;

import javafx.animation.AnimationTimer;
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

    private Button inGameMenuButton = new Button("In-Game Menu");
    private Button changeToLocal = new Button("Change To Local View");
    private Image charachterSprite;
    private Image [][] tileSprites;
    private Image[][] itemSprites;
    private Image [][] terrainSprites;
    private Image [][] decalSprites;
    private Point globalCharacterPos;
    Group root = new Group();
    Scene localScene = new Scene(root,800,800);

    public GlobalGameplayView(){
        globalCharacterPos.x = 0;
        globalCharacterPos.y = 0;
        tileSprites = new Image[5][5];

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10,10,10,10));

        grid.add(inGameMenuButton, 1,0);
        grid.add(changeToLocal,4,4);
        this.getChildren().addAll(grid);
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

    public class globalDisplay extends AnimationTimer{

        private GraphicsContext characterS;
        private Canvas canvas;
        @Override
        public void handle(long now) {

            characterS.drawImage(charachterSprite, convertToGraphicPosX(globalCharacterPos.x) , convertToGraphicPosY(globalCharacterPos.y));

            for(int i = 0; i < tileSprites[0].length; i++)
            {

            }
        }

        public void graphicsStuff(){

            canvas = new Canvas( 512, 512 );
            characterS = canvas.getGraphicsContext2D();
        }

        public int convertToGraphicPosX(int x){
            return x;
        }

        public int convertToGraphicPosY(int y){
            return y;
        }

    }
}
