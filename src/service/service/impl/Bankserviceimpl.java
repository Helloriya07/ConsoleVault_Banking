package service.service.impl;

import domain.Account;
import domain.Transcation;
import domain.Type;
import repository.Accountrepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/*
This class defines - creating object of accountrepo so that we can fetch the accountdetails of user.
generated customerid
openeing account of user
generates account numbers of user
saving the accountdata in the repo
 */

public class Bankserviceimpl implements BankService {

    // creating object of accountrepository  to call
    private final Accountrepository accountRepository= new Accountrepository();
    // creating object of accountrepostory to call
    private final TransactionRepository transactionRepository= new TransactionRepository();
    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerid = UUID.randomUUID().toString();

        //change later to : acc11 when we have list of account
//        String accountnNumber = UUID.randomUUID().toString();

        String accountnNumber = getAccountnNumber(); // calling accnum that will be stored in string.

        Account ac = new Account(accountnNumber,accountType,(double) 0,customerid); // account create/open ho gaya

        //Save the account details -- we don't have database (map ke object me store krege )
        accountRepository.save(ac);
        // passing object of account to save the account details in account repo.
        return accountnNumber; // account number will be returned in string format.
    }

    @Override
    public List<Account> listAccount() {
        return accountRepository.findAll().stream()
                // findall will get u listofallaccounts from accountrepo convert it into stream.

                .sorted(Comparator.comparing(Account::getAccountNumber))
                // sort the account on the basis of accountNumber

                .collect(Collectors.toList());
                 //collecting the listofaccounts in list.
    }

    @Override
    public void deposit(String accountNumber, Double amount, String note) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(()->new RuntimeException("Account not found"+accountNumber));
                 account.setBalance(account.getBalance() + amount);
        Transcation transcation = new Transcation(account.getAccountNumber(),
               amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.DEPOSIT);
        transactionRepository.add(transcation);


    }


    // method to generate account numbers of customers
    private String getAccountnNumber() {
        int size = accountRepository.findAll().size()+1;
        String accountnNumber = String.format("PUNB%06d",size);
        return accountnNumber;
    }
}
