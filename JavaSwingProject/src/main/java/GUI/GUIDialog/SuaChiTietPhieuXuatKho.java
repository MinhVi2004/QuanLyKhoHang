package GUI.GUIDialog;

import BUS.NghiepVuXuatKho.ChiTietPhieuXuatKhoBUS;
import BUS.NghiepVuXuatKho.PhieuXuatKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuXuatKho.*;
import DTO.ThongTinSanPham.*;
import GUI.GUIPanel.PhieuXuatUI;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.ArrayList;
import java.time.*;
import java.time.format.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
// import Others.JDBCConfigure;
// import java.sql.*;

public class SuaChiTietPhieuXuatKho extends JFrame implements ActionListener{
      private int maKhoHang =0 ; 
      SanPhamBUS sanPhamBUS;
      PhieuXuatKhoBUS phieuXuatKhoBUS;
      PhieuXuatUI phieuXuat;
      ChiTietPhieuXuatKhoBUS chiTietPhieuXuatKhoBUS;
      ArrayList<ChiTietPhieuXuatKhoDTO> listFirst = new ArrayList<>();
      long tongGiaTri = 0;
      
      JPanel header;
            JLabel header_lb;
      JPanel main;
            //? Main left
            JPanel main_left;
                  JPanel tim_kiem;
                        JLabel tim_kiem_lb;
                        JTextField tim_kiem_tf;
                  DefaultTableModel model_ds_san_pham;
                  JTable table_ds_san_pham;
                  JScrollPane ds_san_pham;

                  JPanel them_sp;
                        JLabel them_sp_lb;
                        JTextField them_sp_number;
                        JButton them_sp_confirm;
                  //? Main right
                  JPanel main_right;
                        JPanel thong_tin;
                              JPanel ma_phieu_xuat_pn;
                                    JLabel ma_phieu_xuat;
                                    JTextField ma_phieu_xuat_tf;

                              JPanel nguoi_tao_phieu_pn;
                                    JLabel nguoi_tao_phieu;
                                    JTextField nguoi_tao_phieu_tf;

                              JPanel ngay_tao_phieu_pn;
                                    JLabel ngay_tao_phieu;
                                    JTextField ngay_tao_phieu_tf;
                              JPanel trang_thai_pn;
                                    JLabel trang_thai_lb;

                        DefaultTableModel model_ds_xuat_hang;
                        JTable table_ds_xuat_hang;
                        JScrollPane ds_xuat_hang;

                        JPanel chuc_nang_pn;
                              JButton xoa_sp;
                              JButton sua_sl_sp;
                        JPanel thanh_tien_pn;
                              JLabel thanh_tien_lb;
                              JLabel thanh_tien_total;
                              JButton thanh_tien_xac_nhan;


