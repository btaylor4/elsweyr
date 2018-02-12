import java.io.File;

/**
 * Created by dontf on 2/12/2018.
 */
public class printfilenames {

    public static void main (String [] args) {

        File file;
        String[] controllers;

        file = new File("src\\models\\");
        controllers = file.list();

        for (int i = 0; i < controllers.length; ++i) {
            System.out.println("javac src\\models\\" + controllers[i]);
        }

        file = new File("src\\views");
        controllers = file.list();

        for (int i = 0; i < controllers.length; ++i) {
            System.out.println("javac src\\views\\" + controllers[i]);
        }

        file = new File("src\\controllers\\");
        controllers = file.list();

        for (int i = 0; i < controllers.length; ++i) {
            System.out.println("javac src\\controllers\\" + controllers[i]);
        }

        System.out.println("src\\RunGame.java");
    }
}
