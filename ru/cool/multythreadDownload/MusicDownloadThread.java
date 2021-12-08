package ru.cool.multythreadDownload;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

public class MusicDownloadThread extends DownloadThread {

    protected MusicDownloadThread(String listFile) {
        super(listFile);
    }

    private void playMusic() {
        AtomicReference<Path> lastMusicFile = new AtomicReference<>();
        try {
            Files.list(Paths.get(this.getSavePath())).forEach(file ->{
                    lastMusicFile.set(file);
                }
            );
            try(FileInputStream fis = new FileInputStream(lastMusicFile.get().toString())){
                Player player = new Player(fis);
                player.play();
            }catch (IOException e){
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.setSavePath("src/ru/cool/multythreadDownload/music/");
        super.downloadFile();
        this.playMusic();
    }
}
