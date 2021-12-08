package ru.cool.downloadMusic;

import java.io.*;
import java.net.URL;
import java.nio.channels.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MusicDownloader {

    private static final String MUSIC_LINK_FILE = "src/ru/cool/downloadMusic/inFile.txt";
    private static final String MUSIC_PACKAGE = "src/ru/cool/downloadMusic/musics/";
    private static int musicAmount = 3;

    /**
     * @NOTE КОРРЕКТНО РАБОТАЕТ ТОЛЬКО C САЙТОМ, УКАЗАННОМ В ФАЙЛЕ inFile.txt. ДЛЯ ДРУГИХ НУЖНО МЕНЯТЬ РЕГУЛЯРНОЕ ВЫРАЖЕНИЕ
     */

    public static void main(String[] args)
    {
        AtomicInteger counter = new AtomicInteger();
        try(BufferedReader reader = new BufferedReader(new FileReader(MUSIC_LINK_FILE)))
        {
            Optional<String> link = Optional.ofNullable(reader.readLine());
            //Если файл пустой, то назначается начальная страница сайта.
            URL url = new URL(link.orElse("https://stand.hitplayer.ru/"));
            BufferedReader pageReader = new BufferedReader(new InputStreamReader(url.openStream()));
            pageReader.lines().forEach(musicElement ->{
                Pattern pattern = Pattern.compile("(?<=data-audio.\\\").+\\w\\d(?=\\\"\\stitle)");
                Matcher matcher = pattern.matcher(musicElement);
                if(matcher.find() && counter.get() < musicAmount)
                {
                    downloadMusic(MUSIC_PACKAGE + "music" + counter.get() + ".mp3", matcher.group());
                    counter.getAndIncrement();
                }else {
                    return;
                }
            });
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Скачивание музыки завершено!");
    }

    private static void downloadMusic(String musicFile, String urlMusic)
    {
        try {
            URL url = new URL(urlMusic);
            try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                 FileOutputStream stream = new FileOutputStream(musicFile)) {
                stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
