package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class InventoryView extends Parent {   //

    //Declare all the buttons and assets the Load Game view will need

    private FlowPane itemsFlow;
    private Button backToGameButton = new Button("Exit Inventory");
    private Button equipButton = new Button("Equip");
    private Button unEquipButton = new Button("UnEquip");
    private Button dropButton = new Button("Drop");
    private int selectedItemIndex;
    Group root = new Group();

    public InventoryView(){
        selectedItemIndex = -1;
        VBox vbox = new VBox(5);
        HBox buttons = new HBox(5);

        //set up the flowplane for items
        itemsFlow = new FlowPane();
        itemsFlow.setVgap(5);
        itemsFlow.setHgap(5);
        itemsFlow.setPrefWrapLength(550);
        itemsFlow.setMinHeight(450);

        //Add the buttons to the hBox that will be at the bottom
        buttons.getChildren().addAll(backToGameButton,equipButton,unEquipButton, dropButton);
        //Add the flowpane and the buttons hbox to the vbox
        vbox.getChildren().add(itemsFlow);
        vbox.getChildren().add(1, buttons);
        vbox.setMinHeight(300);
        this.getChildren().add(vbox);
    }

    public void initializeSprites(ArrayList<Image> sprites) {
        int i = 0;
        for(Image sprite : sprites) {
            ImageView dummy = new ImageView(sprite);
            dummy.setFitHeight(110);
            dummy.setFitWidth(110);
            itemsFlow.getChildren().add(dummy);
            int finalI = i;
            dummy.setOnMouseClicked(e -> setSelectedItemIndex(finalI));
            ++i;
        }
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }
    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
    public Button getBackToGameButton() {
        return backToGameButton;
    }

    public Button getEquipButton() {
        return equipButton;
    }
    public Button getUnEquipButton() {
        return unEquipButton;
    }
    public Button getDropButton() {
        return dropButton;
    }
    public FlowPane getItemsFlow() {
        return itemsFlow;
    }

    public void addBackToGameListener(EventHandler<ActionEvent> handlerForBackButton) {
        backToGameButton.setOnAction(handlerForBackButton);
    }

    public void addEquipListener(EventHandler<ActionEvent> handlerForEquipButton) {
        equipButton.setOnAction(handlerForEquipButton);
    }

    public void addUnEquipListener(EventHandler<ActionEvent> handlerForUnEquipButton) {
        unEquipButton.setOnAction(handlerForUnEquipButton);
    }

    public void addDropListener(EventHandler<ActionEvent> handlerForDropButton) {
        dropButton.setOnAction(handlerForDropButton);
    }

    public void updateView(ArrayList<Image> sprites) {
        itemsFlow.getChildren().clear();
        initializeSprites(sprites);
    }
}
