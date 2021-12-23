package Menu;

import Controller.HoaDonController;
import Controller.KhachHangController;
import static Controller.KhachHangController.df;
import static Controller.KhachHangController.khachHangList;
import Controller.NhanVienController;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.Person;
import Model.KhachHang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KyThuat88
 */
public class KhachHangManager {
    public static SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");

    public void Action() throws InterruptedException {
        NhanVienController nvc = new NhanVienController();
        Scanner sc = new Scanner(System.in);
        KhachHangController khc = new KhachHangController();
        KhachHangManager nvm = new KhachHangManager();
        khc.loaiKhacchhang();
        int choose =-1;
        System.out.println("        Họ và tên: Nguyễn Quang Bảo");
        System.out.println("        Ngày sinh: 24/09/2001");
        System.out.println("        Mã sinh viên: 70DCTT21241");
       // khc.readFile();
        do {
            
            //System.out.println("");
            System.out.println("");
            System.out.println(" ___________[QUẢN LÝ KHÁCH HÀNG]____________");
            //System.out.println("");
            System.out.println("|                                           |");
            System.out.println("|          1. [THÊM]                        |");
            System.out.println("|          2. [SỬA]                         |");
            System.out.println("|          3. [XÓA]                         |");
            System.out.println("|          4. [TÌM KIẾM]                    |");
            System.out.println("|          5. [SẮP XẾP]                     |");
            System.out.println("|          6. [DANH SÁCH KHÁCH HÀNG]        |");
            System.out.println("|          7. [THOÁT]                       |");
            System.out.println("|___________________________________________|\n");
            System.out.print("choose = ");

            try
            {
                choose = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose = -1;
            }

            switch (choose)
            {
                case 1:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng thêm!!!]");
                    System.out.println("");
                    //Cùng là kiểu dữ liệu, insert truyền vào kiểu dữ liệu là person
                    //Nhưng ở đây đối tượng thật là nhân viên - dẫn xuất của person
                    KhachHang nvInsert = new KhachHang();
                    khc.insert(nvInsert);


                    break;
                case 2:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Thực hiện chức năng sửa1!!]");
                    khc.travel();
                    int manv = 0;
                    khc.update(manv);
                    break;
                case 3:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Thực hiện chức năng xóa](theo mã khách hàng)");
                    int manv1 = 0;
                    khc.delete(manv1);
                    break;
                case 4:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Thực hiện chức năng tìm kiếm]");
                    khc.TimKiem();
                    break;
                case 5:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Thực hiện chức năng sắp xếp!!!]");
                    khc.sort();
                    System.out.println("Danh sách khách hàng sau khi sắp xếp theo tên:");
                    khc.output(khachHangList);
                    
                    break;
                            
                case 6:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Danh sách khách hàng!!!]");
                    khc.travel();
                    break;
                case 7:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Thực hiện chức năng thoát!!!]");
                    choose = nvc.Yes_No_Exit(choose);
                    if(choose == 7){
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Bạn đã quay lại trang chủ!!!");
                    nvc.writerFile();}
                    
                    break;
                default:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("[Không tồn tại chức năng này!!!]");
                    break;
            }

        } while (choose!=7);
    }
}
