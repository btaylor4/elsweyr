
package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Character;
import models.*;
import views.GlobalGameplayView;
import views.MainMenuView;
import views.NewGameView;

import java.io.*;


public class NewGameController {


    private Character character;
    private NewGameView view;
    private GlobalLevel global;

    public NewGameController(NewGameView newView) {
        this.character = new Character();
        this.view = newView;
        this.global = new GlobalLevel();
        this.view.addBackToMainListener(new NewGameController.backToMainButtonHandler());
        this.view.addStartGameListener(new NewGameController.startNewGameButtonHandler());
        this.view.addTableClickListener(new NewGameController.tableClickedEventHandler());
    }


    class startNewGameButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            SaveFile saveFile = view.getSelectedFile();

            // there are files to be deleted
            deleteCurrentFile(saveFile.getPathToMapFile());
            deleteCurrentFile(saveFile.getPathToCharacterFile());

            // create new files from default // input path is to determine saveSlot#
            System.out.println(saveFile.getPathToCharacterFile());
            createMapAndCharacterFiles(saveFile.getPathToCharacterFile());


            //Set up custom character settings
            try {
                character = ReadFiles.loadCharacter("DefaultCharacter.txt");
                global = ReadFiles.loadGame("DefaultMap.txt");
            } catch (IOException e) {
                System.out.println("Game Files Not Found");
                e.printStackTrace();
            }


            character.setCharacterSpritePath(view.getSelectedCharacterFilePath());
            System.out.println("Chosen file path: "+ view.getSelectedCharacterFilePath());
            try {
                character.createCharacterImage();
            } catch (FileNotFoundException e) {
                System.out.println("Default Image Used, could not find selected image");
            }

            character.setCharacterName(view.getSelectedName());
            character.setOnLocal(false);

            System.out.println("Go to global gameplay");
            GlobalGameplayView globalView = new GlobalGameplayView(view.getSelectedCharacterFilePath());
            Scene globalScene = new Scene(globalView, 500, 500);
            GlobalGameplayController globalController = new GlobalGameplayController(globalView,character,global);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(globalScene);

        }

        private void deleteCurrentFile(String fileName) {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }

        private void createMapAndCharacterFiles(String pathToCreateIn) {
            String saveSlot = "SaveSlot";

            File map = new File("DefaultMap.txt");
            File character = new File("DefaultCharacter.txt");

            File newMap;
            File newCharacter;

            for (int i = 1; i < 4; ++i) {
                System.out.println(pathToCreateIn.contains(saveSlot + i));
                if (pathToCreateIn.contains(saveSlot + i)) {
                    newMap = new File(saveSlot + i + File.separator + map.getName());
                    newCharacter = new File(saveSlot + i + File.separator + character.getName());

                    try {
                        newMap.createNewFile();
                        newCharacter.createNewFile();
                        Character c = ReadFiles.loadCharacter(newCharacter.getName());
                        c.setCharacterSpritePath(view.getSelectedCharacterFilePath());
                        c.setCharacterName(view.getSelectedName());

                        Write write = new Write();
                        write.setPath("SaveSlot" + i + File.separator);
                        write.writeCharacterFile("SaveSlot" + i + File.separator, c);

                        copyDefaultToNewFile(map, newMap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }

        }

        private void copyDefaultToNewFile(File input, File output) throws IOException {
            int bytesRead;
            byte[] chunk = new byte[512];

            FileInputStream in = new FileInputStream(input);
            FileOutputStream out = new FileOutputStream(output);

            while ((bytesRead = in.read(chunk)) > 0) {
                out.write(chunk, 0, bytesRead);
            }

            out.close();
            in.close();
        }

    }

    class tableClickedEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            view.enableCreateButton();
        }
    }

    class backToMainButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Back To Main Menu Buttonstuff");
            MainMenuView mainView = new MainMenuView();
            MainMenuController mainController = new MainMenuController(mainView);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(mainView, 500, 500);
            window.setTitle("Main Menu");
            window.setScene(mainScene);
        }
    }
}