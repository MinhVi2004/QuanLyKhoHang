package GUI.GUIPanel;


import BUS.NghiepVuXuatKho.ChiTietPhieuXuatKhoBUS;
import BUS.NghiepVuXuatKho.PhieuXuatKhoBUS;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import GUI.GUIDialog.ChiTietPhieuXuatKho;
import GUI.GUIDialog.SuaChiTietPhieuNhapKho;
import GUI.GUIDialog.SuaChiTietPhieuXuatKho;
import GUI.GUIDialog.TrangThaiDonNhapDialog;
import GUI.GUIDialog.TrangThaiDonXuatDialog;

import java.time.LocalDate;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuXuatUI extends JPanel implements ActionListener{
      int maKhoHang = 0;
      PhieuXuatKhoBUS phieuXuatKhoBUS;
      ChiTietPhieuXuatKhoBUS chiTietPhieuXuatKhoBUS = new ChiTietPhieuXuatKhoBUS();
      JPanel title;
      JLabel title_lb;
      JPanel top;
            JPanel chucNangPanel;
                  JButton sua_btn;
                  JButton chi_tiet_btn;
                  JButton trang_thai_btn;
                  JButton xuatExcel_btn;
            JPanel timKiemPanel;
                  JLabel tim_kiem;
                  JComboBox tim_kiem_cb;
                  JLabel locTrangThai;
                  JComboBox locTrangThai_cb;   
            JButton refeshButton;
      JPanel bot;
            DefaultTableModel model_ds_xuat_hang;
            JTable table_ds_xuat_hang;
            JScrollPane ds_xuat_hang;
      public PhieuXuatUI(int maKhoHang) {
            phieuXuatKhoBUS = new PhieuXuatKhoBUS();
            this.maKhoHang = maKhoHang;
            title = new JPanel();
                  title_lb = new JLabel("PHIẾU XUẤT");
                  title_lb.setFont(new Font("Arial", Font.BOLD, 16)); 
            title.setPreferredSize(new Dimension(1200,40));
            title.add(title_lb);
            top = new JPanel();
                  chucNangPanel = new JPanel();
                        chi_tiet_btn = new JButton("Chi tiết");
                        chi_tiet_btn.addActionListener(this);
                        sua_btn = new JButton("Sửa");
                        sua_btn.addActionListener(this);
                        trang_thai_btn = new JButton("Trạng thái đơn");
                        trang_thai_btn.addActionListener(this);
                  chucNangPanel.setBorder(new CompoundBorder(new TitledBorder("Chức năng"), new EmptyBorder(4, 4, 4, 4)));
                  chucNangPanel.add(customButtonOption(chi_tiet_btn,"chi_tiet.png"));
                  chucNangPanel.add(customButtonOption(sua_btn,"sua_btn.png"));
                  chucNangPanel.add(customButtonOption(trang_thai_btn,"xoa_btn.png"));


                  timKiemPanel = new JPanel();
                        tim_kiem = new JLabel("Tổng giá trị : ");
                        String price[] = {"tất cả","dưới 5,000,000 VNĐ", "5,000,000 VNĐ - 10,000,000 VNĐ", "10,000,000 VNĐ - 20,000,000 VNĐ","20,000,000 VNĐ - 40,000,000 VNĐ", "lớn hơn 40,000,000 VNĐ"};
 
                        tim_kiem_cb = new JComboBox(price);
                          tim_kiem_cb.addItemListener(new ItemListener() {
                              public void itemStateChanged(ItemEvent e) {
                                  if (e.getStateChange() == ItemEvent.SELECTED) {
                                    ArrayList<PhieuXuatKhoDTO> listPhieuXuatKho=phieuXuatKhoBUS.search(tim_kiem_cb.getSelectedIndex(),maKhoHang);
                                    showDanhSachPhieuXuatHang(listPhieuXuatKho);
                                  }
                              }
                          });
                          locTrangThai= new JLabel("Trạng thái:");
                        String tt[]={"Tất cả","Đã Duyệt","Chờ Duyệt"};
                        locTrangThai_cb= new JComboBox<>(tt);
                        locTrangThai_cb.addItemListener(new ItemListener() {
                              public void itemStateChanged(ItemEvent e) {
                                  if (e.getStateChange() == ItemEvent.SELECTED) {
                                      ArrayList<PhieuXuatKhoDTO> danhSachLoc = phieuXuatKhoBUS.locTrangThai(locTrangThai_cb.getSelectedIndex(), maKhoHang);
                                      showDanhSachPhieuXuatHang(danhSachLoc);
                                  }
                              }
                          });     
                  timKiemPanel.setBorder(new CompoundBorder(new TitledBorder("Tìm Kiếm"), new EmptyBorder(4, 4, 4, 4)));
                  timKiemPanel.add(tim_kiem);
                  timKiemPanel.add(tim_kiem_cb);
                  timKiemPanel.add(locTrangThai);
                  timKiemPanel.add(locTrangThai_cb);

                  refeshButton = new JButton("Tải lại");
                  refeshButton.setIcon(new ImageIcon("C:\\Users\\dvmv2\\OneDrive\\Documents\\Nam_2\\Fixx\\Java-Swing-main-29-2\\Java-Swing-main\\JavaSwingProject\\src\\main\\java\\Resources\\refesh.png"));
                  refeshButton.setPreferredSize(new Dimension(110,40));
                  refeshButton.addActionListener(this);
            top.setPreferredSize(new Dimension(1200,100));
            top.add(chucNangPanel);
            top.add(timKiemPanel);
            top.add(refeshButton);

            bot = new JPanel();
                  
                  String columns_ds_xuat_hang[] = {"Mã phiếu xuất", "Ngày xuất kho","Tổng giá trị","Trạng thái"};
                  String data_ds_xuat_hang[][] = {};
                  model_ds_xuat_hang = new DefaultTableModel(data_ds_xuat_hang, columns_ds_xuat_hang){
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                              // Đặt kiểu dữ liệu cho từng cột
                              if (columnIndex == 0) {
                                    return Integer.class; // Kiểu dữ liệu cho cột 0 là Integer
                              } else if (columnIndex == 1) {
                                    return LocalDate.class; // Giả sử kiểu dữ liệu cho cột 3 là LocalDate
                              } else if (columnIndex == 2) {
                                    return Integer.class; // Kiểu dữ liệu cho cột 4 là Integer
                              } else if (columnIndex == 3) {
                                    return String.class; // Kiểu dữ liệu cho cột 4 là String
                              } else {
                                    return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                              }
                        }
                    };
                  table_ds_xuat_hang = new JTable(model_ds_xuat_hang);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setPreferredWidth(100);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setPreferredWidth(200);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setPreferredWidth(200);
                  table_ds_xuat_hang.getColumnModel().getColumn(3).setPreferredWidth(150);
                  //? Set vị trí cho nội dung (căn giữa cho nội dung)
                  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.setRowHeight(40);
                  showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
                  ds_xuat_hang = new JScrollPane(table_ds_xuat_hang);
                  ds_xuat_hang.setPreferredSize(new Dimension(900, 600));
                  ds_xuat_hang.setBackground(Color.WHITE);

            bot.setPreferredSize(new Dimension(1200,700));
            bot.add(ds_xuat_hang);

            add(title);
            add(top);
            add(bot);
            setPreferredSize(new Dimension(1300,800));
            setLayout(new FlowLayout());
            setVisible(true);
            showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
      }
      public JButton customButtonOption(JButton button, String linkIMG) {
            BoxLayout boxlayout = new BoxLayout(button, BoxLayout.Y_AXIS);
            button.setLayout(boxlayout);
            button.setFont(new Font("Arial", Font.BOLD, 12)); // Thiết lập font
            button.setForeground(Color.BLACK); // Thiết lập màu chữ
            button.setBackground(Color.WHITE); // Thiết lập màu nền
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1)); // Thiết lập viền
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // Tùy chỉnh kích thước
            Dimension size = button.getPreferredSize();
            size.width = 100;
            size.height = 40;
            button.setPreferredSize(size);

            ImageIcon icon = new ImageIcon("C:\\Users\\dvmv2\\OneDrive\\Documents\\Nam_2\\Fixx\\Java-Swing-main-2-5\\Java-Swing-main\\JavaSwingProject\\src\\main\\java\\Resources" + linkIMG); 
            button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

            return button;
      }
      public String toCurrency(long a) {
            DecimalFormat numberFormat = new DecimalFormat("###,###.## VNĐ");
            return  numberFormat.format(a);
      }
      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == sua_btn) {
                  suaChiTietPhieuXuatHang();
            } else if(e.getSource() == chi_tiet_btn) {
                  xemChiTietPhieuXuatHang();
            } else if(e.getSource() == trang_thai_btn) {
                  xemTrangThaiPhieuXuatHang();
            }else if(e.getSource() == refeshButton) {
                  tim_kiem_cb.setSelectedIndex(0);
                  showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
            }
      }
      public void showDanhSachPhieuXuatHang(ArrayList<PhieuXuatKhoDTO> dsPhieuXuatKho) {
           //? Xóa bảng danh sách sản phẩm xuất hàng
          
           for (int i = model_ds_xuat_hang.getRowCount() - 1; i >= 0; i--) {
                 model_ds_xuat_hang.removeRow(i);
           }
           for (PhieuXuatKhoDTO phieuXuatKho : dsPhieuXuatKho) {
                 model_ds_xuat_hang.addRow(new Object[]{
                             phieuXuatKho.getMaPhieu(),
                             String.valueOf(phieuXuatKho.getNgayXuatKho()).replace("T"," "),
                             toCurrency(phieuXuatKho.getTongGiaTri()),
                             toStringTrangThai(phieuXuatKho.getTrangThai()),
                 });
           }
      }
      public String toStringTrangThai(String trangThai) {
            if(trangThai.equals("DaDuyet")) {
                  return "Đã Duyệt";
            } else if(trangThai.equals("ChoDuyet")) {
                  return "Chờ Duyệt";
            } else {
                  return "Hủy";
            }
      }
      
      public void xemChiTietPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            //? kiểm tra xem có dòng nào đang được chọn không
            if(selectedRow != -1) {
                  int maPhieuXuat = (Integer) table_ds_xuat_hang.getValueAt(selectedRow, 0);
                  new ChiTietPhieuXuatKho(maPhieuXuat);
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
      public void suaChiTietPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            //? kiểm tra xem có dòng nào đang được chọn không
            if(selectedRow != -1) {
                  if (table_ds_xuat_hang.getValueAt(selectedRow,3).toString().equals("Chờ Duyệt")){
                        int maPhieuXuat = (Integer) table_ds_xuat_hang.getValueAt(selectedRow, 0);
                        new SuaChiTietPhieuXuatKho(this,maPhieuXuat);                  
                  } else {
                        JOptionPane.showMessageDialog(null, "Sửa phiếu xuất chỉ dành cho đơn chờ duyệt !!!","Cảnh báo", JOptionPane.ERROR_MESSAGE);                         
                  }
                  
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
      public void xemTrangThaiPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            if(selectedRow != -1) {
                  if (table_ds_xuat_hang.getValueAt(selectedRow,3).toString().equals("Chờ Duyệt")){
                        int maPhieuXuat = (Integer) table_ds_xuat_hang.getValueAt(selectedRow, 0);
                        new TrangThaiDonXuatDialog(this,maPhieuXuat);                        
                  } else {
                        JOptionPane.showMessageDialog(null, "Thay đổi trạng thái chỉ dành cho đơn chờ duyệt !!!","Cảnh báo", JOptionPane.ERROR_MESSAGE);                        
                  }
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }         
      }
}