      Color bgBlue = new Color(0,145,253);
      public SuaChiTietPhieuXuatKho(PhieuXuatUI phieuXuat, int maPhieuXuat) {
          this.phieuXuat=phieuXuat;
            phieuXuatKhoBUS = new PhieuXuatKhoBUS();
            chiTietPhieuXuatKhoBUS = new ChiTietPhieuXuatKhoBUS();
            header = new JPanel();
                  header_lb = new JLabel("SỬA CHI TIẾT PHIẾU XUẤT");
                  header_lb.setFont(new Font("Arial", Font.BOLD, 20));
                  header_lb.setForeground(Color.WHITE);
            header.setBackground(Color.BLACK);
            header.setPreferredSize(new Dimension(800,35));
            header.add(header_lb);

            main = new JPanel();
            main.setPreferredSize(new Dimension(1300, 800));
            main.setLayout(new FlowLayout());
                        main_left = new JPanel();
                        main_left.setPreferredSize(new Dimension(600,700));
                        main_left.setBackground(Color.WHITE);
                              tim_kiem = new JPanel();
                              tim_kiem.setPreferredSize(new Dimension(550, 70));
                              tim_kiem.setBorder(new CompoundBorder(new TitledBorder("Tìm Kiếm"), new EmptyBorder(4, 4, 4, 4)));
                              tim_kiem.setBackground(Color.WHITE);
                                    tim_kiem_lb = new JLabel("Tìm theo tên : ");
                                    tim_kiem_tf = new JTextField(30);
                                    tim_kiem_tf.setPreferredSize(new Dimension(100, 35));
                                    tim_kiem_tf.addKeyListener(new KeyAdapter(){
                                          public void keyReleased(KeyEvent e){
                                              ArrayList<SanPhamDTO> listSanPham=sanPhamBUS.search(tim_kiem_tf.getText());
                                              showDanhSachSanPham(listSanPham);
                                          }
                                    });
                              tim_kiem.add(tim_kiem_lb);
                              tim_kiem.add(tim_kiem_tf);

                              String columns_ds_san_pham[] = {"Mã máy", "Tên máy", "Số lượng", "Đơn giá"};
                              String data_ds_san_pham[][] = {};
                              model_ds_san_pham = new DefaultTableModel(data_ds_san_pham, columns_ds_san_pham){
                                    @Override
                                    public Class<?> getColumnClass(int columnIndex) {
                                          // Đặt kiểu dữ liệu cho từng cột
                                          if (columnIndex == 0) {
                                                return Integer.class; // Kiểu dữ liệu cho cột 0 là Integer
                                          } else if (columnIndex == 1) {
                                                return String.class; // Kiểu dữ liệu cho cột 1 là String
                                          } else if (columnIndex == 2) {
                                                return Integer.class;
                                          } else if (columnIndex == 3) {
                                                return String.class;
                                          } else {
                                                return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                                          }
                                    }
                                    @Override
                                    public boolean isCellEditable(int row, int column) {
                                    // Make all cells non-editable
                                    return false;
                                    }
                                };
                              table_ds_san_pham = new JTable(model_ds_san_pham);
                              table_ds_san_pham.getColumnModel().getColumn(0).setPreferredWidth(20);
                              table_ds_san_pham.getColumnModel().getColumn(1).setPreferredWidth(225);
                              table_ds_san_pham.getColumnModel().getColumn(2).setPreferredWidth(20);
                              table_ds_san_pham.getColumnModel().getColumn(3).setPreferredWidth(80);
                              table_ds_san_pham.setRowHeight(40);
                                
                              //? Set vị trí cho nội dung (căn giữa cho nội dung)
                              DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                              centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                              table_ds_san_pham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                              table_ds_san_pham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                              table_ds_san_pham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                              table_ds_san_pham.setRowHeight(40);
                              table_ds_san_pham.getTableHeader().setReorderingAllowed(false);

                              JTableHeader header_san_pham = table_ds_san_pham.getTableHeader();
                              // Set the preferred size of the header
                              header_san_pham.setPreferredSize(new Dimension(header_san_pham.getWidth(), 35));
                              ds_san_pham = new JScrollPane(table_ds_san_pham);
                              ds_san_pham.setPreferredSize(new Dimension(550, 500));
                              ds_san_pham.setBackground(Color.WHITE);
                              

                              them_sp = new JPanel();
                              them_sp.setBackground(Color.WHITE);
                                    them_sp_lb = new JLabel("Số lượng : ");
                                    them_sp_lb.setFont(new Font("Arial", Font.BOLD, 16));

                                    them_sp_number = new JTextField(5);
                                    them_sp_number.setText("");
                                    them_sp_number.setPreferredSize(new Dimension(100, 35));
                                    them_sp_number.setHorizontalAlignment(SwingConstants.CENTER);

                                    them_sp_confirm = new JButton("Thêm");
                                    them_sp_confirm.addActionListener(this);
                                    them_sp_confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                              them_sp.setPreferredSize(new Dimension(550, 50));
                              them_sp.add(them_sp_lb);
                              them_sp.add(them_sp_number);
                              them_sp.add(customButtonMain(them_sp_confirm,100,35));

                        main_left.add(tim_kiem);
                        main_left.add(ds_san_pham);
                        main_left.add(them_sp);

                        //? --------------------------------------------

                        main_right = new JPanel();
                        main_right.setBackground(Color.WHITE);
                        main_right.setPreferredSize(new Dimension(600,700));
                              thong_tin = new JPanel();
                              thong_tin.setLayout(new BoxLayout(thong_tin, BoxLayout.Y_AXIS));
                                    ma_phieu_xuat_pn = new JPanel();
                                          ma_phieu_xuat = new JLabel("Mã phiếu xuất : ");
                                          ma_phieu_xuat_tf = new JTextField(30);
                                          ma_phieu_xuat_tf.setPreferredSize(new Dimension(500, 35));
                                    //! sửa ở đây
                                    capNhatMaPhieuXuat(maPhieuXuat);
                                          ma_phieu_xuat_tf.setEditable(false);
                                    ma_phieu_xuat_pn.setPreferredSize(new Dimension(550,40));
                                    ma_phieu_xuat_pn.add(ma_phieu_xuat);
                                    ma_phieu_xuat_pn.add(ma_phieu_xuat_tf);

                                    nguoi_tao_phieu_pn = new JPanel();
                                          nguoi_tao_phieu = new JLabel("Người tạo phiếu : ");
                                          nguoi_tao_phieu_tf = new JTextField("Admin",30);
                                          nguoi_tao_phieu_tf.setPreferredSize(new Dimension(50, 35));
                                          nguoi_tao_phieu_tf.setEditable(false);
                                    //! sửa ở đây
                                    capNhatAdmin(maPhieuXuat);
                                    nguoi_tao_phieu_pn.setPreferredSize(new Dimension(550,40));
                                    nguoi_tao_phieu_pn.add(nguoi_tao_phieu);
                                    nguoi_tao_phieu_pn.add(nguoi_tao_phieu_tf);

                                    ngay_tao_phieu_pn = new JPanel();
                                          ngay_tao_phieu = new JLabel("Ngày tạo phiếu : ");
                                          ngay_tao_phieu_tf = new JTextField(30);
                                          ngay_tao_phieu_tf.setPreferredSize(new Dimension(50, 35));
                                    //! sửa ở đây
                                    capNhatNgayXuatKho(maPhieuXuat);
                                    ngay_tao_phieu_pn.setPreferredSize(new Dimension(550,40));
                                    ngay_tao_phieu_pn.add(ngay_tao_phieu);
                                    ngay_tao_phieu_pn.add(ngay_tao_phieu_tf);

                                    trang_thai_pn = new JPanel();
                                          trang_thai_lb = new JLabel("Chờ duyệt");
                                    trang_thai_pn.add(trang_thai_lb);
                              thong_tin.add(ma_phieu_xuat_pn);
                              thong_tin.add(nguoi_tao_phieu_pn);
                              thong_tin.add(ngay_tao_phieu_pn);
                              thong_tin.add(trang_thai_pn);

                              String columns_xuat_hang[] = {"STT", "Mã SP", "Tên SP","Số lượng", "Đơn giá"};
                              String data_xuat_hang[][] = {};
                              model_ds_xuat_hang = new DefaultTableModel(data_xuat_hang, columns_xuat_hang){
                                    @Override
                                    public Class<?> getColumnClass(int columnIndex) {
                                          // Đặt kiểu dữ liệu cho từng cột
                                          if (columnIndex == 0) {
                                                return Integer.class; // Kiểu dữ liệu cho cột 0 là Double
                                          } else if (columnIndex == 1) {
                                                return String.class; // Kiểu dữ liệu cho cột 1 là String
                                          } else if (columnIndex == 2) {
                                                return Integer.class; // Giả sử kiểu dữ liệu cho cột 2 là Double
                                          } else if (columnIndex == 3) {
                                                return String.class; // Kiểu dữ liệu cho cột 4 là String
                                          } else {
                                                return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                                          }
                                    }
                                    @Override
                                    public boolean isCellEditable(int row, int column) {
                                    // Make all cells non-editable
                                    return false;
                                    }
                                };
                              table_ds_xuat_hang = new JTable(model_ds_xuat_hang);
                              table_ds_xuat_hang.getColumnModel().getColumn(0).setPreferredWidth(15);
                              table_ds_xuat_hang.getColumnModel().getColumn(1).setPreferredWidth(15);
                              table_ds_xuat_hang.getColumnModel().getColumn(2).setPreferredWidth(250);
                              table_ds_xuat_hang.getColumnModel().getColumn(3).setPreferredWidth(20);
                              table_ds_xuat_hang.getColumnModel().getColumn(4).setPreferredWidth(70);
                              JTableHeader header_xuat_hang = table_ds_xuat_hang.getTableHeader();
                              // Set the preferred size of the header
                              header_xuat_hang.setPreferredSize(new Dimension(header_xuat_hang.getWidth(), 35));

                              table_ds_xuat_hang.getTableHeader().setReorderingAllowed(false);
                              table_ds_xuat_hang.setRowHeight(40);
                              ds_xuat_hang = new JScrollPane(table_ds_xuat_hang);
                              ds_xuat_hang.setPreferredSize(new Dimension(550, 410));

                              chuc_nang_pn = new JPanel();
                              chuc_nang_pn.setPreferredSize(new Dimension(550,40));
                                    xoa_sp = new JButton("Xóa sản phẩm");
                                    xoa_sp.addActionListener(this);
                                    sua_sl_sp = new JButton("Sửa số lượng");
                                    sua_sl_sp.addActionListener(this);
                              chuc_nang_pn.add(customButtonMain(xoa_sp,150,35));
                              chuc_nang_pn.add(customButtonMain(sua_sl_sp,150,35));

                              thanh_tien_pn = new JPanel();
                                          thanh_tien_lb = new JLabel("Tổng tiền : ");
                                          thanh_tien_lb.setFont(new Font("Arial", Font.BOLD, 16));
                                          thanh_tien_lb.setPreferredSize(new Dimension(130, 35));
                                          thanh_tien_total = new JLabel("0 đ");
                                          thanh_tien_total.setFont(new Font("Arial", Font.BOLD, 18));
                                          thanh_tien_total.setPreferredSize(new Dimension(150, 35));
                                          thanh_tien_total.setForeground(Color.RED);
                                          thanh_tien_xac_nhan = new JButton("Sửa phiếu xuất hàng");
                                          thanh_tien_xac_nhan.addActionListener(this);
                                          thanh_tien_xac_nhan = customButtonMain(thanh_tien_xac_nhan,130,35);
                                          thanh_tien_xac_nhan.setPreferredSize(new Dimension(130, 35));
                              thanh_tien_pn.setPreferredSize(new Dimension(550, 50));
                              thanh_tien_pn.setLayout(new FlowLayout(FlowLayout.CENTER));
                              thanh_tien_pn.add(thanh_tien_lb);
                              thanh_tien_pn.add(thanh_tien_total);
                              thanh_tien_pn.add(thanh_tien_xac_nhan);
                        main_right.add(thong_tin);
                        main_right.add(ds_xuat_hang);
                        main_right.add(chuc_nang_pn);
                        main_right.add(thanh_tien_pn);

                  main.add(main_left);
                  main.add(main_right);
                  main.setPreferredSize(new Dimension(1300,800));
                  main.setLayout(new FlowLayout());
                  main.setVisible(true);


                  setCTPXK(maPhieuXuat);
                  showDanhSachSanPham(sanPhamBUS.getAll());
                  listFirst = getDanhSachChiTietPhieuXuatKho();
                  add(header);
                  add(main);
                  setLayout(new FlowLayout());
                  setSize(1300,800);
                  setResizable(false);
                  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  setLocationRelativeTo(null);
                  setVisible(true);
                  
      }
      public JButton customButtonMain(JButton button, int width, int height) {
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Thiết lập font
            button.setForeground(Color.WHITE); // Thiết lập màu chữ
            button.setBackground(bgBlue); // Thiết lập màu nền
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY,1)); // Thiết lập viền
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Dimension size = button.getPreferredSize();
            size.width = width;
            size.height = height;
            button.setPreferredSize(size);
            return button;
      }
      public String toCurrency(double a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
      public Double currencyBack(String a) {
            return Double.parseDouble(a.replaceAll("[^0-9]", ""));
      }
      public Integer getIntegerValueAt(JTable data, int rowIndex, int columnIndex) {
            Object value = data.getValueAt(rowIndex, columnIndex); // Giả sử data là một mảng 2 chiều chứa dữ liệu của bảng
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof String) {
                // Cố gắng chuyển đổi từ kiểu String sang kiểu Integer
                try {
                    return Integer.parseInt((String) value);
                } catch (NumberFormatException e) {
                    // Xử lý ngoại lệ nếu không thể chuyển đổi
                    System.out.println("bat loi");
                    return null; // hoặc trả về giá trị mặc định khác tùy thuộc vào nhu cầu
                }
            } else {
                System.out.println("loi khac");
                return null;
            }
        }
      public Double getDoubleValueAt(JTable data, int rowIndex, int columnIndex) {
            Object value = data.getValueAt(rowIndex, columnIndex); // Giả sử data là một mảng 2 chiều chứa dữ liệu của bảng
            if (value instanceof Double) {
                return ((Double) value).doubleValue();
            } else if (value instanceof Integer) {
                  // Xử lý khi value là kiểu int
                  return (double) ((Integer) value);
            } else if (value instanceof String) {
                // Cố gắng chuyển đổi từ kiểu String sang kiểu Double
                try {
                    return Double.parseDouble((String) value);
                } catch (NumberFormatException e) {
                      // Xử lý ngoại lệ nếu không thể chuyển đổi
                  System.out.println("bat loi");
                  return null; // hoặc trả về giá trị mặc định khác tùy thuộc vào nhu cầu
                }
            } else {
                  System.out.println("loi khac");
                  return null;
            }
      }
      public void xuLyTongGiaTri() {
            //? Tính và hiển hiển thị tổng giá trị phiếu xuất hàng
            if(table_ds_xuat_hang.getRowCount() != -1) {
                  int sum = 0;
                  for(int i= 0; i < table_ds_xuat_hang.getRowCount(); i++) {
                        sum += getDoubleValueAt(table_ds_xuat_hang,i ,3) * currencyBack(String.valueOf(table_ds_xuat_hang.getValueAt(i,4)));
                  }
                  thanh_tien_total.setText(toCurrency(sum));
                  tongGiaTri = sum;
            }
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == them_sp_confirm) {
                  themSanPhamPhieuXuatKho();
            } else if(e.getSource() == xoa_sp) {
                  xoaSanPhamPhieuXuatKho();
            } else if(e.getSource() == sua_sl_sp) {
                  suaSanPhamPhieuXuatKho();
            } else if(e.getSource() == thanh_tien_xac_nhan) {
                  suaPhieuXuatKho();
            }else {
                  System.out.println("Bam 1 nut nao do");
            }
      }
      public void showDanhSachSanPham(ArrayList<SanPhamDTO> dsSanPham) {
            int i = 1;
            int rowCount = model_ds_san_pham.getRowCount();
            //? Xóa tất cả các dòng
            for (int j = rowCount - 1; j >= 0; j--) {
                model_ds_san_pham.removeRow(j);
            }
            //? Thêm các dòng
            for (SanPhamDTO sanPham : dsSanPham) {
                  model_ds_san_pham.addRow(new Object[]{
                              i,
                              sanPham.getTenSanPham(),
                              sanPham.getSoLuongConLai(),
                              toCurrency(sanPham.getGiaSanPham())
                  });
                  i++;
            }
      }
      
      public void capNhatMaPhieuXuat(int maPhieuXuat) {
            ma_phieu_xuat_tf.setText(String.valueOf(maPhieuXuat));
      }
      public void capNhatAdmin(int maPhieuXuat) {
            nguoi_tao_phieu_tf.setText(phieuXuatKhoBUS.getHoTenByMaPhieuXuat(maPhieuXuat));
      }
      public void capNhatNgayXuatKho(int maPhieuXuat) {
            PhieuXuatKhoDTO phieuXuat = phieuXuatKhoBUS.getById(maPhieuXuat);
            ngay_tao_phieu_tf.setText(String.valueOf(phieuXuat.getNgayXuatKho()).replace("T"," "));
      }
      public void themSanPhamPhieuXuatKho() {
            //? Check number
            String text = them_sp_number.getText();
            if (!text.matches("^\\d+$")) {
                  //? Check kí tự có hợp lệ hay không
                  JOptionPane.showMessageDialog(null, "Chỉ được nhập số!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                  them_sp_number.setText("");
            } else if(Integer.valueOf(them_sp_number.getText())  <= 0) {
                  //? Check xem số lượng cần thêm có phải số âm hoặc bằng 0 hay không
                  JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                  them_sp_number.setText("");
            } else {
                  int selectedRow = table_ds_san_pham.getSelectedRow();
                  if(selectedRow != -1) {
                        //? Đếm số lượng đã có trong phiếu xuất hàng
                        int tongSoLuong = 0;
                        boolean check = false;
                        int i;
                        for(i= 0; i < table_ds_xuat_hang.getRowCount(); i++) {
                              if(table_ds_san_pham.getValueAt(selectedRow,0) == table_ds_xuat_hang.getValueAt(i, 1)){
                                    tongSoLuong += getIntegerValueAt(table_ds_xuat_hang, i, 3);
                                    check = true;
                                    break;
                              }
                        }
                        tongSoLuong += Integer.valueOf(them_sp_number.getText());
                        if(getIntegerValueAt(table_ds_san_pham, selectedRow, 2) >= tongSoLuong) {
                              if(check == true) {
                                    model_ds_xuat_hang.setValueAt(tongSoLuong, i, 3);
                                    xuLyTongGiaTri();
                                    them_sp_number.setText("");
                              } else {
                                    //? Thêm dữ liệu vào bảng xuất hàng
                                    model_ds_xuat_hang.addRow(new Object[]{
                                          model_ds_xuat_hang.getRowCount()+1,
                                          table_ds_san_pham.getValueAt(selectedRow,0),
                                          table_ds_san_pham.getValueAt(selectedRow,1),
                                          Integer.parseInt(them_sp_number.getText()),
                                          table_ds_san_pham.getValueAt(selectedRow,3)
                                    });
                                    xuLyTongGiaTri();
                                    them_sp_number.setText("");
                              }
                        } else {
                              System.out.println(getIntegerValueAt(table_ds_san_pham, selectedRow, 2));
                              JOptionPane.showMessageDialog(null, "Vượt quá số lượng trong kho!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                              them_sp_number.setText("");
                        }
                  } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                        them_sp_number.setText("");
                  }
            }
      }
      public void xoaSanPhamPhieuXuatKho() {
            //? Dòng đang chọn
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            if(selectedRow != -1) {
                  int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                  if (choice == JOptionPane.YES_OPTION) {
                        //? chọn YES
                        model_ds_xuat_hang.removeRow(selectedRow);
                  }
                  xuLyTongGiaTri();
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm trong bảng xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
      public void suaSanPhamPhieuXuatKho() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
                  if(selectedRow != -1) {
                        String text = JOptionPane.showInputDialog(null,"Nhập số lượng","Sửa số lượng",JOptionPane.PLAIN_MESSAGE);
                        int soLuongConLai = sanPhamBUS.getById((int)(table_ds_xuat_hang.getValueAt(selectedRow, 1))).getSoLuongConLai();
                        if(text!= null) {
                              if (!text.matches("^\\d+$")) {
                                    //? Check kí tự có hợp lệ hay không
                                    JOptionPane.showMessageDialog(null, "Chỉ được nhập số!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                              } else if(Integer.valueOf(text) <= 0 || Integer.valueOf(text) < soLuongConLai + (int)(table_ds_xuat_hang.getValueAt(selectedRow, 3))) {
                                    //? Check xem số lượng cần thêm có phải số âm hoặc bằng 0 hay không
                                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                              } else {
                                    model_ds_xuat_hang.setValueAt(Integer.valueOf(text), selectedRow, 3);
                                    xuLyTongGiaTri();
                              }
                        }
                  } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm trong bảng xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                  }
      }
      public ArrayList<ChiTietPhieuXuatKhoDTO> getDanhSachChiTietPhieuXuatKho() {
            ArrayList<ChiTietPhieuXuatKhoDTO> danhSachSanPhamTaoPhieuXuatHang = new ArrayList<>();
           for(int i = 0; i < table_ds_xuat_hang.getRowCount(); i++) {
                 int maSanPham = (Integer)table_ds_xuat_hang.getValueAt(i, 1);
                 SanPhamDTO sanPham = sanPhamBUS.getById(maSanPham);
                 ChiTietPhieuXuatKhoDTO temp = new ChiTietPhieuXuatKhoDTO();
                 temp.setSoLuong((Integer)table_ds_xuat_hang.getValueAt(i, 3));
                 temp.setDonGia(sanPham.getGiaSanPham());
                 temp.setThanhTien((Integer)table_ds_xuat_hang.getValueAt(i, 3) * sanPham.getGiaSanPham());
                 temp.setMaPhieu(Integer.valueOf(ma_phieu_xuat_tf.getText()));
                 temp.setMaSanPham(sanPham.getMaSanPham());
                 danhSachSanPhamTaoPhieuXuatHang.add(temp);
           }
            return danhSachSanPhamTaoPhieuXuatHang;
      }
      public void suaPhieuXuatKho() {
            if(JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa phiếu xuất này ?", "Sửa phiếu xuất", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                  PhieuXuatKhoDTO phieuXuatKho = new PhieuXuatKhoDTO();
                  System.out.println("Tổng giá trị : " + tongGiaTri);
                  phieuXuatKho.setTongGiaTri(tongGiaTri);
                  phieuXuatKho.setMaPhieu(Integer.parseInt(ma_phieu_xuat_tf.getText()));
                  String ngayTaoPhieu = ngay_tao_phieu_tf.getText();
                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  
                  // Phân tích chuỗi thành LocalDate
                  LocalDate localDate = LocalDate.parse(ngayTaoPhieu, formatter);
                  LocalDate localDateNow = LocalDate.now();

                  if(localDate.isBefore(localDateNow) || localDate.isEqual(localDateNow)) {
                        phieuXuatKho.setNgayXuatKho(localDate);
                  } else if(localDate.isAfter(localDateNow)){
                        JOptionPane.showMessageDialog(null, "Ngày không hợp lệ !","Thông báo", JOptionPane.ERROR_MESSAGE);
                        return;
                  }
                  phieuXuatKho.setTrangThai("ChoDuyet");
                  phieuXuatKhoBUS.update(phieuXuatKho);  //? Update phiếu xuất kho
                  //? DELETE CTPXK
                  for(ChiTietPhieuXuatKhoDTO chiTietPhieuXuatKho : listFirst){
                        chiTietPhieuXuatKhoBUS.delete(chiTietPhieuXuatKho);
                  }
                  //? UPDATE CTPXK
                  for (ChiTietPhieuXuatKhoDTO chiTietPhieuXuatKho : getDanhSachChiTietPhieuXuatKho()) {
                        chiTietPhieuXuatKhoBUS.create(this.maKhoHang, chiTietPhieuXuatKho);
                  }
                  //? cập nhật danh sách sản phẩm
                  for (int i = model_ds_san_pham.getRowCount() - 1; i >= 0; i--) 
                        model_ds_san_pham.removeRow(i);
                  showDanhSachSanPham(sanPhamBUS.getAll());
                  
                  //?cập nhật thành tiền
                  thanh_tien_total.setText("0 đ");
                  JOptionPane.showMessageDialog(null, "Sửa phiếu xuất hàng thành công !","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                  this.phieuXuat.showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
                  dispose();
            } else {
                  System.out.println("Huy sua  phieu xuat");
            }
      }
      public void setCTPXK(int maPhieuXuat) {
            //? Tạo bảng danh sách
            PhieuXuatKhoDTO phieuXuatKho = phieuXuatKhoBUS.getById(maPhieuXuat);
            this.maKhoHang = phieuXuatKho.getMaKhoHang();
            System.out.println("MÃ KHO HÀNG : " + this.maKhoHang);
            sanPhamBUS = new SanPhamBUS(this.maKhoHang);
            ArrayList<ChiTietPhieuXuatKhoDTO> chiTietPhieuXuatKhoList = chiTietPhieuXuatKhoBUS.getAll(maPhieuXuat);
            for(int i = 0; i < chiTietPhieuXuatKhoList.size(); i++) {
                  model_ds_xuat_hang.addRow(new Object[]{
                        model_ds_xuat_hang.getRowCount()+1,
                        chiTietPhieuXuatKhoList.get(i).getMaSanPham(),
                        sanPhamBUS.getById(chiTietPhieuXuatKhoList.get(i).getMaSanPham()).getTenSanPham(),
                        chiTietPhieuXuatKhoList.get(i).getSoLuong(),
                        toCurrency(chiTietPhieuXuatKhoList.get(i).getDonGia()),
                  });
                  //? Set vị trí cho nội dung (căn giữa cho nội dung)
                  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.setRowHeight(40);
                  table_ds_xuat_hang.getTableHeader().setReorderingAllowed(false);
                  
                  xuLyTongGiaTri();
            }
      }
      public void addWindowListener(WindowListener windowListener) {

      }

}
