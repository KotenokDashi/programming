package ru.cool.multythreadDownload;

public class MainDownload {

    private static final String FILE_LIST_PATH = "src/ru/cool/multythreadDownload/filesList.txt";

    public static void main(String[] args) {
        new PictureDownloadThread(FILE_LIST_PATH);
        new MusicDownloadThread(FILE_LIST_PATH);
    }
}
