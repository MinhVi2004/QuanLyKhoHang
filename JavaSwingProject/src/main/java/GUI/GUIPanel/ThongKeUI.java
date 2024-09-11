package GUI.GUIPanel;

import BUS.ThongKe.ThongKeBUS;
import DTO.ThongKe.ThongKeSanPham;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import GUI.GUIThanhPhan.InputFormCustomForStatistic;
import Others.UtilServices;
import lombok.Data;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

@Data
public class ThongKeUI extends JPanel implements ActionListener {


    private DefaultTableModel model;
    private JTable thongTin;
    InputFormCustomForStatistic filterDate;
    private int maKhoHang;
    private ThongKeBUS thongKeBUS;
    private List<ThongKeSanPham> thongKeSanPhams;
    private ButtonCustom thongKeDoanhThu, thongKeChiTieu;
    private JLabel tieuDe, tongTien;
    private boolean flag = false;

    public ThongKeUI(int maKho) {
        this.setSize(1200, 900);
        this.setLayout(new BorderLayout(0, 0));
        this.maKhoHang = maKho;
        this.thongKeBUS = new ThongKeBUS(maKho);
        this.thongKeSanPhams = thongKeBUS.thongKeChiTietNhapKho(null, null);



        // Tiêu đề trên JPanel
        JPanel panelTren = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 30));
        tieuDe = new JLabel("Thống kê nhập kho");
        panelTren.setBackground(new Color(255, 255, 255));
        panelTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        tieuDe.setFont(new Font("Arial", Font.BOLD, 40));
        panelTren.setPreferredSize(new Dimension(1200, 100));
        panelTren.add(tieuDe);


        // 70% trái và 30% phải
        JPanel noiDung = new JPanel(new BorderLayout(0, 0));
        noiDung.setPreferredSize(new Dimension(1200, 800));
        JPanel panelTrai = new JPanel(new BorderLayout(0, 0));
        panelTrai.setPreferredSize(new Dimension(900, 800));
        JPanel panelPhai = new JPanel(new BorderLayout(0, 0));
        panelPhai.setPreferredSize(new Dimension(300, 800));
        panelTrai.setBackground(Color.red);
        panelPhai.setBackground(Color.blue);

