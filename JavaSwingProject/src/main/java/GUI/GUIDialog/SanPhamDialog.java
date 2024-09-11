
package GUI.GUIDialog;

import BUS.ThongTinSanPham.LoaiSanPhamBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIPanel.SanPhamUI;
import GUI.GUIThanhPhan.ComboBoxFormCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import Others.CloundinaryServices;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.border.EmptyBorder;



public class SanPhamDialog extends  JDialog implements ActionListener{
    public String tieuDe,type;
    public InputFormCustom tenSP, thuongHieu, trongLuong, giaSP, xuatXu;
    public ComboBoxFormCustom tenLoai;
    private JLabel hinhAnh;
    private SanPhamDTO SPDuocChon;
    private SanPhamUI SPGUI;
    private File selectedFile=null;
    
    private LoaiSanPhamBUS LoaiSPBUS;
    private SanPhamBUS SanPhamBUS;
    
    
    public SanPhamDialog(SanPhamUI guiaa,String title,String type) {
        this.tieuDe=title;
        this.type=type;
        this.SPGUI=guiaa;
        this.SanPhamBUS= new SanPhamBUS(guiaa.getMaKhoHang());
        this.LoaiSPBUS= new LoaiSanPhamBUS(guiaa.getMaKhoHang());
        this.init();
    }
    public SanPhamDialog(SanPhamUI guiaa,String title,String type, SanPhamDTO sanPhamDuocChon) {
        this.tieuDe=title;
        this.type=type;
        this.SPDuocChon=sanPhamDuocChon;
        this.SPGUI=guiaa;
        this.SanPhamBUS= new SanPhamBUS(sanPhamDuocChon.getMaKhoHang());
        this.LoaiSPBUS= new LoaiSanPhamBUS(sanPhamDuocChon.getMaKhoHang());
        this.LoaiSPBUS= new LoaiSanPhamBUS(sanPhamDuocChon.getMaKhoHang());
        this.init();

    }
    
