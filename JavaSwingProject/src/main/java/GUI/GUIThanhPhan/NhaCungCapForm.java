package GUI.GUIThanhPhan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NhaCungCapForm extends JPanel {
    public JLabel tenNCCLabel, emailLabel, soDienThoaiLabel;
    public JTextField tenNCCText, emailText, soDienThoaiText;

    public NhaCungCapForm() {
        setLayout(new BorderLayout());
        Font tieuDeFont = new Font("Arial", Font.BOLD, 15);
        Font textFont = new Font("Arial", Font.PLAIN, 15);
        setBorder(new EmptyBorder(10, 10, 5, 10));
        setPreferredSize(new Dimension(180, 120));

//        tenNCCLabel = new JLabel(title);
//        tenNCCLabel.setFont(tieuDeFont);
//        add(tenNCCLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 1, 0, 10)); // 3 rows, 1 column
        contentPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        tenNCCLabel = new JLabel("Tên nhà cung cấp");
        tenNCCLabel.setFont(tieuDeFont);
        contentPanel.add(tenNCCLabel);

        tenNCCText = new JTextField();
        tenNCCText.setFont(textFont);
        tenNCCText.setBorder(new EmptyBorder(10, 20, 10, 10));
        contentPanel.add(tenNCCText);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(tieuDeFont);
        contentPanel.add(emailLabel);

        emailText = new JTextField();
        emailText.setFont(textFont);
        emailText.setBorder(new EmptyBorder(10, 20, 10, 10));
        contentPanel.add(emailText);

        soDienThoaiLabel = new JLabel("Số điện thoại");
        soDienThoaiLabel.setFont(tieuDeFont);
        contentPanel.add(soDienThoaiLabel);

        soDienThoaiText = new JTextField();
        soDienThoaiText.setFont(textFont);
        soDienThoaiText.setBorder(new EmptyBorder(10, 20, 10, 10));
        contentPanel.add(soDienThoaiText);

        add(contentPanel, BorderLayout.CENTER);
    }

    public JLabel getTenNCCLabel() {
        return tenNCCLabel;
    }

    public JTextField getTenNCCText() {
        return tenNCCText;
    }

    public void setTenNCCLabel(JLabel tenNCCLabel) {
        this.tenNCCLabel = tenNCCLabel;
    }

    public void setTenNCCText(JTextField tenNCCText) {
        this.tenNCCText = tenNCCText;
    }
}
