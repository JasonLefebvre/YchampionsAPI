// This class represents a bank account
public class BankAccount {
    // The ID of the bank account
    private int id;
    // The current balance of the account
    private int balance;

    // Constructor: Initializes the id and balance of the account
    public BankAccount(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

   // Method to get the ID of the bank account
   public int getId() {
       return this.id;
    }

   // Method to get the balance of the bank account
   public int getBalance() {
       return this.balance;
    }

   // Method to set the balance of the bank account
   public void setBalance(int balance) {
       this.balance = balance;
    }
}