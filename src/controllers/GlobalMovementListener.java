package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import models.Character;
import models.GlobalLevel;

/**
 * Created by Bryan on 2/4/18.
 */
public class GlobalMovementListener implements EventHandler<ActionEvent> {

    public boolean checkVaildMove(String move, Character character, GlobalLevel map) {
        switch (move) {
            case "UP": // 8
                if(character.getGlobalPos().getY() - 1 >= 0) {
                    return true;
                }
                break;

            case "DOWN": // 2
                if(character.getGlobalPos().getY() + 1 < map.getGlobalMap().length - 1) {
                    return true;
                }
                break;

            case "LEFT": // 4
                if(character.getGlobalPos().getX() - 1 >= 0) {
                    return true;
                }
                break;

            case "RIGHT": //6
                if(character.getGlobalPos().getX() + 1 < map.getGlobalMap()[0].length - 1) {
                    return true;
                }
                break;

            case "END": // 1 DOWN_LEFT
                if(character.getGlobalPos().getY() + 1 < map.getGlobalMap().length - 1) {
                    if(character.getGlobalPos().getX() - 1 >= 0) {
                        return true;
                    }
                }
                break;

            case "PAGE_DOWN":  // DOWN_RIGHT
                if(character.getGlobalPos().getY() + 1 < map.getGlobalMap().length - 1) {
                    if(character.getGlobalPos().getX() + 1 < map.getGlobalMap()[0].length - 1) {
                        return true;
                    }
                }
                break;

            case "HOME":  // UP_LEFT
                if(character.getGlobalPos().getY() - 1 >= 0) {
                    if(character.getGlobalPos().getX() - 1 >= 0) {
                        return true;
                    }
                }
                break;


            case "PAGE_UP": // UP_RIGHT
                if(character.getGlobalPos().getY() - 1 >= 0) {
                    if(character.getGlobalPos().getX() + 1 < map.getGlobalMap()[0].length - 1) {
                        return true;
                    }
                }
                break;
        }

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
