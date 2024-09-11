package GUI.GUIPanel;

import BUS.TaiKhoanBUS;
import DTO.NguoiDung.TaiKhoanDTO;
import Others.UtilServices;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerUI extends JPanel{
	
    private JLabel Hello, nameLabel, birthdayLabel, genderLabel, phoneLabel, emailLabel, addressLabel, roleLabel, oldPasswordLabel, newPasswordLabel;
    private JTextField nameField, birthdayField, phoneField, emailField, addressField;
    private JPasswordField oldPasswordField, newPasswordField;
    private JCheckBox changePasswordCheckbox, femaleCheckbox, maleCheckbox;
    private JButton submitButton;
    private MenuTaskBar menu;
    
		public ManagerUI(TaiKhoanDTO taiKhoanDTO,MenuTaskBar menu) {
                        this.menu=menu;
			Border compoundBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("Thông tin cá nhân"),
			BorderFactory.createEmptyBorder(10, 10, 10, 10));
			setBorder(compoundBorder);
			setLayout(null);
			
                        Hello = new JLabel("Xin Chào " + 
                        (taiKhoanDTO.getHoVaTen() != null && !taiKhoanDTO.getHoVaTen().isEmpty() 
                        ? taiKhoanDTO.getHoVaTen() : taiKhoanDTO.getTenDangNhap()) + " !");

                        Hello.setBounds(400, 10, 1000, 100);
                        Hello.setFont(new Font("Times New Roman", Font.PLAIN, 50));

                        nameLabel = new JLabel("Họ và tên : " + 
                        (taiKhoanDTO.getHoVaTen() != null && !taiKhoanDTO.getHoVaTen().isEmpty() 
                        ? taiKhoanDTO.getHoVaTen() : ""));
			nameLabel.setBounds(150, 85, 200, 50);
			nameField = new JTextField(20);
			nameField.setBounds(150, 120, 1050, 30);
            if(!taiKhoanDTO.getNgaySinh().isEmpty()){
                String abc= UtilServices.convertToDate(taiKhoanDTO.getNgaySinh());
                birthdayLabel = new JLabel("Ngày Sinh : " +
                (taiKhoanDTO.getNgaySinh() != null && !taiKhoanDTO.getNgaySinh().isEmpty()
                ?abc : ""));
            }
			birthdayLabel.setBounds(150, 145, 200, 50);
			birthdayField = new JTextField(20);
			birthdayField.setBounds(150, 180, 1050, 30);
			birthdayLabel.setBounds(150, 145, 200, 50);
			birthdayField = new JTextField(20);
			birthdayField.setBounds(150, 180, 1050, 30);


			roleLabel = new JLabel("Vai trò: " + taiKhoanDTO.getQuyen());
			roleLabel.setBounds(150, 200, 200, 50);
                        
                        
                        genderLabel = new JLabel("Giới tính: " +
                        (taiKhoanDTO.getGioiTinh() != null && !taiKhoanDTO.getGioiTinh().isEmpty()
                        ? taiKhoanDTO.getGioiTinh() : ""));
			genderLabel.setBounds(150, 230, 200, 50);
                        maleCheckbox = new JCheckBox("Nam");
                        maleCheckbox.setBounds(350, 250, 200, 50);
                        femaleCheckbox = new JCheckBox("Nữ");
                        femaleCheckbox.setBounds(550, 250, 200, 50);
                        
                        ButtonGroup genderButtonGroup = new ButtonGroup();
                        genderButtonGroup.add(maleCheckbox);
                        genderButtonGroup.add(femaleCheckbox);
                        phoneLabel = new JLabel("Số điện thoại : " +(taiKhoanDTO.getSoDienThoai() != null && !taiKhoanDTO.getSoDienThoai().isEmpty()? taiKhoanDTO.getSoDienThoai().trim() : ""));
                        
			phoneLabel.setBounds(150, 280, 300, 50);
			phoneField = new JTextField(20);
			phoneField.setBounds(150, 315, 1050, 30);
                        
                        
                        
                        emailLabel = new JLabel("Email : " + 
                        (taiKhoanDTO.getEmail() != null && !taiKhoanDTO.getEmail().isEmpty()
                        ? taiKhoanDTO.getEmail() : ""));
			emailLabel.setBounds(150, 340, 300, 50);
			emailField = new JTextField(20);
			emailField.setBounds(150, 375, 1050, 30);
                        
                        
                        
                        
                        addressLabel = new JLabel("Địa chỉ : " + 
                        (taiKhoanDTO.getDiaChi()!= null && !taiKhoanDTO.getDiaChi().isEmpty()
                        ? taiKhoanDTO.getDiaChi() : ""));
			addressLabel.setBounds(150, 400, 300, 50);
			addressField = new JTextField(20);
			addressField.setBounds(150, 435, 1050, 30);
                        

			changePasswordCheckbox = new JCheckBox("Đổi mật khẩu");
			changePasswordCheckbox.setBounds(150, 490, 150, 50);
			changePasswordCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changePasswordCheckbox.isSelected()) {
					oldPasswordLabel.setVisible(true);
					oldPasswordField.setVisible(true);
					newPasswordLabel.setVisible(true);
					newPasswordField.setVisible(true);
				} else {
					oldPasswordLabel.setVisible(false);
					oldPasswordField.setVisible(false);
                                        oldPasswordField.setText("");
					newPasswordLabel.setVisible(false);
					newPasswordField.setVisible(false);
                                        newPasswordField.setText("");
				}
			}
			});

			oldPasswordLabel = new JLabel("Mật khẩu cũ:");
			oldPasswordLabel.setBounds(150, 540, 100, 50);

			oldPasswordField = new JPasswordField(20);
			oldPasswordField.setBounds(150, 575, 1050, 30);

			newPasswordLabel = new JLabel("Mật khẩu mới:");
			newPasswordLabel.setBounds(150, 610, 100, 50);

			newPasswordField = new JPasswordField(20);
			newPasswordField.setBounds(150, 645, 1050, 30);

			oldPasswordLabel.setVisible(false);
			oldPasswordField.setVisible(false);
			newPasswordLabel.setVisible(false);
			newPasswordField.setVisible(false);
			
			submitButton = new JButton("Cập nhật");
			submitButton.setBounds(570, 690, 200, 30);
                        
                        loadData(taiKhoanDTO);
                        
                        submitButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String newName = nameField.getText().trim();
        String newBirthday = birthdayField.getText().trim();
        String newPhone = phoneField.getText().trim();
        String newEmail = emailField.getText().trim();
        String newAddress = addressField.getText().trim();
        String newGender = "";
        if (maleCheckbox.isSelected()) {
            newGender = "Nam";
        } else if (femaleCheckbox.isSelected()) {
            newGender = "Nữ";
        }

        // Kiểm tra điều kiện của từng trường
        if (!newName.isEmpty() && newName.length() < 7) {
            JOptionPane.showMessageDialog(null, "Họ và tên phải có ít nhất 7 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
     
        if (!newBirthday.isEmpty()) {

            // Kiểm tra định dạng ngày sinh
            if (!UtilServices.isValidDate2(newBirthday)) {
                JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            newBirthday= UtilServices.convertFromDate(newBirthday);

        }

        
        if (!newPhone.isEmpty() && !newPhone.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!newEmail.isEmpty() && !newEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!newAddress.isEmpty() && newAddress.length() < 10) {
            JOptionPane.showMessageDialog(null, "Địa chỉ phải có ít nhất 10 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }



        // Kiểm tra mật khẩu
        if (changePasswordCheckbox.isSelected()) {
            String oldPassword = new String(oldPasswordField.getPassword()).trim();
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String currentPassword = taiKhoanDTO.getMatKhau();
            if (!oldPassword.equals(currentPassword)) {
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (newPassword.length() < 8) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới phải có ít nhất 8 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                taiKhoanDTO.setMatKhau(newPassword);
            }
        }

        // Cập nhật thông tin
        taiKhoanDTO.setHoVaTen(newName.isEmpty() ? null : newName);
        taiKhoanDTO.setNgaySinh(newBirthday.isEmpty() ? null : newBirthday);
        taiKhoanDTO.setSoDienThoai(newPhone.isEmpty() ? null : newPhone);
        taiKhoanDTO.setEmail(newEmail.isEmpty() ? null : newEmail);
        taiKhoanDTO.setDiaChi(newAddress.isEmpty() ? null : newAddress);
        taiKhoanDTO.setGioiTinh(newGender.isEmpty() ? null : newGender);
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        int result = taiKhoanBUS.updateAccount(taiKhoanDTO);
        
        // Kiểm tra kết quả của phương thức update
        if (result > 0) {
            
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadData(taiKhoanDTO);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
});

                        add(Hello);
			add(nameLabel);
			add(nameField);
			add(birthdayLabel);
			add(birthdayField);
			add(roleLabel);
                        add(genderLabel);
                        add(femaleCheckbox);
                        add(maleCheckbox);
                        add(phoneLabel);
                        add(phoneField);
                        add(emailLabel);
                        add(emailField);
                        add(addressLabel);
                        add(addressField);
			add(changePasswordCheckbox);
			add(oldPasswordLabel);
			add(oldPasswordField);
			add(newPasswordLabel);
			add(newPasswordField);
			add(submitButton);
			
			setPreferredSize(new Dimension(1000,700));
			setVisible(true);
		}
		
                
public void loadData(TaiKhoanDTO taiKhoanDTO) {
      
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        TaiKhoanDTO taiKhoanFromDB = taiKhoanBUS.getUserByUserName(taiKhoanDTO.getTenDangNhap());
        if (taiKhoanFromDB != null) {
            String ten=taiKhoanDTO.getHoVaTen();
            this.menu.getHoTen().setText(ten!=null&&!ten.isEmpty()?ten:taiKhoanDTO.getTenDangNhap());
           Hello.setText("Xin Chào " +(taiKhoanDTO.getHoVaTen() != null && !taiKhoanDTO.getHoVaTen().isEmpty() 
                        ? taiKhoanDTO.getHoVaTen() : taiKhoanDTO.getTenDangNhap()) + " !");
            nameLabel.setText("Họ và tên : " + (taiKhoanFromDB.getHoVaTen() != null && !taiKhoanFromDB.getHoVaTen().isEmpty() ? taiKhoanFromDB.getHoVaTen() : ""));
            System.out.println("Taikhoanfromdb" + taiKhoanFromDB.getNgaySinh());

            String ngayMoi= UtilServices.convertToDate(taiKhoanFromDB.getNgaySinh());
            birthdayLabel.setText("Ngày Sinh : " + (taiKhoanFromDB.getNgaySinh() != null && !taiKhoanFromDB.getNgaySinh().isEmpty() ?  ngayMoi : ""));
            roleLabel.setText("Vai trò: " + taiKhoanFromDB.getQuyen());
            
            String genderText = "Giới tính: " + (taiKhoanFromDB.getGioiTinh() != null && !taiKhoanFromDB.getGioiTinh().isEmpty() ? taiKhoanFromDB.getGioiTinh() : "");
            genderLabel.setText(genderText);
            maleCheckbox.setSelected("Nam".equals(taiKhoanFromDB.getGioiTinh()));
            femaleCheckbox.setSelected("Nữ".equals(taiKhoanFromDB.getGioiTinh()));
            phoneLabel.setText("Số điện thoại : " +
                        (taiKhoanFromDB.getSoDienThoai() != null && !taiKhoanFromDB.getSoDienThoai().isEmpty()
                        ? taiKhoanFromDB.getSoDienThoai() : ""));
            emailLabel.setText("Email : " + 
                        (taiKhoanDTO.getEmail() != null && !taiKhoanDTO.getEmail().isEmpty()
                        ? taiKhoanDTO.getEmail() : ""));
            addressLabel.setText("Địa chỉ : " + 
                        (taiKhoanDTO.getDiaChi()!= null && !taiKhoanDTO.getDiaChi().isEmpty()
                        ? taiKhoanDTO.getDiaChi() : ""));
            nameField.setText(taiKhoanFromDB.getHoVaTen());
            birthdayField.setText(ngayMoi);
            phoneField.setText(taiKhoanFromDB.getSoDienThoai());
            emailField.setText(taiKhoanFromDB.getEmail());
            addressField.setText(taiKhoanFromDB.getDiaChi());
            
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin người dùng trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


}