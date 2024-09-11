package BUS.NghiepVuXuatKho;

import BUS.ThongTinSanPham.SanPhamBUS;
import DAO.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NghiepVuXuatKho.PhieuXuatKhoDAO;
import DTO.NghiepVuXuatKho.*;
import DTO.ThongTinSanPham.*;
import Others.JDBCConfigure;

public class PhieuXuatKhoBUS {
    private final PhieuXuatKhoDAO phieuXuatKhoDAO= new PhieuXuatKhoDAO();

    public PhieuXuatKhoBUS() {

    }

    public ArrayList<PhieuXuatKhoDTO> getAll(int maKhoHang){
        return phieuXuatKhoDAO.getAll(maKhoHang);
    }
    public PhieuXuatKhoDTO getById(int maPhieu){
        return phieuXuatKhoDAO.getById(maPhieu);
    }
    public boolean create(Integer maKhoHang, PhieuXuatKhoDTO phieuXuatKhoDTO) {
        return phieuXuatKhoDAO.create(maKhoHang, phieuXuatKhoDTO);
    }
    public boolean update(PhieuXuatKhoDTO phieuXuatKhoDTO) {
        return phieuXuatKhoDAO.update(phieuXuatKhoDTO);
    }
    public boolean delete(PhieuXuatKhoDTO phieuXuatKhoDTO) {
        return phieuXuatKhoDAO.delete(phieuXuatKhoDTO);
    }
    public int maPhieuXuatKhoTiepTheo() {
        return phieuXuatKhoDAO.maPhieuXuatKhoTiepTheo();
    }
    public ArrayList<PhieuXuatKhoDTO> search(int index, int maKhoHang){
        ArrayList<PhieuXuatKhoDTO> kq= new ArrayList<>();
        switch (index) {
            case 0:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    kq.add(sp);
                }
                break;
            case 1:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if( sp.getTongGiaTri() <=  5000000)
                        kq.add(sp);
                }
                break;
            case 2:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if( sp.getTongGiaTri() >  5000000 && sp.getTongGiaTri() <=  10000000)
                        kq.add(sp);
                }
                break;
            case 3:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if( sp.getTongGiaTri() >  10000000 && sp.getTongGiaTri() <=  20000000)
                        kq.add(sp);
                }
                break;
            case 4:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if( sp.getTongGiaTri() >  20000000 && sp.getTongGiaTri() <=  40000000)
                        kq.add(sp);
                }
                break;
            case 5:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if( sp.getTongGiaTri() >  40000000)
                        kq.add(sp);
                }
                break;

            default:
                break;
        }
        return kq;
    }
    public ArrayList<PhieuXuatKhoDTO> locTrangThai(int index, int maKhoHang){
        ArrayList<PhieuXuatKhoDTO> kq= new ArrayList<>();
        switch (index) {
            case 0:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    kq.add(sp);
                }
                break;
            case 1:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if(sp.getTrangThai().equals("DaDuyet"))
                        kq.add(sp);
                }
                break;
            case 2:
                for(PhieuXuatKhoDTO sp: this.getAll(maKhoHang)){
                    if(sp.getTrangThai().equals("ChoDuyet"))
                        kq.add(sp);
                }
                break;
            default:
                break;
        }
        return kq;
    }
    public String getTenKhoHang(int maPhieuXuat) {
        return phieuXuatKhoDAO.getTenKhoHang(maPhieuXuat);
    }
    public String getHoTen(int maKhoHang) {
        return phieuXuatKhoDAO.getHoTen(maKhoHang);
    }
    public String getHoTenByMaPhieuXuat(int maPhieuXuat) {
        return phieuXuatKhoDAO.getHoTenByMaPhieuXuat(maPhieuXuat);
    }
}