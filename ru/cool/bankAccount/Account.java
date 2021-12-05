package ru.cool.bankAccount;

public class Account{

    private float balance;
    public boolean shouldTake;

    public  void putMoney(float value) throws InterruptedException
    {
        this.balance += value;
    }

    public  void takeMoney(float value) throws InterruptedException
    {
        this.balance -= value;
    }


    public float getBalance()
    {
        return this.balance;
    }

}
