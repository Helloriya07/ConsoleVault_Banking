package domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transcation {
    private String id;
    private Type type;
    private String accountNumber;
    private Double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transcation(String id, Type type, String accountNumber, Double amount, LocalDateTime timestamp, String note) {
        this.id = id;
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }
}
