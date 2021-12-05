package ru.cool.exercise3;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyThread implements Runnable{

    private Path from;
    private Path to;
    private int threadNumber;

    public CopyThread(Path from, Path to, int threadNumber)
    {
        this.from = from;
        this.to = to;
        this.threadNumber = threadNumber;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        this.copy();
    }

    private synchronized void copy()
    {
        Path pathFrom = this.from;
        Path pathTo = this.to;
        FileChannel fileFrom;
        FileChannel fileTo;
        try
        {
            fileFrom = (FileChannel) Files.newByteChannel(pathFrom);
            fileTo = (FileChannel) Files.newByteChannel(pathTo, StandardOpenOption.APPEND);
            fileTo.transferFrom(fileFrom, 0, fileFrom.size());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private int getThreadNumber()
    {
        return this.threadNumber;
    }
}

