/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HoaDon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author NINH
 */
public class HoaDonController implements Dao<HoaDon>{
    public static List<HoaDon> hoaDonList = new ArrayList<>();
    @Override
    public String insert(HoaDon hd) {
        hd.nhapMaHoaDon();
        for (HoaDon hoaDon : hoaDonList) {
            if (hoaDon.getMaHD().equals(hd.getMaHD())) {
                return "Trùng mã";
            }
        }
        try {
            hd.input();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDonList.add(hd);
        writerFile();
        return "Nhập thành công với mã hóa đơn: "+hd.getMaHD();
    }
    public boolean check(HoaDon hd)
    {
        for (HoaDon hoaDon : hoaDonList) {
            if (hoaDon.getMaHD().equals(hd.getMaHD())) {
                return true;
            }
        }
        return  false;
    }
    @Override
    public boolean update(HoaDon hd) {
        for (int i = 0; i < hoaDonList.size(); i++) {
            if(hoaDonList.get(i).getMaHD().equals(hd.getMaHD())) {
                hoaDonList.set(i, hd);
                writerFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(HoaDon hd) {
        for (int i = 0; i < hoaDonList.size(); i++) {
            if (hoaDonList.get(i).getMaHD().equals(hd.getMaHD())) {
                hoaDonList.remove(i);
                writerFile();
                return true;
            }
        }

        return false;
    }

    @Override
    public List<HoaDon> findByMa(String MaHD) {
        List<HoaDon> list = new ArrayList<>();
        for (int i = 0; i < hoaDonList.size(); i++) {
            if (hoaDonList.get(i).getMaHD().contains(MaHD)) { // contains giống like trong sql
                list.add(hoaDonList.get(i));
            }
        }
        return list;
    }
//    public List<HoaDon> findByDate(Date ns){
//        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
//        List<HoaDon> list1 = new ArrayList<>();
//        for(int i=0; i< hoaDonList.size();i++){
//            if(df.format(hoaDonList.get(i).getNgayThanhLap())==df.format(ns)){
//                list1.add(hoaDonList.get(i));
//            }
//        }
//        return list1;
//    }

    public void sort(){
        Collections.sort(hoaDonList);
        writerFile();
    }
    public void travel(){
        if (hoaDonList.size()==0) {
            System.out.println("Danh sach rong!");
        }
        for (HoaDon hd : hoaDonList) {
            System.out.println(hd.xuatHoaDon());
        }
    }
    public void writerFile() {
        File file = new File("d:/HoaDon.dat");
        ObjectOutputStream obs = null;
        try {
            obs = new ObjectOutputStream(new FileOutputStream(file));
            for (HoaDon hoaDon : hoaDonList) {
                obs.writeObject(hoaDon);
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
    public void readFile() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("d:/HoaDon.dat"));
            Object obj = null;
            while((obj=ois.readObject())!= null){
                try {
                    HoaDon hd=(HoaDon) obj;
                    hoaDonList.add(hd);
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