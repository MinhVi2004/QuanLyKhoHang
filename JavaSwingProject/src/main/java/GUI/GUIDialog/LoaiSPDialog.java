package GUI.GUIDialog;
import BUS.ThongTinSanPham.LoaiSanPhamBUS;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import GUI.GUIPanel.LoaiSanPhamUI;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoaiSPDialog extends  JDialog implements ActionListener{
    public String tieuDe,type; // type để set cho nút thêm hoặc xóa
    public InputFormCustom tenLoaiSP;
    public ButtonCustom btn_them,btn_chinhsua,btn_huy;
    int maKho=1;
    
    private LoaiSanPhamBUS LoaiSPBUS;
    private LoaiSanPhamDTO loaiSPDuocChon;
    private LoaiSanPhamUI loaiSPGUI;
    
    
    public LoaiSPDialog() {
        this.init();
    }

    public LoaiSPDialog(LoaiSanPhamUI LoaiSPGUI,String tieuDe, String type) {
        this.maKho=LoaiSPGUI.getMaKhoHang();
        this.LoaiSPBUS= new LoaiSanPhamBUS(maKho);
        this.tieuDe=tieuDe;
        this.type=type;
        this.loaiSPGUI=LoaiSPGUI;
        init();
    }
    public LoaiSPDialog(LoaiSanPhamUI LoaiSPGUI,String tieuDe, String type, LoaiSanPhamDTO loaisp) {
        this.maKho=LoaiSPGUI.getMaKhoHang();
        this.tieuDe=tieuDe;
        this.type=type;
        this.loaiSPDuocChon=loaisp;
        this.loaiSPGUI=LoaiSPGUI;
        this.LoaiSPBUS= new LoaiSanPhamBUS(maKho);
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout(20,20));
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        
        this.setTitle(this.tieuDe);
        JPanel tieuDePanel= new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        tieuDePanel.setBackground(Color.WHITE);
        JLabel tieuDeLabel= new JLabel("Thông Tin Loại Sản Phẩm");
        Font tieuDeFont= new Font("Arial",Font.BOLD,30);
        tieuDeLabel.setFont(tieuDeFont);
        tieuDePanel.add(tieuDeLabel);
        JPanel thongTin= new JPanel(new  FlowLayout(FlowLayout.CENTER,20,20));
        this.tenLoaiSP=new InputFormCustom("Tên Loại Sản Phẩm:");
        tenLoaiSP.setPreferredSize(new Dimension(400,100));
        thongTin.add(tenLoaiSP);
        // các nút 
        JPanel cacNutNhan= new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        this.btn_huy= new ButtonCustom("Thoát","","#DC143C");
        if ( type.equals("Add")){
            this.btn_them= new ButtonCustom("Thêm","","#00FF7F");
            this.btn_them.setHorizontalTextPosition(SwingConstants.CENTER);
            this.btn_them.setHorizontalAlignment(SwingConstants.CENTER);
            this.btn_them.addActionListener(this);
            cacNutNhan.add(this.btn_them);
        }
        else if (type.equals("Change")){
            this.btn_chinhsua= new ButtonCustom("Chỉnh Sửa","","#FFD700");
            this.btn_chinhsua.setHorizontalTextPosition(SwingConstants.CENTER);
            this.btn_chinhsua.setHorizontalAlignment(SwingConstants.CENTER);              
            themDuLieu();
            this.btn_chinhsua.addActionListener(this);
            cacNutNhan.add(this.btn_chinhsua);
        }
        this.btn_huy.setHorizontalTextPosition(SwingConstants.CENTER);
        this.btn_huy.setHorizontalAlignment(SwingConstants.CENTER);
       cacNutNhan.add(this.btn_huy);
        // sự kiện
        this.btn_huy.addActionListener(this);
        
        
        this.add(tieuDePanel,BorderLayout.NORTH);
        this.add(thongTin,BorderLayout.CENTER);
        this.add(cacNutNhan,BorderLayout.SOUTH);
        this.setVisible(true);
        
    }
    
    public void themDuLieu(){
        this.tenLoaiSP.getTxtForm().setText(this.loaiSPDuocChon.getTenLoaiSanPham());
        
    }
    
    
    
    public void XuLyThemLoaiSP(){
        if (tenLoaiSP.getTxtForm().getText().trim().equals(""))
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        else {
                String tenLoaiSP=this.tenLoaiSP.getTxtForm().getText().trim();
                if(LoaiSPBUS.create(this.maKho, new LoaiSanPhamDTO(tenLoaiSP,maKho))){
                    this.loaiSPGUI.loadDuLieuLoaiSP(LoaiSPBUS.getAll());
                    this.loaiSPGUI.chinhSuaGiaoDienTable();
                    JOptionPane.showMessageDialog(this, "Thêm Loại Sản Phẩm Mới Thành Công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
                else 
                {
                    JOptionPane.showMessageDialog(this, "Tên loại sản phẩm này đã tồn tại trong hệ thống","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                 
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cauLenh=e.getActionCommand();
        switch(cauLenh){
            case "Thoát":
                this.dispose();
                break;
            case "Thêm":
                XuLyThemLoaiSP();
                break;
            case "Chỉnh Sửa":
                XuLyChinhSuaLoaiSP();
                break;
        }
    }
    
    public void XuLyChinhSuaLoaiSP(){
        if (
            tenLoaiSP.getTxtForm().getText().trim().equals("") ){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
        else {
            try{
                String tenLoaiSP=this.tenLoaiSP.getTxtForm().getText().trim();
                
                if(LoaiSPBUS.update(new LoaiSanPhamDTO(loaiSPDuocChon.getMaLoaiSanPham(),tenLoaiSP,maKho))){
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.loaiSPGUI.loadDuLieuLoaiSP(LoaiSPBUS.getAll());
                    this.loaiSPGUI.chinhSuaGiaoDienTable();
                    this.dispose();
                }
                else 
                    JOptionPane.showMessageDialog(this, "Tên loại này đã tồn tại !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số với các ô yêu cầu điền mã loại, mã kho","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
                 
        }
    }
    
    
}
