package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class BankAccount {
    protected int id;
    protected String accountCode;
    protected String ownerName;
    protected LocalDate createdDate;

    protected static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BankAccount(int id, String accountCode, String ownerName, LocalDate createdDate) {
        this.id = id;
        this.accountCode = accountCode;
        this.ownerName = ownerName;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public abstract String toCsv();

    protected String commonCsv() {
        return id + "," + accountCode + "," + ownerName + "," + createdDate.format(DATE_FORMAT);
    }

    @Override
    public abstract String toString();
}
