package GUI.GUIPanel;

import BUS.NghiepVuNhapKho.NhaCungCapBUS;
import DTO.NghiepVuNhapKho.NhaCungCapDTO;
import GUI.GUIDialog.NhaCungCapDialog;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import lombok.Data;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;

@Data
public class NhaCungCapUI extends JPanel implements ActionListener {

    private DefaultTableModel model;
    private JTable thongTin;

    private int maKhoHang;
    private NhaCungCapBUS nhaCungCapBUS;
    private List<NhaCungCapDTO> nhaCungCapDTOArrayList;
    private ButtonCustom themNhaCungCap, suaNhaCungCap, xoaNhaCungCap;


    public NhaCungCapUI(int maKho) {
        this.setSize(1200, 900);
        this.setLayout(new BorderLayout(0, 0));
        this.maKhoHang = maKho;
        this.nhaCungCapBUS = new NhaCungCapBUS();
        this.nhaCungCapDTOArrayList = nhaCungCapBUS.getAllNhaCungCap(maKhoHang);

        // Tiêu đề trên JPanel
        JPanel panelTren = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 30));
        JLabel tieuDe = new JLabel("Nhà cung cấp");
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

        // Bên phải
        JPanel timKiemLoaiSP = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        timKiemLoaiSP.setPreferredSize(new Dimension(300, 200));
        InputFormCustom timKiem = new InputFormCustom("Nhà cung cấp: ");
        timKiem.setPreferredSize(new Dimension(250, 120));
        timKiemLoaiSP.add(timKiem);
        panelPhai.add(timKiemLoaiSP, BorderLayout.NORTH);

        // Hiệu ứng cho textfield
        timKiem.getTxtForm().setText("Nhập tên nhà cung cấp tìm kiếm...");
        timKiem.getTxtForm().setForeground(Color.gray);
        timKiem.setBorder(new EmptyBorder(10, 20, 10, 10));
        timKiem.getTxtForm().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),
                    BorderFactory.createEmptyBorder(10, 20, 10, 10));
                timKiem.getTxtForm().setBorder(border);
                if (timKiem.getTxtForm().getText().equals(("Nhập tên nhà cung cấp tìm kiếm..."))) {
                    timKiem.getTxtForm().setText("");
                    timKiem.getTxtForm().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                timKiem.getTxtForm().setBorder(new EmptyBorder(10, 20, 10, 10));
                if (timKiem.getTxtForm().getText().isEmpty()) {
                    timKiem.getTxtForm().setForeground(Color.GRAY);
                    timKiem.getTxtForm().setText("Nhập tên nhà cung cấp tìm kiếm...");
                }
            }
        });

        JPanel cacNutNhan = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));
        cacNutNhan.setBackground(Color.white);
        themNhaCungCap = new ButtonCustom("Thêm nhà cung cấp mới", "", "#0091fd");
        themNhaCungCap.setPreferredSize(new Dimension(280, 100));
        themNhaCungCap.setForeground(Color.white);
        suaNhaCungCap = new ButtonCustom("Sửa  nhà cung cấp hiện tại", "", "#0091fd");
        suaNhaCungCap.setPreferredSize(new Dimension(280, 100));
        suaNhaCungCap.setForeground(Color.white);
        xoaNhaCungCap = new ButtonCustom("Xóa nhà cung cấp", "", "#0091fd");
        xoaNhaCungCap.setPreferredSize(new Dimension(280, 100));
        xoaNhaCungCap.setForeground(Color.white);
        chinhSuaGiaoDienNut();
        cacNutNhan.add(themNhaCungCap);
        cacNutNhan.add(suaNhaCungCap);
        cacNutNhan.add(xoaNhaCungCap);
        panelPhai.add(cacNutNhan, BorderLayout.CENTER);

        // Bên trái
        String[] colum = new String[] { "STT", "Tên nhà cung cấp", "Email", "Số điện thoại" };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colum);
        thongTin = new JTable(model);
        loadDuLieuNhaCungCap(nhaCungCapDTOArrayList);
        chinhSuaGiaoDienTable();
        JScrollPane dulieuLoaiSP = new JScrollPane(thongTin);
        panelTrai.add(dulieuLoaiSP, BorderLayout.CENTER);

        // Add sự kiện button
        themNhaCungCap.addActionListener(this);
        suaNhaCungCap.addActionListener(this);
        xoaNhaCungCap.addActionListener(this);
        timKiem.txtForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                nhaCungCapDTOArrayList = nhaCungCapBUS.searchNhaCungCapByTenNCC(maKhoHang ,timKiem.txtForm.getText());
                loadDuLieuNhaCungCap(nhaCungCapDTOArrayList);
                chinhSuaGiaoDienTable();
            }
        });

        noiDung.add(panelTrai, BorderLayout.CENTER);
        noiDung.add(panelPhai, BorderLayout.EAST);
        this.add(panelTren, BorderLayout.NORTH);
        this.add(noiDung);
    }

    public void loadDuLieuNhaCungCap(List<NhaCungCapDTO> listNhaCungCapDTOs) {
        model.setRowCount(0);
        int dem = 1;
        for (NhaCungCapDTO nhaCungCapDTO : listNhaCungCapDTOs) {
            Object dong[] = { dem, nhaCungCapDTO.getTenNCC(), nhaCungCapDTO.getEmail(), nhaCungCapDTO.getSoDienThoai() };
            model.addRow(dong);
            dem++;
        }
        this.nhaCungCapDTOArrayList = nhaCungCapBUS.getAllNhaCungCap(maKhoHang);

    }

    public void chinhSuaGiaoDienNut() {
        this.themNhaCungCap.setHorizontalTextPosition(SwingConstants.CENTER);
        this.themNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);

        this.suaNhaCungCap.setHorizontalTextPosition(SwingConstants.CENTER);
        this.suaNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);

        this.xoaNhaCungCap.setHorizontalTextPosition(SwingConstants.CENTER);
        this.xoaNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);
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
        NhaCungCapDialog nhaCungCapDialog = null;

        switch (btnLenh) {
            case "Thêm nhà cung cấp mới":
                NhaCungCapDialog nhaCungCapDialog1 = new NhaCungCapDialog(this, "Thêm Mới", "Add");


                break;
            case "Sửa  nhà cung cấp hiện tại":
                if (this.thongTin.getSelectedRow() != -1) {
                    if (nhaCungCapDTOArrayList.get(thongTin.getSelectedRow()).getMaNCC() == 1) {
                        JOptionPane.showMessageDialog(this, "Nhà cung cấp mặc định không thể chỉnh sửa !!", "Thông báo",
                            JOptionPane.ERROR_MESSAGE);
                    } else {
                        nhaCungCapDialog = new NhaCungCapDialog(this, "Chỉnh Sửa", "Change",
                            this.nhaCungCapDTOArrayList.get(thongTin.getSelectedRow()));
                    }
                } else
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để chỉnh sửa!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                loadDuLieuNhaCungCap(nhaCungCapBUS.getAllNhaCungCap(maKhoHang));
                chinhSuaGiaoDienTable();
                break;
            case "Xóa nhà cung cấp":
                int luaChon = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa Nhà cung cấp ?", "Xóa Nhà cung cấp",
                    JOptionPane.YES_NO_OPTION);
                if (luaChon == 0) {
                    if (nhaCungCapDTOArrayList.get(thongTin.getSelectedRow()).getMaNCC() == 1)
                        JOptionPane.showMessageDialog(this, "Nhà cung cấp mặc định không thể xóa !!", "Thông báo",
                            JOptionPane.ERROR_MESSAGE);
                    else {
                        nhaCungCapBUS.deleteNhaCungCap(nhaCungCapDTOArrayList.get(thongTin.getSelectedRow()));
                        loadDuLieuNhaCungCap(nhaCungCapBUS.getAllNhaCungCap(maKhoHang));
                        chinhSuaGiaoDienTable();
                        JOptionPane.showMessageDialog(this, "Xóa thành công nhà cung cấp !!");

                    }

                }
                break;
        }
        loadDuLieuNhaCungCap(nhaCungCapBUS.getAllNhaCungCap(maKhoHang));
        chinhSuaGiaoDienTable();

    }

}
