package service.service.impl;

import domain.Account;
import domain.Customer;
import domain.Transcation;
import domain.Type;
import repository.Accountrepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    // creating object of customerRepo to call
    private final CustomerRepository customerRepository= new  CustomerRepository();

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
        Account account = accountRepository.findByNumber(accountNumber) // finding the account
                .orElseThrow(()->new RuntimeException("Account not found"+accountNumber));

                 account.setBalance(account.getBalance() + amount); // adding deposited new amount to the account
        Transcation transcation = new Transcation(account.getAccountNumber(),
               amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.DEPOSIT);
        transactionRepository.add(transcation); // transaction detaiil


    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(()->new RuntimeException("Account not found"+accountNumber));
        //check if enough amount is present in bank account.
        if(account.getBalance().compareTo(amount)<0)
            throw new RuntimeException("Insufficient Balance");

        account.setBalance(account.getBalance() - amount); // reducing withdrwan amount from account.
        Transcation transcation = new Transcation(account.getAccountNumber(),
                amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.WITHDRAW);
        transactionRepository.add(transcation);

    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        if(fromAcc.equals(toAcc))
            throw new RuntimeException("Cannot transfer to your own account");

        // fetching from and to accounts
        Account from = accountRepository.findByNumber(fromAcc)
                .orElseThrow(()->new RuntimeException("Account not found " +fromAcc));
        Account to = accountRepository.findByNumber(toAcc)
                .orElseThrow(()->new RuntimeException("Account not found " +toAcc));

        //check if enough amount is present in fromAcc bank account.
        if(from.getBalance().compareTo(amount)<0)
            throw new RuntimeException("Insufficient Balance");
        from.setBalance(from.getBalance() - amount); // reducing withdrwan amount from account .
        to.setBalance(to.getBalance() + amount); // adding withdrwan amount to account .

        //maitaining the transaction of fromAcc in repo
        transactionRepository.add(new Transcation(from.getAccountNumber(),
                amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.TRANSFER_OUT));

        //maitaining the transaction of toAcc in repo
        transactionRepository.add(new Transcation(to.getAccountNumber(),
                amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.TRANSFER_IN));
    }

    @Override
    public List<Transcation> getStatement(String account) {
        return transactionRepository.findByAccount(account).stream()
                .sorted(Comparator.comparing(Transcation::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountsByCustomerName(String query) {
        String q = (query==null)?"":query.toLowerCase();
        List<Account> result = new ArrayList<>();
        for(Customer c: customerRepository.findAll()){
            if(c.getName().toLowerCase().contains(q))
                result.addAll(accountRepository.findByCustomerId(c.getId()));
        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;
    }


    // method to generate account numbers of customers
    private String getAccountnNumber() {
        int size = accountRepository.findAll().size()+1;
        String accountnNumber = String.format("PUNB%06d",size);
        return accountnNumber;
    }
}
