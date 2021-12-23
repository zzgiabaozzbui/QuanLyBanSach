/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Controller.*;
import java.awt.Robot;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class QuanLySach_FormChinh {
    public static void clearScreen() {
        try {
            Robot pressbot = new Robot();
            pressbot.keyPress(17); // giữ CTRL key.
            pressbot.keyPress(76); // giữ L key.
            pressbot.keyRelease(17); // thả CTRL key.
            pressbot.keyRelease(76); // thả L key.
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        KhachHangController khachHangController = new KhachHangController();
        khachHangController.readFile();
        NhanVienController nhanVienController = new NhanVienController();
        nhanVienController.readFile();
        SachController sachController = new SachController();
        sachController.read();
        HoaDonController hoaDonController = new HoaDonController();
        hoaDonController.readFile();
        NSXController nsxController = new NSXController();
        nsxController.readFileToList();
        int check1 = 0;
        int choose;
        do {
            System.out.println(" ___________[PHẦN MỀM QUẢN LÝ BÁN SÁCH]____________");
            System.out.println("|                                                  |");
            System.out.println("|          1. [Quản lý khách hàng]                 |");
            System.out.println("|          2. [Quản lý nhân viên]                  |");
            System.out.println("|          3. [Quản lý sách]                       |");
            System.out.println("|          4. [Quản lý nhà sản xuất]               |");
            System.out.println("|          5. [Quản lý hóa đơn]                    |");
            System.out.println("|          6. [Exit]                               |");
            System.out.println("|__________________________________________________|\n");
            System.out.print("Choose = ");
            try {
                choose = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                choose = -1;
            }
            switch (choose) {
                case 1:
                    clearScreen();
                    Thread.sleep(50);
                    KhachHangManager khachHangManager = new KhachHangManager();
                    khachHangManager.Action();
                    break;
                case 2:
                    clearScreen();
                    Thread.sleep(50);
                    NhanVienManager nhanVienManager = new NhanVienManager();
                    nhanVienManager.Action();
                    break;
                case 3:
                    clearScreen();
                    Thread.sleep(50);
                    QuanLySach quanLySach = new QuanLySach();
                    quanLySach.Action();
                    break;
                case 4:
                    clearScreen();
                    Thread.sleep(50);
                    NSXManager nsxManager = new NSXManager();
                    nsxManager.Action();
                    break;
                case 5:
                    clearScreen();
                    Thread.sleep(50);
                    QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
                    quanLyHoaDon.Action();
                    break;
                case 6:
                    System.out.println("Bạn đã chọn thoát");
                    System.out.println("Bạn thực sự muốn thoát (Y/N)");
                    String a;
                    a = new Scanner(System.in).nextLine();
                    if (a.equalsIgnoreCase("N")) {
                        check1 = -1;
                        clearScreen();
                        Thread.sleep(50);
                    } else if (a.equalsIgnoreCase("Y")) {
                        khachHangController.writerFile();
                        nhanVienController.writerFile();
                        sachController.write();
                        sachController.write();
                        hoaDonController.writerFile();
                        nsxController.saveToFile();
                        check1 = 0;
                        clearScreen();
                        Thread.sleep(50);
                    } else {
                        System.out.println("Bạn đã nhập sai sự lựa chọn!");
                    }
                    break;
                default:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Không tồn tại chức năng này!");
            }
        } while (choose != 6 || check1 == (-1));
    }
}
