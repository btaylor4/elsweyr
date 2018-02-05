package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import models.Character;
import models.GlobalLevel;

import java.awt.*;


/**
 * Created by Bryan on 2/4/18.
 */
public class GlobalMovementListener implements EventHandler<ActionEvent> {

    private boolean checkVaildMove(Point projectedMove, Character character, GlobalLevel map) {
        Point characterPosition = character.getGlobalPos();
        int xPositionChange = (int)(characterPosition.getX() + projectedMove.getX());
        int yPositionChange = (int)(characterPosition.getY() + projectedMove.getY());

        if(xPositionChange < 0 || xPositionChange > map.getGlobalMap()[0].length - 1)
            return false;

        else if(yPositionChange < 0 || yPositionChange > map.getGlobalMap().length - 1)
            return false;

        else
            return true;
    }

    public void updateCharacterPosition(String move, Character character, GlobalLevel map) {
        Point projectedMove;

        switch (move) {
            case "UP": // 8
                projectedMove = new Point(0, -1);
                break;

            case "DOWN": // 2
                projectedMove = new Point(0, 1);
                break;

            case "LEFT": // 4
                projectedMove = new Point(-1, 0);
                break;

            case "RIGHT": //6
                projectedMove = new Point(1, 0);
                break;

            case "END": // 1 DOWN_LEFT
                projectedMove = new Point(-1, 1);
                break;

            case "PAGE_DOWN":  // DOWN_RIGHT
                projectedMove = new Point(1, 1);
                break;

            case "HOME":  // UP_LEFT
                projectedMove = new Point(-1, -1);
                break;


            case "PAGE_UP": // UP_RIGHT
                projectedMove = new Point(1, -1);
                break;

            default:
                projectedMove = new Point(0, 0);
        }

        if(checkVaildMove(projectedMove, character, map)) {
            character.updateGlobalPos(projectedMove);
        }
    }

    public boolean checkForLocalLevel() {
        return false;
    }

    void passControlToGlobalGamePlay() {}

    @Override
    public void handle(ActionEvent event) {}
}
