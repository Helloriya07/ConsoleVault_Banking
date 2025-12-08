package service;

import domain.Account;
import domain.Transcation;

import java.util.List;

public interface BankService {
    String openAccount(String name ,String email,String accountType);
    List<Account> listAccount();
    void deposit(String accountNumber, Double amount, String note);
    void withdraw(String accountNumber, Double amount, String withdrawal);
    void transfer(String from, String to, Double amount, String transfer);
    List<Transcation> getStatement (String account);
    List<Account> searchAccountsByCustomerName(String query);
}
