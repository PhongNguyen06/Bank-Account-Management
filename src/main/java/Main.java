import service.BankAccountService;
import util.InputHelper;

public class Main {
    private static final BankAccountService service = new BankAccountService();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = InputHelper.readInt("Chọn chức năng: ", 1, 5);

            switch (choice) {
                case 1:
                    service.addAccount();
                    break;
                case 2:
                    service.deleteAccount();
                    break;
                case 3:
                    service.displayAll();
                    break;
                case 4:
                    service.search();
                    break;
                case 5:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== CHƯƠNG TRÌNH QUẢN LÝ TÀI KHOẢN NGÂN HÀNG =====");
        System.out.println("1. Thêm mới");
        System.out.println("2. Xóa");
        System.out.println("3. Xem danh sách các tài khoản ngân hàng");
        System.out.println("4. Tìm kiếm");
        System.out.println("5. Thoát");
    }
}
