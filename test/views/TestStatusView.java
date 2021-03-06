package views;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bryan on 2/7/18.
 */
public class TestStatusView extends ApplicationTest {
    private StatusView statusView;
    private Stage window;
    private Scene localScene;

    @Test
    public void testLevelChange() throws InterruptedException {
        Timer apply = new Timer();
        apply.schedule(new TimerTask() {
            @Override
            public void run() {
                statusView.updateCharacterLevel(1);
            }
        }, 0, 1000);

        AnimationTimer animate = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {

                try {
                    window.setScene(localScene);
                    window.show();
                } catch (Exception e) {
                    // Do nothing
                    e.printStackTrace();
                }
            }
        };

        Thread.sleep(4000);
    }

    @Ignore
    public void testHealthChanges() throws InterruptedException {
        Timer apply = new Timer();
        apply.schedule(new TimerTask() {
            @Override
            public void run() {
                statusView.updateCharacterHealth(-1);
            }
        }, 0, 1000);

       AnimationTimer animate = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {

                try {
                    window.setScene(localScene);
                    window.show();
                } catch (Exception e) {
                    // Do nothing
                    e.printStackTrace();
                }
            }
        };

        animate.start();
        Thread.sleep(10000);

        apply.cancel();
        animate.stop();
        window.close();
        Thread.sleep(5000);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = new Stage();
        statusView = new StatusView();
        localScene = new Scene(statusView, 500, 500);
        window.setScene(localScene);
        window.show();
    }
}
