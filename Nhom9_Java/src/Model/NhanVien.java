package Model;

import Controller.NhanVienController;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KyThuat88
 */
//Serializable có nhiệm vụ chuyển trạng thái đối tượng (object) sang trạng thái luồng(stream) để có thể ghi file
public class NhanVien extends Person implements Serializable{
    //Đóng gói là sự che giấu bên trong dữ liệu riêng của mỗi đối tượng của 
    //lớp được khai báo và chỉ được truy xuất thông qua hệ thống các phương thức có sẵn của lớp
    //(chỉ có thể gọi những phương thức có sẵn của lớp). 
    //Vì vậy, nó còn được gọi là data hiding (nghĩa là che giấu dữ liệu).
    
    
    // khai báo các thuộc tính của đối tượng là private để che giấu thông tin
    private int maNV;
    private String chucVu;
    private float heSoLuong;
    private float luong;

    public NhanVien() {

    }

    public NhanVien(String hoTen, Date NgaySinh, String gioiTinh, String diachi, String sdt, String email, String cmnd,int maNV, String chucVu, float heSoLuong, float luong) {
        super( hoTen,  NgaySinh,  gioiTinh,  diachi,  sdt,  email,  cmnd);
        this.maNV = maNV;
        this.chucVu = chucVu;
        this.heSoLuong = heSoLuong;
        this.luong = luong;
    }

    //getter dùng để trả về thuộc tính của đối tượng
    //setter dùng để gán giá trị cho thuộc tính của đối tượng
    // trong đó tham số truyền vào của 2 phương thức này được gọi là tham số (biến cục bộ)
    // và có kiểu dữ liệu tương ứng với kiểu dữ liệu của thuộc tính (biến đối tượng)
    public int getMaNV() {
        return maNV;
    }

    // this là từ khóa có ý nghĩa là một tham chiếu đặc biệt 
    // chiếu tới đối tượng chủ của phương thức hiện hành
    // this có thể được dùng để truy cập biến đối tượng (instance variable)
    // hoặc gọi phương thức đối với đối tượng hiện hành. 
    // Thông thường, công dụng này của this chỉ có ích
    // khi tên biến đối tượng bị trùng với tham số (biến cục bộ - local variable) của phương thức
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
        int hsl = phanLoai();
        this.setHeSoLuong(hsl);
    }

    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
        this.setLuong((float)(heSoLuong*1500000));
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }
    //Nạp chồng over loading
    public void input(int ma){
        this.setMaNV(ma);
    }
    //Ghi đè(overriding)
    @Override
    public void input(){
        super.input();
        chucVu();
        
    }
    public void chucVu(){
        String cv = "";
        
        int choose1 =-1;
        do {
            
            System.out.println("Chọn chức vụ:");
            System.out.println("1. Nhân viên");
            System.out.println("2. Quản lý");
            System.out.println("3. Cửa hàng trưởng");
            System.out.print("Lựa chọn của bạn : ");
            try
            {
                choose1 = new Scanner(System.in).nextInt();
            } catch (Exception e)
            {
                System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                choose1 = 0;
            }
            switch (choose1)
            {
                case 1:
                    System.out.println("Chức vụ đã chọn là: Nhân viên ");
                    cv ="Nhân viên";
                    break;
                case 2:
                    System.out.println("Chức vụ đã chọn là: Quản lý");
                    cv ="Quản lý";
                    break;
                case 3:
                    System.out.println("Chức vụ đã chọn là: Cửa hàng trưởng");
                    cv ="Cửa hàng trưởng";
                    break;    
                default:
                    choose1 = 0;
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 == 0);
        this.setChucVu(cv);
        
        
    }
    public int phanLoai(){
        int hsl = 0 ;
        if(chucVu.equals("Nhân viên"))
            hsl = 2;
        else if(chucVu.equals("Quản lý"))
            hsl = 4;
        else if(chucVu.equals("Cửa hàng trưởng") )
            hsl = 6;
        return hsl;
    }
    @Override
    public String toString() {
        return "NhanVien{" +super.toString()+ "maNV=" + maNV + ", chucVu=" + chucVu + ", heSoLuong=" + heSoLuong + ", luong=" + luong + '}';
    }

}
