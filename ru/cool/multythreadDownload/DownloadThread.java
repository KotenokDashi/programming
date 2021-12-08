package ru.cool.multythreadDownload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class DownloadThread implements Runnable{

    protected Thread t;
    private String listFile;
    private String savePath;
    private int counter = 0;

    protected DownloadThread(String listFile)
    {
        this.listFile = listFile;
        this.t = new Thread(this);
        this.t.start();
    }

    public void downloadFile()
    {
        try
        {
            Files.lines(Paths.get(this.listFile)).forEach(line -> {
                String[] links = line.split("\\s");
                String urlLink = links[0];
                if (links[1].equals(this.getSavePath()))
                {
                    URL url = null;
                    try {
                        try {
                            url = new URL(urlLink);
                        }catch (MalformedURLException e){
                            System.err.println("Не корректно указана ссылка на файл: " + urlLink);
                        }
                        if (url != null) {
                            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                            FileOutputStream fos = new FileOutputStream(this.getSavePath() + this.getFileName());
                            fos.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
                            byteChannel.close();
                            fos.close();
                            this.downloadCompleting();
                        }else
                            System.err.println("Скачивание невозможно");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName()
    {
        if (this.getSavePath().endsWith("music/"))
            return "music" + counter++ + ".mp3";
        else if(this.getSavePath().endsWith("pictures/"))
            return "picture" + counter++ + ".png";
        return "file";
    }

    private void downloadCompleting()
    {
        if (this.getSavePath().endsWith("music/"))
            System.out.println("Загрузка музыки завершена!");
        else if(this.getSavePath().endsWith("pictures/"))
            System.out.println("Загрузка картинки завершена!");
    }

    @Override
    public void run()
    {
        this.downloadFile();
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
