package GUI.GUIPanel;

import Application.Main;
import DAO.TaiKhoanDAO;
import DTO.NguoiDung.TaiKhoanDTO;
import GUI.GUIThanhPhan.ButtonCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DangNhap extends JFrame{

    private JLabel userNameLabel;
    private JTextField userNameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private ButtonCustom buttonLogin;
    private JLabel labelQuestion;
    private JButton labelRegister;
    private String linkToIMG = "C:\\Users\\dvmv2\\OneDrive\\Documents\\Nam_2\\Java\\QuanLyKhoHang\\Java-Swing-main\\JavaSwingProject\\src\\main\\java\\Resources";
    public DangNhap() {
        this.init();
    }

    public void init() {
        this.setTitle("Đăng Nhập");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Color.white);

        JLabel Title = new JLabel("Đăng nhập ");
        Title.setFont(new Font("Arial", Font.BOLD, 25));
        Title.setBounds(150, 30, 200, 30);

        userNameLabel = new JLabel("Tên đăng nhập");
        userNameLabel.setBounds(50, 100, 100, 30);
        userNameField = new JTextField(50);
        userNameField.setBounds(50, 130, 300, 30);

        passwordLabel = new JLabel("Mật Khẩu");
        passwordLabel.setBounds(50, 160, 100, 30);
        passwordField = new JPasswordField(50);
        passwordField.setBounds(50, 190, 300, 30);

        buttonLogin = new ButtonCustom("Đăng nhập","","#3eceff");
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 18));
        buttonLogin.setBounds(50, 270, 300, 50);
        buttonLogin.setForeground(Color.white);
        buttonLogin.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });

        labelQuestion = new JLabel("Bạn chưa có tài khoản?");
        labelQuestion.setFont(new Font("Arial", Font.PLAIN, 15));
        labelQuestion.setBounds(50, 400, 300, 30);
        
        labelRegister = new JButton("Đăng ký");
        labelRegister.setActionCommand("Đăng ký");
        labelRegister.setBorderPainted(false);
        labelRegister.setBackground(Color.white);
        labelRegister.setFont(new Font("Arial", Font.PLAIN, 15));
        labelRegister.setBounds(220, 405, 100, 20);
        labelRegister.setForeground(Color.decode("#3eceff"));
        labelRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mevt){
                labelRegister.setBackground(Color.decode("#3eceff"));
                labelRegister.setForeground(Color.white);
                labelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent mevt){
                labelRegister.setBackground(Color.white);
                labelRegister.setForeground(Color.decode("#3eceff"));
                labelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
    });
        
        labelRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DangKy();
                closeWindow();
            }
        });

        userNameField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
        
        
        passwordField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
        
        
        
        panelLeft.add(Title);
        panelLeft.add(userNameLabel);
        panelLeft.add(userNameField);
        panelLeft.add(passwordLabel);
        panelLeft.add(passwordField);
        panelLeft.add(buttonLogin);
        panelLeft.add(labelQuestion);
        panelLeft.add(labelRegister);
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

    public void closeWindow () {
        this.dispose();
    }

    public void checkLogin() {
        String userField = userNameField.getText();System.out.println("tk:"+userField);
        String passField = new String(passwordField.getPassword());
        if (userField.equals("") || passField.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                TaiKhoanDTO acc = TaiKhoanDAO.getInstance().getUserByUserName(userField);
                if (acc == null) {
                    JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại trên hệ thống !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (passField.equals(acc.getMatKhau())) {
                        if (acc.getTrangThai() == 1) {
                            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                            this.dispose();
                            new Main(acc);
                        } else {
                            JOptionPane.showMessageDialog(this, "Tài khoản của bạn đã bị khóa !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Sai mật khẩu !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
