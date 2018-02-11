package controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import views.InGameMenuView;

/**
 * Created by dontf on 2/10/2018.
 */
public class InGameMenuViewTest extends ApplicationTest{

    private InGameMenuView view;

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();
        view = new InGameMenuView();
        Scene localScene = new Scene(view, 500, 500);
        window.setScene(localScene);
        window.show();
    }

    @Test
    public void testView(){

    }
}
