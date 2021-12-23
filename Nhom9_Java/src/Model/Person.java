package Model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//(Nạp chồng)overloading cho phép khai báo nhiều phương thức trong một lớp có tên trùng nhau
//Nhưng các tham số truyền vào lại khác nhau
//(Ghi đè)overriding cho phép ghi đè phương thức ở lớp cha nhưng phải cùng tên và tham số truyền vào
/**
 *
 * @author KyThuat88
 */
public class Person implements Comparable<Person>,Serializable{
    private String hoTen;
    private Date NgaySinh;
    private String gioiTinh;
    private String diachi;
    private String sdt;
    private String email;
    private String cmnd;
    //Ép kiểu thành ngày tháng năm
    public static SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
    //Đồng bộ do lúc dùng Serializable bị nhảy id
    public static final long serialVersionUID = -4333316296251054416L;

    public Person() {
    }

    public Person(String hoTen, Date NgaySinh, String gioiTinh, String diachi, String sdt, String email, String cmnd) {
        this.hoTen = hoTen;
        this.NgaySinh = NgaySinh;
        this.gioiTinh = gioiTinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.cmnd = cmnd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void input(int ma){
        System.out.println("");
    }

    public void input(){
        System.out.println("Họ và tên:");
        String hoten = "";
        this.setHoTen(chuanHoaTen(hoten));
        //---------------------------------------------Ngày sinh--------------------------
        int checkNgayHD = -1;
        do {
            try {
                System.out.println("Nhập ngày sinh(\"dd-mm-yyyy\"):");
                String date = new Scanner(System.in).nextLine();
                
                if(checkdate(date) == true){
                NgaySinh=df.parse(date);
                checkNgayHD = 1;}
                else{
                    System.out.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
                    checkNgayHD = -1;
                        }
            }catch (Exception e)
            {
                System.out.println("Bạn đã nhập sai định dạng ngày! Yêu cầu nhập lại!");
            }
        }while (checkNgayHD != 1);
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
                    this.setGioiTinh("Nam");
                    break;
                case 2:
                    this.setGioiTinh("Nữ");;
                    break;
                default:
                    System.out.println("Không tồn tại lựa chọn này này. Vui lòng chọn lại!!!");
                    break;
            }
        } while (choose1 != 1 && choose1 != 2);
        System.out.println("Quê quán:");
        String dc ="";
        this.setDiachi(chuanHoaTen(dc)) ;
        this.setSdt(kiemTraSDT());
        this.setEmail(kiemTraEmail());
        this.setCmnd(kiemTraSCMND());
    }

    public boolean checkdate(String date){
        String date1 = "";
        int m = 0;
        int d= 0;
        int y =0;
        String[] strArr;
        strArr = date.split("[-]");
        date1=strArr[0];
        d = Integer.parseInt(date1); 
        date1=strArr[1];
        m = Integer.parseInt(date1); 
        date1=strArr[2];
        y = Integer.parseInt(date1);     
        
        if(m>12)
            return false;
        else{
        switch(m)
	{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: 
                    if(d > 31)
                        return false;
                    break;
		case 4:
		case 6:
		case 9:
		case 11: 
                    if(d>30)
                        return false;
                    break;
		case 2: 
                    if (y % 4==0) {
                        if(d>29)
                        return false; }
                    else {
                        if(d> 28)
                            return false;}
                    break;
	}}
        return true;
    }

    public String kiemTraNgaySinh(){
        
        boolean kiemtra;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            String phonePattern = "^(0|\\d{3}-)+\\d{2}-\\d{7}$";
            System.out.print("Nhập số điện thoại(xxx-xx-xxxxxxx hoặc 0xx-xxxxxxx): ");
            input = sc.next();
            kiemtra = input.matches(phonePattern);
            if (!kiemtra) System.out.println("Số điện thoại không hợp lệ!!!");
        } while (!kiemtra);
        System.out.println("Số điện thoại hợp lệ");
        return input;
    }

    //kiểm tra email
    //Bắt đầu bằng chữ cái.[a-zA-Z]
    //Chỉ chứa chữ cái, chữ số và dấu gạch ngang (-).[\\w-]
    //Chứa một ký tự @, sau @ là tên miền.@
    //Tên miền có thể là domain.xxx.yyy hoặc domain.xxx. Trong đó xxx và yyy là các chữ cái và có độ dài từ 2 trở lên.
    //([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})
    //Đánh dấu Kết thúc của một dòng $
    //Bắt đầu chuỗi ^
    public String kiemTraEmail(){
        boolean kiemtra;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            String emailPattern = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
            System.out.print("Nhập email(email@address.com): ");
            input = sc.nextLine();
            kiemtra = input.matches(emailPattern);
            if (!kiemtra) System.out.println("Email không tồn tại!!!");
        } while (!kiemtra);
        System.out.println("Email hợp lệ");
        return input;
    }
    //Kiểm tra định dạng số điện thoại
    //Mẫu :Mã quốc gia (3 số) – mã vùng (2 số) – số điện thoại (7 số)
    //? ở đây nghĩa là có thể có mã quốc gia hoặc không
    //matches kiểm tra xem biểu thức chính quy có khớp với mẫu hay không.
    //\\d là bất kỳ số nào từ 0 đến 9
    public String kiemTraSDT(){
        boolean kiemtra;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            String phonePattern = "^(0|\\d{3}-)+\\d{2}-\\d{7}$";
            System.out.print("Nhập số điện thoại(xxx-xx-xxxxxxx hoặc 0xx-xxxxxxx): ");
            input = sc.next();
            kiemtra = input.matches(phonePattern);
            if (!kiemtra) System.out.println("Số điện thoại không hợp lệ!!!");
        } while (!kiemtra);
        System.out.println("Số điện thoại hợp lệ");
        return input;
    }

    //Kiểm tra định dạng số điện thoại
    //Mẫu :12 số tự nhiên hoặc 9 số
    public String kiemTraSCMND(){
        boolean kiemtra;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            String phonePattern = "^(\\d{12}|\\d{9})$";
            System.out.print("Nhập số cmnd(9 hoặc 12 số): ");
            input = sc.next();
            kiemtra = input.matches(phonePattern);
            if (!kiemtra) System.out.println("Số cmnd không hợp lệ!!!");
        } while (!kiemtra);
        System.out.println("Số cmnd hợp lệ");
        return input;
    }

    //Chuẩn hóa họ và tên
    public String chuanHoa(String str) {
        str = str.trim().toLowerCase();
        //thay thê 1 hay nhiều khoảng trắng thành 1 khoảng trắng
        str = str.replaceAll("\\s+", " ");
        return str;
    }


    public String chuanHoaTen(String str) {
        boolean kiemtra;
        do {
            String phonePattern = "([a-zA-Z]\\s*)+";
            str = new Scanner(System.in).nextLine();
            kiemtra = str.matches(phonePattern);
            if (!kiemtra) System.out.println("Sai định dạng. Vui lòng nhập lại!!!");
        } while (!kiemtra);
        System.out.println("Thông tin hợp lệ hợp lệ");
        str = chuanHoa(str);
        //tách xâu bằng mảng các từ bằng siplit
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            //String.valueOf(temp[i].charAt(0)).toUpperCase() lấy viết hoa chữ cái đầu của temp[i]
            //temp[i].substring(1) trả về chuỗi temp[i] nhưng ko lấy 1 ký tự thứ 1(đã được viết hoa)
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1)
                str += " ";
        }
        return str;
    }
    @Override
    public String toString() {
        return "hoTen=" + hoTen + ", NgaySinh=" + df.format(NgaySinh) + ", gioiTinh=" + gioiTinh + ", quequan=" + diachi + ", sdt=" + sdt + ", email=" + email + ", cmnd=" + cmnd ;
    }

    @Override
    public int compareTo(Person o) {
        
        int i = this.hoTen.compareTo(o.hoTen);
        //phải
        if (i>0) {
            return 1;
        } else if (i<0) {//trái
            return -1;
        }else//giữ nguyên
            return 0;
    }


}
