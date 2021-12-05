package ru.cool.lostResult;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * Пример совместного использования ресурсов
 */
public class InterferenceExample {
    private static final int HUNDRED_MILLION = 100_000_000;
    private AtomicInteger counter = new AtomicInteger();
    private InterferenceThread thread1;
    private InterferenceThread thread2;

    public boolean stop(){
        return counter.incrementAndGet() > HUNDRED_MILLION;
    }

    public void resetCounter(){
        this.counter.set(0);
    }

    public void notSynchronizedExample() throws InterruptedException {
        thread1 = new InterferenceThread(this);
        thread2 = new InterferenceThread(this);
        this.setIncrementType(EnumIncrement.SIMPLE_INCREMENT);
        this.startThreads();
        this.awaitEndThreads();
        System.out.println("Expected: " + HUNDRED_MILLION);
        System.out.println("Not static synchronization");
        System.out.println("\tThread1's result: " + thread1.getIncrValue() +
                "\n" + "\tThread2's result: " + thread2.getIncrValue());
        InterferenceThread.i = 0;
    }

    public void synchronizedExample() throws InterruptedException {
        thread1 = new InterferenceThread(this);
        thread2 = new InterferenceThread(this);
        this.setIncrementType(EnumIncrement.STATIC_SYNCH_INCREMENT);
        this.startThreads();
        this.awaitEndThreads();
        System.out.println("Expected: " + HUNDRED_MILLION);
        System.out.println("Static synchronization");
        System.out.println("\tThread1's result: " + thread1.getIncrValue() +
                "\n" + "\tThread2's result: " + thread2.getIncrValue());
        InterferenceThread.i = 0;
    }

    private void setIncrementType(EnumIncrement type)
    {
        thread1.setEnumType(type);
        thread2.setEnumType(type);
    }

    private void startThreads()
    {
        thread1.start();
        thread2.start();
    }

    private void awaitEndThreads() throws InterruptedException
    {
        thread1.join();
        thread2.join();
    }

}
