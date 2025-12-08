package app;

import service.BankService;
import service.service.impl.Bankserviceimpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to ConsoleVault Banking");
        BankService bankService = new Bankserviceimpl(); // object of bank service me assign krege bankserviceimp ka object.
        boolean running =true;
        while (running) {
            System.out.println("""
                    1) Open Account
                    2) Deposit
                    3) Withdraw
                    4) Transfer
                    5) Account statement 
                    6) List accounts
                    7) Search Account by customer name
                    0) Exit 
                    
                    """);
            Scanner sc = new Scanner(System.in);
            System.out.println("CHOOSE");
            String choice = sc.nextLine().trim();
            System.out.println("CHOICE  " + choice);

            switch (choice){
                case "1" -> openAccount(sc,bankService);
                case "2" -> deposit(sc,bankService);
                case "3" -> withdraw(sc,bankService);
                case "4" -> transfer(sc);
                case "5" -> statement(sc);
                case "6" -> listAccount(sc, bankService);
                case "7" -> searchAccount(sc);
                case "0 " -> running = false;

            }

        }

    }

    private static void openAccount(Scanner sc,BankService bankService) {
        System.out.println("Customer Name :");
        String name = sc.nextLine().trim();
        System.out.println("Customer Email :");
        String email = sc.nextLine().trim();
        System.out.println("Account Type (SAVINGS/CURRENT) :");
        String accountType = sc.nextLine().trim();
        System.out.println("Initial Deposit amount(optional,blank for 0 ");
        String initDepo = sc.nextLine().trim();
        Double initial = Double.valueOf(initDepo);
        String accountNumber = bankService.openAccount(name,email,accountType);
        if(initial>0){
            bankService.deposit(accountNumber,initial,"Initital Deposit");
        }
        System.out.println("Account openened : "+accountNumber);
    }

    private static void deposit(Scanner sc,BankService bankService) {
        System.out.println("Account Number :");
        String accountNumber = sc.nextLine().trim();
        System.out.println("Amount :");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.deposit(accountNumber,amount,"Deposit");
        System.out.println("Deposited : ");

    }

    private static void withdraw(Scanner sc,BankService bankService) {
        System.out.println("Account Number :");
        String accountNumber = sc.nextLine().trim();
        System.out.println("Amount :");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.withdraw(accountNumber,amount,"Withdrawal");
        System.out.println("Withdrawn : ");
    }

    private static void transfer(Scanner sc) {
    }

    private static void statement(Scanner sc) {
    }

    private static void listAccount(Scanner sc,BankService bankService) {
        bankService.listAccount().forEach(a->{
            System.out.println(a.getAccountNumber() +" | "+ a.getAccountType() +" | " + a.getBalance());
        });

    }

    private static void searchAccount(Scanner sc) {
    }
}
