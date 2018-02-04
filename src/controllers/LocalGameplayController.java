package controllers;

import models.Zone;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.LocalGameplayView;


public class LocalGameplayController implements EventHandler<ActionEvent> {
    private Character character;
    private Zone map;
    private EventHandler<ActionEvent> localMovementListener;
    private EventHandler<ActionEvent> inventoryListener;
    private LocalGameplayView view;



    @Override
    public void handle(ActionEvent event) {

    }
}
