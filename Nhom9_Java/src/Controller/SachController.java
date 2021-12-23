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

import Model.Sach;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SachController implements Dao<Sach>{
    public static List<Sach> BookList = new ArrayList<>();

    //Hàm thêm dữ liệu vào file
    @Override
    public String insert(Sach s) {
        if(check(s) == true){
            System.err.println("Mã đã tồn tại.");
        }else{
            try {
                s.Input();
                BookList.add(s);
            } catch (ParseException ex) {
                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        write();
        return s.getMaSach();
    }

    public String insert(String maSach, String tenSach, int soLuong, String tacGia, String theLoai, double giaSach, String NSX, Date namSB) {
        Sach s = new Sach(maSach, tenSach, soLuong, tacGia, theLoai, giaSach, NSX, namSB);
        BookList.add(s);
        write();
        return s.getMaSach();
    }
    //Ghi dữ liệu trong list vào file
    public void write(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("d:/QuanLySach.dat"));
            for (Sach s : BookList) {
                oos.writeObject(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }

    //Kiem tra ma sách có tồn tại hay chưa
    public static boolean check(Sach s)
    {
        for (Sach sachs : BookList) {
            if (sachs.getMaSach().equalsIgnoreCase(s.getMaSach())) {
                return true;
            }
        }
        return  false;
    }
    public static boolean checkSachHD(String a)
    {
        for (Sach sachs : BookList) {
            if (sachs.getMaSach().equalsIgnoreCase(a)) {
                return true;
            }
        }
        return  false;
    }
    public double checkTien(String maSach)
    {
        for (int i = 0; i < BookList.size(); i++) {
            if(BookList.get(i).getMaSach().equalsIgnoreCase(maSach))
            {
                return BookList.get(i).getGiaSach();
            }
        }
        return 0;
    }
    //Đọc dữ liệu từ file ra list
    public void read(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("d:/QuanLySach.dat"));
            Object obj = null;
            while((obj = ois.readObject()) != null){
                try {
                    Sach s = (Sach)obj;
                    BookList.add(s);
                } catch (Exception e) {
                    obj = null;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }finally{
            try {
                ois.close();
            } catch (Exception e) {
            }
        }
    }
    //sap sep du lieu theo tên sách lấy chữ cái đầu theo bảng chữ cái
    public void sort(){
        Collections.sort(BookList);
        write();
    }
    //xuất dữ liệu trong list
    public void travel(){
        if (BookList.size()==0) {
            System.out.println("Danh sach rong!");
        }
        for (Sach s : BookList) {
            System.out.println(s.Xuat());
        }
    }

    //sửa dữ lieeun trong list
    @Override
    public boolean update(Sach s) {
        for (int i = 0; i < BookList.size(); i++) {
            if(BookList.get(i).getMaSach().equals(s.getMaSach())) {
                BookList.set(i, s);
                write();
                return true;
            }
        }
        return false;
    }
    //xóa dữ liệu trong list
    @Override
    public boolean delete(Sach s) {
        for (int i = 0; i < BookList.size(); i++) {
            if (BookList.get(i).getMaSach().equals(s.getMaSach()) ) {
                BookList.remove(i);
                return true;
            }
        }
        write();
        return false;
    }
    //Tìm kiếm theo mã sách
    @Override
    public List<Sach> findByMa(String ma) {
        List<Sach> list = new ArrayList<>();
        for (int i = 0; i < BookList.size(); i++) {
            if (BookList.get(i).getMaSach().equalsIgnoreCase(ma)) {
                list.add(BookList.get(i));
            }
        }
        return list;
    }
    //Tìm kiếm theo tên sách
    public List<Sach> findByTenSach(String tenSach) {
        List<Sach> list = new ArrayList<>();
        for (int i = 0; i < BookList.size(); i++) {
            if (BookList.get(i).getTenSach().equalsIgnoreCase(tenSach)) {
                list.add(BookList.get(i));
            }
        }
        return list;
    }
    //Tìm kiếm theo loại sách
    public List<Sach> findByLoaiSach(String loaiSach) {
        List<Sach> list = new ArrayList<>();
        for (int i = 0; i < BookList.size(); i++) {
            if (BookList.get(i).getTheLoai().equalsIgnoreCase(loaiSach)) {
                list.add(BookList.get(i));
            }
        }
        return list;
    }
    //Tìm kiếm theo tên tác giả
    public List<Sach> findByTacGia(String tacGia) {
        List<Sach> list = new ArrayList<>();
        for (int i = 0; i < BookList.size(); i++) {
            if (BookList.get(i).getTacGia().equalsIgnoreCase(tacGia)) {
                list.add(BookList.get(i));
            }
        }
        return list;
    }





}
