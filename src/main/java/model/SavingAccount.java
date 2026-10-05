package model;

import java.time.LocalDate;

public class SavingAccount extends BankAccount {
    private double savingAmount;
    private LocalDate depositDate;
    private double interestRate;
    private int termMonths;

    public SavingAccount(int id, String accountCode, String ownerName, LocalDate createdDate,
                         double savingAmount, LocalDate depositDate,
                         double interestRate, int termMonths) {
        super(id, accountCode, ownerName, createdDate);
        this.savingAmount = savingAmount;
        this.depositDate = depositDate;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
    }

    @Override
    public String toCsv() {
        return commonCsv() + "," + savingAmount + "," +
                depositDate.format(DATE_FORMAT) + "," + interestRate + "," + termMonths;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Mã TK: %s | Chủ TK: %s | Ngày tạo: %s | " +
                "Tiền gửi: %.2f | Ngày gửi: %s | Lãi suất: %.2f%% | Kỳ hạn: %d tháng",
                id, accountCode, ownerName, createdDate.format(DATE_FORMAT),
                savingAmount, depositDate.format(DATE_FORMAT), interestRate, termMonths
        );
    }
}
