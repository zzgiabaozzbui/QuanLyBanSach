package Model;


import Controller.NSXController;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sach implements Serializable, Comparable<Sach> {

    public static SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    private String _maSach;
    private String _tenSach;
    private int _soLuong;
    private String _tacGia;
    private String _theLoai;
    private double _giaSach;
    private String _NSX;
    private Date _namSB;
    public static DecimalFormat format = new DecimalFormat("###,###");//0.#
    public List<String> listDaHinh;
    /*
     (0) Một chữ số - luôn được hiển thị, ngay cả khi số có ít chữ số hơn (thì 0 được hiển thị)
     (.) Dấu phân cách dấu thập phân
     (#)  Một chữ số, số 0 đứng đầu sẽ được bỏ qua (Không hiển thị).
     (,) Dấu phân cách nhóm (ví dụ: dấu phân cách hàng nghìn)
     */
    public Sach() {
        listDaHinh= new ArrayList<>();
    }

    public Sach(String _maSach) {
        this._maSach = _maSach;
    }

    public Sach(String _tenSach, int _soLuong, String _tacGia, String _theLoai, double _giaSach, String _NSX, Date _namSB) {
        this._tenSach = _tenSach;
        this._soLuong = _soLuong;
        this._tacGia = _tacGia;
        this._theLoai = _theLoai;
        this._giaSach = _giaSach;
        this._NSX = _NSX;
        this._namSB = _namSB;
        listDaHinh= new LinkedList<>();
    }

    public Sach(String _maSach, String _tenSach, int _soLuong, String _tacGia, String _theLoai, double _giaSach, String _NSX, Date _namSB) {
        this._maSach = _maSach;
        this._tenSach = _tenSach;
        this._soLuong = _soLuong;
        this._tacGia = _tacGia;
        this._theLoai = _theLoai;
        this._giaSach = _giaSach;
        this._NSX = _NSX;
        this._namSB = _namSB;
    }

    public String getMaSach() {
        return _maSach;
    }

    public void setMaSach(String _maSach) {
        this._maSach = _maSach;
    }

    public String getTenSach() {
        return _tenSach;
    }

    public void setTenSach(String _tenSach) {
        this._tenSach = _tenSach;
    }

    public int getSoLuong() {
        return _soLuong;
    }

    public void setSoLuong(int _soLuong) {
        this._soLuong = _soLuong;
    }

    public String getTacGia() {
        return _tacGia;
    }

    public void setTacGia(String _tacGia) {
        this._tacGia = _tacGia;
    }

    public String getTheLoai() {
        return _theLoai;
    }

    public void setTheLoai(String _theLoai) {
        this._theLoai = _theLoai;
    }

    public double getGiaSach() {
        return _giaSach;
    }

    public void setGiaSach(double _giaSach) {
        this._giaSach = _giaSach;
    }

    public String getNSX() {
        return _NSX;
    }

    public void setNSX(String _NSX) {
        this._NSX = _NSX;
    }

    public Date getNamSB() {
        return _namSB;
    }

    public void setNamSB(Date _namSB) {
        this._namSB = _namSB;
    }

    public void InputMa() {
        boolean b = true;
        do {
            Scanner index = new Scanner(System.in);
            System.out.print("Nhập mã sách : ");
            _maSach = index.nextLine();
            // Kiểm tra ký tự đặc biệt
            // ^: phủ định
            // CASE_INSENSITIVE: Không quan tâm hoa thường
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            // Kiểm tra với regex
            Matcher m = p.matcher(_maSach);
            b = m.find();
        } while (b);
    }

    public void Input() throws ParseException {
        Scanner index = new Scanner(System.in);
        System.out.print("Nhập tên sách : ");
        this.setTenSach(new Scanner(System.in).nextLine());
        System.out.print("Tác giả : ");
        this.setTacGia(new Scanner(System.in).nextLine());
        //The loại
        String checkTheLoai;
        int check = 0;
        do {
            System.out.println("-------Mời bạn chọn thể loại-----");
            System.out.println("1. Chính trị – pháp luật");
            System.out.println("2. Khoa học công nghệ – Kinh tế");
            System.out.println("3. Văn học nghệ thuật");
            System.out.println("4. Văn hóa xã hội – Lịch sử");
            System.out.println("5. Giáo trình");
            System.out.println("6. Truyện, tiểu thuyết");
            System.out.println("7. Tâm lý, tâm linh, tôn giáo");
            System.out.println("8. Thiếu nhi.");

            System.out.print("Chọn thể loại :  ");
            try {
                checkTheLoai = index.nextLine();
                check = Integer.parseInt(checkTheLoai);
                if (check <= 0 || check > 8) {
                    System.err.println("Bạn chỉ có thể nhập số > 0 và <= 8");
                }
            } catch (Exception e) {
                check = 0;
                System.err.println("Sai định dạng!!!! Yêu cầu nhập lại");
            }

            if (check == 1) {
                this.setTheLoai("Chinh tri – phap luat");
            } else if (check == 2) {
                this.setTheLoai("Khoa hoc cong nghe – Kinh te");

            } else if (check == 3) {
                this.setTheLoai("Van hoc nghe thuat");

            } else if (check == 4) {
                this.setTheLoai("Van hoa xa hoi - Lich su");

            } else if (check == 5) {
                this.setTheLoai("Giao trinh");

            } else if (check == 6) {
                this.setTheLoai("Truyen, tieu thuyet");

            } else if (check == 7) {
                this.setTheLoai("Tan ly, tam linh, ton giao");

            } else if (check == 8) {
                this.setTheLoai("Thieu nhi");
            }
        } while (check <= 0 || check > 8);

        //so luong
        String checkSL;
        _soLuong = 0;
        do {
            System.out.print("Nhập số lượng sách :");
            try {
                checkSL = index.nextLine();
                _soLuong = Integer.parseInt(checkSL);
                if (_soLuong <= 0) {
                    System.out.println("Bạn chỉ có thể nhập số lượng lớn hơn 0");
                }
            } catch (Exception e) {
                _soLuong = 0;
                System.out.println("Sai định dạng!!!! Yêu cầu nhập lại");
            }
        } while (_soLuong <= 0);
        //Gia sach
        String checkGia;
        _giaSach = 0;
        do {
            System.out.print("Nhập giá sách :");
            try {
                checkGia = index.nextLine();
                _giaSach = Integer.parseInt(checkGia);
                if (_giaSach <= 0) {
                    System.out.println("Bạn chỉ có thể nhập giá sách lớn hơn 0");
                }
            } catch (Exception e) {
                _giaSach = 0;
                System.out.println("Sai định dạng!!!! Yêu cầu nhập lại");
            }
        } while (_giaSach <= 0);
        //================================

        NSXController nsxController= new NSXController();
        int checkSach=0;
        do {
            System.out.print("Nhà sản xuất : ");
            this.setNSX(new Scanner(System.in).nextLine());
            if(nsxController.checkTenNSX(getNSX()))
            {
                checkSach=-1;
            }
            else {
                System.err.println("Nhà sản xuất không tồn tại! Yêu cầu nhập lại!\n");
            }
        }while (checkSach==0);
        //Nam xuat ban
        int checkNamSB = -1;
        do {
            try {
                System.out.print("Năm xuất bản :");
                _namSB = df.parse(index.nextLine());
                checkNamSB = 1;
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
            }
        } while (checkNamSB != 1);
    }

    public char[] Xuat() {
        System.out.printf("%-10s %-10s\t  %-20s  %-32s  %-10s   %-15s %-16s %-10s\n", _maSach,
                _tenSach, _tacGia, _theLoai, _soLuong, format.format(_giaSach), _NSX, df.format(_namSB));
        return new char[0];
    }

    @Override
    public String toString() {
        return "Sach{" + "_maSach=" + _maSach + ", _tenSach=" + _tenSach + ", _soLuong=" + _soLuong + ", _tacGia=" + _tacGia + ", _theLoai=" + _theLoai + ", _giaSach=" + _giaSach + ", _NSX=" + _NSX + ", _namSB=" + df.format(_namSB) + '}';
    }

    @Override
    public int compareTo(Sach o) {
        int i = this._tenSach.compareTo(o._tenSach);
        if (i > 0) {
            return 1;
        } else if (i < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}