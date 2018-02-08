import controllers.LocalGameplayController;
import controllers.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.GlobalLevel;
import models.Zone;
import views.LocalGameplayView;
import models.Character;
import views.MainMenuView;

/**
 * Created by Bryan on 2/2/18.
 */
public class RPG extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage primaryWindow = new Stage();

//        LocalGameplayView localView = new LocalGameplayView();
//        Character playerCharacter = new Character();
//        Zone localLevel = new Zone();
//
//        GlobalLevel global = new GlobalLevel();
//        LocalGameplayController localController = new LocalGameplayController(localView, playerCharacter, global);

        MainMenuView mainMenu = new MainMenuView();
        MainMenuController mainController = new MainMenuController(mainMenu);
        Scene menuScene = new Scene(mainMenu, 500, 500);
        primaryWindow.setScene(menuScene);
        primaryWindow.setTitle("Main Menu");
        primaryWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
