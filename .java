import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int pin) {
        return this.pin == pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private Account account;
    private Scanner scanner;

    public ATM(Account account) {
        this.account = account;
        this.scanner = new Scanner(System.in); // Initialize scanner here
    }

    public void start() {
        System.out.println("Welcome to the ATM.");
        System.out.print("Enter your account number: ");
        int accNumber = scanner.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        if (accNumber == account.getAccountNumber() && account.validatePin(pin)) {
            System.out.println("Login successful.");
            showMenu();
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Balance: $" + account.getBalance());
                break;
            case 2:
                System.out.print("Enter amount to deposit: $");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                System.out.println("Deposited $" + depositAmount);
                break;
            case 3:
                System.out.print("Enter amount to withdraw: $");
                double withdrawAmount = scanner.nextDouble();
                if (account.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawn $" + withdrawAmount);
                } else {
                    System.out.println("Insufficient balance.");
                }
                break;
            case 4:
                System.out.println("Thank you for using the ATM.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        showMenu(); // Show menu again after completion
    }
}

public class mainn {
    public static void main(String[] args) {
        Account account = new Account(123456, 1234, 1000); // Example account
        ATM atm = new ATM(account);
        atm.start();
    }
}
