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
import views.LoadGameView;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TestLoadGameController extends ApplicationTest{

    private LoadGameController.backToMainButtonHandler mainButtonHandler;
    private LoadGameController.tableClickedEventHandler tableClickedHandler;
    private LoadGameController.loadGameHandler loadGameHandler;
    private LoadGameController loadGameController;

    private LoadGameView loadGameView;
    private int width = 500;
    private int height = 500;

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();
        loadGameView = new LoadGameView();
        Scene loadGameScene = new Scene(loadGameView, width, height);
        window.setScene(loadGameScene);
        window.show();
    }

    @Test
    public void loadGameTest(){
        loadGameController = new LoadGameController(loadGameView);

        tableClickedHandler = loadGameController.new tableClickedEventHandler();
        MouseEvent mouseEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, width, height, 10, 10, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, false, false, true, null);
        tableClickedHandler.handle(mouseEvent);

        ActionEvent actionEvent = new ActionEvent();
        loadGameHandler = loadGameController.new loadGameHandler();
        loadGameHandler.handle(actionEvent);
    }
}