package ru.cool.lostResult;

/**
 * Класс для инкрементирования общей переменной
 */
public class InterferenceThread extends Thread {
    private final InterferenceExample checker;
    private EnumIncrement incrementType;
    public static int i;

    public InterferenceThread(InterferenceExample checker) {
        this.checker = checker;
    }

    private synchronized static void synchronizedIncrement()
    {
        i++;
    }
    
    private void simpleIncrement()
    {
        i++;
    }

    public int getIncrValue() {
        return i;
    }

    public void run() {
        switch (incrementType) {
            case STATIC_SYNCH_INCREMENT:
                while (!this.checker.stop()) {
                    this.synchronizedIncrement();
                }
                this.checker.resetCounter();
                break;
            case SIMPLE_INCREMENT:
                while (!this.checker.stop()) {
                    this.simpleIncrement();
                }
                this.checker.resetCounter();
                break;
        }
    }

    public void setEnumType(EnumIncrement type)
    {
        this.incrementType = type;
    }
}

