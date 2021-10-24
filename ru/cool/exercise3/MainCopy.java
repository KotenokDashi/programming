package ru.cool.exercise3;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainCopy {
    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        MainCopy.multithreadingCopy("src/ru/cool/files/from/copyableFile.txt",
                "src/ru/cool/files/from/copyableFile2.txt",
                "src/ru/cool/files/to/insertFile.txt");
        long finish = System.nanoTime();
        long runtime = finish - start;
        System.out.println(runtime);
    }

    static void simpleCopying(String from, String to) throws IOException
    {
        Path pathFrom = Paths.get(from);
        Path pathTo = Paths.get(to);
        FileChannel fileFrom = (FileChannel) Files.newByteChannel(pathFrom);
        FileChannel fileTo = (FileChannel) Files.newByteChannel(pathTo, StandardOpenOption.WRITE);
        fileTo.transferFrom(fileFrom, 0, fileFrom.size());
    }

    static void doubleCopying(String firstFile, String secondFile, String to) throws IOException
    {
        Path pathFrom = Paths.get(firstFile);
        Path pathTo = Paths.get(to);
        FileChannel fileFrom = (FileChannel) Files.newByteChannel(pathFrom);
        FileChannel fileTo = (FileChannel) Files.newByteChannel(pathTo, StandardOpenOption.APPEND);
        fileFrom.transferTo(0, fileFrom.size(), fileTo);
        pathFrom = Paths.get(secondFile);
        fileFrom = (FileChannel) Files.newByteChannel(pathFrom);
        fileFrom.transferTo(0, fileFrom.size(), fileTo);
    }

    static void multithreadingCopy(String firstFilePath, String secondFilePath, String to)
    {
        CopyThread firstFile = new CopyThread(
                Paths.get(firstFilePath),
                Paths.get(to),
                1);
        CopyThread secondFile = new CopyThread(
                Paths.get(secondFilePath),
                Paths.get(to),
                2);
    }

}