//         Bên phải
        JPanel timKiemLoaiSP = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        timKiemLoaiSP.setPreferredSize(new Dimension(300, 200));

        Long tongTien = getThongKeBUS().thongKeTongChiTieu(null, null);
        filterDate = new InputFormCustomForStatistic("Lọc theo ngày", tongTien);
        filterDate.setPreferredSize(new Dimension(250, 150));
        timKiemLoaiSP.add(filterDate);
        panelPhai.add(timKiemLoaiSP, BorderLayout.NORTH);

        // Hiệu ứng cho textfield1
        filterDate.getTxtForm1().setText("Min date");
        filterDate.getTxtForm1().setForeground(Color.gray);
        filterDate.setBorder(new EmptyBorder(10, 15, 10, 10));
        filterDate.getTxtForm1().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),
                    BorderFactory.createEmptyBorder(10, 20, 10, 10));
                filterDate.getTxtForm1().setBorder(border);
                if (filterDate.getTxtForm1().getText().equals(("Min date"))) {
                    filterDate.getTxtForm1().setText("");
                    filterDate.getTxtForm1().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                filterDate.getTxtForm1().setBorder(new EmptyBorder(10, 20, 10, 10));
                if (filterDate.getTxtForm1().getText().isEmpty()) {
                    filterDate.getTxtForm1().setForeground(Color.GRAY);
                    filterDate.getTxtForm1().setText("Min date");
                }
            }
        });

        // Hiệu ứng cho textfield2
        filterDate.getTxtForm2().setText("Max date");
        filterDate.getTxtForm2().setForeground(Color.gray);
        filterDate.setBorder(new EmptyBorder(10, 15, 10, 10));
        filterDate.getTxtForm2().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),
                    BorderFactory.createEmptyBorder(10, 20, 10, 10));
                filterDate.getTxtForm2().setBorder(border);
                if (filterDate.getTxtForm2().getText().equals(("Max date"))) {
                    filterDate.getTxtForm2().setText("");
                    filterDate.getTxtForm2().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                filterDate.getTxtForm2().setBorder(new EmptyBorder(10, 20, 10, 10));
                if (filterDate.getTxtForm2().getText().isEmpty()) {
                    filterDate.getTxtForm2().setForeground(Color.GRAY);
                    filterDate.getTxtForm2().setText("Max date");
                }
            }
        });



        JPanel cacNutNhan = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));
        cacNutNhan.setBackground(Color.white);
        Font font = new Font("Arial", Font.PLAIN, 24);


        thongKeDoanhThu = new ButtonCustom("Thông kê nhập kho", "", "#0091fd");
        thongKeDoanhThu.setActionCommand("-");
        thongKeDoanhThu.setPreferredSize(new Dimension(280, 200));
        thongKeDoanhThu.setForeground(Color.white);
        thongKeDoanhThu.setFont(font);

        thongKeChiTieu = new ButtonCustom("Thống kê xuất kho", "", "#0091fd");
        thongKeChiTieu.setPreferredSize(new Dimension(280, 200));
        thongKeChiTieu.setActionCommand("+");
        thongKeChiTieu.setForeground(Color.white);
        thongKeChiTieu.setFont(font);

        chinhSuaGiaoDienNut();
        cacNutNhan.add(thongKeDoanhThu);
        cacNutNhan.add(thongKeChiTieu);
        panelPhai.add(cacNutNhan, BorderLayout.CENTER);

        // Bên trái
        String[] colum = new String[] { "STT", "Tên sản phẩm", "Số lượng", "Tổng giá trị" };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colum);
        thongTin = new JTable(model);
        loadDuLieuThongKe(thongKeSanPhams);
        chinhSuaGiaoDienTable();
        JScrollPane dulieuLoaiSP = new JScrollPane(thongTin);
        panelTrai.add(dulieuLoaiSP, BorderLayout.CENTER);

        // Add sự kiện button
        thongKeDoanhThu.addActionListener(this);
        thongKeChiTieu.addActionListener(this);

        filterDate.getClick().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String minDate = !filterDate.getTxtForm1().getText().equals("Min date") ? filterDate.getTxtForm1().getText() : null;
                    String maxDate = !filterDate.getTxtForm2().getText().equals("Max date") ? filterDate.getTxtForm2().getText() : null;

                    if (minDate == null && maxDate == null){
                        if (!flag) {
                            filterDate.setTongTien(thongKeBUS.thongKeTongChiTieu(minDate, maxDate));
                            thongKeSanPhams = thongKeBUS.thongKeChiTietNhapKho(minDate, maxDate);

                        } else {
                            filterDate.setTongTien(thongKeBUS.thongKeTongDoanhThu(minDate, maxDate));
                            thongKeSanPhams = thongKeBUS.thongKeChiTietXuatKho(minDate, maxDate);
                        }
                        loadDuLieuThongKe(thongKeSanPhams);
                        chinhSuaGiaoDienTable();
                    }
                    else if (minDate != null && maxDate != null){
                        if (!UtilServices.isValidDate(minDate)){
                            JOptionPane.showMessageDialog(null, "Ngày " + minDate + " không hợp lệ ", "Thông báo", JOptionPane.ERROR_MESSAGE);

                        }
                        else if (!UtilServices.isValidDate(maxDate)) {
                            JOptionPane.showMessageDialog(null, "Ngày " + maxDate + "không hợp lệ ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (!UtilServices.isValidDateRange(minDate, maxDate)){
                            JOptionPane.showMessageDialog(null, "Ngày đầu vào không hợp lệ (2 tời điểm phải cách nhau tối thiểu 1 ngày để có thể thống kê)", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            minDate = UtilServices.convertFromDateNew(minDate);
                            maxDate = UtilServices.convertFromDateNew(maxDate);
                            if (!flag) {
                                filterDate.setTongTien(thongKeBUS.thongKeTongChiTieu(minDate, maxDate));
                                thongKeSanPhams = thongKeBUS.thongKeChiTietNhapKho(minDate, maxDate);

                            } else {
                                filterDate.setTongTien(thongKeBUS.thongKeTongDoanhThu(minDate, maxDate));
                                thongKeSanPhams = thongKeBUS.thongKeChiTietXuatKho(minDate, maxDate);
                            }
                            loadDuLieuThongKe(thongKeSanPhams);
                            chinhSuaGiaoDienTable();
                        }
                    }
                    else if (minDate == null){
                        if (!UtilServices.isValidDate(maxDate)) {
                            JOptionPane.showMessageDialog(null, "Ngày " + maxDate + " không hợp lệ !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }else {
                            maxDate = UtilServices.convertFromDateNew(maxDate);
                            if (!flag) {
                                filterDate.setTongTien(thongKeBUS.thongKeTongChiTieu(minDate, maxDate));

                                thongKeSanPhams = thongKeBUS.thongKeChiTietNhapKho(minDate, maxDate);

                            } else {
                                filterDate.setTongTien(thongKeBUS.thongKeTongDoanhThu(minDate, maxDate));

                                thongKeSanPhams = thongKeBUS.thongKeChiTietXuatKho(minDate, maxDate);
                            }
                            loadDuLieuThongKe(thongKeSanPhams);
                            chinhSuaGiaoDienTable();
                        }
                    }
                    else {
                        if (!UtilServices.isValidDate(minDate)) {
                            JOptionPane.showMessageDialog(null, "Ngày " + minDate + " không hợp lệ !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }else {

                            minDate = UtilServices.convertFromDateNew(minDate);
                            if (!flag) {
                                filterDate.setTongTien(thongKeBUS.thongKeTongChiTieu(minDate, maxDate));

                                thongKeSanPhams = thongKeBUS.thongKeChiTietNhapKho(minDate, maxDate);

                            } else {
                                filterDate.setTongTien(thongKeBUS.thongKeTongDoanhThu(minDate, maxDate));

                                thongKeSanPhams = thongKeBUS.thongKeChiTietXuatKho(minDate, maxDate);
                            }
                            loadDuLieuThongKe(thongKeSanPhams);
                            chinhSuaGiaoDienTable();
                        }
                    }



            }
        });

        noiDung.add(panelTrai, BorderLayout.CENTER);
        noiDung.add(panelPhai, BorderLayout.EAST);
        this.add(panelTren, BorderLayout.NORTH);

        this.add(noiDung);
    }

    public void loadDuLieuThongKe(List<ThongKeSanPham> thongKeSanPhams) {
        model.setRowCount(0);
        int dem = 1;
        for (ThongKeSanPham sanPham : thongKeSanPhams) {
            Object dong[] = { dem, sanPham.getTenSanPham(), sanPham.getSoLuong(), UtilServices.formatVND(sanPham.getTongTien()) };
            model.addRow(dong);
            dem++;
        }

        if (!flag){
            this.thongKeSanPhams = thongKeBUS.thongKeChiTietNhapKho(null, null);
        }else{
            this.thongKeSanPhams = thongKeBUS.thongKeChiTietXuatKho(null, null);
        }

    }

    public void chinhSuaGiaoDienNut() {
        this.thongKeDoanhThu.setHorizontalTextPosition(SwingConstants.CENTER);
        this.thongKeDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);

        this.thongKeChiTieu.setHorizontalTextPosition(SwingConstants.CENTER);
        this.thongKeChiTieu.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void chinhSuaGiaoDienTable() {
        thongTin.setShowGrid(false);
        thongTin.setShowHorizontalLines(true);
        thongTin.setDefaultEditor(Object.class, null);
        thongTin.setRowHeight(50);
        thongTin.getTableHeader().setReorderingAllowed(false);
        thongTin.getTableHeader().setPreferredSize(new Dimension(0, 50));
        thongTin.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        thongTin.getTableHeader().setBackground(Color.WHITE);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < thongTin.getColumnCount(); i++) {
            thongTin.getColumnModel().getColumn(i).setPreferredWidth(180);
            thongTin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        thongTin.getColumnModel().getColumn(0).setPreferredWidth(20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnLenh = e.getActionCommand();

        Long tongNe = 0L;
            switch (btnLenh) {
                case "-":
                    flag = false;
                    tieuDe.setText("Thống kê nhập kho");
                    loadDuLieuThongKe(thongKeBUS.thongKeChiTietNhapKho(null, null));
                    filterDate.getTxtForm1().setText("Min date");
                    filterDate.getTxtForm2().setText("Max date");

                    tongNe = thongKeBUS.thongKeTongChiTieu(null, null);

                    filterDate.setTongTien(tongNe);
                    System.err.println("Tổng nhập: " + tongNe);

                    break;
                case "+":
                    flag = true;
                    tieuDe.setText("Thống kê xuất kho");
                    loadDuLieuThongKe(thongKeBUS.thongKeChiTietXuatKho(null, null));
                    filterDate.getTxtForm1().setText("Min date");
                    filterDate.getTxtForm2().setText("Max date");
                    tongNe = thongKeBUS.thongKeTongDoanhThu(null, null);
                    System.err.println("Tổng xuất: " + tongNe);
                    filterDate.setTongTien(tongNe);
                    break;

            }
            chinhSuaGiaoDienTable();

    }
}
