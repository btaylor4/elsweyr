package views;

import controllers.DeathController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class DeathViewTest extends ApplicationTest {

    private DeathView view;
    private DeathController deathController;
    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();
        view = new DeathView();
        deathController = new DeathController(view);
        Scene localScene = new Scene(view, 500, 500);
        window.setScene(localScene);
        window.show();

    }

    @Test
    public void tryTest() throws InterruptedException {
        Thread.sleep(2000);


    }

}