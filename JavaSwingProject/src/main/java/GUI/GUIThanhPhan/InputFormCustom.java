
package GUI.GUIThanhPhan;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;


public class InputFormCustom extends JPanel{
    public JLabel tieuDe;
    public JTextField txtForm;
    public InputFormCustom() {
    }

    public InputFormCustom(String title) {
        this.setLayout(new GridLayout(2, 1,0,0));
        Font tieuDeFont= new Font("Arial",Font.BOLD,15);
        Font textFont= new Font("Arial",Font.PLAIN,15);
        //this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 5, 10));
        this.setPreferredSize(new Dimension(180, 120));
        tieuDe = new JLabel(title);
        tieuDe.setFont(tieuDeFont);
        txtForm= new  JTextField();
        txtForm.setFont(textFont);
        txtForm.setBorder(new EmptyBorder(10,20,10,10));
        //txtForm.setPreferredSize(new Dimension(50,30));
        this.add(tieuDe);
        this.add(txtForm);
    }

    public JLabel getTieuDe() {
        return tieuDe;
    }

    public JTextField getTxtForm() {
        return txtForm;
    }

    public void setTieuDe(JLabel tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setTxtForm(JTextField txtForm) {
        this.txtForm = txtForm;
    }
    
}
