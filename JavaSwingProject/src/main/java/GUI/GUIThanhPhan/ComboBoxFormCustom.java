
package GUI.GUIThanhPhan;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ComboBoxFormCustom extends JPanel{
    public JLabel tieuDe;
    public JComboBox<String> list;

    public ComboBoxFormCustom() {
    }

    public ComboBoxFormCustom(String tieude, String[] list) {
        this.setLayout(new GridLayout(2, 1,0,0));
//        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 5, 10));
        this.setPreferredSize(new Dimension(180, 120));
         this.tieuDe= new JLabel(tieude);
        this.list= new JComboBox(list);
        Font tieuDeFont= new Font("Arial",Font.BOLD,15);
        Font comboFont= new Font("Arial",Font.PLAIN,15);

        this.tieuDe.setFont(tieuDeFont);
        this.list.setFont(comboFont);
        
        this.list.setBackground(Color.WHITE);
        this.add(tieuDe);
        this.add(this.list);
        
    }

    public JLabel getTieuDe() {
        return tieuDe;
    }

    public JComboBox<String> getList() {
        return list;
    }

    public void setTieuDe(JLabel tieuDe) {
        this.tieuDe = tieuDe;
    }

    public void setList(JComboBox<String> list) {
        this.list = list;
    }
    
    
}
