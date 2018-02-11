package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class InventoryView extends Parent {   //

    //Declare all the buttons and assets the Load Game view will need

    private FlowPane itemsFlow;
    private Button backToGameButton = new Button("Exit Inventory");
    private Button equipButton = new Button("Equip");
    private Button unEquipButton = new Button("UnEquip");
    private Button dropButton = new Button("Drop");
    private int selectedItemIndex;
    private int equippedItemIndex;
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
        disableButtons();
        buttons.getChildren().addAll(backToGameButton,equipButton,unEquipButton, dropButton);
        //Add the flowpane and the buttons hbox to the vbox
        vbox.getChildren().add(itemsFlow);
        vbox.getChildren().add(1, buttons);
        vbox.setMinHeight(300);
        this.getChildren().add(vbox);
    }

    public void initializeSprites(ArrayList<Image> sprites, int indexOfEquippedItem) {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.RED);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        setEquippedItemIndex(indexOfEquippedItem);

        int i = 0;
        for(Image sprite : sprites) {
            ImageView imageView = new ImageView(sprite);
            imageView.setFitHeight(110);
            imageView.setFitWidth(110);
            ImageView equippedPlaceHolder = new ImageView();
            equippedPlaceHolder.setFitWidth(25);
            equippedPlaceHolder.setFitHeight(25);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(imageView);
            stackPane.getChildren().add(equippedPlaceHolder);
            stackPane.setAlignment(Pos.BASELINE_RIGHT);
            itemsFlow.getChildren().add(stackPane);
            int finalI = i;
            imageView.setOnMouseClicked(e -> setSelectedItem(finalI, borderGlow, imageView));
            ++i;
        }
        updateEquippedBadge(getEquippedItemIndex());
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }
    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
    public void setEquippedItemIndex(int equippedItemIndex) { this.equippedItemIndex = equippedItemIndex; }
    public int getEquippedItemIndex() { return equippedItemIndex; }
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

    public void updateItems (ArrayList<Image> sprites) {
        itemsFlow.getChildren().clear();
        initializeSprites(sprites, getEquippedItemIndex());
    }

    private void setSelectedItem(int i, DropShadow boderGlow, ImageView imageView) {
        if (getSelectedItemIndex() != -1) {
            StackPane stackPane = (StackPane) itemsFlow.getChildren().get(getSelectedItemIndex());
            stackPane.getChildren().get(0).setEffect(null);
        }
        if (getSelectedItemIndex() == -1) {
            dropButton.setDisable(false);
        }

        setSelectedItemIndex(i);
        if (getSelectedItemIndex() == getEquippedItemIndex()) {
            equipButton.setDisable(true);
            unEquipButton.setDisable(false);
        }
        if (getSelectedItemIndex() != getEquippedItemIndex()) {
            equipButton.setDisable(false);
            unEquipButton.setDisable(true);
        }
        imageView.setEffect(boderGlow);
    }

    public void updateEquippedBadge(int index) {
        removeBadgeFromPreviouslyEquipped();

        if (index != -1) {
            StackPane stackPane = (StackPane) itemsFlow.getChildren().get(index);
            ImageView imageView = (ImageView) stackPane.getChildren().get(1);
            imageView.setImage((new Image("file:PlaceHolderForImages/EquippedBadge.png", 100., 100., true, true)));
            setEquippedItemIndex(index);
        }
    }

    public void removeBadgeFromPreviouslyEquipped() {
        if (getEquippedItemIndex() != -1) {
            StackPane stackPaneOldEquipped = (StackPane) itemsFlow.getChildren().get(getEquippedItemIndex());
            ImageView imageViewOldEquipped = (ImageView) stackPaneOldEquipped.getChildren().get(1);
            imageViewOldEquipped.setImage(null);
            setEquippedItemIndex(-1);
        }
    }

    public void disableButtons() {
        equipButton.setDisable(true);
        unEquipButton.setDisable(true);
        dropButton.setDisable(true);
    }
}
