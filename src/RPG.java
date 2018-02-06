import controllers.LocalGameplayController;
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

/**
 * Created by Bryan on 2/2/18.
 */
public class RPG extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage primaryWindow = new Stage();

        LocalGameplayView localView = new LocalGameplayView();
        Character playerCharacter = new Character();
        Zone localLevel = new Zone();

        GlobalLevel global = new GlobalLevel();
        LocalGameplayController localController = new LocalGameplayController(localView, playerCharacter, global);

        Scene localScene = new Scene(localView, 500, 500);
        primaryWindow.setScene(localScene);
        primaryWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
