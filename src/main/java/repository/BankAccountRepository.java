package repository;

import model.BankAccount;
import model.PaymentAccount;
import model.SavingAccount;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankAccountRepository {
    private static final String FILE_PATH = "data/bank_accounts.csv";
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BankAccountRepository() {
        ensureFileExists();
    }

    public List<BankAccount> findAll() {
        List<BankAccount> accounts = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        try {
            if (!Files.exists(path)) {
                return accounts;
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                BankAccount account = fromCsv(line);
                if (account != null) {
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file dữ liệu: " + e.getMessage());
        }

        return accounts;
    }

    public void saveAll(List<BankAccount> accounts) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (BankAccount account : accounts) {
                writer.write(account.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Không thể ghi file dữ liệu: " + e.getMessage());
        }
    }

    public void append(BankAccount account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(account.toCsv());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Không thể thêm dữ liệu vào file: " + e.getMessage());
        }
    }

    private BankAccount fromCsv(String line) {
        try {
            String[] parts = line.split(",", -1);

            if (parts.length == 8) {
                return new SavingAccount(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        LocalDate.parse(parts[3].trim(), DATE_FORMAT),
                        Double.parseDouble(parts[4].trim()),
                        LocalDate.parse(parts[5].trim(), DATE_FORMAT),
                        Double.parseDouble(parts[6].trim()),
                        Integer.parseInt(parts[7].trim())
                );
            }

            if (parts.length == 6) {
                return new PaymentAccount(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        LocalDate.parse(parts[3].trim(), DATE_FORMAT),
                        parts[4].trim(),
                        Double.parseDouble(parts[5].trim())
                );
            }
        } catch (Exception e) {
            System.out.println("Bỏ qua dòng dữ liệu không hợp lệ: " + line);
        }

        return null;
    }

    private void ensureFileExists() {
        try {
            Path path = Paths.get(FILE_PATH);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("Không thể tạo file dữ liệu: " + e.getMessage());
        }
    }
}
