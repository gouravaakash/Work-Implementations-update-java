package multithreading;

public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance){
        balance=initialBalance;
    }

    public void transfer(BankAccount targetAccount, int amount) {
        synchronized (this) {
            if (this.balance >= amount) {
                this.balance -= amount;
                synchronized (targetAccount) {
                    targetAccount.balance += amount;
                }
            }
        }
    }

    public int getBalance(){
        return balance;
    }
}
