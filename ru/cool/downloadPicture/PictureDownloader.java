package ru.cool.downloadPicture;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PictureDownloader {

    private static final String PICTURES_LIST_FILE = "src/ru/cool/downloadPicture/picturesList.txt";
    private static final String PICTURES_FOLDER = "src/ru/cool/downloadPicture/pictures/";

    public static void main(String[] args) throws IOException {
        String url = Files.lines(Paths.get(PICTURES_LIST_FILE)).collect(Collectors.joining("\n"));
        if (url.isEmpty()) {
            System.out.println("Файл с ссылками пуст. Введите, пожалуйста, ссылку на картинку вручную.");
            downloadFromInput(PICTURES_FOLDER);
        }else{
            downloadFromFile(PICTURES_LIST_FILE, PICTURES_FOLDER);
        }
    }

    private static void downloadFromFile(String listFile, String picturesFolder) {
        AtomicInteger counter = new AtomicInteger();
        try {
            Files.lines(Paths.get(listFile)).forEach(link -> {
                try {
                    counter.getAndIncrement();
                    URL urlPicture = new URL(link);
                    try(ReadableByteChannel urlChannel = Channels.newChannel(urlPicture.openStream());
                        FileOutputStream fileStream = new FileOutputStream(picturesFolder + "/pic" + counter.get() + ".png")){
                        fileStream.getChannel().transferFrom(urlChannel, 0, Long.MAX_VALUE);
                    }catch (IOException e){
                        System.err.println("Не");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFromInput(String picturesFolder){
        Scanner input = new Scanner(System.in);
        System.out.println("Введите ссылку на картинку");
        String pictureURL = input.nextLine();
        try{
            URL url = new URL(pictureURL);
            System.out.println("Введите имя картинки");
            String pictureName = input.nextLine();
            try(ReadableByteChannel urlChannel = Channels.newChannel(url.openStream());
                FileOutputStream fileStream = new FileOutputStream(picturesFolder + "/" + pictureName + ".png")){
                fileStream.getChannel().transferFrom(urlChannel, 0, Long.MAX_VALUE);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
