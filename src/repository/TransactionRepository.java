package repository;

import domain.Transcation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    private final Map<String, List<Transcation>> txByAccount = new HashMap<>();
    //String - Accountnumber Value- listoftransaction

    public void add(Transcation transcation) {
       List<Transcation> list = txByAccount.computeIfAbsent(transcation.getAccountNumber(),
        k -> new ArrayList<>());
       // if no transaction exist create a new array list of fresh transaction.
       list.add(transcation); // add it to the list.
    }

}
