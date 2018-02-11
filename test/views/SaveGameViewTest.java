package views;

import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Character;
import models.GlobalLevel;
import models.ReadFiles;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;


public class SaveGameViewTest extends ApplicationTest{

    private SaveGameView saveGameView;
    private Character character;
    private GlobalLevel map;


    @Before
    public void createCharacter()throws IOException{
        character = ReadFiles.loadCharacter("TheCharacter.txt");
    }

    public void createMap() throws IOException  {
        map = ReadFiles.loadGame("TheMap.txt");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();
        createCharacter();
        createMap();
        saveGameView = new SaveGameView(this.character, this.map);
        Scene saveGameScene = new Scene(saveGameView, 500, 500);
        window.setScene(saveGameScene);
        window.show();
        this.character = new Character();
        this.map = new GlobalLevel();
    }

    @Ignore
    @Test
    public void saveGameTest() {

    }

}
