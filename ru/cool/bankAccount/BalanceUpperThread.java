package ru.cool.bankAccount;

public class BalanceUpperThread extends Thread{

    private Account bankAccount;
    private float maxValue;

    public BalanceUpperThread(Account bankAccount, float maxValue)
    {
        this.bankAccount = bankAccount;
        this.maxValue = maxValue;
        this.start();
    }

    @Override
    public void run() {
        float money;
        while (this.bankAccount.getBalance() < this.maxValue) {
            try {
                money = (float) (100 + Math.floor(Math.random() * (300 - 100)));
                this.bankAccount.putMoney(money);
                System.out.println("На баланс было зачислено: " + money + "." +
                        " Сумма на балансе: " + this.bankAccount.getBalance());
                if(this.bankAccount.getBalance() >= this.maxValue )
                    this.bankAccount.shouldTake = true;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
