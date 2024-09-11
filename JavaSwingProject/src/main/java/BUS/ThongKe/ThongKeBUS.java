package BUS.ThongKe;

import DAO.ThongKe.ThongKeDAO;
import DTO.ThongKe.ThongKeSanPham;

import java.util.List;

public class ThongKeBUS {

    private ThongKeDAO thongKeDAO = null ;

    public ThongKeBUS(Integer maKhoHang){
        thongKeDAO = new ThongKeDAO(maKhoHang);
    }

    public Long thongKeTongChiTieu(String minDate, String maxDate){
        return thongKeDAO.tongChiTieu(minDate, maxDate);
    }

    public Long thongKeTongDoanhThu(String minDate, String maxDate){
        return thongKeDAO.tongDoanhThu(minDate, maxDate);
    }

    public List<ThongKeSanPham> thongKeChiTietNhapKho(String minDate, String maxDate){
        return thongKeDAO.thongKeChiTietNhapKho(minDate, maxDate);
    }

    public List<ThongKeSanPham> thongKeChiTietXuatKho(String minDate, String maxDate){
        return thongKeDAO.thongKeChiTietXuatKho(minDate, maxDate);
    }


}
