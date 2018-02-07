package models;

import java.util.Date;

public class SaveFile {
    private String fileName;
    private String dateLastPlayed;
    private String pathToFile;

    public SaveFile(String fileName, String dateLastPlayed, String pathToFile) {
        this.fileName = fileName;
        this.dateLastPlayed = dateLastPlayed;
        this.pathToFile = pathToFile;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDateLastPlayed() {
        return dateLastPlayed;
    }

    public void setDateLastPlayed(String dateLastPlayed) {
        this.dateLastPlayed = dateLastPlayed;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
