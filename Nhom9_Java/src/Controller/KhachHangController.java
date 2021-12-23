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
import Menu.KhachHangManager;
import static Menu.KhachHangManager.df;
import static Menu.QuanLySach_FormChinh.clearScreen;
import Model.HoaDon;
import Model.KhachHang;
import Model.Person;
import Model.Sach;

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
public class KhachHangController {
    public static List<KhachHang> khachHangList = new ArrayList<>();
    //Ép kiểu thành ngày tháng năm
    public static SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    
    public void insert(KhachHang n) throws InterruptedException{
        n = new KhachHang();
        int makh = 0  ;
        int checkNgay=-1;
        int Kiemtra = 0;
        do {
            try {
                System.out.println("Nhập mã  khách hàng:");
                makh=new Scanner(System.in).nextInt();
                checkNgay = 1;
            }catch (Exception e)
            {
                System.out.println("Bạn đã nhập sai định dạng ! Yêu cầu nhập lại!");
                makh = 2147483640;
            }
            if(makh < 0)
                System.out.println("Mã khách hàng phải lớn hơn hoặc bằng 0!!!");

            Kiemtra = 0;
            for(int i=0; i< khachHangList.size();i++){
                if(khachHangList.get(i).getMaKH()==(makh))
                {
                    System.out.println("Mã khách hàng bị trùng. Vui long nhập lại!!!");
                    Kiemtra = 1;
                }
            }
        }while (checkNgay != 1 || makh<0  || Kiemtra != 0);


        n.input(makh);
        n.input();
        khachHangList.add(n);
        
        writerFile();
        clearScreen();
        Thread.sleep(50);
        System.out.println("thêm khách hàng có mã ["+ n.getMaKH()+"] thành công");

        System.out.println("Kết quả sau khi thêm:");
        travel();
    }
    
    public void loaiKhacchhang(){
        KhachHangController khc = new KhachHangController();
        HoaDonController hdc = new HoaDonController();
        int slm;
        for(KhachHang kh : khc.khachHangList ){
            slm = 0;
            for(HoaDon hd : hdc.hoaDonList ){
                if(hd.getMaKH().equals(String.valueOf(kh.getMaKH())) ){
                    slm ++;
                }
            }
            kh.setSoLanMuaSach(slm);
        }
        writerFile();
    }
    public void TruongSua(KhachHang kh,int i) throws InterruptedException{
        Person p = new KhachHang();
        KhachHangController khc = new KhachHangController();
        int choose1 =-1;
        do {
            System.out.println("Chọn trường thông tin muốn sửa của  khách hàng :");
            System.out.println("1.[Mã khách hàng]\n"
                    + "2.[Họ và tên]\n"
                    + "3.[Ngày sinh]\n"
                    + "4.[Giới tính]\n"
                    + "5.[Quê quán]\n"
                    + "6.[Số điện thoại]\n"
                    + "7.[Email]\n"
                    + "8.[Cmnd]\n"
                    + "9.[Tất cả](trừ mã khách hàng)\n"
                    + "10.[Exit]\n");
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
                    System.out.println("Không được phép sửa mã  khách hàng.Vui lòng chọn lại!!!");
                    break;
                case 2:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa tên khách hàng thành:");
                    String tenkh = null;
                    kh.setHoTen(p.chuanHoaTen(tenkh));
                    khc.writerFile();
                    break;
                case 3:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa ngày sinh");
                    int checkNgayHD = -1;
                    do {
                        try {
                            System.out.println("Nhập ngày sinh(\"dd-mm-yyyy\"):");
                            kh.setNgaySinh(df.parse(new Scanner(System.in).nextLine()));
                            checkNgayHD = 1;
                        }catch (Exception e)
                        {
                            System.out.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
                        }
                    }while (checkNgayHD != 1);
                    khc.writerFile();
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
                                kh.setGioiTinh("Nam");
                                break;
                            case 2:
                                kh.setGioiTinh("Nữ");;
                                break;
                            default:
                                System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                                break;
                        }
                    } while (choose1 == 0);
                    khc.writerFile();
                    break;
                case 5:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa quê quán:");
                    String quequan = null;
                    kh.setDiachi(p.chuanHoaTen(quequan));
                    khc.writerFile();
                    break;
                case 6:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa số điện thoại:");
                    kh.setSdt(p.kiemTraSDT());
                    khc.writerFile();
                    break;
                case 7:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa Email:");
                    kh.setEmail(p.kiemTraEmail());
                    khc.writerFile();
                    break;
                case 8:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa số cmnd");
                    kh.setCmnd(p.kiemTraSCMND());
                    khc.writerFile();
                    break;

