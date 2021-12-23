package Model;

import Controller.KhachHangController;
import Controller.SachController;
import com.sun.istack.internal.NotNull;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HoaDon implements Serializable, Comparable<HoaDon>  {
    private String maHD;
    private String maKH;
    private String maSach;
    private int soLuong;
    private Date ngayThanhLap;
    private int thueSach;
    private double tongTienSach;
    private double tongGiaTri;
    public static final long serialVersionUID = -4333316296251054416L;
    public static SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    public static  DecimalFormat format = new DecimalFormat("###,###");//0.#
    public List<String> listDaHinh;
    /*
     (0) Một chữ số - luôn được hiển thị, ngay cả khi số có ít chữ số hơn (thì 0 được hiển thị)
     (.) Dấu phân cách dấu thập phân
     (#)  Một chữ số, số 0 đứng đầu sẽ được bỏ qua (Không hiển thị).
     (,) Dấu phân cách nhóm (ví dụ: dấu phân cách hàng nghìn)
    */

    public HoaDon() {
        listDaHinh= new ArrayList<>();
    }

    public HoaDon(String maHD, String maKH, String maSach, int soLuong, Date ngayThanhLap, int thueSach, double tongTienSach) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.ngayThanhLap = ngayThanhLap;
        this.thueSach = thueSach;
        this.tongTienSach = tongTienSach;
        listDaHinh= new LinkedList<>();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(Date ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public int getThueSach() {
        return thueSach;
    }

    public void setThueSach(int thueSach) {
        this.thueSach = thueSach;
    }

    public double getTongGiaTri() {
        return getTongTienSach()+getTongTienSach()*thueSach/100;
    }

    public void setTongGiaTri(double tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    public double getTongTienSach() {
        return soLuong*tongTienSach;
    }

    public void setTongTienSach(double tongTienSach) {
        this.tongTienSach = tongTienSach;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD='" + maHD + '\'' +
                ", maKH='" + maKH + '\'' +
                ", maSach='" + maSach + '\'' +
                ", soLuong=" + soLuong +
                ", ngayThanhLap=" + df.format(ngayThanhLap) +
                ", thueSach=" + thueSach +
                ", tongTienSach=" + tongTienSach +
                ", tongGiaTri=" + getTongGiaTri() +
                '}';
    }
    public char[] xuatHoaDon()
    {
        String date= df.format(ngayThanhLap);
        System.out.printf("%-10s %-10s\t  %-15s%-4d\t%-10s      %-10s  %-10s \t   %-10s\n",maHD,
                maKH,maSach,soLuong,date,thueSach,format.format(getTongTienSach()),format.format(getTongGiaTri()));
        return new char[0];
    }



    public void nhapMaHoaDon()
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Nhập mã hóa đơn");
        maHD= sc.nextLine();
    }
    public void input() throws ParseException {
        //------------------------------------------------Nhập mã khách hàng---------------------
        KhachHangController kh= new KhachHangController();
         Scanner sc= new Scanner(System.in);
         int checkKH=0;
         do {
             System.out.println("Nhập mã khách:");
             maKH = sc.nextLine();
             if(kh.check(maKH))
             {
                 checkKH=-1;
             }
             else {
                 System.err.println("Mã khách hàng không tồn tại! Yêu cầu nhập lại!");
             }
         }while (checkKH==0);
         //------------------------------------------------Nhập mã sách----------------------
        SachController sachController= new SachController();
        int checkSach=0;
        do {
            System.out.println("Nhập mã sách:");
            maSach= sc.nextLine();
            if(sachController.checkSachHD(maSach))
            {
                checkSach=-1;
            }
            else {
                System.err.println("Mã sách không tồn tại! Yêu cầu nhập lại!");
            }
        }while (checkSach==0);
        //---------------------------------------------Nhập số lượng--------------------------
        String checkSL;
        soLuong=0;
           do {
               System.out.println("Nhập số lượng sách:");
            try {
                checkSL=sc.nextLine();
                soLuong=Integer.parseInt(checkSL);
                if(soLuong<=0)
                {
                    System.out.println("Bạn chỉ có thể nhập số lượng lớn hơn 0");
                }
            }catch (Exception e)
            {
                soLuong= 0;
                System.err.println("Sai định dạng!!!! Yêu cầu nhập lại");
            }
            }while (soLuong<=0);

        //---------------------------------------------Ngày lập hóa đơn--------------------------
        int checkNgayHD=-1;
        SimpleDateFormat checkdate= new SimpleDateFormat("mm");
        int month=0;
        do {
            try {
                System.out.println("Nhập ngày lập hóa đơn:");
                ngayThanhLap=df.parse(sc.nextLine());
                month=Integer.parseInt(checkdate.format(ngayThanhLap));
                if(month>0 && month<=12)
                {
                    checkNgayHD=1;
                }
            }catch (Exception e)
            {
                System.err.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
            }
        }while (checkNgayHD != 1);
        //---------------------------------------------Thuế sách--------------------------
        String checkThue="";
        do {
            System.out.println("Nhập thuế sách:");
            try {
                checkThue=sc.nextLine();
                thueSach=Integer.parseInt(checkThue);
                if(thueSach<0)
                    System.out.println("Bạn chỉ có thể nhập số thuế lớn hơn hoặc bằng 0");
            }catch (Exception e)
            {
                thueSach= 0;
                System.err.println("Bạn đã nhập sai định dạng! Yêu cầu nhập lại");
            }
        }while (thueSach<=0);

        //---------------------------------------------Tiền sách--------------------------
//        tongTienSach= 0;
//        String checkTienSach;
//        do {
//            System.out.println("Nhập tổng tiền sách:");
//            try {
//                checkTienSach=sc.nextLine();
//                tongTienSach=Double.parseDouble(checkTienSach);
//                if(tongTienSach<=0)
//                    System.out.println("Bạn chỉ có thể nhập tiền sách lớn hơn 0");
//            }catch (Exception e)
//            {
//                tongTienSach= 0;
//                System.out.println("Bạn đã nhập sai định dạng! Yêu cầu nhập lại");
//            }
//        }while (tongTienSach<=0);
            tongTienSach=sachController.checkTien(maSach);
    }


    @Override
    public int compareTo(@NotNull HoaDon o) {
        int i = this.maHD.compareTo(o.maHD);
        if (i>0) {
            return 1;
        } else if (i<0) {
            return -1;
        }else{
            return 0;
        }
    }
}
