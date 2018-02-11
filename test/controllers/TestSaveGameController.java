package controllers;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import models.*;
import models.Character;
import models.GlobalLevel;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import views.SaveGameView;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class TestSaveGameController extends ApplicationTest{
    private SaveGameController.backToGameButtonHandler localMovementHandler;
    private SaveGameController.saveSlotOneHandler saveSlotOneHandler;
    private SaveGameController.saveSlotTwoHandler saveSlotTwoHandler;
    private SaveGameController.saveSlotThreeHandler saveSlotThreeHandler;
    private SaveGameController.saveGameHandler saveGameHandler;
    private SaveGameController saveGameController;

    private SaveGameView saveGameView;
    private Character character;
    private GlobalLevel map;
    private int width = 500;
    private int height = 500;


    public void createCharacter() throws IOException {
        this.character =  ReadFiles.loadCharacter("DefaultCharacter.txt");
    }

    public GlobalLevel createMap() throws IOException  {
        return ReadFiles.loadGame("Defaultmap.txt");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();

        this.character = createCharacter();
        this.map = createMap();
        saveGameView = new SaveGameView(this.character, this.map);

        Scene saveGameScene = new Scene(saveGameView, width, height);
        window.setScene(saveGameScene);
        window.show();
    }

    @Test
    public void saveGameTest() {
        saveGameController = new SaveGameController(saveGameView, this.character, this.map);

        saveSlotOneHandler = saveGameController.new saveSlotOneHandler();
        MouseEvent mouseEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, width, height, 10, 10, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, false, false, true, null);
        saveSlotOneHandler.handle(mouseEvent);

        saveGameHandler = saveGameController.new saveGameHandler();
        saveGameHandler.handle(mouseEvent);
    }

}
