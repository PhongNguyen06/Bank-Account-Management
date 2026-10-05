package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private InputHelper() {
    }

    public static String readRequired(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Dữ liệu bắt buộc, không được để trống.");
        }
    }

    public static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Vui lòng nhập số từ " + min + " đến " + max + ".");
        }
    }

    public static int readPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Giá trị phải là số nguyên dương.");
        }
    }

    public static long readPositiveLong(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            try {
                long number = Long.parseLong(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Giá trị phải là số dương.");
        }
    }

    public static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim().replace(',', '.');

            try {
                double number = Double.parseDouble(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Giá trị phải là số dương.");
        }
    }

    public static LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            try {
                return LocalDate.parse(value, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
            }
        }
    }

    public static String readYesNo(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y")) {
                return "yes";
            }

            if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("n")) {
                return "no";
            }

            System.out.println("Vui lòng nhập Yes hoặc No.");
        }
    }

    public static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static void pressEnter() {
        System.out.println("Nhấn Enter để quay lại menu chính...");
        scanner.nextLine();
    }
}
