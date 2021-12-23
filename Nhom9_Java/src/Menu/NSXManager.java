package Menu;
import java.awt.Robot;
import java.util.List;
import java.util.Scanner;
import Controller.NSXController;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.NSX;

/**
 *
 * @author SONY
 */
public class NSXManager {
    public static void clearScreen() {
        try {
            Robot pressbot = new Robot();
            pressbot.keyPress(17); // Holds CTRL key.
            pressbot.keyPress(76); // Holds L key.
            pressbot.keyRelease(17); // Releases CTRL key.
            pressbot.keyRelease(76); // Releases L key.
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    

    public void Action() {
        NSXManager nsxm = new NSXManager();
        NSXController sc = new NSXController();
        try {
            int choose = -1;
            while (choose != 0) {
                String str;
                Thread.sleep(200);
                //System.out.println("");
            System.out.println("");
            System.out.println(" ___________[QUẢN LÝ NHÀ SẢN XUẤT]__________");
            //System.out.println("");
            System.out.println("|                                           |");
            System.out.println("|          1. [THÊM]                        |");
            System.out.println("|          2. [SỬA]                         |");
            System.out.println("|          3. [XÓA]                         |");
            System.out.println("|          4. [TÌM KIẾM]                    |");
            System.out.println("|          5. [SẮP XẾP]                     |");
            System.out.println("|          6. [DANH SÁCH NHÀ SẢN XUẤT]      |");
            System.out.println("|          0. [THOÁT]                       |");
            System.out.println("|___________________________________________|\n");
            System.out.print("choose = ");

                try {
                    choose = new Scanner(System.in).nextInt();

                } catch (Exception e) {
                    choose = -1;
                }

                switch (choose) {
                    case 1:
                        System.out.println("Thực hiện chức năng Add");
                        NSX nAdd = new NSX();
                        int maNSX = sc.insert(nAdd);
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                        break;
                    case 2:
                        System.out.println("Thực hiện chức năng Update");
                        System.out.println("Nhập dữ liệu cần sửa");
                        NSX nUpdate = new NSX();
                        nUpdate.input();
                        if (sc.update(nUpdate)) {
                            System.out.println("Sửa thành công nhà sản xuất có mã: " + nUpdate.getMaNSX());
                        } else {
                            System.out.println("Sửa thất bại: " + nUpdate.getMaNSX());
                        }
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                        break;
                    case 3:
                        System.out.println("Thực hiện chức năng Delete");
                        System.out.print("Nhập mã nhà sản xuất cần xóa: ");
                        NSX nDelete = new NSX();
                        nDelete.setMaNSX(new Scanner(System.in).nextInt());
                        if (sc.delete(nDelete)) {
                            System.out.println("Xóa thành công nhà sản xuất có mã: " + nDelete.getMaNSX());
                        } else {
                            System.out.println("Xóa thất bại: ");
                        }
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                        break;
                    case 4:
                        System.out.println("Thực hiện chức năng Search");
                        System.out.print("Nhập từ khóa cần tìm (name): ");

                        String keywork = new Scanner(System.in).nextLine();
                        List<NSX> list = sc.findByTenNSX(keywork);

                        if (list.size() > 0) {
                            System.out.println("Kết quả tìm kiếm: ");
                            System.out.format("| %-8s | %-24s | %-34s | %-14s |\n", "Mã NSX", "Tên NSX", "Địa chỉ", "Số điện thoại");
                            for (NSX n : list) {
                                n.output();
                            }
                        } else {
                            System.out.println("Không tìm thấy kết quả");
                        }
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                        break;
                    case 5:
                        System.out.println("Thực hiện chức năng Sort");
                        sc.sort();
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                        break;
                    case 6:
                        System.out.println("Thực hiện chức năng Travel");
                        sc.travel();
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                        break;
                    case 0:
                        System.out.println("Thực hiện chức năng Exit");
                        clearScreen();
                    Thread.sleep(50);
                        
                        break;
                    default:
                        System.out.println("Không tồn tại chức năng này");
                        System.out.print("Nhấn phím bất kỳ để tiếp tục!");
                        str = (new Scanner(System.in)).nextLine();
                        nsxm.clearScreen();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();//tb trang thai loi
        }
    }
}
