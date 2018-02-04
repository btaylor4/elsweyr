package controllers;

import javafx.event.*;
import models.Character;
import models.GlobalLevel;
import views.GlobalGameplayView;


/**
 * Created by Bryan on 2/4/18.
 */
public class GlobalGameplayController implements EventHandler<ActionEvent> {
    private Character character;
    private GlobalLevel map;
    private GlobalMovementListener globalMovementListener;
    private InGameMenuListener inGameMenuListener;
    private InventoryListener inventoryListener;  //TODO: Should we have this listener inside of the global gameplay? If so, why?
    private GlobalGameplayView view;

    @Override
    public void handle(ActionEvent event) {}
}
