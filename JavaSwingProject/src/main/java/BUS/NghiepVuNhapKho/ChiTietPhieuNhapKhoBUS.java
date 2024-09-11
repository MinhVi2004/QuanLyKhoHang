package BUS.NghiepVuNhapKho;


import DAO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDAO;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;

import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapKhoBUS {

    private final ChiTietPhieuNhapKhoDAO chiTietPhieuNhapKhoDAO;

    public ChiTietPhieuNhapKhoBUS() {
        this.chiTietPhieuNhapKhoDAO = new ChiTietPhieuNhapKhoDAO();
    }

    public ArrayList<ChiTietPhieuNhapKhoDTO> getChiTietPhieuNhapKhoByMaPhieu(Integer maPhieu){
        return (ArrayList<ChiTietPhieuNhapKhoDTO>) chiTietPhieuNhapKhoDAO.getAll(maPhieu);
    }

    public boolean createChiTietPhieuNhapKho(ChiTietPhieuNhapKhoDTO dto){
        return chiTietPhieuNhapKhoDAO.create(null, dto);
    }
    public boolean deleteChiTietPhieuNhapKho(ChiTietPhieuNhapKhoDTO dto) {
        return chiTietPhieuNhapKhoDAO.delete(dto);
    }

}