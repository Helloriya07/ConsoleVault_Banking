package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to ConsoleVault Banking");
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
                case "1" -> openAccount(sc);
                case "2" -> deposit(sc);
                case "3" -> withdraw(sc);
                case "4" -> transfer(sc);
                case "5" -> statement(sc);
                case "6" -> listAccount(sc);
                case "7" -> searchAccount(sc);
                case "0 " -> running = false;

            }

        }

    }

    private static void openAccount(Scanner sc) {
        System.out.println("Customer Name :");
        String name = sc.nextLine().trim();
        System.out.println("Customer Email :");
        String email = sc.nextLine().trim();
        System.out.println("Account Type (SAVINGS?CURRENT) :");
        String accType = sc.nextLine().trim();
        System.out.println("Initial Deposit amount(optional,blank for 0 ");
        String initDepo = sc.nextLine().trim();
        Double initial = Double.valueOf(initDepo);
    }

    private static void deposit(Scanner sc) {
    }

    private static void withdraw(Scanner sc) {
    }

    private static void transfer(Scanner sc) {
    }

    private static void statement(Scanner sc) {
    }

    private static void listAccount(Scanner sc) {
    }

    private static void searchAccount(Scanner sc) {
    }
}
