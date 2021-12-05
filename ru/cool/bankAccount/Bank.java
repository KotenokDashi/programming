package ru.cool.bankAccount;

public class Bank {

    public static void main(String[] args) throws InterruptedException {
        int maxValue = 1250;
        Account account = new Account();
        while (true) {
            BalanceUpperThread balanceThread = new BalanceUpperThread(account, maxValue);
            balanceThread.join();
            account.takeMoney(maxValue);
            System.out.println("С баланса было снято: " + maxValue + "." +
                    " Остаток на балансе: " + account.getBalance());
            account.shouldTake = false;
        }
    }
}

