package Menu;

import Controller.HoaDonController;
import Controller.KhachHangController;
import Controller.NhanVienController;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;
import java.awt.Robot;

import java.util.List;
import java.util.Scanner;

public class QuanLyHoaDon {
     
    public void Action() {
        KhachHangController khc = new KhachHangController();
        NhanVienController nvc = new NhanVienController();
        HoaDonController hd = new HoaDonController();
        System.out.println("        CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI BÀI CỦA PHẠM ĐÌNH THẮNG");
        System.out.println("           TRƯỜNG ĐẠI HỌC CÔNG NGHỆ GIAO THÔNG VẬN TẢI");
        System.out.println("                        NHÓM 9- JAVA");
        //hd.readFile();
        try {
            int choose;
            int check=0;
            do {
                //System.out.println("");
                System.out.println("");
                System.out.println(" ____________________[QUẢN LÝ HOÁ ĐƠN]______________________");
                //System.out.println("");
                System.out.println("|                                                           |");
                System.out.println("|          1. [THÊM]                                        |");
                System.out.println("|          2. [SỬA]                                         |");
                System.out.println("|          3. [XÓA](Theo mã hóa đơn)                        |");
                System.out.println("|          4. [TÌM KIẾM](Theo mã hóa đơn)                   |");
                System.out.println("|          5. [SẮP XẾP](Theo mã hóa đơn)                    |");
                System.out.println("|          6. [DANH SÁCH HÓA ĐƠN]                           |");
                System.out.println("|          0. [THOÁT]                                       |");
                System.out.println("|___________________________________________________________|\n");
                System.out.print("choose = ");

                try {
                    choose = new Scanner(System.in).nextInt();
                } catch (Exception e) {
                    choose = -1;
                }

                switch (choose) {
                    case 1:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("Thực hiện chức năng thêm!");
                        HoaDon hdAdd = new HoaDon();
                         String id = hd.insert(hdAdd);
                        System.out.println(id);                      
                        
                        break;
                    case 2:
                        clearScreen();
                        Thread.sleep(50);
                        HoaDon hdUpdate = new HoaDon();
                        System.out.println("Thực hiện chức năng sửa!");
                        do {
                            System.out.println("Nhập dữ liệu cần sửa:");
                            hdUpdate.nhapMaHoaDon();
                            if (hd.check(hdUpdate)) {
                                hdUpdate.input();
                                System.out.println("Bạn có thực sự muốn sửa!(Y/N)");
                                String checkUpdate=new Scanner(System.in).nextLine();
                                if(checkUpdate.equalsIgnoreCase("Y")){
                                if (hd.update(hdUpdate)) {
                                    System.out.println("Sửa thành công hóa đơn có mã: " + hdUpdate.getMaHD());
                                }
                                else {
                                    System.out.println("Sửa thất bại");
                                }
                                }
                            } else {
                                System.err.println("Mã hóa đơn không tồn tại! Yêu cầu nhập lại");
                            }
                        }while (!hd.check(hdUpdate));

                        break;
                    case 3:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("Thực hiện chức năng xóa!");
                        System.out.print("Nhập mã hóa đơn cần xóa:");
                        HoaDon hdDelete = new HoaDon();
                        hdDelete.setMaHD(new Scanner(System.in).nextLine());
                        System.out.println("Bạn có thực sự muốn xóa!(Y/N)");
                        String checkXoa=new Scanner(System.in).nextLine();
                        if(checkXoa.equalsIgnoreCase("Y")){
                        if (hd.delete(hdDelete)) {
                            System.out.println("Xóa thành công mã hóa đơn: "+hdDelete.getMaHD());
                        } else {
                            System.out.println("Xóa thất bại vì không tồn tại mã hóa đơn! ");
                        }}
                        break;
                    case 4:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("Thực hiện chức năng tìm kiếm!");
                        System.out.println("Nhập từ khóa cần tìm (Mã hóa đơn): ");
                        String keywork = new Scanner(System.in).nextLine();
                        List<HoaDon> list = hd.findByMa(keywork);
                        if (list.size() > 0) {
                            System.out.println("Kết quả tìm kiếm:");
                            System.out.printf("%-10s|%-10s|%-10s|%-10s|%-13s|%-10s|%-15s|%-15s\n","Mã hóa đơn",
                                    "Mã khách hàng","Mã sách","Số lượng","Ngày thành lập","Thuế sách","Tổng tiền sách","Tổng giá trị");
                            for (HoaDon s : list) {
                                System.out.println(s.xuatHoaDon());
                            }
                        } else {
                            System.out.println("Không tìm thấy kết quả");
                        }
                        break;
                    case 5:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("Thực hiện chức năng sắp xếp!");
                        hd.sort();
                        break;
                    case 6:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("Thực hiện chức năng in!");
                        System.out.printf("%-10s|%-10s|%-10s|%-10s|%-13s|%-10s|%-15s|%-15s\n","Mã hóa đơn",
                                "Mã khách hàng","Mã sách","Số lượng","Ngày thành lập","Thuế sách","Tổng tiền sách","Tổng giá trị");
                        hd.travel();
                        break;
                    case 0:
                        System.out.println("Bạn đã chọn thoát");
                        System.out.println("Bạn thực sự muốn thoát (Y/N)");
                        String a;
                        a=new Scanner(System.in).nextLine();
                        if(a.equalsIgnoreCase("N")) {
                            check = -1;
                        }
                        else if(a.equalsIgnoreCase("Y"))
                        {
                            check=0;
                            clearScreen();
                            Thread.sleep(50);
                        }
                        else
                            System.err.println("Bạn đã nhập sai sự lựa chọn!");
                        break;
                    default:
                        System.out.println("Không tồn tại chức năng này!");
                }
            } while (choose != 0 || check<0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
