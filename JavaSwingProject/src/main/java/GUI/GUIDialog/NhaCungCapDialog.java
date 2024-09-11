package GUI.GUIDialog;


import BUS.NghiepVuNhapKho.NhaCungCapBUS;
import DTO.NghiepVuNhapKho.NhaCungCapDTO;
import ErrorResponse.TheValueAlreadyExists;
import GUI.GUIPanel.NhaCungCapUI;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.NhaCungCapForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhaCungCapDialog extends JDialog implements ActionListener {
    public String tieuDe,type; // type để set cho nút thêm hoặc xóa
    public NhaCungCapForm nhaCungCapForm;
    public ButtonCustom btn_them, btn_chinhsua, btn_huy;
    int maKho=1;

    Integer maNCC = null;

    private NhaCungCapBUS nhaCungCapBUS;
    private NhaCungCapDTO nhaCungCapDTO;
    private NhaCungCapUI nhaCungCapGUI;


    public NhaCungCapDialog() {
        this.init();
    }

    public NhaCungCapDialog(NhaCungCapUI nhaCungCapGUI, String tieuDe, String type) {
        this.maKho= nhaCungCapGUI.getMaKhoHang();
        this.nhaCungCapBUS = new NhaCungCapBUS();
        this.tieuDe = tieuDe;
        this.type = type;
        this.nhaCungCapGUI = nhaCungCapGUI;
        init();
    }
    public NhaCungCapDialog(NhaCungCapUI nhaCungCapGUI, String tieuDe, String type, NhaCungCapDTO nhaCungCapDTO) {
        this.maKho= nhaCungCapGUI.getMaKhoHang();
        this.tieuDe=tieuDe;
        this.type=type;
        this.nhaCungCapDTO =nhaCungCapDTO;
        this.nhaCungCapGUI = nhaCungCapGUI;
        this.nhaCungCapBUS = new NhaCungCapBUS();
        init();
    }

    public void init(){
        this.setLayout(new BorderLayout(20,20));
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setTitle(this.tieuDe);
        JPanel tieuDePanel= new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        tieuDePanel.setBackground(Color.WHITE);
        JLabel tieuDeLabel= new JLabel("Thông tin nhà cung cấp");
        Font tieuDeFont= new Font("Arial",Font.BOLD,30);
        tieuDeLabel.setFont(tieuDeFont);
        tieuDePanel.add(tieuDeLabel);
        JPanel thongTin= new JPanel(new  FlowLayout(FlowLayout.CENTER,20,20));

        this.nhaCungCapForm =new NhaCungCapForm();
        nhaCungCapForm.setPreferredSize(new Dimension(400,300));
        thongTin.add(nhaCungCapForm);


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
        this.nhaCungCapForm.tenNCCText.setText(this.nhaCungCapDTO.getTenNCC());
        this.nhaCungCapForm.emailText.setText(this.nhaCungCapDTO.getEmail());
        this.nhaCungCapForm.soDienThoaiText.setText(this.nhaCungCapDTO.getSoDienThoai());

    }



    public void createNhaCungCap(){
        if (nhaCungCapForm.tenNCCText.getText().trim().equals("") ||
            nhaCungCapForm.emailText.getText().trim().equals("") ||
            nhaCungCapForm.soDienThoaiText.getText().trim().equals(""))
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        else if (!nhaCungCapForm.soDienThoaiText.getText().trim().matches("\\d{10,11}")){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);        
        }else if (!nhaCungCapForm.emailText.getText().trim().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            JOptionPane.showMessageDialog(null, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);            
        }
        
        else {
            String tenNCC = this.nhaCungCapForm.tenNCCText.getText().trim();
            String email = this.nhaCungCapForm.emailText.getText().trim();
            String soDienThoai = this.nhaCungCapForm.soDienThoaiText.getText().trim();
            NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO(null, tenNCC, email, soDienThoai, maKho);
            try {
                if( nhaCungCapBUS.createNhaCungCap( nhaCungCapDTO ) ){
                    this.nhaCungCapGUI.loadDuLieuNhaCungCap(nhaCungCapBUS.getAllNhaCungCap(maKho));
                    this.nhaCungCapGUI.chinhSuaGiaoDienTable();
                    JOptionPane.showMessageDialog(this, "Thêm Nhà Cung Cấp Mới Thành Công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (TheValueAlreadyExists e) {
                JOptionPane.showMessageDialog(this, "Tên Nhà Cung Cấp này đã tồn tại trong hệ thống","Thông báo",JOptionPane.ERROR_MESSAGE);
            }

        }
    }



    public void updateNhaCungCap(){
        if (nhaCungCapForm.tenNCCText.getText().trim().equals("") ||
            nhaCungCapForm.emailText.getText().trim().equals("") ||
            nhaCungCapForm.soDienThoaiText.getText().trim().equals(""))
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        else if (!nhaCungCapForm.soDienThoaiText.getText().trim().matches("\\d{10,11}")){
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);        
        }else if (!nhaCungCapForm.emailText.getText().trim().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            JOptionPane.showMessageDialog(null, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);            
        }
        else {
            try{
                String tenNCC = this.nhaCungCapForm.tenNCCText.getText().trim();
                String email = this.nhaCungCapForm.emailText.getText().trim();
                String soDienThoai = this.nhaCungCapForm.soDienThoaiText.getText().trim();
                Integer maNCC = this.nhaCungCapDTO.getMaNCC();
                NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO(maNCC, tenNCC, email, soDienThoai, maKho);

                try {
                    if(nhaCungCapBUS.updateNhaCungCap(nhaCungCapDTO)) {
                        JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công ^^", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        this.nhaCungCapGUI.loadDuLieuNhaCungCap(nhaCungCapBUS.getAllNhaCungCap(maKho));
                        this.dispose();
                    }
                } catch (TheValueAlreadyExists e) {
                    JOptionPane.showMessageDialog(this, "Tên loại này đã tồn tại !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số với các ô yêu cầu điền mã loại, mã kho","Thông báo",JOptionPane.ERROR_MESSAGE);
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
                createNhaCungCap();
                break;
            case "Chỉnh Sửa":
                updateNhaCungCap();
                break;
        }
    }
}

