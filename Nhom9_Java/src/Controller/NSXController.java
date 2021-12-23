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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.NSX;
import Model.Sach;


public class NSXController {

    public static List<NSX> NSXList = new ArrayList<>();

    public int insert(NSX n) {
        n.input();
        for (NSX NSX : NSXList) {
            if (NSX.getMaNSX() == n.getMaNSX()) {
                System.out.println("Trung ma");
                return -1;
            }
        }
        NSXList.add(n);
        saveToFile();
        return n.getMaNSX();
    }
    public boolean checkTenNSX(String ten)
    {
        for (NSX nsx : NSXList) {
            if (nsx.getTenNSX().equalsIgnoreCase(ten)) {
                return true;
            }
        }
        return  false;
    }

    public int insert(int maNSX, String tenNSX, String diaChi, String soDienThoai) {
        NSX n = new NSX(maNSX, tenNSX, diaChi, soDienThoai);
        NSXList.add(n);
        saveToFile();
        return n.getMaNSX();
    }

    public boolean update(NSX n) {
        for (int i = 0; i < NSXList.size(); i++) {
            if (NSXList.get(i).getMaNSX() == n.getMaNSX()) {
                NSXList.set(i, n);
                return true;
            }
        }

        return false;
    }

    public boolean delete(NSX n) {
        for (int i = 0; i < NSXList.size(); i++) {
            if (NSXList.get(i).getMaNSX() == n.getMaNSX()) {
                NSXList.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public List<NSX> findByTenNSX(String tenNSX) {
        List<NSX> list = new ArrayList<>();
        for (int i = 0; i < NSXList.size(); i++) {
            if (NSXList.get(i).getTenNSX().contains(tenNSX)) {
                list.add(NSXList.get(i));
            }
        }

        return list;
    }

    public void sort() {
        List<NSX> list = new ArrayList<>();
        for (NSX nsx : NSXList) {
            list.add(nsx);
        }
        Collections.sort(list);
        travel(list);
    }

    public void travel(List<NSX> NSXList) {
        if (NSXList.size() == 0) {
            System.out.println("Danh sach rong!");
        } else {
            System.out.format("| %-8s | %-24s | %-34s | %-14s |\n", "Mã NSX", "Tên NSX", "Địa chỉ", "Số điện thoại");
            for (NSX n : NSXList) {
                n.output();
            }
        }
    }

    public void travel() {
        if (NSXList.size() == 0) {
            System.out.println("Danh sach rong!");
        } else {
            System.out.format("| %-8s | %-24s | %-34s | %-14s |\n", "Mã NSX", "Tên NSX", "Địa chỉ", "Số điện thoại");
            for (NSX n : NSXList) {
                n.output();
            }
        }
    }

    public void saveToFile() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("d:/NSX.dat"));
            for (NSX n : NSXList) {
                oos.writeObject(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }

    public void readFileToList() {
        File file = new File("d:/NSX.dat");
        ObjectInputStream ois = null;

        try {
            if (file.isFile()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                Object obj = null;
                while ((obj = ois.readObject()) != null) {
                    try {
                        NSX n = (NSX) obj;
                        NSXList.add(n);
                    } catch (Exception e) {
                        obj = null;
                    }
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
        }
    }
}