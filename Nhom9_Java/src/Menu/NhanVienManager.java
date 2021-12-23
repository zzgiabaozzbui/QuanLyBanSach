package Menu;
import Controller.HoaDonController;
import static Controller.KhachHangController.khachHangList;
import Controller.NhanVienController;
import static Controller.NhanVienController.nhanVienList;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.NhanVien;
import Model.Person;
import static Model.Person.df;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KyThuat88
 */
public class NhanVienManager {
    
    public void Action() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        NhanVienController nvc = new NhanVienController();
        NhanVienManager nvm = new NhanVienManager();
        int choose =-1;
        System.out.println("        Họ và tên: Nguyễn Quang Bảo");
        System.out.println("        Ngày sinh: 24/09/2001");
        System.out.println("        Mã sinh viên: 70DCTT21241");
        //nvc.readFile();
        do {
            System.out.println("");
            System.out.println(" ___________[QUẢN LÝ NHÂN VIÊN]_____________");
            //System.out.println("");
            System.out.println("|                                           |");
            System.out.println("|          1. [THÊM]                        |");
            System.out.println("|          2. [SỬA]                         |");
            System.out.println("|          3. [XÓA]                         |");
            System.out.println("|          4. [TÌM KIẾM]                    |");
            System.out.println("|          5. [SẮP XẾP]                     |");
            System.out.println("|          6. [DANH SÁCH NHÂN VIÊN]         |");
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
                    clearScreen();            Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng thêm!!!]");
                    System.out.println("");
                    //Cùng là kiểu dữ liệu, insert truyền vào kiểu dữ liệu là person
                    //Nhưng ở đây đối tượng thật là nhân viên - dẫn xuất của person
                    Person nvInsert = null;
                    nvc.insert(nvInsert);
                    break;
                case 2:
                    clearScreen();            Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng sửa!!!]");
                    nvc.travel();
                    int manv = 0;
                    nvc.update(manv);
                    break;
                case 3:
                    clearScreen();            Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng xóa](theo mã nhân viên)");
                    int manv1 = 0;
                    nvc.delete(manv1);
                    break;
                case 4:
                    clearScreen();            Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng tìm kiếm]");
                    nvc.TimKiem();
                    break;
                case 5:
                    clearScreen();            Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng sắp xếp!!!]");
                    nvc.sort();
                    System.out.println("Danh sách nhân viên sau khi sắp xếp theo tên:");
                    nvc.output(nhanVienList);
                    
                    break;
                case 6:
                    clearScreen();            Thread.sleep(50);
                    System.out.println(" [Danh sách nhân viên!!!]");
                    nvc.travel();
                    break;

                case 7:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println(" [Thực hiện chức năng thoát!!!]");
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
                    System.out.println("Không tồn tại chức năng này!!!");
                    break;
            }

        } while (choose!=7);
    }
}