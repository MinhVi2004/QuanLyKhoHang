package GUI.GUIPanel;

import BUS.ThongTinSanPham.LoaiSanPhamBUS;
import DTO.NguoiDung.TaiKhoanDTO;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import GUI.GUIDialog.LoaiSPDialog;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lombok.Data;

@Data
public class LoaiSanPhamUI extends JPanel implements ActionListener{

    private  DefaultTableModel model;
    private  JTable thongTin;

    int maKhoHang=0;
    private LoaiSanPhamBUS LoaiSPBUS;
    private ArrayList<LoaiSanPhamDTO> listLoaiSP;
    private  ButtonCustom themLoaiSP, suaLoaiSP, xoaLoaiSP;
    
    
    public LoaiSanPhamUI(int maKho) {
        this.setSize(1200,900);// 1980, 1050
        this.setLayout(new BorderLayout(0,0));  
        this.maKhoHang=maKho;
        this.LoaiSPBUS= new LoaiSanPhamBUS(this.maKhoHang);
        this.listLoaiSP= LoaiSPBUS.getAll();
        // tiêu đề trên JPanel
        JPanel panelTren= new JPanel(new FlowLayout(FlowLayout.LEFT,20,30));
        JLabel tieuDe=new JLabel("Loại Sản Phẩm");
        panelTren.setBackground(new Color(255,255,255));
        panelTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        tieuDe.setFont(new Font("Arial",Font.BOLD, 40));
        panelTren.setPreferredSize(new Dimension(1200,100));
        panelTren.add(tieuDe);
        //70% trái và 30% phải
        JPanel noiDung= new JPanel(new BorderLayout(0,0));
        noiDung.setPreferredSize(new Dimension(1200,800));
        JPanel panelTrai= new JPanel(new BorderLayout(0,0));
        panelTrai.setPreferredSize(new Dimension(900,800));
        JPanel panelPhai= new JPanel(new BorderLayout(0,0));
        panelPhai.setPreferredSize(new Dimension(300,800));
        panelTrai.setBackground(Color.red);
        panelPhai.setBackground(Color.blue);
        // bên phải
        JPanel timKiemLoaiSP= new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        timKiemLoaiSP.setPreferredSize(new Dimension(300,200));
        InputFormCustom timKiem= new InputFormCustom("Loại Sản Phẩm: ");
        timKiem.setPreferredSize(new Dimension(250,120));
        timKiemLoaiSP.add(timKiem);
        panelPhai.add(timKiemLoaiSP,BorderLayout.NORTH);
        
            // hiệu ứng cho textfield
        timKiem.getTxtForm().setText("Nhập tên loại tìm kiếm...");
        timKiem.getTxtForm().setForeground(Color.gray);
        timKiem.setBorder(new EmptyBorder(10,20,10,10)); // top left bottom right
        timKiem.getTxtForm().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),BorderFactory.createEmptyBorder(10,20,10,10));
                timKiem.getTxtForm().setBorder(border);
                if(timKiem.getTxtForm().getText().equals(("Nhập tên loại tìm kiếm...")))
                {
                    timKiem.getTxtForm().setText("");
                    timKiem.getTxtForm().setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent fe) {
                timKiem.getTxtForm().setBorder(new EmptyBorder(10,20,10,10));
                if( timKiem.getTxtForm().getText().isEmpty())
                {
                    timKiem.getTxtForm().setForeground(Color.GRAY);
                    timKiem.getTxtForm().setText("Nhập tên loại tìm kiếm...");
                }

            }
        });
        
        JPanel cacNutNhan= new JPanel(new FlowLayout(FlowLayout.CENTER,20,50));
        cacNutNhan.setBackground(Color.white);
        themLoaiSP= new ButtonCustom("Thêm Loại Mới", "", "#0091fd");
        themLoaiSP.setPreferredSize(new Dimension(280,100));
        themLoaiSP.setForeground(Color.white);
        suaLoaiSP= new ButtonCustom("Sửa Loại Hiện Tại","","#0091fd");
        suaLoaiSP.setPreferredSize(new Dimension(280,100));
        suaLoaiSP.setForeground(Color.white);
        xoaLoaiSP= new ButtonCustom("Xóa Loại Sản Phẩm","","#0091fd");
        xoaLoaiSP.setPreferredSize(new Dimension(280,100));
        xoaLoaiSP.setForeground(Color.white);
        chinhSuaGiaoDienNut();
        cacNutNhan.add(themLoaiSP);cacNutNhan.add(suaLoaiSP);cacNutNhan.add(xoaLoaiSP);
        panelPhai.add(cacNutNhan,BorderLayout.CENTER);
        // bên trái
        String[] colum = new String[]{"STT","Tên Loại Sản Phẩm"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colum);
        thongTin = new JTable(model);
        loadDuLieuLoaiSP(listLoaiSP);
        chinhSuaGiaoDienTable();
        JScrollPane dulieuLoaiSP = new JScrollPane(thongTin);
        
        panelTrai.add(dulieuLoaiSP,BorderLayout.CENTER);
        // add sự kiện button
        themLoaiSP.addActionListener(this);
        suaLoaiSP.addActionListener(this);
        xoaLoaiSP.addActionListener(this);
        timKiem.txtForm.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                listLoaiSP=LoaiSPBUS.search(timKiem.txtForm.getText());
                loadDuLieuLoaiSP(listLoaiSP);
                chinhSuaGiaoDienTable();
            }
        });
        
        
        noiDung.add(panelTrai,BorderLayout.CENTER);
        noiDung.add(panelPhai,BorderLayout.EAST);
        this.add(panelTren,BorderLayout.NORTH);
        this.add(noiDung);
    }
    
    
    public void loadDuLieuLoaiSP(ArrayList<LoaiSanPhamDTO> listLoaiSP){
        model.setRowCount(0);
        int dem=1;
        for(LoaiSanPhamDTO loaiSP: listLoaiSP){
            Object dong[]={dem,loaiSP.getTenLoaiSanPham()};
            model.addRow(dong);
            dem++;
        }
    }
    
    public void chinhSuaGiaoDienNut(){
        this.themLoaiSP.setHorizontalTextPosition(SwingConstants.CENTER);
        this.themLoaiSP.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.suaLoaiSP.setHorizontalTextPosition(SwingConstants.CENTER);
        this.suaLoaiSP.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.xoaLoaiSP.setHorizontalTextPosition(SwingConstants.CENTER);
        this.xoaLoaiSP.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void chinhSuaGiaoDienTable(){
                // Căn giữa tiêu đề của các cột
            //DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) thongTin.getTableHeader().getDefaultRenderer();
            //headerRenderer.setHorizontalAlignment(JLabel.CENTER);
            thongTin.setShowGrid(false);        thongTin.setShowHorizontalLines(true);
            thongTin.setDefaultEditor(Object.class, null);
            thongTin.setRowHeight(50);
            thongTin.getTableHeader().setReorderingAllowed(false);
            thongTin.getTableHeader().setPreferredSize(new Dimension(0,50));
            thongTin.getTableHeader().setFont(new Font("Arial",Font.BOLD,15));
            thongTin.getTableHeader().setBackground(Color.WHITE);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for(int i=0; i < thongTin.getColumnCount();i++){
                thongTin.getColumnModel().getColumn(i).setPreferredWidth(180);
                thongTin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer );
            }
            thongTin.getColumnModel().getColumn(0).setPreferredWidth(20);
    }


        
    @Override
    public void actionPerformed(ActionEvent e) {
        String btnLenh=e.getActionCommand();
        
        switch(btnLenh){
            case "Thêm Loại Mới":
                LoaiSPDialog themLoaiSP=new LoaiSPDialog(this,"Thêm Mới", "Add");
                break;
            case "Sửa Loại Hiện Tại":
                LoaiSanPhamBUS a= new LoaiSanPhamBUS(this.maKhoHang);
                listLoaiSP=a.getAll();
                if( this.thongTin.getSelectedRow()!=-1){
                    if ( listLoaiSP.get(thongTin.getSelectedRow()).getMaLoaiSanPham()==1){
                        JOptionPane.showMessageDialog(this, "Sản phẩm mặc định không thể chỉnh sửa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);           
                    }else{
                         LoaiSPDialog suaLoaiSP=new LoaiSPDialog(this,"Chỉnh Sửa", "Change",listLoaiSP.get(thongTin.getSelectedRow()));                     
                    }
                }
                else 
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để chỉnh sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Xóa Loại Sản Phẩm":
                LoaiSanPhamBUS b= new LoaiSanPhamBUS(this.maKhoHang);
                listLoaiSP=b.getAll();
                    int luaChon=JOptionPane.showConfirmDialog(this,"Chắc chắn xóa sản phẩm ?","Xóa Sản Phẩm",JOptionPane.YES_NO_OPTION);
                if( luaChon==0)
                {
                    if ( listLoaiSP.get(thongTin.getSelectedRow()).getMaLoaiSanPham()==1)
                        JOptionPane.showMessageDialog(this, "Sản phẩm mặc định không thể xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else {
                        LoaiSPBUS.delete(listLoaiSP.get(thongTin.getSelectedRow()));
                        loadDuLieuLoaiSP(LoaiSPBUS.getAll());
                        chinhSuaGiaoDienTable();
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã xóa");

                    }

                }
                    break;      
        }
        
    }
    
}