package Model;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author SONY
 */
public class NSX implements Comparable<NSX>, Serializable {

    private int maNSX;
    private String tenNSX;
    private String diaChi;
    private String soDienThoai;
    public List<NSX> nsx;
    public NSX() {
        nsx= new ArrayList<>();
    }

    public NSX(int maNSX, String tenNSX, String diaChi, String soDienThoai) {
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        nsx= new Stack<>();
    }

    public int getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(int maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void input() {
        System.out.print("Mã NSX: ");
        this.setMaNSX(new Scanner(System.in).nextInt());
        System.out.print("Tên NSX: ");
        this.setTenNSX(new Scanner(System.in).nextLine());
        System.out.print("Địa chỉ: ");
        this.setDiaChi(new Scanner(System.in).nextLine());
        System.out.print("Số điện thoại: ");
        this.setSoDienThoai(new Scanner(System.in).nextLine());
    }

    public void output() {
        System.out.format("| %-8s | %-24s | %-34s | %-14s |\n", maNSX, tenNSX, diaChi, soDienThoai);
    }

    @Override
    public String toString() {
        return "NSX{" + "maNSX=" + maNSX + ", tenNSX=" + tenNSX + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + '}';
    }

    @Override
    public int compareTo(NSX o) {
        int i = this.tenNSX.compareTo(o.tenNSX);
        if (i > 0) {
            return 1;
        } else if (i < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public int setMaNSX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}