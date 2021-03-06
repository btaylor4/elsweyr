package views;

import controllers.GlobalGameplayController;
import controllers.LocalGameplayController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;
import models.Character;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.*;

import static org.junit.Assert.*;


public class GlobalGameplayViewTest extends ApplicationTest {


    private GlobalGameplayView view;
    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();
        view = new GlobalGameplayView("file:Character1MovementSprites/");
        Scene localScene = new Scene(view, 500, 500);
        window.setScene(localScene);
        window.show();

    }

    @Ignore
    public void tryTest() throws InterruptedException {




        Thread.sleep(1000);
        view.updateMove("DOWN");
        view.updateCharacterPos(new Point(16,15));
        Thread.sleep(1000);
        view.updateMove("LEFT");
        view.updateCharacterPos(new Point(16,16));
        Thread.sleep(1000);
        view.updateMove("UP");
        view.updateCharacterPos(new Point(15,16));
        Thread.sleep(1000);
        view.updateCharacterPos(new Point(15,15));
        Thread.sleep(1000);

    }

}