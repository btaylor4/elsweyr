package views;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private TextField field;
    private int curLevel;

    public StatusView() {
        curLevel = 1;
        GridPane pane = new GridPane();
        pane.setHgap(10);

        field = new TextField();
        field.setEditable(false);
        field.setPrefWidth(50);
        pane.add(field, 0, 0);

        maxHealth = new Rectangle(0,0, 100, 10);
        maxHealth.setStrokeWidth(2);
        maxHealth.setStrokeType( StrokeType.OUTSIDE);
        maxHealth.setFill(Color.RED);
        pane.add(maxHealth, 1, 0);

        currentHealth = new Rectangle(0,0, 100, 10);
        currentHealth.setStrokeType( StrokeType.OUTSIDE);
        currentHealth.setFill(Color.LIMEGREEN);
        pane.add(currentHealth, 1, 0);

        this.getChildren().addAll(pane);
    }

    public void updateCharacterHealth(int change) {
        currentHealth.setWidth(currentHealth.getWidth() + change);
    }

    public void updateCharacterLevel(int levelChange) {
        curLevel += levelChange;
        field.setText(Integer.toString(curLevel));
    }
}
