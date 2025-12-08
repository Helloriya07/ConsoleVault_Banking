package repository;

import domain.Account;
import domain.Customer;
/*
created an repo of users account
saving the data in repo
implementaion of findAll so that it can return the account numbers of user to get the size.
 */

import java.util.*;

// creating repository to save the account details of customers
public class Accountrepository {
    private final Map<String, Account> accountsByNumber = new HashMap<>();


    public void save(Account account){
        accountsByNumber.put(account.getAccountNumber(),account);
        // key - accountnumber , value - accountobject
    }
    public final List<Account> findAll(){
        return new ArrayList<>(accountsByNumber.values()); // fetches account numbers

    }

    public Optional<Account> findByNumber(String accountNumber) {
        return Optional.ofNullable(accountsByNumber.get(accountNumber));
    }

    public List<Account> findByCustomerId(String customerId) {

        List<Account> result = new ArrayList<>();
        for(Account a: accountsByNumber.values()){
            if(a.getCustomerId().equals(customerId))
                result.add(a);
    }
        return result;
}
}