package GUI.GUIPanel;
import DTO.NguoiDung.TaiKhoanDTO;
import GUI.GUIThanhPhan.ButtonCustom;
import javax.swing.*;


import java.awt.*;
import lombok.*;
@Data

public class MenuTaskBar extends JPanel{
    private String linkToIMG = "C:\\Users\\dvmv2\\OneDrive\\Documents\\Nam_2\\Java\\QuanLyKhoHang\\Java-Swing-main\\JavaSwingProject\\src\\main\\java\\Resources";
    private ButtonCustom btn_sanPham, btn_loaiSP;
    private ButtonCustom btn_NhaCungCap, btn_NhapHang, btn_PhieuNhap;
    private ButtonCustom btn_XuatHang, btn_PhieuXuat;
    private ButtonCustom btn_TaiKhoan,btn_ThongKe, btn_DoiThongTin, btn_DangXuat;
    private JLabel hoTen;
    
    public MenuTaskBar(TaiKhoanDTO taiKhoanDTO) {
        this.setPreferredSize(new Dimension(288,800));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(0,0));
        
        JPanel thongTinUser= new JPanel(new BorderLayout(0,0));
        thongTinUser.setBackground(Color.WHITE);
        thongTinUser.setPreferredSize(new Dimension(288,120));
        String ten=taiKhoanDTO.getHoVaTen();
        hoTen=new JLabel(ten!=null&&!ten.isEmpty()?ten:taiKhoanDTO.getTenDangNhap());
        hoTen.setVerticalAlignment(JLabel.CENTER);
        hoTen.setHorizontalAlignment(JLabel.CENTER);
        hoTen.setIcon(new ImageIcon(linkToIMG+"\\iconUser64.png"));
        JPanel bar1= new JPanel();
        bar1.setBackground(Color.BLACK);//new Color(204,214,219));
        bar1.setPreferredSize(new Dimension(1,0));
        JPanel bar2= new JPanel();
        bar2.setBackground(Color.BLACK);//new Color(204,214,219));
        bar2.setPreferredSize(new Dimension(0,1));
        JPanel bar3= new JPanel();
        bar3.setBackground(Color.BLACK); //Color(204,214,219));
        bar3.setPreferredSize(new Dimension(1,0));
        
        
        thongTinUser.add(hoTen,BorderLayout.CENTER);
        thongTinUser.add(bar1,BorderLayout.EAST);
        thongTinUser.add(bar2,BorderLayout.SOUTH);
        this.add(bar3,BorderLayout.EAST);
        this.add(thongTinUser,BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(200,900));
        // Chuyển thành giao diện sản phẩm
        JPanel dieuHuong= new JPanel(new GridLayout(11,1,0,0));// giữa, khoảng cách chiều ngang là 0, khoảng cách dọc là 5
        dieuHuong.setBackground(Color.WHITE);
        btn_sanPham= new ButtonCustom("Sản phẩm",linkToIMG + "\\sanpham.png");
        btn_loaiSP= new ButtonCustom("Loại Sản phẩm",linkToIMG + "\\loaisanpham.png");
        btn_NhaCungCap = new ButtonCustom("Nhà cung cấp",linkToIMG + "\\nhacungcap.png");

        btn_NhapHang = new ButtonCustom("Nhập hàng",linkToIMG + "\\nhaphang.png");
        btn_PhieuNhap= new ButtonCustom("Phiếu nhập",linkToIMG + "\\phieunhap.png");
        btn_XuatHang = new ButtonCustom("Xuất hàng", linkToIMG + "\\xuathang.png");
        btn_PhieuXuat= new ButtonCustom("Phiếu xuất",linkToIMG + "\\phieuxuat.png");
        btn_TaiKhoan= new ButtonCustom("Tài khoản",linkToIMG + "\\taikhoan.png");
        btn_ThongKe= new ButtonCustom("Thống kê",linkToIMG + "\\thongke.png");
        btn_DoiThongTin= new ButtonCustom("Đổi thông tin",linkToIMG + "\\thongtin.png");
        btn_DangXuat= new ButtonCustom("Đăng xuất",linkToIMG + "\\dangxuat.png");
        dieuHuong.add(btn_sanPham); dieuHuong.add(btn_loaiSP);
        dieuHuong.add(btn_NhaCungCap); dieuHuong.add(btn_NhapHang); dieuHuong.add(btn_PhieuNhap);
        dieuHuong.add(btn_XuatHang); dieuHuong.add(btn_PhieuXuat); 
        if(taiKhoanDTO.getQuyen().equals("Admin")){
            dieuHuong.add(btn_TaiKhoan);
        }
        dieuHuong.add(btn_ThongKe);
        dieuHuong.add(btn_DoiThongTin); dieuHuong.add(btn_DangXuat);
        
        this.add(dieuHuong,BorderLayout.CENTER);
    }
    
    
}
