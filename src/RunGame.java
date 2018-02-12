import controllers.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainMenuView;

/**
 * Created by Bryan on 2/2/18.
 */
public class RunGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage primaryWindow = new Stage();

        MainMenuView mainMenu = new MainMenuView();
        MainMenuController mainController = new MainMenuController(mainMenu);
        Scene menuScene = new Scene(mainMenu, 500, 500);
        primaryWindow.setScene(menuScene);
        primaryWindow.setTitle("Save View");
        primaryWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
