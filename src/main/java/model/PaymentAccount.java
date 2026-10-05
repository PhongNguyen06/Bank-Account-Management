package model;

import java.time.LocalDate;

public class PaymentAccount extends BankAccount {
    private String cardNumber;
    private double balance;

    public PaymentAccount(int id, String accountCode, String ownerName, LocalDate createdDate,
                          String cardNumber, double balance) {
        super(id, accountCode, ownerName, createdDate);
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    @Override
    public String toCsv() {
        return commonCsv() + "," + cardNumber + "," + balance;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Mã TK: %s | Chủ TK: %s | Ngày tạo: %s | " +
                "Số thẻ: %s | Số tiền: %.2f",
                id, accountCode, ownerName, createdDate.format(DATE_FORMAT),
                cardNumber, balance
        );
    }
}
