/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author NINH
 */
import Menu.NhanVienManager;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.NhanVien;
import Model.Person;
import static Model.Person.df;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class NhanVienController {
    
    public static List<NhanVien> nhanVienList = new ArrayList<>();
    //Ép kiểu thành ngày tháng năm
    public static SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    
    
    public void insert(Person n) throws InterruptedException{
        //đa hinh
        n = new NhanVien();
        int manv = 0  ;
        int checkNgay=-1;
        int Kiemtra = 0;
        do {
            try {
                System.out.println("Nhập mã nhân viên:");
                manv=new Scanner(System.in).nextInt();
                checkNgay = 1;
            }catch (Exception e)
            {
                System.out.println("Bạn đã nhập sai định dạng ! Yêu cầu nhập lại!");
                manv = 2147483640;
            }
            if(manv < 0)
                System.out.println("Mã nhân viên phải lớn hơn hoặc bằng 0!!!");

            Kiemtra = 0;
            for(int i=0; i< nhanVienList.size();i++){
                if(nhanVienList.get(i).getMaNV()==(manv))
                {
                    System.out.println("Mã nhân viên bị trùng. Vui long nhập lại!!!");
                    Kiemtra = 1;
                }
            }
        }while (checkNgay != 1 || manv<0  || Kiemtra != 0);


        //Đa hình
        n.input(manv);
        //Đa hình
        n.input();
        NhanVien nv =(NhanVien)n;
        nhanVienList.add(nv);
        writerFile();
        clearScreen();
        Thread.sleep(50);
        System.out.println("thêm nhân viên có mã ["+ nv.getMaNV()+"] thành công");

        System.out.println("Kết quả sau khi thêm:");
        travel();
    }

    
    public void TruongSua(NhanVien nv,int i) throws InterruptedException{
        Person p = new NhanVien();
        NhanVienController nvc = new NhanVienController();
        int choose1 =-1;
        do {
            System.out.println("   Chọn trường thông tin muốn sửa của nhân viên :");
            System.out.println("1. [Mã nhân viên]\n"
                    + "2. [Họ và tên]\n"
                    + "3. [Ngày sinh]\n"
                    + "4. [Giới tính]\n"
                    + "5. [Quê quán]\n"
                    + "6. [Số điện thoại]\n"
                    + "7. [Email]\n"
                    + "8. [Cmnd]\n"
                    + "9. [Chức vụ]\n"
                    
                    + "10. [Tất cả](trừ mã nhân viên)\n"
                    
                    + "11. [Exit]\n");
            System.out.print("Lựa chọn của bạn: ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = -1;
            }

            switch (choose1)
            {
                case 1:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Không được phép sửa mã nhân viên.Vui lòng chọn lại!!!");
                    break;
                case 2:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa tên nhân viên thành:");
                    String tennv = null ;
                    nv.setHoTen(p.chuanHoaTen(tennv));
                    nvc.writerFile();
                    break;
                case 3:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa ngày sinh");
                    int checkNgayHD = -1;
                    do {
                        try {
                            System.out.println("Nhập ngày sinh(\"dd-mm-yyyy\"):");
                            String date = new Scanner(System.in).nextLine();

                            if (p.checkdate(date) == true) {
                                 nv.setNgaySinh(df.parse(date));
                                checkNgayHD = 1;
                            } else {
                                System.out.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
                                checkNgayHD = -1;
                            }
                        } catch (Exception e) {
                            System.out.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
                        }
                    } while (checkNgayHD != 1);
                    nvc.writerFile();
                    break;
                case 4:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa giới tính:");
                    choose1 =-1;
                    do {
                        System.out.println("Giới tính:");
                        System.out.println("1. Nam");
                        System.out.println("2. Nữ");
                        System.out.print("Lựa chọn của bạn : ");
                        try
                        {
                            choose1 = new Scanner(System.in).nextInt();
                        } catch (Exception e)
                        {
                            choose1 = -1;
                        }

                        switch (choose1)
                        {
                            case 1:
                                nv.setGioiTinh("Nam");
                                break;
                            case 2:
                                nv.setGioiTinh("Nữ");;
                                break;
                            default:
                                System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                                break;
                        }
                    } while (choose1 == 0);
                    nvc.writerFile();
                    break;
                case 5:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa quê quán:");
                    String quequan = null;
                    nv.setDiachi(p.chuanHoaTen(quequan));
                    nvc.writerFile();
                    break;
                case 6:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa số điện thoại:");
                    nv.setSdt(p.kiemTraSDT());
                    nvc.writerFile();
                    break;
                case 7:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa Email:");
                    nv.setEmail(p.kiemTraEmail());
                    nvc.writerFile();
                    break;
                case 8:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa số cmnd");
                    nv.setCmnd(p.kiemTraSCMND());
                    nvc.writerFile();
                    break;
                case 9:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa chức vụ:");
                    nv.chucVu();
                    nvc.writerFile();
                    break;
                
                case 10:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa tất cả các trường(trừ mã nhân viên)");
                    nv.input();
                    nvc.writerFile();
                    break;
                
                case 11:
                    clearScreen();
                    Thread.sleep(50);
        
                    do {
                        System.out.println("Bạn có chắc muốn thoát chức năng sửa ?");
                        System.out.println("1. Có");
                        System.out.println("2. Không");
                        System.out.print("Lựa chọn của bạn : ");
                        try {
                            choose1 = new Scanner(System.in).nextInt();
                        } catch (Exception e) {
                            choose1 = -1;
                        }

                        switch (choose1) {
                            case 1:
                                choose1 = 11;
                                clearScreen();
                                Thread.sleep(50);
                                System.out.println("Bạn chọn : [CÓ] ");
                                nhanVienList.set(i, nv);
                                System.out.println("Sửa  nhân viên [" + nv.getMaNV() + "] thành công");
                                System.out.println("Kết quả sau khi sửa:");
                                nvc.writerFile();
                                travel();
                                System.out.println("Bạn đã quay về menu quản lý nhân viên!!!");
                                break;
                            case 2:
                                choose1 = 20;
                                clearScreen();
                                Thread.sleep(50);
                                System.out.println("Bạn chọn : [Không] ");
                                break;
                            default:
                                choose1 = 0;
                                System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                                break;
                        }
                    } while (choose1 == 0);
                    nvc.writerFile();
                    break;
                default:
                    System.out.println("Không có trường này. Vui lòng chọn lại!!! ");

                    break;
            }
        } while (choose1 != 11);
    }
    




    public void update(int manv) throws InterruptedException{
        NhanVien nv = new NhanVien();
        int check = -1;
        int kiemtra = 0;
        manv = -1;
        do {
            check = -1;
            kiemtra = 0;
            try {
                System.out.print("Nhập mã nhân viên muốn sửa:");
                manv=new Scanner(System.in).nextInt();
                check = 1;
            }catch (Exception e)
            {
                check = -1;
            }

            for(int i=0; i< nhanVienList.size();i++){
                kiemtra = 0;
                if(nhanVienList.get(i).getMaNV()==(manv))
                {
                    kiemtra = 1;
                    break;
                }
            }
            if(check==-1||manv<0||kiemtra == 0){
                
                System.out.println("Mã nhân viên không tồn tại. Vui long nhập lại!!!");
                System.out.println("Mã nhân viên phải là số tự nhiên >= 0 có trong danh sách");
            }

        } while (kiemtra==0||check!=1||manv<0);
        nv.input(manv);
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getMaNV()==(nv.getMaNV())){
                nv = nhanVienList.get(i);
                TruongSua(nv,i);


            }
        }
    }

    public void delete(int manv){
        System.out.println("Danh sách nhân viên:");
        travel();
        NhanVienController nvc = new NhanVienController();
        NhanVien nv = new NhanVien();
        int check = -1;
        int kiemtra = 0;
        manv = -1;
        do {
            check = -1;
            kiemtra = 0;
            try {
                System.out.print("Nhập mã nhân viên muốn xóa:");
                manv=new Scanner(System.in).nextInt();
                check = 1;
            }catch (Exception e)
            {
                check = -1;
            }

            for(int i=0; i< nhanVienList.size();i++){
                kiemtra = 0;
                if(nhanVienList.get(i).getMaNV()==(manv))
                {
                    kiemtra = 1;
                    break;
                }
            }
            if(check==-1||manv<0||kiemtra == 0){
                System.out.println("Mã nhân viên phải là số tự nhiên >= 0 có trong danh sách");

                System.out.println("Mã nhân viên không tồn tại. Vui long nhập lại!!!");
            }

        } while (kiemtra==0||check!=1||manv<0);
        nv.input(manv);
        int choose1 ;
        do {
            System.out.println("Bạn có chắc muốn xóa nhân viên"+ nv.getMaNV()+" ?");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Lựa chọn của bạn : ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = -1;
            }

            switch (choose1)
            {
                case 1:
                    System.out.println("Bạn chọn : [CÓ] ");
                    for(int i=0; i< nhanVienList.size();i++){
                        if(nhanVienList.get(i).getMaNV() == (nv.getMaNV()) == true){
                            nhanVienList.remove(i);
                            System.out.println("Xóa nhân viên ["+ nv.getMaNV()+"] thành công");
                            System.out.println("Kết quả sau khi xóa:");
                            nvc.writerFile();
                            travel();
                        }
                    }
                    System.out.println("Bạn đã quay về menu quản lý nhân viên!!!");
                    break;
                case 2:
                    System.out.println("Bạn chọn : [Không]. Xóa thất bại!!! ");
                    System.out.println("Danh sách nhân viên:");
                            travel();
                    break;
                default:
                    choose1 = 0;
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
        
    }


    //Tìm kiếm

    public List<NhanVien> findByMaNV(String ma){
        ma = new Scanner(System.in).nextLine();
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(String.valueOf( nhanVienList.get(i).getMaNV()).contains(ma)){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }


    public List<NhanVien> findByName(String fullName){
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if((nhanVienList.get(i).getHoTen().toLowerCase()).contains(fullName.toLowerCase()) == true){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }

    public List<NhanVien> findByDate(String ns ){
        ns = new Scanner(System.in).nextLine();
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(df.format(nhanVienList.get(i).getNgaySinh()).contains(ns)){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }


    public List<NhanVien> findBySDT(String sdt){
        sdt =new Scanner(System.in).nextLine();
        Person p = new Person();
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getSdt().contains(sdt) == true){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }

    public List<NhanVien> findByGioiTinh(){
        String gt = "";
        //---------------------------------------------Giới tính--------------------------
        int choose1 =-1;
        do {
            System.out.println("Giới tính:");
            System.out.println("1. Nam");
            System.out.println("2. Nữ");
            System.out.print("Lựa chọn của bạn : ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = -1;
            }

            switch (choose1)
            {
                case 1:
                    gt = "Nam";
                    break;
                case 2:
                    gt= "Nữ";;
                    break;
                default:
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getGioiTinh().equals(gt) == true){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }

    public List<NhanVien> findByDiaChi(String dc){
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getDiachi().toLowerCase().contains(dc.toLowerCase()) == true){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }
    public List<NhanVien> findByEmail(String email){
        Person p =new Person();
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getEmail().contains(email) == true){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }

    public List<NhanVien> findByCMND(String cmnd){
        Person p =new Person();
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getCmnd().contains(cmnd) == true){
                list.add(nhanVienList.get(i));
                return list;
            }
        }
        return list;
    }

    public List<NhanVien> findByChucVu(String cv){
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(nhanVienList.get(i).getChucVu().toLowerCase().contains(cv.toLowerCase()) == true){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }
    public List<NhanVien> findByHSL(String hsl){
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(String.valueOf(nhanVienList.get(i).getHeSoLuong()).contains(hsl)==true ){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }
    public List<NhanVien> findByLuong(String l){
        List<NhanVien> list = new ArrayList<>();
        for(int i=0; i< nhanVienList.size();i++){
            if(String.valueOf(nhanVienList.get(i).getLuong()).contains(l)==true ){
                list.add(nhanVienList.get(i));
            }
        }
        return list;
    }

    //Duyệt
    public void travel(){
        if(nhanVienList.size() == 0)
            System.out.println("Danh sách rỗng!!!");
        System.out.printf(" __________________________________________________"
                + "_________________________________________________________________________"
                + "______________________________________________________________ \n");
        System.out.printf("|%-12s|%-26s|%-10s|%-9s|%-15s|%-15s|%-30s|%-20s|%-15s|%-11s|%-12s|\n","Mã nhân viên",
                "Họ và tên","Ngày sinh","Giới tính","Quê quán",
                "Số điện thoại","Email","Cmnd","Chức vụ","Hệ số lương","Lương");
        System.out.printf("|============|==========================|=========="
                + "|=========|===============|===============|=============================="
                + "|====================|===============|===========|============|\n");
        for(NhanVien n : nhanVienList){
            System.out.format("|%-12d", n.getMaNV());
            System.out.format("|%-26s", n.getHoTen());
            System.out.format("|%-10s", df.format(n.getNgaySinh()));
            System.out.format("|%-9s", n.getGioiTinh());
            System.out.format("|%-15s", n.getDiachi());
            System.out.format("|%-15s", n.getSdt());
            System.out.format("|%-30s", n.getEmail());
            System.out.format("|%-20s", n.getCmnd());
            System.out.format("|%-15s", n.getChucVu());
            System.out.format("|%-11s", n.getHeSoLuong());
            System.out.format("|%-12s|", n.getLuong());
            System.out.println("");

        }
        System.out.printf("|____________|__________________________|__________"
                + "|_________|_______________|_______________|______________________________"
                + "|____________________|_______________|___________|____________|\n");
    }

    
    public void output(List<NhanVien> nv ){
        if(nv.size() == 0)
            System.out.println("Danh sách rỗng!!!");
        System.out.printf(" __________________________________________________"
                + "_________________________________________________________________________"
                + "______________________________________________________________ \n");
        System.out.printf("|%-12s|%-26s|%-10s|%-9s|%-15s|%-15s|%-30s|%-20s|%-15s|%-11s|%-12s|\n","Mã nhân viên",
                "Họ và tên","Ngày sinh","Giới tính","Quê quán",
                "Số điện thoại","Email","Cmnd","Chức vụ","Hệ số lương","Lương");
        System.out.printf("|============|==========================|=========="
                + "|=========|===============|===============|=============================="
                + "|====================|===============|===========|============|\n");
        for(NhanVien n : nv){
            System.out.format("|%-12d", n.getMaNV());
            System.out.format("|%-26s", n.getHoTen());
            System.out.format("|%-10s", df.format(n.getNgaySinh()));
            System.out.format("|%-9s", n.getGioiTinh());
            System.out.format("|%-15s", n.getDiachi());
            System.out.format("|%-15s", n.getSdt());
            System.out.format("|%-30s", n.getEmail());
            System.out.format("|%-20s", n.getCmnd());
            System.out.format("|%-15s", n.getChucVu());
            System.out.format("|%-11s", n.getHeSoLuong());
            System.out.format("|%-12s|", n.getLuong());
            System.out.println("");

        }
        System.out.printf("|____________|__________________________|__________"
                + "|_________|_______________|_______________|______________________________"
                + "|____________________|_______________|___________|____________|\n");

    }
    public void Yes_No(){
        NhanVienController nvc1 = new NhanVienController();
        int choose1 =-1;
        do {
            System.out.println("Choose:");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Lựa chọn của bạn : ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = -1;
            }

            switch (choose1)
            {
                case 1:
                    nvc1.writerFile();
                    System.out.println("Bạn chọn Có. Sửa file thành ");
                    break;
                case 2:
                    System.out.println("Bạn chọn Không. Sửa file thất bại ");
                    break;
                default:
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
    }


    public void TimKiem() throws InterruptedException{
        String keywork;
        List<NhanVien> list = new ArrayList<>();
        NhanVienController nvc = new NhanVienController();
        int choose1 =-1;
        do {
            System.out.println("  Chọn trường tìm kiếm:");
            System.out.println("1. [Mã nhân viên]\n"
                    + "2. [Họ và tên]\n"
                    + "3. [Ngày sinh]\n"
                    + "4. [Giới tính]\n"
                    + "5. [Quê quán]\n"
                    + "6. [Số điện thoại]\n"
                    + "7. [Email]\n"
                    + "8. [Cmnd]\n"
                    + "9. [Chức vụ]\n"
                    + "10. [Hệ số lương]\n"
                    + "11. [Lương]\n"
                    + "12. [Exit]\n");
            System.out.print("Lựa chọn của bạn: ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = -1;
            }

            switch (choose1)
            {
                case 1:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo mã nhân viên");
                    System.out.println("Nhap tu khoa can tim (mã nhân viên): ");
                    String keywork1 = "";
                    list = nvc.findByMaNV(keywork1);
                    output(list);
                    break;
                case 2:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Nhap tu khoa can tim (fullname): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = nvc.findByName(keywork);
                    System.out.println("Ket qua tim kiem:");
                    output(list);
                    break;
                case 3:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo ngày sinh");
                    String ns = "" ;
                    list = nvc.findByDate(ns);
                    output(list);
                    break;
                case 4:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo giới tính:");
                    list = nvc.findByGioiTinh();
                    output(list);
                    break;
                case 5:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo quê quán:");
                    System.out.println("Nhap tu khoa can tim (Quê quán): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = nvc.findByDiaChi(keywork);
                    output(list);
                    break;
                case 6:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo số điện thoại");
                    System.out.println("Nhap tu khoa can tim (Sdt): ");
                    keywork = "";
                    list = nvc.findBySDT(keywork);
                    output(list);
                    break;
                case 7:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo Email");
                    System.out.println("Nhap tu khoa can tim (email): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = nvc.findByEmail(keywork);
                    output(list);
                    break;
                case 8:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo cmnd");
                    System.out.println("Nhap tu khoa can tim (cmnd): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = nvc.findByCMND(keywork);
                    output(list);
                    break;
                case 9:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo chức vụ:");
                    System.out.println("Nhap tu khoa can tim (chức vụ): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = nvc.findByChucVu(keywork);
                    output(list);
                    break;
                case 10:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo hệ số lương");
                    System.out.println("Nhap tu khoa can tim (HSL): ");
                    String keywork2 = new Scanner(System.in).nextLine();
                    list = nvc.findByHSL(keywork2);
                    output(list);
                    break;
                case 11:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo lương:");
                    System.out.println("Nhap tu khoa can tim (Lương): ");
                    String keywork3 = new Scanner(System.in).nextLine();
                    list = nvc.findByLuong(keywork3);
                    output(list);
                    break;
                case 12:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Thực hiện chức năng Exit!!!");
                    choose1 = nvc.Yes_No_Exit(choose1);
                    if(choose1 == 11){
                    System.out.println("Bạn đã quay về menu quản lý nhân viên!!!");
                    }
                    
                    break;
                default:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Không có trường này. Vui lòng chọn lại!!! ");

                    break;
            }
        } while (choose1 != 12);
    }

    public int Yes_No_Exit(int choose){
        NhanVienController nvc1 = new NhanVienController();
        int c = choose;
        int choose1 =-1;
        do {
            System.out.println("Choose = ");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Lựa chọn của bạn : ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = -1;
            }

            switch (choose1)
            {
                case 1:
                    choose = choose;
                    System.out.println("Bạn chọn : [CÓ] ");
                    
                    break;
                case 2:
                    choose = 20;
                    System.out.println("Bạn chọn : [KHÔNG] ");
                    break;
                default:
                    choose1 = 0;
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
        return choose;
    }
    //sắp xếp
    public void sort(){
        Collections.sort(nhanVienList);
        writerFile();
    }

    //ObjectOutputStream để ghi kiểu dữ liệu nguyên thủy byte
    public void writerFile() {
        File file = new File("d:/NhanVien.dat");
        ObjectOutputStream obs = null;
        try {
            obs = new ObjectOutputStream(new FileOutputStream(file));
            for (NhanVien p : nhanVienList) {
                NhanVien nv = (NhanVien)p;
                obs.writeObject(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                obs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void readFile(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("d:/NhanVien.dat"));
            Object obj = null;
            while((obj=ois.readObject())!= null){
                try {
                    NhanVien p=(NhanVien) obj;
                    nhanVienList.add(p);
                } catch (Exception e) {
                    obj = null;
                }
            }
        } catch (Exception e) {
        }finally{
            try {
                ois.close();
            } catch (Exception e) {
            }
        }
    }
}

