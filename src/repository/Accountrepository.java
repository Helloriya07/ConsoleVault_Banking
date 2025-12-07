package repository;

import domain.Account;
/*
created an repo of users account
saving the data in repo
implementaion of findAll so that it can return the account numbers of user to get the size.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

}
