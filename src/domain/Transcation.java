package domain;

import java.time.LocalDateTime;

public class Transcation {
    private String accountNumber;
    private Double amount;
    private String id;
    private String note;
    private LocalDateTime timestamp;
    private Type type;

    public Transcation(String accountNumber, Double amount, String id, String note, LocalDateTime timestamp, Type type) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
