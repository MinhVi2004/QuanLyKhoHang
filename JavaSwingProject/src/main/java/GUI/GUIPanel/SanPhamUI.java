package GUI.GUIPanel;


import BUS.ThongTinSanPham.LoaiSanPhamBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIDialog.LocGiaSPDialog;
import GUI.GUIDialog.SanPhamDialog;
import GUI.GUIThanhPhan.ButtonCustom;
import Others.CloundinaryServices;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import lombok.Data;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.*;
import org.apache.poi.xssf.usermodel.*;

@Data
public class SanPhamUI extends JPanel implements ActionListener{

    private  JTable thongTin;
    private  DefaultTableModel model;
    private LoaiSanPhamBUS LoaiSPBUS;
    private JComboBox<String> locSP, locGia;
    private JRadioButton rbtn1, rbtn2, rbtn3, rbtn4;
    private String linkToIMG = "C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaVeryNew\\JavaSwingProject\\src\\main\\java\\Resources";
    int maKhoHang=0;
    private SanPhamBUS SPBUS;
    private ArrayList<SanPhamDTO> listSP;
    public SanPhamUI(int maKho)  {
        this.maKhoHang=maKho;
        this.SPBUS= new SanPhamBUS(this.maKhoHang);
        this.LoaiSPBUS= new LoaiSanPhamBUS(this.maKhoHang);
        this.listSP= SPBUS.getAll();
        this.setSize(1200,800);// 1980, 1050
        this.setLayout(new BorderLayout(0,0));    
        // menu điều hướng bên trái 15% 
        
        // chia 1/3 là chức năng, 2/3 dưới là table sản phẩm
        // PanelTren
        JPanel panelTren= new JPanel(new BorderLayout(0,0));
        panelTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        panelTren.setPreferredSize(new Dimension(1200,100));
        panelTren.setBackground(Color.WHITE);
        
        // PanelTren bên trái
        JPanel chucNangTrai= new JPanel(new FlowLayout(FlowLayout.CENTER,20,30));
        chucNangTrai.setBackground(Color.WHITE);//Color.decode("#3366FF"))
        JLabel noiDung=new JLabel("Các Sản Phẩm Trong Kho");
        noiDung.setFont(new Font("Arial",Font.BOLD, 40));
        chucNangTrai.add(noiDung);
        // PanelTren bên phải
        JPanel chucNangPhai= new JPanel(new FlowLayout(FlowLayout.RIGHT,20,20));
        chucNangPhai.setBackground(Color.WHITE);
        JButton themSP,xoaSP,excelSP;
        xoaSP = new ButtonCustom("Xóa Sản Phẩm", linkToIMG +"\\iconDelete24.png");
        themSP= new ButtonCustom("Thêm Sản Phẩm", linkToIMG +"\\iconAdd24.png");
        excelSP= new ButtonCustom("Xuất File Excel", linkToIMG+ "\\iconExcel24.png");
        xoaSP.setHorizontalTextPosition(SwingConstants.CENTER); 
        xoaSP.setVerticalTextPosition(SwingConstants.BOTTOM);
        themSP.setHorizontalTextPosition(SwingConstants.CENTER); 
        themSP.setVerticalTextPosition(SwingConstants.BOTTOM);
        excelSP.setHorizontalTextPosition(SwingConstants.CENTER); 
        excelSP.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        chucNangPhai.add(excelSP); chucNangPhai.add(themSP); chucNangPhai.add(xoaSP);
        panelTren.add(chucNangTrai,BorderLayout.WEST);
        panelTren.add(chucNangPhai,BorderLayout.EAST);
        
        
        // PanelDuoi
        JPanel panelDuoi= new JPanel(new BorderLayout(0,0));
        //PanelDuoi TimKiem
        JPanel phuongThucLocSP= new JPanel(new GridLayout(2,1,0,0));
        JPanel timKiemLocSP= new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
        // lọc SP theo loại
        locSP= new JComboBox<>(LoaiSPBUS.tenLoaiSanPham());
        locSP.setBackground(Color.WHITE);
        locSP.setPreferredSize(new Dimension(130,40));
        // lọc theo giá tăng giảm]
        String[] otp = {"Giá tăng dần", "Giá giảm dần"};
        locGia = new JComboBox<>(otp);
        locGia.setBackground(Color.WHITE);
        locGia.setPreferredSize(new Dimension(130,40));
        JTextField timKiem = new JTextField("Nhập nội dung tìm kiếm...");
        timKiem.setForeground(Color.GRAY);
        timKiem.setBorder(new EmptyBorder(10,20,10,10)); // top left bottom right
        timKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),BorderFactory.createEmptyBorder(10,20,10,10));
                timKiem.setBorder(border);
                if(timKiem.getText().equals(("Nhập nội dung tìm kiếm...")))
                {
                    timKiem.setText("");
                    timKiem.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent fe) {
                timKiem.setBorder(new EmptyBorder(10,20,10,10));
                if( timKiem.getText().isEmpty())
                {
                    timKiem.setForeground(Color.GRAY);
                    timKiem.setText("Nhập nội dung tìm kiếm...");
                }

            }
        });
        timKiem.setPreferredSize(new Dimension(900, 40));
        timKiemLocSP.add(locSP); timKiemLocSP.add(locGia); timKiemLocSP.add(timKiem);
        // lọc theo khoảng giá
        JPanel khoangGiaLocSP= new JPanel(new FlowLayout());
        ButtonCustom btn_LocNangCao= new ButtonCustom("Lọc Khoảng Giá");
        ButtonGroup locGroup= new ButtonGroup();
        btn_LocNangCao.setPreferredSize(new Dimension(150,30));
        rbtn1= new JRadioButton("Dưới 200,000 VNĐ");
        rbtn2= new JRadioButton("Từ 200,000 - 1,000,000 VNĐ");
        rbtn3= new JRadioButton("Từ 1,000,000 - 2,000,000 VNĐ");
        rbtn4= new JRadioButton("Trên 2,000,000 VNĐ");
        rbtn1.setFont(new Font("Arial",Font.BOLD,12));  rbtn2.setFont(new Font("Arial",Font.BOLD,12));
        rbtn3.setFont(new Font("Arial",Font.BOLD,12));  rbtn4.setFont(new Font("Arial",Font.BOLD,12));
        khoangGiaLocSP.add(btn_LocNangCao);
        locGroup.add(rbtn1);    locGroup.add(rbtn2);    locGroup.add(rbtn3);    locGroup.add(rbtn4);
        khoangGiaLocSP.add(rbtn1);  khoangGiaLocSP.add(rbtn2);  khoangGiaLocSP.add(rbtn3);  khoangGiaLocSP.add(rbtn4);
        phuongThucLocSP.add(timKiemLocSP);  phuongThucLocSP.add(khoangGiaLocSP);
        // PanelDuoi Table
        String[] colum = new String[]{"STT", "Hình Ảnh", "Mã Sản Phẩm", "Tên sản phẩm","Loại Sản Phẩm", "Xuất xứ",  "Số lượng", "Giá"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colum);
        thongTin = new JTable(model);
        thongTin.setPreferredScrollableViewportSize(thongTin.getPreferredSize());
        loadDuLieuTuDatabase(listSP);
        chinhSuaGiaoDienTable();


        JScrollPane dulieuSP = new JScrollPane(thongTin);

        
        panelDuoi.add(phuongThucLocSP,BorderLayout.NORTH);
        panelDuoi.add(dulieuSP,BorderLayout.CENTER);
        
        //// mục sự kiện chính cho giao diện
        // nút thêm sp
        themSP.addActionListener(this);
        // double click xem thông tin
        thongTin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                if (e.getClickCount() == 2)
                {
                    SanPhamDialog spDia=new SanPhamDialog(SanPhamUI.this,"Chi Tiết Sản Phẩm","ChiTiet",listSP.get(thongTin.getSelectedRow()));
                    loadDuLieuTuDatabase(new SanPhamBUS(maKhoHang).getAll());
                    chinhSuaGiaoDienTable();
                }
            }
        });
        // nút xóa sản phẩm
        xoaSP.addActionListener(this);
        // lọc loại sản phẩm và tìm kiếm
        excelSP.addActionListener(this);
        locSP.addActionListener(this);          locGia.addActionListener(this);
        rbtn1.addActionListener(this); rbtn2.addActionListener(this);   rbtn3.addActionListener(this);  rbtn4.addActionListener(this);
        btn_LocNangCao.addActionListener(this);
        timKiem.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                listSP=SPBUS.search(timKiem.getText());
                loadDuLieuTuDatabase(listSP);
                chinhSuaGiaoDienTable();
            }
        });
        
        
        this.add(panelTren,BorderLayout.NORTH);
        this.add(panelDuoi,BorderLayout.CENTER);
    }
    public void chinhSuaGiaoDienTable(){
                // Căn giữa tiêu đề của các cột
        //DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) thongTin.getTableHeader().getDefaultRenderer();
        //headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        thongTin.setShowGrid(false);        thongTin.setShowHorizontalLines(true);
        
       thongTin.setDefaultEditor(Object.class, null);
        
        thongTin.setRowHeight(80);
       
        thongTin.getTableHeader().setReorderingAllowed(false);
        thongTin.getTableHeader().setPreferredSize(new Dimension(0,50));
       thongTin.getTableHeader().setFont(new Font("Arial",Font.BOLD,15));
        thongTin.getTableHeader().setBackground(Color.WHITE);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        
        
       for(int i=0; i < thongTin.getColumnCount();i++){
           // set kích thước, ngoài trừ cột chứa ảnh
            if( i!=1){
                thongTin.getColumnModel().getColumn(i).setPreferredWidth(150);
                if( i!=2)
                    thongTin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer );      
            }
        }
        thongTin.getColumnModel().getColumn(0).setPreferredWidth(60);
        thongTin.getColumnModel().getColumn(2).setPreferredWidth(350);
    }
    
    public String toCurrency(double a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
    public void loadDuLieuTuDatabase(ArrayList<SanPhamDTO> listSP){
        
//        for (int i = model.getRowCount() - 1; i >= 0; i--) {
//                  model.removeRow(i);
//            }        
        DefaultTableModel dtm= new DefaultTableModel(){
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }};
        String[] colum = new String[]{"STT", "Hình Ảnh", "Tên sản phẩm","Loại Sản Phẩm", "Xuất xứ",  "Số lượng", "Giá"};
        dtm.setColumnIdentifiers(colum);
        thongTin.setModel(dtm);
        thongTin.getColumnModel().getColumn(1).setPreferredWidth(150);
        thongTin.setDefaultEditor(Object.class, null);
        int dem=1;
        for(SanPhamDTO sanPham: listSP){
            try {
                int viTriTrongKho=LoaiSPBUS.getIndexByMaLoaiSP(sanPham.getMaLoaiSanPham());
                String tenLoaiSP=LoaiSPBUS.tenLoaiSanPham()[viTriTrongKho+1];
                URL img= new URL(CloundinaryServices.getUrlImage(sanPham.getAnhMinhhoa()));
                ImageIcon pic= new ImageIcon(img);
                Image scaleImage = pic.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
                Icon picFix= new ImageIcon(scaleImage);
                Object dong[]={dem,picFix,sanPham.getTenSanPham(),tenLoaiSP,sanPham.getXuatXu(), sanPham.getSoLuongConLai(), toCurrency(sanPham.getGiaSanPham())};
                dtm.addRow(dong);
                dem+=1;
            } catch (MalformedURLException ex) {
                System.out.println("Ko lấy dc link ảnh");
            }
        }
    }
          
    public void xuatFileExcelSanPham(){
        XSSFWorkbook workbook=new XSSFWorkbook();
        // tạo sheet cho excel
        XSSFSheet sheet=workbook.createSheet("DanhSachSanPham");
        // khởi tạo dòng và ô
        XSSFRow row=null; 
        XSSFCell cell=null;
        row=sheet.createRow(2); // xuống 2 dòng trong excel
        // tạo các ô tiêu đề bắt đầu từ dòng row theo cột 0
        String[] colum = new String[]{"STT","Mã Sản Phẩm", "Tên sản phẩm","Loại Sản Phẩm", "Xuất xứ",  "Số lượng", "Giá"};
        for ( int i=0; i < colum.length;i++){
            cell=row.createCell(i,CellType.STRING);
            cell.setCellValue(colum[i]);
                  
        }
        

       for( int i=0; i < this.listSP.size();i++){
            // bắt đầu từ dòng 3 vì dòng 2 là tiêu đề
            row=sheet.createRow(3+i);
            cell=row.createCell(0,CellType.NUMERIC);
            cell.setCellValue(i+1);
            
            cell=row.createCell(1,CellType.NUMERIC);
            cell.setCellValue(listSP.get(i).getMaSanPham());
            
            cell=row.createCell(2,CellType.STRING);
            cell.setCellValue(listSP.get(i).getTenSanPham());
            
            cell=row.createCell(3,CellType.STRING);
            cell.setCellValue(LoaiSPBUS.tenLoaiSanPham()[listSP.get(i).getMaLoaiSanPham()]);
            
            cell=row.createCell(4,CellType.STRING);
            cell.setCellValue(listSP.get(i).getXuatXu());
            
            cell=row.createCell(5,CellType.NUMERIC);
            cell.setCellValue(listSP.get(i).getSoLuongConLai());
            
            cell=row.createCell(6,CellType.NUMERIC);
            cell.setCellValue(listSP.get(i).getGiaSanPham());
       }
    JFileChooser chonFile = new JFileChooser();
    chonFile.setDialogTitle("Chọn thư mục tạo file");

   
   // Ktra chọn file
   int chonThuMuc = chonFile.showSaveDialog(null);
   if (chonThuMuc == JFileChooser.APPROVE_OPTION) {
       File chonTenFile = chonFile.getSelectedFile();
       String filePath = chonTenFile.getAbsolutePath();
       
       if (!filePath.toLowerCase().endsWith(".xlsx"))
           filePath += ".xlsx";

       try {
           FileOutputStream file= new FileOutputStream(filePath);
           workbook.write(file);
           file.close();
           JOptionPane.showMessageDialog(this, "Tạo file thành công");
       } catch (FileNotFoundException e) {
           System.out.println("Lỗi ko tìm dc file");
       } catch (IOException ex) {
           System.out.println("Lỗi IO");
       }
   }
       
}
    
    
@Override
    public void actionPerformed(ActionEvent ae) {
        String lenh=ae.getActionCommand();
        SanPhamDialog ab= null;
        if (ae.getSource() instanceof JRadioButton) {
            JRadioButton btnDuocChon = (JRadioButton) ae.getSource();
            String lenhRadio=btnDuocChon.getText();
            switch(lenhRadio){
                case "Dưới 200,000 VNĐ":
                    listSP=SPBUS.locTheoKhoangGia(1,0,0);
                    break;
                case "Từ 200,000 - 1,000,000 VNĐ":
                    listSP=SPBUS.locTheoKhoangGia(2,0,0);
                    break;
                case "Từ 1,000,000 - 2,000,000 VNĐ":
                    listSP=SPBUS.locTheoKhoangGia(3,0,0);
                    break;
                case "Trên 2,000,000 VNĐ":
                    listSP=SPBUS.locTheoKhoangGia(4,0,0);
                    break;
            }
            loadDuLieuTuDatabase(listSP);
            chinhSuaGiaoDienTable();
        }
        else if( ae.getSource()== locSP){
            if (locSP.getSelectedItem().toString().equals("Tất cả"))
                    listSP=SPBUS.getAll();   
            else 
                listSP=SPBUS.searchVoiLoaiSP(locSP.getSelectedItem().toString());
            loadDuLieuTuDatabase(listSP);
            chinhSuaGiaoDienTable(); 
        }
        
        else if (ae.getSource()==locGia){
            if(locGia.getSelectedItem().toString().equals("Giá tăng dần"))
                listSP=SPBUS.locTheoGiaTangGiam(listSP,"Tăng");
            else 
                listSP=SPBUS.locTheoGiaTangGiam(listSP,"Giảm");
            loadDuLieuTuDatabase(listSP);
            chinhSuaGiaoDienTable();
        }
        switch (lenh) {
            case "Thêm Sản Phẩm":
                ab=new SanPhamDialog(this,"Thêm SP mới","Add");
                listSP=SPBUS.getAll();
                loadDuLieuTuDatabase(listSP);
                chinhSuaGiaoDienTable();
                break;
            case "Xuất File Excel":
                xuatFileExcelSanPham();
                break;
            case "Xóa Sản Phẩm":
                if(lenh.equals("Xóa Sản Phẩm") && thongTin.getSelectedRow()!=-1){
                    int luaChon=JOptionPane.showConfirmDialog(this,"Chắc chắn xóa sản phẩm ?","Xóa Sản Phẩm",JOptionPane.YES_NO_OPTION);
                    if( luaChon==0){
                       SPBUS.delete(listSP.get(thongTin.getSelectedRow()));
                       JOptionPane.showMessageDialog(this, "Sản phẩm đã xóa");
                       loadDuLieuTuDatabase(SPBUS.getAll());
                       chinhSuaGiaoDienTable();
                    }
                   }else 
                       JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");          
                break;
            case "Lọc Khoảng Giá":
                LocGiaSPDialog locKhoangGia= new LocGiaSPDialog(this);
                break;
                
            }
        }    
    }
