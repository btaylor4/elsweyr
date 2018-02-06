import controllers.LocalGameplayController;
import javafx.application.Application;
import javafx.stage.Stage;
import models.GlobalLevel;
import models.Zone;
import views.LocalGameplayView;
import models.Character;

/**
 * Created by Bryan on 2/2/18.
 */
public class RPG extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        //TODO: this is not the actual start logic, its just testing and showing the local view
        LocalGameplayView localView = new LocalGameplayView();
        Character playerCharacter = new Character();
        Zone localLevel = new Zone();
        LocalGameplayController localController = new LocalGameplayController(localView, playerCharacter, localLevel);
        localView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
