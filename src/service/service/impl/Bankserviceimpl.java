package service.service.impl;

import domain.Account;
import repository.Accountrepository;
import service.BankService;

import java.util.UUID;

public class Bankserviceimpl implements BankService {

    // creating object of accountrepository  to call
    private final Accountrepository accountRepository= new Accountrepository();
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

    // method to generate account numbers of customers
    private String getAccountnNumber() {
        int size = accountRepository.findAll().size()+1;
        String accountnNumber = String.format("PUNB%6d",size);
        return accountnNumber;
    }
}
