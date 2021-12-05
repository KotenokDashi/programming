package ru.cool.lostResult;

/**
 * Лаунчер
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new InterferenceExample().notSynchronizedExample();
        new InterferenceExample().synchronizedExample();
    }
}
