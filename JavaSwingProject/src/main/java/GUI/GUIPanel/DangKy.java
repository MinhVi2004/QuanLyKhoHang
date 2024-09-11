package GUI.GUIPanel;


import BUS.TaiKhoanBUS;
import DTO.NguoiDung.TaiKhoanDTO;
import GUI.GUIThanhPhan.ButtonCustom;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


public class DangKy extends JFrame {

   
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private ButtonCustom buttonRegister;
    private String linkToIMG = "C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaVeryNew\\JavaSwingProject\\src\\main\\java\\Resources";
    public DangKy() {
        this.init();
    }

    
    public void init() {
        this.setTitle("Đăng Ký");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(Color.white);
        
        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Color.white);
        
        JLabel title = new JLabel("Đăng ký ");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(150, 30, 200, 30);
        
        
        JLabel userNameLabel = new JLabel("Tên đăng nhập");
        userNameLabel.setBounds(50, 100, 100, 30);
        userNameField = new JTextField(50);
        userNameField.setBounds(50, 130, 300, 30);
        
        
        JLabel passwordLabel = new JLabel("Mật Khẩu");
        passwordLabel.setBounds(50, 160, 100, 30);
        passwordField = new JPasswordField(50);
        passwordField.setBounds(50, 190, 300, 30);
        
        
        JLabel confirmPasswordLabel = new JLabel("Xác nhận mật khẩu");
        confirmPasswordLabel.setBounds(50, 220, 130, 30);
        confirmPasswordField = new JPasswordField(50);
        confirmPasswordField.setBounds(50, 250, 300, 30);
        
       
        buttonRegister = new ButtonCustom("Đăng ký","","#3eceff");
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 18));
        buttonRegister.setHorizontalAlignment(SwingConstants.CENTER);
        buttonRegister.setBounds(50, 300, 300, 50);
        buttonRegister.setBackground(Color.decode("#3eceff"));
        buttonRegister.setForeground(Color.white);
        buttonRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
//        userNameField.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//                registerAccount();
//            }
//        });
//
//
//        passwordField.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//                registerAccount();
//            }
//        });
//
//
//        confirmPasswordField.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//                registerAccount();
//            }
//        });

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerAccount();
            }
        });
        
        
        JLabel labelQuestion = new JLabel("Bạn đã có tài khoản?");
        labelQuestion.setFont(new Font("Arial", Font.PLAIN, 15));
        labelQuestion.setBounds(50, 400, 300, 30);
        JButton labelLogin = new JButton("Đăng nhập");
        labelLogin.setActionCommand("Đăng nhập");
        labelLogin.setBorderPainted(false);
        labelLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        labelLogin.setBounds(220, 405, 130, 20);
        labelLogin.setForeground(Color.white);
        labelLogin.setBackground(Color.decode("#3eceff"));

        labelLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mevt){
                labelLogin.setBackground(Color.decode("#3eceff"));
                labelLogin.setForeground(Color.white);
                labelLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent mevt){
                labelLogin.setBackground(Color.white);
                labelLogin.setForeground(Color.decode("#3eceff"));
                labelLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
    });       
        
        labelLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DangNhap(); 
                closeWindow();
            }
        });
        
        
        panelLeft.add(title);
        panelLeft.add(userNameLabel);
        panelLeft.add(userNameField);
        panelLeft.add(passwordLabel);
        panelLeft.add(passwordField);
        panelLeft.add(confirmPasswordLabel);
        panelLeft.add(confirmPasswordField);
        panelLeft.add(buttonRegister);
        panelLeft.add(labelQuestion);
        panelLeft.add(labelLogin);
        panelLeft.setLayout(null);
        mainPanel.add(panelLeft);

        
        JPanel panelRight = new JPanel();
        panelRight.setBackground(Color.white);
        ImageIcon icon = new ImageIcon(linkToIMG+"\\imgLogin.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel label = new JLabel(scaledIcon);
        panelRight.add(label);
        
        mainPanel.add(panelRight);

        this.add(mainPanel);
        this.setVisible(true);
    }

    public void registerAccount() {
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống.");
            return;
        }

        if (userName.length() < 6) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập phải có ít nhất 6 ký tự.");
            return;
        }

        if (password.length() < 7) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 8 ký tự.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp.");
            return;
        }

        TaiKhoanDTO newAccount = new TaiKhoanDTO();
        newAccount.setTenDangNhap(userName);
        newAccount.setMatKhau(password);

        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

        TaiKhoanDTO existingAccount = taiKhoanBUS.getUserByUserName(userName);
        if (existingAccount != null) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại.");
            return;
        }

        int result = taiKhoanBUS.insertAccount(newAccount);

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Đăng Ký Thành Công.");
            this.dispose();
            new DangNhap();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng Ký Thất Bại. Vui lòng thử lại sau.");
        }
    }


    public void closeWindow() {
        this.dispose();
    }

}
