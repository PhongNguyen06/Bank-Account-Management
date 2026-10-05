package service;

import exception.NotFoundBankAccountException;
import model.BankAccount;
import model.PaymentAccount;
import model.SavingAccount;
import repository.BankAccountRepository;
import util.InputHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BankAccountService {
    private final BankAccountRepository repository;

    public BankAccountService() {
        repository = new BankAccountRepository();
    }

    public void addAccount() {
        System.out.println("\n===== THÊM MỚI TÀI KHOẢN =====");

        List<BankAccount> accounts = repository.findAll();
        int newId = getNextId(accounts);

        System.out.println("1. Tài khoản tiết kiệm");
        System.out.println("2. Tài khoản thanh toán");
        int type = InputHelper.readInt("Chọn loại tài khoản: ", 1, 2);

        String accountCode = InputHelper.readRequired("Mã tài khoản: ");
        String ownerName = InputHelper.readRequired("Tên chủ tài khoản: ");
        LocalDate createdDate = InputHelper.readDate("Ngày tạo tài khoản (dd/MM/yyyy): ");

        BankAccount account;

        if (type == 1) {
            double savingAmount =
                    InputHelper.readPositiveDouble("Số tiền gửi tiết kiệm: ");
            LocalDate depositDate =
                    InputHelper.readDate("Ngày gửi tiết kiệm (dd/MM/yyyy): ");
            double interestRate =
                    InputHelper.readPositiveDouble("Lãi suất (%): ");
            int termMonths =
                    InputHelper.readPositiveInt("Kỳ hạn (số tháng): ");

            account = new SavingAccount(
                    newId, accountCode, ownerName, createdDate,
                    savingAmount, depositDate, interestRate, termMonths
            );
        } else {
            long cardNumber =
                    InputHelper.readPositiveLong("Số thẻ: ");
            double balance =
                    InputHelper.readPositiveDouble("Số tiền trong tài khoản: ");

            account = new PaymentAccount(
                    newId, accountCode, ownerName, createdDate,
                    String.valueOf(cardNumber), balance
            );
        }

        repository.append(account);
        System.out.println("Thêm tài khoản thành công.");
    }

    public void deleteAccount() {
        System.out.println("\n===== XÓA TÀI KHOẢN =====");
        String accountCode = InputHelper.readRequired("Nhập Mã tài khoản cần xóa: ");

        try {
            List<BankAccount> accounts = repository.findAll();
            BankAccount account = findByAccountCode(accounts, accountCode);

            String answer = InputHelper.readYesNo(
                    "Bạn có chắc chắn muốn xóa tài khoản " +
                    account.getAccountCode() + " - " +
                    account.getOwnerName() + "? (Yes/No): "
            );

            if (answer.equals("no")) {
                return;
            }

            accounts.remove(account);
            repository.saveAll(accounts);

            System.out.println("Xóa tài khoản thành công.");
            System.out.println("\n===== DANH SÁCH SAU KHI XÓA =====");
            printAccounts(accounts);

        } catch (NotFoundBankAccountException e) {
            System.out.println(e.getMessage());
            InputHelper.pressEnter();
        }
    }

    public void displayAll() {
        System.out.println("\n===== DANH SÁCH TÀI KHOẢN NGÂN HÀNG =====");
        List<BankAccount> accounts = repository.findAll();

        if (accounts.isEmpty()) {
            System.out.println("Danh sách tài khoản đang trống.");
            return;
        }

        printAccounts(accounts);
    }

    public void search() {
        System.out.println("\n===== TÌM KIẾM TÀI KHOẢN =====");
        String keyword = InputHelper.readRequired("Nhập Mã tài khoản hoặc Tên chủ tài khoản: ");

        List<BankAccount> accounts = repository.findAll();
        String lowerKeyword = keyword.toLowerCase();

        List<BankAccount> result = accounts.stream()
                .filter(account ->
                        account.getAccountCode().toLowerCase().contains(lowerKeyword)
                                || account.getOwnerName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy tài khoản phù hợp.");
            return;
        }

        System.out.println("===== KẾT QUẢ TÌM KIẾM =====");
        printAccounts(result);
    }

    private BankAccount findByAccountCode(List<BankAccount> accounts, String accountCode)
            throws NotFoundBankAccountException {
        for (BankAccount account : accounts) {
            if (account.getAccountCode().equalsIgnoreCase(accountCode)) {
                return account;
            }
        }

        throw new NotFoundBankAccountException();
    }

    private int getNextId(List<BankAccount> accounts) {
        int maxId = 0;

        for (BankAccount account : accounts) {
            if (account.getId() > maxId) {
                maxId = account.getId();
            }
        }

        return maxId + 1;
    }

    private void printAccounts(List<BankAccount> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("Danh sách tài khoản đang trống.");
            return;
        }

        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }
}
