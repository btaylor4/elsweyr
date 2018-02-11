import controllers.InGameMenuController;
import controllers.SaveGameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Character;
import models.GlobalLevel;
import models.ReadFiles;
import views.InGameMenuView;
import views.SaveGameView;

/**
 * Created by Bryan on 2/2/18.
 */
public class RPG extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage primaryWindow = new Stage();

       Character playerCharacter = ReadFiles.loadCharacter("DefaultCharacter.txt");
       GlobalLevel global = ReadFiles.loadGame("DefaultMap.txt");
        InGameMenuView inGameMenuView = new InGameMenuView();
        InGameMenuController inGameMenuController = new InGameMenuController(inGameMenuView, playerCharacter, global);


//        MainMenuView mainMenu = new MainMenuView();
//        MainMenuController mainController = new MainMenuController(mainMenu);
        SaveGameView saveView = new SaveGameView(playerCharacter, global);
        SaveGameController saveController = new SaveGameController(saveView, playerCharacter, global);
        Scene menuScene = new Scene(inGameMenuView, 500, 500);
        primaryWindow.setScene(menuScene);
        primaryWindow.setTitle("Save View");
        primaryWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
