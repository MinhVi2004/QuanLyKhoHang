package GUI.GUIDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
// import javax.swing.border.*;
// import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.*;

import BUS.NghiepVuXuatKho.ChiTietPhieuXuatKhoBUS;
import BUS.NghiepVuXuatKho.PhieuXuatKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuXuatKho.*;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIPanel.PhieuXuatUI;
import GUI.GUIThanhPhan.ButtonCustom;

public class TrangThaiDonXuatDialog implements ActionListener{
      ChiTietPhieuXuatKhoBUS chiTietPhieuXuatKhoBUS;
      PhieuXuatKhoBUS phieuXuatKhoBUS;
      PhieuXuatUI phieuXuat;
      SanPhamBUS sanPhamBUS;
      JFrame frame = new JFrame();
      JPanel header;
      JLabel header_lb;
      JPanel main;
      JPanel main_top;
      JLabel ma_phieu,ma_phieu_lb;
      JLabel ma_kho_hang, ma_kho_hang_lb;
      JLabel ten_kho_hang, ten_kho_hang_lb;
      JLabel nguoi_xuat, nguoi_xuat_lb;
      JLabel ngay_xuat_kho, ngay_xuat_kho_lb;
      JLabel trang_thai;
      JComboBox trang_thai_cb;
      JPanel main_middle;
      DefaultTableModel model_ds_ctpxk;
      JTable table_ds_ctpxk;
      JScrollPane ds_ctpxk;
      JPanel main_bottom;
      JLabel tong_tien, tong_tien_lb;
      ButtonCustom capnhat_btn;
      public TrangThaiDonXuatDialog(PhieuXuatUI phieuXuat, int maPhieuXuat) {
          this.phieuXuat=phieuXuat;
            chiTietPhieuXuatKhoBUS = new ChiTietPhieuXuatKhoBUS();
            phieuXuatKhoBUS = new PhieuXuatKhoBUS();
            header = new JPanel();
            header_lb = new JLabel("CHI TIẾT PHIẾU XUẤT");
            header_lb.setForeground(Color.WHITE);
            header_lb.setFont(new Font("Arial", Font.BOLD, 20));
            header.setBackground(Color.BLACK);
            header.setPreferredSize(new Dimension(800,35));
            header.add(header_lb);
            main = new JPanel();
            main_top = new JPanel();
            //? JLabel ma_phieu,ma_phieu_lb;
            ma_phieu = new JLabel("Mã phiếu : ");
            ma_phieu_lb = new JLabel();

            //? JLabel ma_kho_hang, ma_kho_hang_lb;
            ma_kho_hang = new JLabel("Mã kho hàng : ");
            ma_kho_hang_lb = new JLabel();

            //? JLabel ten_kho_hang, ten_kho_hang_lb;
            ten_kho_hang = new JLabel("Tên kho hàng : ");
            ten_kho_hang_lb = new JLabel();

            //? JLabel nguoi_xuat, nguoi_xuat_lb;
            nguoi_xuat = new JLabel("Tên người xuất : ");
            nguoi_xuat_lb = new JLabel();

            //? JLabel ngay_xuat_kho, ngay_xuat_kho_lb;
            ngay_xuat_kho = new JLabel("Ngày xuất kho : ");
            ngay_xuat_kho_lb = new JLabel();

            //? JLabel trang_thai, trang_thai_lb;
            trang_thai = new JLabel("Trạng thái : ");
            setTrangThai(maPhieuXuat);
            trang_thai_cb.setPreferredSize(new Dimension(70,35));
            main_top.add(ma_phieu); main_top.add(ma_phieu_lb);
            main_top.add(ma_kho_hang); main_top.add(ma_kho_hang_lb);
            main_top.add(ten_kho_hang); main_top.add(ten_kho_hang_lb);
            main_top.add(nguoi_xuat); main_top.add(nguoi_xuat_lb);
            main_top.add(ngay_xuat_kho); main_top.add(ngay_xuat_kho_lb);
            main_top.add(trang_thai); main_top.add(trang_thai_cb);
            main_top.setLayout(new GridLayout(3, 3));
            main_top.setPreferredSize(new Dimension(600,70));

            main_middle = new JPanel();
            String columns_ds_ctpxk[] = {"STT", "Mã sản phẩm","Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
            String data_ds_ctpxk[][] = {};
            model_ds_ctpxk = new DefaultTableModel(data_ds_ctpxk, columns_ds_ctpxk){
                  @Override
                  public Class<?> getColumnClass(int columnIndex) {
                        // Đặt kiểu dữ liệu cho từng cột
                        if (columnIndex == 0 || columnIndex == 1 || columnIndex == 3) {
                              return Integer.class;
                        } else if (columnIndex == 2 || columnIndex == 4 || columnIndex == 5) {
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
            table_ds_ctpxk = new JTable(model_ds_ctpxk);
            table_ds_ctpxk.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_ds_ctpxk.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_ds_ctpxk.getColumnModel().getColumn(2).setPreferredWidth(250);
            table_ds_ctpxk.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_ds_ctpxk.getColumnModel().getColumn(4).setPreferredWidth(40);
            table_ds_ctpxk.getColumnModel().getColumn(5).setPreferredWidth(80);
            //? Set vị trí cho nội dung (căn giữa cho nội dung)
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table_ds_ctpxk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getTableHeader().setReorderingAllowed(false);
            table_ds_ctpxk.setRowHeight(40);
            table_ds_ctpxk.setEnabled(false);
            ds_ctpxk = new JScrollPane(table_ds_ctpxk);
            ds_ctpxk.setPreferredSize(new Dimension(730,300));
            ds_ctpxk.setBackground(Color.WHITE);
            main_middle.add(ds_ctpxk);

            main_bottom = new JPanel();
            // JLabel tong_tien, tong_tien_lb;
            tong_tien = new JLabel("Tổng tiền : ");
            tong_tien_lb = new JLabel();
            // JButton closeCTPXK;
            capnhat_btn = new ButtonCustom("Cập nhật","","#3eceff");
            capnhat_btn.addActionListener(this);
            main_bottom.add(tong_tien); main_bottom.add(tong_tien_lb);
            main_bottom.add(capnhat_btn);
            main.add(main_top);
            main.add(main_middle);
            main.add(main_bottom);
            main.setLayout(new FlowLayout());
            main.setPreferredSize(new Dimension(750,480));
            setThongTinMain(maPhieuXuat);
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));

            frame.add(header);
            frame.add(main);
            frame.setLayout(new FlowLayout());
            frame.setSize(800,600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
      }
      public String toCurrency(int a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
      public String toCurrency(long a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
      public String toStringTrangThai(String trangThai) {
            if(trangThai.equals("DaDuyet")) {
                  return "Đã duyệt";
            } else if(trangThai.equals("ChoDuyet")) {
                  return "Chờ duyệt";
            } else {
                  return "Hủy";
            }
      }
      public void setTrangThai(int maPhieuXuat) {
            PhieuXuatKhoDTO phieu = phieuXuatKhoBUS.getById(maPhieuXuat);
            String[] trangThai = {"Chờ Duyệt", "Đã Duyệt", "Hủy"};
            trang_thai_cb = new JComboBox<>(trangThai); 
            if(phieu.getTrangThai().equals("ChoDuyet")) {
                  trang_thai_cb.setSelectedIndex(0);
            }
      }
      public void setThongTinMain(int maPhieuXuat) {
            PhieuXuatKhoDTO phieuXuat = phieuXuatKhoBUS.getById(maPhieuXuat);
            //? Set thông tin hiển thị
            ma_phieu_lb.setText(String.valueOf(maPhieuXuat));
            ma_kho_hang_lb.setText(String.valueOf(phieuXuat.getMaKhoHang()));
            ten_kho_hang_lb.setText(phieuXuatKhoBUS.getTenKhoHang(maPhieuXuat));
            nguoi_xuat_lb.setText(phieuXuatKhoBUS.getHoTenByMaPhieuXuat(maPhieuXuat));
            ngay_xuat_kho_lb.setText(String.valueOf(phieuXuat.getNgayXuatKho()));
            tong_tien_lb.setText(toCurrency(phieuXuat.getTongGiaTri()));
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));
            //? Tạo bảng danh sách
            ArrayList<ChiTietPhieuXuatKhoDTO> chiTietPhieuXuatKhoList = chiTietPhieuXuatKhoBUS.getAll(maPhieuXuat);
            for(int i = 0; i < chiTietPhieuXuatKhoList.size(); i++) {
                  model_ds_ctpxk.addRow(new Object[]{
                      model_ds_ctpxk.getRowCount()+1,
                      chiTietPhieuXuatKhoList.get(i).getMaSanPham(),
                      sanPhamBUS.getById(chiTietPhieuXuatKhoList.get(i).getMaSanPham()).getTenSanPham(),
                      toCurrency(chiTietPhieuXuatKhoList.get(i).getDonGia()),
                      chiTietPhieuXuatKhoList.get(i).getSoLuong(),
                      toCurrency(chiTietPhieuXuatKhoList.get(i).getThanhTien()),
                  });
            }
      }
      public void capNhatTrangThaiDonXuat() {
            if(trang_thai_cb.getSelectedIndex() == 1) {
                  if(JOptionPane.showConfirmDialog(null, "Bạn có muốn duyệt phiếu xuất này ?", "Cập nhật trạng thái phiếu xuất", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        PhieuXuatKhoDTO phieu = phieuXuatKhoBUS.getById(Integer.valueOf(ma_phieu_lb.getText()));
                        phieu.setTrangThai("DaDuyet");
                        phieuXuatKhoBUS.update(phieu);

                        //? Update Số lượng còn lại của sản phẩm
                        for(int i = 0; i < model_ds_ctpxk.getRowCount(); i++) {
                              SanPhamDTO sp = sanPhamBUS.getById((Integer)model_ds_ctpxk.getValueAt(i, 1));
                              sp.setSoLuongConLai(sp.getSoLuongConLai() - (Integer)model_ds_ctpxk.getValueAt(i, 4));

                              sanPhamBUS.update(sp);
                        }
                        JOptionPane.showMessageDialog(null, "Duyệt phiếu xuất thành công !","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        this.phieuXuat.showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(Integer.parseInt(ma_kho_hang_lb.getText())));
                        frame.dispose();
                  }
            }
            if(trang_thai_cb.getSelectedIndex() == 2) {
                  if(JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy phiếu xuất này ?", "Cập nhật trạng thái phiếu xuất", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        PhieuXuatKhoDTO phieu = phieuXuatKhoBUS.getById(Integer.valueOf(ma_phieu_lb.getText()));
                        phieu.setTrangThai("Huy");
                        phieuXuatKhoBUS.update(phieu);

                        //!Hủy thì không cần set số lượng còn lại của sản phẩm
                        // //? Update Số lượng còn lại của sản phẩm
                        // for(int i = 0; i < model_ds_ctpxk.getRowCount(); i++) {
                        //       SanPhamDTO sp = sanPhamBUS.getById((Integer)model_ds_ctpxk.getValueAt(i, 1));
                        //       sp.setSoLuongConLai(sp.getSoLuongConLai() - (Integer)model_ds_ctpxk.getValueAt(i, 4));

                        //       sanPhamBUS.update(sp);
                        // }
                        JOptionPane.showMessageDialog(null, "Hủy phiếu xuất thành công !","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        this.phieuXuat.showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(Integer.parseInt(ma_kho_hang_lb.getText())));
                        frame.dispose();
                  }
            }
            if(trang_thai_cb.getSelectedIndex() == 0) {
                  System.out.println("chờ duyệt");
                  frame.dispose();
            }
      }
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == capnhat_btn) {
                  capNhatTrangThaiDonXuat();
            }
      }
}
