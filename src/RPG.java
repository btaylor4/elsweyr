import controllers.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainMenuView;

/**
 * Created by Bryan on 2/2/18.
 */
public class RPG extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage primaryWindow = new Stage();

//        LocalGameplayView localView = new LocalGameplayView();
//        Character playerCharacter = ReadFiles.loadCharacter("DefaultCharacter.txt");
//        Zone localLevel = new Zone();
//
//        GlobalLevel global = ReadFiles.loadGame("DefaultMap.txt");
//        LocalGameplayController localController = new LocalGameplayController(localView, playerCharacter, global);

        MainMenuView mainMenu = new MainMenuView();
        MainMenuController mainController = new MainMenuController(mainMenu);
//        SaveGameView saveView = new SaveGameView(playerCharacter, global);
//        SaveGameController saveController = new SaveGameController(saveView, playerCharacter, global);
        Scene menuScene = new Scene(mainMenu, 500, 500);
        primaryWindow.setScene(menuScene);
        primaryWindow.setTitle("Save View");
        primaryWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
