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

import BUS.NghiepVuNhapKho.ChiTietPhieuNhapKhoBUS;
import BUS.NghiepVuNhapKho.PhieuNhapKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuNhapKho.*;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIPanel.PhieuNhapUI;
import GUI.GUIThanhPhan.ButtonCustom;
import Others.JDBCConfigure;

public class TrangThaiDonNhapDialog implements ActionListener{
      ChiTietPhieuNhapKhoBUS chiTietPhieuNhapKhoBUS ;
      PhieuNhapKhoBUS phieuNhapKhoBUS;
      SanPhamBUS sanPhamBUS;
      PhieuNhapUI phieuNhap;
      JFrame frame = new JFrame();
      JPanel header;
      JLabel header_lb;
      JPanel main;
      JPanel main_top;
      JLabel ma_phieu,ma_phieu_lb;
      JLabel ma_kho_hang, ma_kho_hang_lb;
      JLabel ten_kho_hang, ten_kho_hang_lb;
      JLabel nguoi_nhap, nguoi_nhap_lb;
      JLabel ngay_nhap_kho, ngay_nhap_kho_lb;
      JLabel trang_thai;
      JComboBox trang_thai_cb;
      JLabel nha_cung_cap, nha_cung_cap_lb;
      JPanel main_middle;
      DefaultTableModel model_ds_ctpnk;
      JTable table_ds_ctpnk;
      JScrollPane ds_ctpnk;
      JPanel main_bottom;
      JLabel tong_tien, tong_tien_lb;
      ButtonCustom capnhat_btn;
      public TrangThaiDonNhapDialog(PhieuNhapUI phieuNhap, int maPhieuNhap) {
            this.phieuNhap=phieuNhap;
            chiTietPhieuNhapKhoBUS = new ChiTietPhieuNhapKhoBUS();
            phieuNhapKhoBUS = new PhieuNhapKhoBUS();
            header = new JPanel();
            header_lb = new JLabel("CHI TIẾT PHIẾU NHẬP");
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

            //? JLabel nguoi_nhap, nguoi_nhap_lb;
            nguoi_nhap = new JLabel("Tên người nhập : ");
            nguoi_nhap_lb = new JLabel();

            //? JLabel ngay_nhap_kho, ngay_nhap_kho_lb;
            ngay_nhap_kho = new JLabel("Ngày nhập kho : ");
            ngay_nhap_kho_lb = new JLabel();

            //? JLabel trang_thai, trang_thai_lb;
            trang_thai = new JLabel("Trạng thái : ");
            setTrangThai(maPhieuNhap);
            trang_thai_cb.setPreferredSize(new Dimension(70,35));
            
            //? JLabel nha_cung_cap, nha_cung_cap_lb;
            nha_cung_cap = new JLabel("Nhà cung cấp : ");
            nha_cung_cap_lb = new JLabel();
            
            main_top.add(ma_phieu); main_top.add(ma_phieu_lb);
            main_top.add(ma_kho_hang); main_top.add(ma_kho_hang_lb);
            main_top.add(ten_kho_hang); main_top.add(ten_kho_hang_lb);
            main_top.add(nguoi_nhap); main_top.add(nguoi_nhap_lb);
            main_top.add(ngay_nhap_kho); main_top.add(ngay_nhap_kho_lb);
            main_top.add(trang_thai); main_top.add(trang_thai_cb);
            main_top.setLayout(new GridLayout(3, 3));
            main_top.setPreferredSize(new Dimension(700,70));

            main_middle = new JPanel();
            String columns_ds_ctpnk[] = {"STT", "Mã sản phẩm","Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
            String data_ds_ctpnk[][] = {};
            model_ds_ctpnk = new DefaultTableModel(data_ds_ctpnk, columns_ds_ctpnk){
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
            table_ds_ctpnk = new JTable(model_ds_ctpnk);
            table_ds_ctpnk.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_ds_ctpnk.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_ds_ctpnk.getColumnModel().getColumn(2).setPreferredWidth(250);
            table_ds_ctpnk.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_ds_ctpnk.getColumnModel().getColumn(4).setPreferredWidth(40);
            table_ds_ctpnk.getColumnModel().getColumn(5).setPreferredWidth(80);
            //? Set vị trí cho nội dung (căn giữa cho nội dung)
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table_ds_ctpnk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getTableHeader().setReorderingAllowed(false);
            table_ds_ctpnk.setRowHeight(40);
            table_ds_ctpnk.setEnabled(false);
            ds_ctpnk = new JScrollPane(table_ds_ctpnk);
            ds_ctpnk.setPreferredSize(new Dimension(730,300));
            ds_ctpnk.setBackground(Color.WHITE);
            main_middle.add(ds_ctpnk);

            main_bottom = new JPanel();
            // JLabel tong_tien, tong_tien_lb;
            tong_tien = new JLabel("Tổng tiền : ");
            tong_tien_lb = new JLabel();
            // JButton closeCTPnk;
            capnhat_btn = new ButtonCustom("Cập nhật","","#3eceff");
            capnhat_btn.addActionListener(this);
            main_bottom.add(tong_tien); main_bottom.add(tong_tien_lb);
            main_bottom.add(capnhat_btn);
            main.add(main_top);
            main.add(nha_cung_cap);
            main.add(nha_cung_cap_lb);
            main.add(main_middle);
            main.add(main_bottom);
            main.setLayout(new FlowLayout());
            main.setPreferredSize(new Dimension(750,480));
            setThongTinMain(maPhieuNhap);
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
      public void setTrangThai(int maPhieuNhap) {
            PhieuNhapKhoDTO phieu = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(maPhieuNhap);
            String[] trangThai = {"Chờ Duyệt", "Đã Duyệt", "Hủy"};
            trang_thai_cb = new JComboBox<>(trangThai); 
            if(phieu.getTrangThai().equals("ChoDuyet")) {
                  trang_thai_cb.setSelectedIndex(0);
            }
      }
      public void setThongTinMain(int maPhieuNhap) {
            PhieuNhapKhoDTO phieuNhap = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(maPhieuNhap);
            //? Set thông tin hiển thị
            ma_phieu_lb.setText(String.valueOf(maPhieuNhap));
            ma_kho_hang_lb.setText(String.valueOf(phieuNhap.getMaKhoHang()));
            ten_kho_hang_lb.setText(phieuNhapKhoBUS.getTenKhoHang(maPhieuNhap));
            nguoi_nhap_lb.setText(phieuNhapKhoBUS.getHoTenByMaPhieuNhap(maPhieuNhap));
            nha_cung_cap_lb.setText(phieuNhapKhoBUS.getTenNhaCungCap(maPhieuNhap));
            ngay_nhap_kho_lb.setText(String.valueOf(phieuNhap.getNgayNhapKho()));
            tong_tien_lb.setText(toCurrency(phieuNhap.getTongGiaTri()));
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));
            //? Tạo bảng danh sách
            ArrayList<ChiTietPhieuNhapKhoDTO> chiTietPhieuNhapKhoList = chiTietPhieuNhapKhoBUS.getChiTietPhieuNhapKhoByMaPhieu(maPhieuNhap);
            for(int i = 0; i < chiTietPhieuNhapKhoList.size(); i++) {
                  model_ds_ctpnk.addRow(new Object[]{
                      model_ds_ctpnk.getRowCount()+1,
                      chiTietPhieuNhapKhoList.get(i).getMaSanPham(),
                      sanPhamBUS.getById(chiTietPhieuNhapKhoList.get(i).getMaSanPham()).getTenSanPham(),
                      toCurrency(chiTietPhieuNhapKhoList.get(i).getDonGia()),
                      chiTietPhieuNhapKhoList.get(i).getSoLuong(),
                      toCurrency(chiTietPhieuNhapKhoList.get(i).getThanhTien()),
                  });
            }
      }
      public void capNhatTrangThaiDonNhap() {
            if(trang_thai_cb.getSelectedIndex() == 1) {
                  if(JOptionPane.showConfirmDialog(null, "Bạn có muốn duyệt phiếu nhập này ?", "Cập nhật trạng thái phiếu nhập", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        PhieuNhapKhoDTO phieu = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(Integer.valueOf(ma_phieu_lb.getText()));
                        phieu.setTrangThai("DaDuyet");
                        phieuNhapKhoBUS.updatePhieuNhapKho(phieu);

                        //? Update Số lượng còn lại của sản phẩm
                        for(int i = 0; i < model_ds_ctpnk.getRowCount(); i++) {
                              SanPhamDTO sp = sanPhamBUS.getById((Integer)model_ds_ctpnk.getValueAt(i, 1));
                              sp.setSoLuongConLai(sp.getSoLuongConLai() + (Integer)model_ds_ctpnk.getValueAt(i, 4));

                              sanPhamBUS.update(sp);
                        }
                        JOptionPane.showMessageDialog(null, "Duyệt phiếu nhập thành công !","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        this.phieuNhap.showDanhSachPhieuNhapHang((ArrayList<PhieuNhapKhoDTO>) phieuNhapKhoBUS.getAllPhieuNhapKho(Integer.parseInt(ma_kho_hang_lb.getText())));
                        frame.dispose();
                  } 
            }
            if(trang_thai_cb.getSelectedIndex() == 2) {
                  if(JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy phiếu nhập này ?", "Cập nhật trạng thái phiếu nhập", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        PhieuNhapKhoDTO phieu = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(Integer.valueOf(ma_phieu_lb.getText()));
                        phieu.setTrangThai("Huy");
                        phieuNhapKhoBUS.updatePhieuNhapKho(phieu);

                        //! hủy nên không cập nhật số lượng còn lại của sản phẩm
                        //? Update Số lượng còn lại của sản phẩm
                        // for(int i = 0; i < model_ds_ctpnk.getRowCount(); i++) {
                        //       SanPhamDTO sp = sanPhamBUS.getById((Integer)model_ds_ctpnk.getValueAt(i, 1));
                        //       sp.setSoLuongConLai(sp.getSoLuongConLai() + (Integer)model_ds_ctpnk.getValueAt(i, 4));

                        //       sanPhamBUS.update(sp);
                        // }
                        JOptionPane.showMessageDialog(null, "Hủy phiếu nhập thành công !","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        this.phieuNhap.showDanhSachPhieuNhapHang((ArrayList<PhieuNhapKhoDTO>) phieuNhapKhoBUS.getAllPhieuNhapKho(Integer.parseInt(ma_kho_hang_lb.getText())));
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
                  capNhatTrangThaiDonNhap();
            }
      }
}
