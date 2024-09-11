
package GUI.GUIThanhPhan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ButtonCustom extends JButton{
    public ButtonCustom(String text, String icon,String color) {
        init(text,icon,color);
    }
    public ButtonCustom(String text, String icon) {
        init(text,icon,"");
    }
    public ButtonCustom(String text) {
        init(text,"","");
    }

    private void init(String text, String icon,String color) {
        this.setText(text);
        if ( !icon.equals(""))
            this.setIcon(new ImageIcon(icon));
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        if( !color.equals(""))
            this.setBackground(Color.decode(color));
        else this.setBackground(Color.WHITE);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(130,60));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mevt){
                    setBackground(getBackground().darker());
                setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent mevt){
                if ( !color.equals(""))
                    setBackground(Color.decode(color));
                else setBackground(Color.WHITE);
                setOpaque(true);
            }
    });
}
}
