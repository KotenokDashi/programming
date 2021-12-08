package ru.cool.multythreadDownload;


public class PictureDownloadThread extends DownloadThread {

    protected PictureDownloadThread(String listFile) {
        super(listFile);
    }

    @Override
    public void run() {
        super.setSavePath("src/ru/cool/multythreadDownload/pictures/");
        super.downloadFile();
    }
}
