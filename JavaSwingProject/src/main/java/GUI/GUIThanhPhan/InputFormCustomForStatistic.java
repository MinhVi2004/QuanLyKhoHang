package GUI.GUIThanhPhan;

import Others.UtilServices;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

@Data
@NoArgsConstructor
public class InputFormCustomForStatistic extends JPanel {

    private JLabel tieuDe, tongGiaTri;
    private JTextField txtForm1, txtForm2;
    private JButton click;

    public InputFormCustomForStatistic(String title, Long tongTien) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);

        Font tieuDeFont = new Font("Arial", Font.BOLD, 15);
        Font textFont = new Font("Arial", Font.PLAIN, 15);

        tieuDe = new JLabel(title);
        tieuDe.setFont(tieuDeFont);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        this.add(tieuDe, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        txtForm1 = new JTextField();
        txtForm1.setFont(textFont);
        txtForm1.setBorder(new EmptyBorder(10, 15, 10, 10));
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtForm1, gbc);

        gbc.gridx = 1;
        txtForm2 = new JTextField();
        txtForm2.setFont(textFont);
        txtForm2.setBorder(new EmptyBorder(10, 15, 10, 10));
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtForm2, gbc);

        // Thêm nhãn tổng giá trị và hiển thị giá trị tongTien
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        tongGiaTri = new JLabel("Tổng giá trị: " + UtilServices.formatVND(tongTien) );
        this.add(tongGiaTri, gbc);

        // Thêm nút "Lọc"
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        click = new JButton("Lọc");
        this.add(click, gbc);
    }

    public void setTongTien(Long tongTien){
        this.tongGiaTri.setText("Tổng giá trị: " + UtilServices.formatVND(tongTien));
    }
}