    public void init(){
        this.setTitle("Sản Phẩm");
        this.setLayout(new BorderLayout(0,0));
        this.setSize(1050, 600);
        // Font chữ
        Font tieudeFont= new Font("Arial",Font.BOLD,35);
        Font nutNhanFont= new Font("Arial",Font.BOLD,15);
        // Tiêu đề cho dialog
        JPanel tieuDe= new JPanel(new BorderLayout(0,0));
        JLabel tieuDeTrang= new JLabel(this.tieuDe);
        tieuDeTrang.setForeground(Color.white);
        tieuDeTrang.setFont(tieudeFont);
        tieuDeTrang.setSize(800, 100);
        tieuDeTrang.setHorizontalAlignment(JLabel.CENTER);
        tieuDe.add(tieuDeTrang);
        
        // Phần giữa
        // trái là hình ảnh và nút thêm ảnh; Phải là các thông tin label panel
        JPanel noiDungGiua= new JPanel(new BorderLayout(50, 0));
        noiDungGiua.setPreferredSize(new Dimension(1050,400));
        
        JPanel themHinhAnh= new JPanel(new FlowLayout(FlowLayout.CENTER));
        themHinhAnh.setPreferredSize(new Dimension(400,400));
        

         hinhAnh= new JLabel();
        JButton nutHinhAnh= new JButton("Hình ảnh");
        hinhAnh.setBorder(new EmptyBorder(10,10,10,10));
        hinhAnh.setPreferredSize(new Dimension(350,350));
        hinhAnh.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 10, 10), // Đặt khoảng cách
                BorderFactory.createLineBorder(Color.BLACK) // Đặt màu viền
        ));
        nutHinhAnh.addActionListener(this);
        nutHinhAnh.setPreferredSize(new Dimension(150,40));
        nutHinhAnh.setBackground(Color.decode("#82CAFF"));
        nutHinhAnh.setForeground(Color.white);
        nutHinhAnh.setFont(nutNhanFont);
        themHinhAnh.add(hinhAnh,BorderLayout.CENTER);
        themHinhAnh.add(nutHinhAnh,BorderLayout.SOUTH);
        
       
        themHinhAnh.add(nutHinhAnh,BorderLayout.SOUTH);
        noiDungGiua.add(themHinhAnh,BorderLayout.WEST);
        
        // phải là các input cho sản phẩm
        JPanel thongTinSP=new JPanel(new GridLayout(4,1));//GridLayout(3,4,10,5)
        thongTinSP.setPreferredSize(new Dimension(650,600));
        tenSP= new InputFormCustom("Tên Sản Phẩm:");
        tenSP.setPreferredSize(new Dimension(400,150));
        xuatXu= new InputFormCustom("Xuất Xứ");
                xuatXu.setPreferredSize(new Dimension(400,150));
        //thuongHieu= new InputFormCustom("Thương Hiệu:");
        //trongLuong= new InputFormCustom("Trọng Lượng:");
        tenLoai= new ComboBoxFormCustom("Loại:",LoaiSPBUS.tenLoaiSanPham());
                tenLoai.setPreferredSize(new Dimension(400,150));
        giaSP= new InputFormCustom("Giá Sản Phẩm:");
                giaSP.setPreferredSize(new Dimension(400,150));
        thongTinSP.add(tenSP); thongTinSP.add(giaSP);
        thongTinSP.add(tenLoai); thongTinSP.add(xuatXu); 
        //thongTinSP.add(thuongHieu); thongTinSP.add(trongLuong);
        noiDungGiua.add(thongTinSP,BorderLayout.CENTER);
        
        // Phần dưới
        // Nút thêm SP và hủy
        JPanel noiDungDuoi= new JPanel(new FlowLayout(FlowLayout.CENTER,50, 50));
        noiDungDuoi.setPreferredSize(new Dimension(1000,100));
        //noiDungDuoi.setBackground(Color.lightGray);
        JButton themSP, huySP, chinhsua;
        if (type.equals("Add"))
        {
            tieuDe.setBackground(Color.decode("#66FF33"));
            themSP= new JButton("Thêm SP Mới"); themSP.setPreferredSize(new Dimension(200,40));
            themSP.setOpaque(true);
            themSP.setFont(nutNhanFont);    
            themSP.setBackground(Color.decode("#66FF33"));
            noiDungDuoi.add(themSP);
            huySP= new JButton("Hủy Bỏ");
            themSP.addActionListener(this);
            themSP.setForeground(Color.white);
        }
        else {
            tieuDe.setBackground(Color.decode("#82CAFF"));
            themDuLieu();
            chinhsua= new JButton("Sửa Sản Phẩm"); chinhsua.setPreferredSize(new Dimension(200,40));
            chinhsua.setOpaque(true);
            chinhsua.setFont(nutNhanFont);    
            chinhsua.setBackground(Color.decode("#F88017"));
            noiDungDuoi.add(chinhsua);
            huySP= new JButton("Thoát");
            chinhsua.addActionListener(this);
            chinhsua.setForeground(Color.white);
            
        }
        huySP.setPreferredSize(new Dimension(200,40));
        huySP.setFont(nutNhanFont);
        huySP.setBackground(Color.decode("#CD0000"));
        huySP.setForeground(Color.white);
        noiDungDuoi.add(huySP);
        // xử lý sự kiện
        huySP.addActionListener(this);
                
                    
        // thêm nội dung
        this.add(tieuDe,BorderLayout.NORTH);
        this.add(noiDungGiua,BorderLayout.CENTER);
        this.add(noiDungDuoi,BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void themDuLieu(){
        URL img=null;
        try {
            img = new URL(CloundinaryServices.getUrlImage(this.SPDuocChon.getAnhMinhhoa()));
        } catch (MalformedURLException ex) {
            System.out.println("Lỗi ko load được ảnh từ SanPhamDialog");
        }
        ImageIcon pic= new ImageIcon(img);
        Image scaleImage = pic.getImage().getScaledInstance(320, 320,Image.SCALE_SMOOTH);
        this.hinhAnh.setIcon(new ImageIcon(scaleImage));
        this.tenSP.getTxtForm().setText(this.SPDuocChon.getTenSanPham());
        this.tenLoai.getList().setSelectedIndex(LoaiSPBUS.getIndexByMaLoaiSP(this.SPDuocChon.getMaLoaiSanPham()));
        this.giaSP.getTxtForm().setText(this.SPDuocChon.getGiaSanPham().toString());
        this.xuatXu.getTxtForm().setText(this.SPDuocChon.getXuatXu());
        System.out.println("Tênloại:"+ LoaiSPBUS.getLoaiSPChung().get(SPDuocChon.getMaLoaiSanPham()));
        this.tenLoai.list.setSelectedItem(LoaiSPBUS.getLoaiSPChung().get(SPDuocChon.getMaLoaiSanPham()-1));
        
    }

    public void XuLyThemSP(){
        if( this.tenSP.txtForm.getText().trim().equals("") ||
            this.tenLoai.list.getSelectedItem().toString().equals("Tất cả") ||
            this.giaSP.txtForm.getText().trim().equals("") ||
            this.xuatXu.txtForm.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (Double.parseDouble(this.giaSP.txtForm.getText().trim()) <= 0){
            JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else 
        try {
            String tenSP=this.tenSP.txtForm.getText();
            String xuatXu=this.xuatXu.txtForm.getText();
            int maKho=SPGUI.getMaKhoHang();    
            int giaSP=Integer.parseInt(this.giaSP.txtForm.getText());
            int maLoai=LoaiSPBUS.getMaLoaispWithTen(tenLoai.list.getSelectedItem().toString(),maKho);
        
            
            // hình ảnh thêm
            String linkAnhTuCloud="";
            if(this.selectedFile!=null){
                String hinhanh=selectedFile.getPath();
                linkAnhTuCloud=CloundinaryServices.createImage(hinhanh);

            }
            if (maLoai!=0){
                SanPhamDTO spMoi=new SanPhamDTO(tenSP, xuatXu, giaSP, 0, true, linkAnhTuCloud, maLoai, maKho);
                if(SanPhamBUS.create(spMoi)){
                    JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Mới Thành Công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.SPGUI.loadDuLieuTuDatabase(SanPhamBUS.getAll());
                    this.SPGUI.chinhSuaGiaoDienTable();
                    this.dispose();
                }else JOptionPane.showMessageDialog(this, "Lỗi, tên sản phẩm đã tồn tại trong kho!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(this, "Lỗi, chưa chọn loại sản phẩm!","Thông báo",JOptionPane.ERROR_MESSAGE);
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số với ô giá tiền !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void XuLyChinhSuaSP(){
              if(   this.tenSP.txtForm.getText().trim().equals("") ||
                    this.xuatXu.txtForm.getText().equals("") ||
                    this.tenLoai.list.getSelectedItem().toString().equals("Tất cả") ||
                    this.giaSP.txtForm.getText().trim().equals(""))
                {
                   JOptionPane.showMessageDialog(this, "Vui lòng không để trống !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                      else if (Double.parseDouble(this.giaSP.txtForm.getText().trim()) <= 0){
            JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
              else {
                  try {
                   int maSP=this.SPDuocChon.getMaSanPham();
                    String tenSP=this.tenSP.txtForm.getText();
                    String xuatXu=this.xuatXu.txtForm.getText();
                    int giaSP=Integer.parseInt(this.giaSP.txtForm.getText());
                    int soLuong=this.SPDuocChon.getSoLuongConLai();
                    
                    // xử lý ảnh trên cloud
                    String anhMinhHoaMoi=this.SPDuocChon.getAnhMinhhoa();
                    
                    if(this.selectedFile!=null){
                    String hinhAnhGoc=this.SPDuocChon.getAnhMinhhoa();
                    String hinhanh=selectedFile.getPath();
                    // xóa hình cũ
                    CloundinaryServices.deleteImage(hinhAnhGoc);
                     //hình mới
                    anhMinhHoaMoi=CloundinaryServices.createImage(hinhanh);
                    }
                    int maLoai=LoaiSPBUS.getMaLoaispWithTen(tenLoai.list.getSelectedItem().toString(),SPGUI.getMaKhoHang());
                    int maKho=SPGUI.getMaKhoHang();
                    SanPhamDTO spChinhSua=new SanPhamDTO(maSP,tenSP, xuatXu, giaSP, soLuong, true, anhMinhHoaMoi, maLoai, maKho);
                    if(SanPhamBUS.update(spChinhSua)){
                        JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                        this.SPGUI.loadDuLieuTuDatabase(SanPhamBUS.getAll());
                        this.SPGUI.chinhSuaGiaoDienTable();
                        this.dispose();
                    }else JOptionPane.showMessageDialog(this, "Lỗi, Tên không được trùng hoặc thông tin phải là số khác","Thông báo",JOptionPane.ERROR_MESSAGE);                 
                  }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số với ô giá tiền !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
   
              }
     
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String lenh=ae.getActionCommand();
        switch(lenh){
            case "Hủy Bỏ": case "Thoát":
                this.dispose();
                break;
            case "Hình ảnh":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose an Image");
                int userSelection = fileChooser.showOpenDialog(SanPhamDialog.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    this.selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile+"");
                    Image scaleImage = icon.getImage().getScaledInstance(320, 320,Image.SCALE_SMOOTH);
                    hinhAnh.setIcon(new ImageIcon(scaleImage));
                }
                break;
            case "Thêm SP Mới":
                XuLyThemSP();
                break;
            case "Sửa Sản Phẩm":
                XuLyChinhSuaSP();
                break;
              
               
        }

        
    }
}