                case 9:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Sửa tất cả các trường(trừ mã khách hàng)");
                    kh.input();
                    khc.writerFile();
                    break;
                
                case 10:
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
                                choose1 = 10;
                                System.out.println("Bạn chọn : [CÓ] ");
                                khachHangList.set(i, kh);
                                System.out.println("Sửa  khách hàng" + kh.getMaKH() + " thành công");
                                System.out.println("Kết quả sau khi sửa:");
                                khc.writerFile();
                                travel();
                                System.out.println("Bạn đã quay về menu quản lý khách hàng!!!");
                                break;
                            case 2:
                                choose1 = 20;
                                System.out.println("Bạn chọn : [Không] ");
                                break;
                            default:
                                choose1 = 0;
                                System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                                break;
                        }
                    } while (choose1 == 0);
                    khc.writerFile();
                    
                    break;
                default:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Không có trường này. Vui lòng chọn lại!!! ");
                    break;
            }
        } while (choose1 != 10);
    }
    public boolean check(String s)
    {
        for (KhachHang khachHang : khachHangList) {
            if (String.valueOf(khachHang.getMaKH()).equals(s)) {
                return true;
            }
        }
        return  false;
    }
    





    public void update(int makh) throws InterruptedException{
        KhachHang kh = new KhachHang();
        int check = -1;
        int kiemtra = 0;
        makh = -1;
        do {
            check = -1;
            kiemtra = 0;
            try {
                System.out.print("Nhập mã khách hàng muốn sửa:");
                makh=new Scanner(System.in).nextInt();
                check = 1;
            }catch (Exception e)
            {
                check = -1;
            }

            for(int i=0; i< khachHangList.size();i++){
                kiemtra = 0;
                if(khachHangList.get(i).getMaKH()==(makh))
                {
                    kiemtra = 1;
                    break;
                }
            }
            if(check==-1||makh<0||kiemtra == 0){
                
                System.out.println("Mã khách hàng không tồn tại. Vui long nhập lại!!!");
                System.out.println("Mã khách hàng phải là số tự nhiên >= 0 có trong danh sách");
            }

        } while (kiemtra==0||check!=1||makh<0);
        kh.input(makh);
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getMaKH()==(kh.getMaKH())){
                kh = khachHangList.get(i);
                TruongSua(kh,i);


            }
        }
    }

    public void delete(int makh){
        System.out.println("Danh sách khách hàng:");
        travel();
        KhachHang kh = new KhachHang();
        KhachHangController khc = new KhachHangController();
        int check = -1;
        int kiemtra = 0;
        makh = -1;
        do {
            check = -1;
            kiemtra = 0;
            try {
                System.out.print("Nhập mã  khách hàng muốn xóa:");
                makh=new Scanner(System.in).nextInt();
                check = 1;
            }catch (Exception e)
            {
                check = -1;
            }

            for(int i=0; i< khachHangList.size();i++){
                kiemtra = 0;
                if(khachHangList.get(i).getMaKH()==(makh))
                {
                    kiemtra = 1;
                    break;
                }
            }
            if(check==-1||makh<0||kiemtra == 0){
                System.out.println("Mã  khách hàng phải là số tự nhiên >= 0 có trong danh sách");

                System.out.println("Mã khách hàng không tồn tại. Vui long nhập lại!!!");
            }

        } while (kiemtra==0||check!=1||makh<0);
        kh.input(makh);
        int choose1 ;
        do {
            System.out.println("Bạn có chắc muốn xóa khách hàng ["+ kh.getMaKH()+"] ?");
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
                    for(int i=0; i< khachHangList.size();i++){
                        if(khachHangList.get(i).getMaKH() == (kh.getMaKH()) == true){
                            khachHangList.remove(i);
                            System.out.println("Xóa khách hàng ["+ kh.getMaKH()+"] thành công");
                            System.out.println("Kết quả sau khi xóa:");
                            khc.writerFile();
                            travel();
                        }
                    }
                    System.out.println("Bạn đã quay về menu quản lý khách hàng!!!");
                    break;
                case 2:
                    System.out.println("Bạn chọn : [Không]. Xóa thất bại!!! ");
                    System.out.println("Danh sách khách hàng:");
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

    public List<KhachHang> findByMaNV(int ma){
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getMaKH()== ma){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }


    public List<KhachHang> findByName(String fullName){
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if((khachHangList.get(i).getHoTen().toLowerCase()).contains(fullName.toLowerCase()) == true){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }

    public List<KhachHang> findByDate(String ns ){
        ns = new Scanner(System.in).nextLine();
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(df.format(khachHangList.get(i).getNgaySinh()).contains(ns)){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }


    public List<KhachHang> findBySDT(String sdt){
        Person p = new Person();
        sdt = new Scanner(System.in).nextLine();
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getSdt().contains(sdt) == true){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }

    public List<KhachHang> findByGioiTinh(){
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
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getGioiTinh().equals(gt) == true){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }

    public List<KhachHang> findByDiaChi(String dc){
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getDiachi().toLowerCase().contains(dc.toLowerCase()) == true){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }
    public List<KhachHang> findByEmail(String email){
        
        email = new Scanner(System.in).nextLine();
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getEmail().toLowerCase().contains(email.toLowerCase()) == true){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }

    public List<KhachHang> findByCMND(String cmnd){
        
        cmnd = new Scanner(System.in).nextLine();
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getCmnd().contains(cmnd) == true){
                list.add(khachHangList.get(i));
                return list;
            }
        }
        return list;
    }

    public List<KhachHang> findByLoaiKH(){
        String cv = "";
        int choose1 =-1;
        do {
            
            System.out.println("Choose:");
            System.out.println("1. Khách hàng mới");
            System.out.println("2. Khách hàng cũ");
            System.out.println("3. Khách hàng thân thiết");
            System.out.print("Lựa chọn của bạn : ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                choose1 = 0;
            }
            switch (choose1)
            {
                case 1:
                    System.out.println("Bạn chọn Khách hàng mới. ");
                    cv ="Khách hàng mới";
                    break;
                case 2:
                    System.out.println("Bạn chọn Khách hàng cũ. ");
                    cv ="Khách hàng cũ";
                    break;
                case 3:
                    System.out.println("Bạn chọn Khách hàng cũ. ");
                    cv ="Khách hàng thân thiết";
                    break;    
                default:
                    choose1 = 0;
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getLoaiKhachHang().toLowerCase().contains(cv.toLowerCase()) == true){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }
    public List<KhachHang> findBySLM(int hsl){
        List<KhachHang> list = new ArrayList<>();
        for(int i=0; i< khachHangList.size();i++){
            if(khachHangList.get(i).getSoLanMuaSach()==(hsl) ){
                list.add(khachHangList.get(i));
            }
        }
        return list;
    }


    //Duyệt
    public void travel(){
        if(khachHangList.size() == 0)
            System.out.println("Danh sách rỗng!!!");
        System.out.printf(" __________________________________________________"
                + "__________________________________________________________________________"
                + "______________________________________________________________ \n");
        System.out.printf("|%-14s|%-26s|%-10s|%-9s|%-15s|%-15s|%-30s|%-20s|%-15s|%-23s|\n","Mã  khách hàng",
                "Họ và tên","Ngày sinh","Giới tính","Quê quán",
                "Số điện thoại","Email","Cmnd","Số lần mua sách","Loại khách hàng");
        System.out.printf("|==============|==========================|=========="
                + "|=========|===============|===============|=============================="
                + "|====================|===============|=======================|\n");
        for(KhachHang n : khachHangList){
            System.out.format("|%-14s", n.getMaKH());
            System.out.format("|%-26s", n.getHoTen());
            System.out.format("|%-10s", df.format(n.getNgaySinh()));
            System.out.format("|%-9s", n.getGioiTinh());
            System.out.format("|%-15s", n.getDiachi());
            System.out.format("|%-15s", n.getSdt());
            System.out.format("|%-30s", n.getEmail());
            System.out.format("|%-20s", n.getCmnd());
            System.out.format("|%-15s", n.getSoLanMuaSach());
            System.out.format("|%-23s|", n.getLoaiKhachHang());
            System.out.println("");

        }
        System.out.printf("|______________|__________________________|__________"
                + "|_________|_______________|_______________|______________________________"
                + "|____________________|_______________|_______________________|\n");
    }

    public void TimKiem() throws InterruptedException{
        NhanVienController nvc = new NhanVienController();
        String keywork;
        List<KhachHang> list = new ArrayList<>();
        KhachHangController khc = new KhachHangController();
        int choose1 =-1;
        do {
            System.out.println("   Chọn trường tìm kiếm:");
            System.out.println("1. [Mã khách hàng]\n"
                    + "2. [Họ và tên]\n"
                    + "3. [Ngày sinh]\n"
                    + "4. [Giới tính]\n"
                    + "5. [Quê quán]\n"
                    + "6. [Số điện thoại]\n"
                    + "7. [Email]\n"
                    + "8. [Cmnd]\n"
                    + "9. [Số lần mua sách]\n"
                    + "10. [Loại khách hàng]\n"
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
                    System.out.println("Tìm kiếm theo mã Khách hàng:");
                    KhachHang n = new KhachHang();
                    int makh = 0  ;
                    int checkNgay=-1;
                    do {
                        checkNgay=-1;
                        try {
                            System.out.println("Nhập mã Khách hàng:");
                            makh=new Scanner(System.in).nextInt();
                            checkNgay = 1;
                        }catch (Exception e)
                        {
                            System.out.println("Bạn đã nhập sai định dạng ! Yêu cầu nhập lại!");
                        }
                        if(makh < 0)
                            System.out.println("Mã khách hàng phải lớn hơn hoặc bằng 0!!!");


                    }while (checkNgay != 1 || makh<0 );
                    list = khc.findByMaNV(makh);
                    output(list);
                    break;
                case 2:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Nhap tu khoa can tim (fullname): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = khc.findByName(keywork);
                    System.out.println("Ket qua tim kiem:");
                    output(list);
                    break;
                case 3:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo ngày sinh");
                    String ns = "" ;
                    list = khc.findByDate(ns);
                    output(list);
                    break;
                case 4:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo giới tính:");
                    list = khc.findByGioiTinh();
                    output(list);
                    break;
                case 5:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo quê quán:");
                    System.out.println("Nhap tu khoa can tim (Quê quán): ");
                    keywork = new Scanner(System.in).nextLine();
                    list = khc.findByDiaChi(keywork);
                    output(list);
                    break;
                case 6:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo số điện thoại");
                    System.out.println("Nhap tu khoa can tim (Sdt): ");
                    keywork = "";
                    list = khc.findBySDT(keywork);
                    output(list);
                    break;
                case 7:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo Email");
                    System.out.println("Nhap tu khoa can tim (email): ");
                    keywork = "";
                    list = khc.findByEmail(keywork);
                    output(list);
                    break;
                case 8:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo cmnd");
                    System.out.println("Nhap tu khoa can tim (cmnd): ");
                    keywork = "";
                    list = khc.findByCMND(keywork);
                    output(list);
                    break;
                case 9:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo số lần mua sách:");
                    
                    int slm = 0  ;
                    int check=-1;
                    do {
                        check=-1;
                        try {
                            System.out.println("Nhap tu khoa can tim : ");
                            slm=new Scanner(System.in).nextInt();
                            check = 1;
                        }catch (Exception e)
                        {
                            System.out.println("Bạn đã nhập sai định dạng ! Yêu cầu nhập lại!");
                        }
                        if(slm < 0)
                            System.out.println("Số lần mua >= 0!!!");


                    }while (check != 1 || slm<0 );
                    list = khc.findBySLM(slm);
                    output(list);
                    break;
                case 10:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Tìm kiếm theo loại khách hàng:");
                    System.out.println("Chọn loại khách hàng: ");
                    
                    list = khc.findByLoaiKH();
                    output(list);
                    break;
                case 11:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Thực hiện chức năng Exit!!!");
                    choose1 = nvc.Yes_No_Exit(choose1);
                    if(choose1 == 11){
                    System.out.println("Bạn đã quay về menu quản lý  khách hàng!!!");
                    }
                    break;
                default:
                    clearScreen();
                    Thread.sleep(50);
                    System.out.println("Không có trường này. Vui lòng chọn lại!!! ");

                    break;
            }
        } while (choose1 != 11);
    }

    
    public void output(List<KhachHang> nv ){
        if(nv.size() == 0)
            System.out.println("Danh sách rỗng!!!");
        System.out.printf(" __________________________________________________"
                + "__________________________________________________________________________"
                + "______________________________________________________________ \n");
        System.out.printf("|%-14s|%-26s|%-10s|%-9s|%-15s|%-15s|%-30s|%-20s|%-15s|%-23s|\n","Mã  khách hàng",
                "Họ và tên","Ngày sinh","Giới tính","Quê quán",
                "Số điện thoại","Email","Cmnd","Số lần mua sách","Loại khách hàng");
        System.out.printf("|==============|==========================|=========="
                + "|=========|===============|===============|=============================="
                + "|====================|===============|=======================|\n");
        for(KhachHang n : nv){
            System.out.format("|%-14s", n.getMaKH());
            System.out.format("|%-26s", n.getHoTen());
            System.out.format("|%-10s", df.format(n.getNgaySinh()));
            System.out.format("|%-9s", n.getGioiTinh());
            System.out.format("|%-15s", n.getDiachi());
            System.out.format("|%-15s", n.getSdt());
            System.out.format("|%-30s", n.getEmail());
            System.out.format("|%-20s", n.getCmnd());
            System.out.format("|%-15s", n.getSoLanMuaSach());
            System.out.format("|%-23s|", n.getLoaiKhachHang());
            System.out.println("");

        }
        System.out.printf("|______________|__________________________|__________"
                + "|_________|_______________|_______________|______________________________"
                + "|____________________|_______________|_______________________|\n");

    }
    public void Yes_No(){
        KhachHangController khc1 = new KhachHangController();
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
                    khc1.writerFile();
                    System.out.println("Bạn chọn Có. Sửa thành ");
                    break;
                case 2:
                    System.out.println("Bạn chọn Không. Sửa thất bại ");
                    break;
                default:
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
    }

    //sắp xếp
    public void sort(){
        Collections.sort(khachHangList);
        writerFile();
    }

    //ObjectOutputStream để ghi kiểu dữ liệu nguyên thủy byte
    public void writerFile() {
        File file = new File("d:/KhachHang.dat");
        ObjectOutputStream obs = null;
        try {
            obs = new ObjectOutputStream(new FileOutputStream(file));
            for (KhachHang p : khachHangList) {
                KhachHang kh = (KhachHang)p;
                obs.writeObject(kh);
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
            ois = new ObjectInputStream(new FileInputStream("d:/KhachHang.dat"));
            Object obj = null;
            while((obj=ois.readObject())!= null){
                try {
                    KhachHang p=(KhachHang) obj;
                    khachHangList.add(p);
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

