package models;

public class SaveFile {
    private String fileName;
    private String dateLastPlayed;
    private String pathToCharacterFile;
    private String pathToMapFile;

    public SaveFile(String fileName, String dateLastPlayed, String pathToCharacterFile, String pathToMapFile) {
        this.fileName = fileName;
        this.dateLastPlayed = dateLastPlayed;
        this.pathToCharacterFile = pathToCharacterFile;
        this.pathToMapFile = pathToMapFile;
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

    public String getPathToCharacterFile() {
        return pathToCharacterFile;
    }

    public void setPathToCharacterFile(String pathToFile) {
        this.pathToCharacterFile = pathToFile;
    }

    public String getPathToMapFile() {
        return pathToMapFile;
    }

    public void setPathToMapFile(String pathToMapFile) {
        this.pathToMapFile = pathToMapFile;
    }
}
