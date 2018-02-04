package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by Bryan on 2/4/18.
 */
public class GlobalMovementListener implements EventHandler<ActionEvent> {
    public boolean checkVaildMove() {
        return false;
    }

    public void updateCharacterPosition() {}

    public boolean checkForLocalLevel() {
        return false;
    }

    void passControlToGlobalGamePlay() {}

    @Override
    public void handle(ActionEvent event) {}
}
