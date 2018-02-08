package views;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.shape.Rectangle;

import java.awt.*;

/**
 * Created by Bryan on 2/7/18.
 */
public class StatusView extends Parent{

    private Rectangle maxHealth;
    private Rectangle currentHealth;

    public StatusView() {
        maxHealth = new Rectangle(0,0, 100, 10);
        currentHealth = new Rectangle(0,0, 100, 10);

        maxHealth.setStrokeWidth(2);
        maxHealth.setStrokeType( StrokeType.OUTSIDE);
        maxHealth.setFill(Color.RED);

        currentHealth.setStrokeType( StrokeType.OUTSIDE);
        currentHealth.setFill(Color.LIMEGREEN);
        this.getChildren().addAll(maxHealth, currentHealth);
    }

    public void updateCharacterHealth(int change) {
        currentHealth.setWidth(currentHealth.getWidth() + change);
    }
}
