package ru.cool.exercise1;

import java.util.Random;

public class AnimalThread implements Runnable{

    private String threadName;
    private int threadPriority;
    private AnimalThread otherThread;
    private int animalDistance;

    public AnimalThread(String threadName, int threadPriority){
        this.threadName = threadName;
        this.threadPriority = threadPriority;
        Thread t = new Thread(this, threadName);
        t.setPriority(threadPriority);
        t.start();
    }

    public void run() {
        animalDistance = 0;
        int speedFactor = 10;
        while(animalDistance < this.getMaxDistance())
        {
            animalDistance++;
            System.out.println(this.threadName + " пробежал " + animalDistance);
            try {
                Thread.sleep(50 * (speedFactor / this.getThreadPriority()));
            }catch (Exception e) {
                e.printStackTrace();
            }
            if(this.getOtherThread().getThreadDistance() == (this.getMaxDistance() / 2)
                    && this.getThreadDistance() < this.getOtherThread().getThreadDistance())
            {
                this.overtakeThread();  //гарантия того, что отстающий поток догонит опережающий
            }
        }
        System.out.println(this.threadName + " добежал до финиша!");
    }

    private void overtakeThread(){
        //немного костыль. можно сделать подбор приоритета который меньше приоритета текущего потока
        this.getOtherThread().setThreadPriority(1);
        this.setThreadPriority(10);
        System.out.println(this.threadName + " отстает, и решает ускориться!");
    }

    private final int getMaxDistance()
    {
        return 100;
    }

    public int getThreadDistance()
    {
        return this.animalDistance;
    }

    public int getThreadPriority()
    {
        return this.threadPriority;
    }

    public void setThreadPriority(int priority)
    {
        this.threadPriority = priority;
    }

    public void setOtherThread(AnimalThread thread)
    {
        this.otherThread = thread;
    }

    public AnimalThread getOtherThread()
    {
        return this.otherThread;
    }

}
