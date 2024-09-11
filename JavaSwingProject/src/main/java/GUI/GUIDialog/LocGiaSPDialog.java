/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GUIDialog;

import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIPanel.SanPhamUI;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LocGiaSPDialog extends  JDialog implements ActionListener{
    private SanPhamUI SPGUI;
    private SanPhamBUS SanPhamBUS;
    private InputFormCustom tuGia, denGia;
    public LocGiaSPDialog(SanPhamUI guiSP) {
        this.SPGUI=guiSP;
        this.SanPhamBUS= new SanPhamBUS(guiSP.getMaKhoHang());
        this.init();
    }
    
    public void init(){
        this.setTitle("Sản Phẩm");
        this.setLayout(new BorderLayout(5,5));
        this.setSize(400, 350);
        this.setBackground(Color.white);
        Font tieudeFont= new Font("Arial",Font.BOLD,20);
        Font nutNhanFont= new Font("Arial",Font.BOLD,10);
        JPanel chuaTieuDe= new JPanel();
        JLabel tieuDe= new JLabel("Lọc Theo Khoảng Giá");
        tieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        tieuDe.setFont(tieudeFont);
        tieuDe.setForeground(Color.white);
        chuaTieuDe.setBackground(Color.decode("#82CAFF"));
        chuaTieuDe.add(tieuDe);
        JPanel thongtin= new JPanel(new GridLayout(2,1,10,10));
        tuGia= new InputFormCustom("Từ:");
        tuGia.setPreferredSize(new Dimension(300,50));
        denGia= new InputFormCustom("Đến:");
        denGia.setPreferredSize(new Dimension(300,50));
        JPanel chuaNut= new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
        ButtonCustom locSP= new ButtonCustom("Lọc","","#82CAFF");
        ButtonCustom huy= new ButtonCustom("Hủy","","#CD0000");
        locSP.setHorizontalAlignment(SwingConstants.CENTER);
        locSP.setPreferredSize(new Dimension(100,50));
        huy.setPreferredSize(new Dimension(100,50));
        huy.setHorizontalAlignment(SwingConstants.CENTER);
        locSP.setForeground(Color.white);
        huy.setForeground(Color.white);
        thongtin.add(tuGia);
        thongtin.add(denGia);
        chuaNut.add(locSP); chuaNut.add(huy);
        tuGia.setFont(nutNhanFont); denGia.setFont(nutNhanFont);
        this.add(chuaTieuDe,BorderLayout.NORTH);
        this.add(thongtin,BorderLayout.CENTER);
        this.add(chuaNut,BorderLayout.SOUTH);
        
        
        locSP.addActionListener(this);
        huy.addActionListener(this);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
       String lenh=ae.getActionCommand();
       if(lenh.equals("Hủy"))
           this.dispose();
       else{
           if(tuGia.getTxtForm().getText().trim().equals("") ||
              denGia.getTxtForm().getText().trim().equals(""))
               JOptionPane.showMessageDialog(this, "Vui lòng không để trống","Thông báo",JOptionPane.INFORMATION_MESSAGE);

           else{
               try{
                   int num1=Integer.valueOf(tuGia.getTxtForm().getText().trim());
                   int num2=Integer.valueOf(denGia.getTxtForm().getText().trim());
                   if( num2 < num1)
                   {
                       JOptionPane.showMessageDialog(this, "Giá tiền giới hạn phải lớn hơn giá tiền bắt đầu !!","Thông báo",JOptionPane.ERROR_MESSAGE);                       
                   }else if (num2 <0 || num1 <0){
                       JOptionPane.showMessageDialog(this, "Vui lòng nhập số lớn hơn 0 !!","Thông báo",JOptionPane.ERROR_MESSAGE);                           
                   }

                    
                   else {
                       
                       ArrayList<SanPhamDTO> kq=SanPhamBUS.locTheoKhoangGia(5, num1, num2);
                        SPGUI.loadDuLieuTuDatabase(kq);
                        SPGUI.chinhSuaGiaoDienTable();
                        this.dispose();
                   }


               }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số với các ô yêu cầu điền số lượng, giá tiền !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
           }
       }
    }
    
}
