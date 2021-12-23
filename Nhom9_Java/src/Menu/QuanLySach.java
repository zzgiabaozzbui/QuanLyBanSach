package Menu;

import Controller.SachController;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.Sach;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author NINH
 */
public class QuanLySach {

    public void Action() {
        SachController sc = new SachController();
        System.out.println("CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI BÀI CỦA NGUYỄN NGHĨA NINH ");
        Scanner index = new Scanner(System.in);
        try {
            // sc.read();
            int choose;
            int thoat = 0;
            do {

                System.out.println("");
                System.out.println(" _______________________[QUẢN LÝ SÁCH]______________________");
                //System.out.println("");
                System.out.println("|                                                           |");
                System.out.println("|          1. [THÊM]                                        |");
                System.out.println("|          2. [SỬA]                                         |");
                System.out.println("|          3. [XÓA](Theo mã sách)                           |");
                System.out.println("|          4. [TÌM KIẾM]                                    |");
                System.out.println("|          5. [SẮP XẾP](Theo tên sách)                      |");
                System.out.println("|          6. [HIỂN THỊ]                                    |");
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
                        System.out.println("---Thực hiện chức năng thêm---");
                        Sach sAdd = new Sach();
                        sAdd.InputMa();

                        String id = sc.insert(sAdd);
                        System.out.println("Thêm sách thành công có mã sách : "+id);
                        break;
                    case 2:
                        clearScreen();
                        Thread.sleep(50);
                        Sach hdUpdate = new Sach();
                        System.out.println("---Thực hiện chức năng sửa---");
                        do {
                            System.out.println("Nhập dữ liệu cần sửa:");
                            hdUpdate.InputMa();
                            if (sc.check(hdUpdate)) {
                                hdUpdate.Input();
                                if (sc.update(hdUpdate)) {
                                    System.out.println("Sửa thành công sách có mã: " + hdUpdate.getMaSach());
                                } else {
                                    System.out.println("Sửa thất bại");
                                }
                            } else {
                                System.out.println("Mã sách không tồn tại! Yêu cầu nhập lại");
                            }
                        } while (!sc.check(hdUpdate));

                        break;
                    case 3:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("Thực hiện chức năng xóa!");
                        System.out.print("Nhập mã sách cần xóa : ");
                         Sach sDelete = new Sach();
                        sDelete.setMaSach(new Scanner(System.in).nextLine());
                        System.out.println("Bạn có thực sự muốn xóa! (Y/N)");
                        String checkXoa=new Scanner(System.in).nextLine();
                        if(checkXoa.equalsIgnoreCase("Y")){
                            if (sc.delete(sDelete)) {
                            System.out.println("Xóa thành công mã sách : " + sDelete.getMaSach());
                        } else {
                            System.out.println("Xóa thất bại vì không tồn tại mã sách! ");
                        }
                        }
                        break;
                    case 4:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("----Thực hiện chức năng tìm kiếm---- ");
                        try {

                            int chooses;
                            String keywork = "";
                            List<Sach> list = new ArrayList<>();
                            do {
                                System.out.println(" Chọn tìm kiếm theo : ");
                                System.out.println(" 1. Mã sách");
                                System.out.println(" 2. Tên sách");
                                System.out.println(" 3. Tác giả");
                                System.out.println(" 4. Thể loại");
                                System.out.println(" 0. Exit");
                                System.out.print("Lựa chọn : ");
                                try {
                                    chooses = new Scanner(System.in).nextInt();
                                } catch (Exception e) {
                                    chooses = -1;
                                }
                                switch (chooses) {
                                    case 1:
                                        System.out.print("Nhập từ khóa cần tìm (Mã sách): ");
                                        keywork = new Scanner(System.in).nextLine();
                                        list = sc.findByMa(keywork);
                                        if (list.size() > 0) {
                                            System.out.println("Kết quả tìm kiếm: ");
                                            System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                    "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                            for (Sach s : list) {
                                                System.out.println(s.Xuat());
                                            }
                                        } else {
                                            System.err.println("Không tìm thấy kết quả");
                                        }
                                        break;
                                    case 2:
                                        System.out.print("Nhập từ khóa cần tìm (Tên sách): ");
                                        keywork = new Scanner(System.in).nextLine();
                                        list = sc.findByTenSach(keywork);
                                        if (list.size() > 0) {
                                            System.out.println("Kết quả tìm kiếm:");
                                            System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                    "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                            for (Sach s : list) {
                                                System.out.println(s.Xuat());
                                            }
                                        } else {
                                            System.err.println("Không tìm thấy kết quả!");
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Nhập từ khóa cần tìm (Tác giả): ");
                                        keywork = new Scanner(System.in).nextLine();
                                        list = sc.findByTacGia(keywork);
                                        if (list.size() > 0) {
                                            System.out.println("Kết quả tìm kiếm:");
                                            System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                    "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                            for (Sach s : list) {
                                                System.out.println(s.Xuat());
                                            }
                                        } else {
                                            System.err.println("Không tìm thấy kết quả");
                                        }
                                        break;
                                    case 4:
                                        String checkTheLoai;
                                        int check = 0;

                                        System.out.println("-------Mời bạn chọn thể loại-----");
                                        System.out.println("1. Chính trị – pháp luật");
                                        System.out.println("2. Khoa học công nghệ – Kinh tế");
                                        System.out.println("3. Văn học nghệ thuật");
                                        System.out.println("4. Văn hóa xã hội – Lịch sử");
                                        System.out.println("5. Giáo trình");
                                        System.out.println("6. Truyện, tiểu thuyết");
                                        System.out.println("7. Tâm lý, tâm linh, tôn giáo");
                                        System.out.println("8. Thiếu nhi.");
                                        System.out.println("---------------------------------");
                                        do {
                                            try {
                                                System.out.print("Chọn thể loại :   ");
                                                checkTheLoai = index.nextLine();
                                                check = Integer.parseInt(checkTheLoai);
                                                if (check <= 0 || check > 8) {
                                                    System.out.println("Bạn chỉ có thể nhập số > 0 và <= 8");
                                                }
                                            } catch (Exception e) {
                                                check = 0;
                                                System.out.println("Sai định dạng!!!! Yêu cầu nhập lại");
                                            }
                                            switch (check) {
                                                case 1:
                                                    list = sc.findByLoaiSach("Chinh tri – phap luat");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 2:
                                                    list = sc.findByLoaiSach("Khoa hoc cong nghe – Kinh te");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 3:
                                                    list = sc.findByLoaiSach("Van hoc nghe thuat");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 4:
                                                    list = sc.findByLoaiSach("Van hoa xa hoi - Lich su");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 5:
                                                    list = sc.findByLoaiSach("Giao trinh");

                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 6:
                                                    list = sc.findByLoaiSach("Truyen, tieu thuyet");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 7:
                                                    list = sc.findByLoaiSach("Tan ly, tam linh, ton giao");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                case 8:
                                                    list = sc.findByLoaiSach("Thieu nhi");
                                                    if (list.size() > 0) {
                                                        System.out.println("Kết quả tìm kiếm:");
                                                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                                                        for (Sach s : list) {
                                                            System.out.println(s.Xuat());
                                                        }
                                                    } else {
                                                        System.err.println("Không tìm thấy kết quả");
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("Không tồn tại chức năng này.");
                                                    break;
                                            }
                                        } while (check < 0 && check > 8);

                                        break;
                                    case 0:
                                        clearScreen();
                                        Thread.sleep(50);
                                        System.out.println("Thực hiện chức năng thoát !");
                                        clearScreen();
                                        Thread.sleep(50);
                                        break;
                                    default:
                                        System.out.println("Không tồn tại chức năng này.");
                                }

                            } while (chooses != 0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 5:
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("---Thực hiện chức năng sắp sếp---");
                        sc.sort();
                        System.out.println("-----------------Danh sách------------------ ");
                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                        sc.travel();
                        break;
                    case 6:
                        //Xuất dữ liệu
                        clearScreen();
                        Thread.sleep(50);
                        System.out.println("-------------Thực hiện chức năng in-----------");
                        System.out.printf("%-10s|%-10s    |%-15s      |%-15s\t\t\t|%-10s  |%10s      |%-10s    |%-15s\n", "Mã sách",
                                "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Giá sách", "Nhà xuất bản", "Năm xuất bản");
                        sc.travel();
                        break;
                    case 0:
                        System.out.println("Bạn đã chọn thoát");
                        System.out.println("Bạn thực sự muốn thoát (Y/N)");
                        String a;
                        a = new Scanner(System.in).nextLine();
                        if (a.equalsIgnoreCase("N")) {
                            thoat = -1;
                        } else if (a.equalsIgnoreCase("Y")) {
                            thoat = 0;
                            clearScreen();
                            Thread.sleep(50);
                        } else {
                            System.out.println("Bạn đã nhập sai sự lựa chọn!");
                            thoat = -1;
                        }

                        break;
                    default:
                        System.err.println("Không tồn tại chức năng này");
                }
            } while (choose != 0 || thoat < 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
