package Model;

import Controller.HoaDonController;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KyThuat88
 */
//Serializable có nhiệm vụ chuyển trạng thái đối tượng (object) sang trạng thái luồng(stream) để có thể ghi file
public class KhachHang extends Person implements Serializable{
    private int maKH;
    private int soLanMuaSach;
    private String loaiKhachHang;

    public KhachHang() {
    }

    public KhachHang(int maKH, int soLanMuaSach, String loaiKhachHang, String hoTen, Date NgaySinh, String gioiTinh, String diachi, String sdt, String email, String cmnd) {
        super(hoTen, NgaySinh, gioiTinh, diachi, sdt, email, cmnd);
        this.maKH = maKH;
        this.soLanMuaSach = soLanMuaSach;
        this.loaiKhachHang = loaiKhachHang;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getSoLanMuaSach() {
        return soLanMuaSach;
    }

    public void setSoLanMuaSach(int soLanMuaSach) {
        this.soLanMuaSach = soLanMuaSach;
        this.setLoaiKhachHang(phanLoai());
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", soLanMuaSach=" + soLanMuaSach + ", loaiKhachHang=" + loaiKhachHang + '}';
    }



//    //Lấy số lần mua trước đó từ file hóa đơn
//    public int soLanMua(){
//        int solanmua = 0;
//        HoaDonController hdc = new HoaDonController();
//        List<HoaDon> l = hdc.findByMa(String.valueOf(this.getMaKH()));
//        solanmua = l.size();
//        return solanmua;
//    }
    //Phân loại khách hàng
    public String phanLoai(){
        String loaiKhach ="";
        if(soLanMuaSach >= 0 && soLanMuaSach <= 3)
            loaiKhach = "Khách hàng mới";
        else if(soLanMuaSach > 3 && soLanMuaSach <= 5)
            loaiKhach = "Khách hàng cũ";
        else if(soLanMuaSach > 5 )
            loaiKhach = "Khách hàng thân thiết";
        return loaiKhach;
    }
    public void input(int ma){
        this.setMaKH(ma);
    }
    @Override
    public void input(){
        super.input();
        int slm = 0;
        this.setSoLanMuaSach(slm);
        
    }



}
