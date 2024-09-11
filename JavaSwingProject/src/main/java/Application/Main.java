package Application;





import DTO.NguoiDung.TaiKhoanDTO;
import GUI.GUIPanel.*;
import GUI.GUIPanel.ManagerUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author Admin
 */
public class Main extends JFrame implements ActionListener{
    private JPanel MainContent;
    private MenuTaskBar menu;
    public Main(TaiKhoanDTO taiKhoanDTO){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0,0));    
        
        
        // MenuTaskbar
         menu = new MenuTaskBar(taiKhoanDTO);
        // add sự kiện     
        menu.getBtn_sanPham().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                SanPhamUI sanPham= new SanPhamUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(sanPham);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_loaiSP().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                LoaiSanPhamUI loaiSP= new LoaiSanPhamUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(loaiSP);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_NhaCungCap().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                NhaCungCapUI nhaCC= new NhaCungCapUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(nhaCC);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_NhapHang().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel nhaphang= new NhapHangUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(nhaphang); 
                hieuUngHover(e);
            }
        });
        menu.getBtn_PhieuNhap().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel phieunhap= new PhieuNhapUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(phieunhap); 
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_XuatHang().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel xuathang= new XuatHangUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(xuathang); 
                hieuUngHover(e);
            }
        });
        menu.getBtn_PhieuXuat().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel phieuxuat= new PhieuXuatUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(phieuxuat); 
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_TaiKhoan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                AdminUI ad = new AdminUI();
                setPanelMain(ad);
                hieuUngHover(e);
            }
        });

        menu.getBtn_ThongKe().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                ThongKeUI thongKeUI = new ThongKeUI(taiKhoanDTO.getMaKhoHang());
                setPanelMain(thongKeUI);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_DoiThongTin().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
//                JPanel doithongtin= new JPanel();
//                doithongtin.setBackground(Color.yellow);  
//                setPanelMain(doithongtin);
                  ManagerUI managerUI = new ManagerUI(taiKhoanDTO,menu);
                  setPanelMain(managerUI);
                  hieuUngHover(e);
            }
        });
        
        menu.getBtn_DangXuat().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                dispose();
                new DangNhap();
            }
        });
        
        this.add(menu,BorderLayout.WEST);
        
        
        // Panel giao diện bên phải
        MainContent = new JPanel();
        MainContent.setBackground(new Color(255,255,255));
        MainContent.setLayout(new BorderLayout(0, 0));
        this.add(MainContent, BorderLayout.CENTER);
        ManagerUI managerUI = new ManagerUI(taiKhoanDTO,menu);
        this.setPanelMain(managerUI);
        
        this.setResizable(false);
        this.setVisible(true);
    }

 
    public void setPanelMain(JPanel pn){
        MainContent.removeAll();
        MainContent.add(pn).setVisible(true);
        MainContent.repaint();
        MainContent.validate();
    }
    public void hieuUngHover(MouseEvent event){
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) {
        new DangNhap();
    }
    
}
