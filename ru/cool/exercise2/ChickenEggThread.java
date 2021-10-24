package ru.cool.exercise2;

public class ChickenEggThread implements Runnable {

    protected Thread t;
    private String threadName;

    public ChickenEggThread(String threadName){
        this.threadName = threadName;
        t = new Thread(this, threadName);
        t.start();
    }

    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getThreadName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getThreadName(){
        return this.threadName;
    }
}
