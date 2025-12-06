package service.service.impl;

import domain.Account;
import service.BankService;

import java.util.UUID;

public class Bankserviceimpl implements BankService {
    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerid = UUID.randomUUID().toString();

        //change later to : acc11 when we have list of accounr
        String accountnNumber = UUID.randomUUID().toString();
        Account ac = new Account(accountnNumber,accountType,(double) 0,customerid);
        return "";
    }
}
