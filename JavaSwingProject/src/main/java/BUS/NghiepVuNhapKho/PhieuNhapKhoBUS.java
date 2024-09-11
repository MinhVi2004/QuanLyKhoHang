package BUS.NghiepVuNhapKho;


import BUS.ThongTinSanPham.SanPhamBUS;
import DAO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDAO;
import DAO.NghiepVuNhapKho.PhieuNhapKhoDAO;
import DAO.NghiepVuXuatKho.PhieuXuatKhoDAO;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import DTO.ThongTinSanPham.SanPhamDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class PhieuNhapKhoBUS {

    private final PhieuNhapKhoDAO phieuNhapKhoDAO;

    private final ChiTietPhieuNhapKhoBUS chiTietPhieuNhapKhoBUS;

    private final SanPhamBUS sanPhamBUS;
    private int maKhoHang;
    
    public PhieuNhapKhoBUS() {
        this.phieuNhapKhoDAO = new PhieuNhapKhoDAO();
        this.chiTietPhieuNhapKhoBUS = new ChiTietPhieuNhapKhoBUS();
        this.sanPhamBUS = new SanPhamBUS(this.maKhoHang);
    }

    public List<PhieuNhapKhoDTO> getAllPhieuNhapKho(Integer maKhoHang){
        this.maKhoHang=maKhoHang;
        return phieuNhapKhoDAO.getAll(maKhoHang);
    }

    public PhieuNhapKhoDTO getPhieuNhapKhoByMaPhieu(Integer maPhieu){
        return phieuNhapKhoDAO.getById(maPhieu);
    }

    public boolean createPhieuNhapKho(Integer maKhoHang,
                                      PhieuNhapKhoDTO phieuNhapKhoDTO,
                                      List<ChiTietPhieuNhapKhoDTO> chiTietPhieuNhapKhoDTOList){
        //Tạo phiếu nhập kho
        phieuNhapKhoDAO.create(maKhoHang, phieuNhapKhoDTO);

        for (ChiTietPhieuNhapKhoDTO dto: chiTietPhieuNhapKhoDTOList){

            //Lấy mã phiếu mới được tạo :3
            dto.setMaPhieu(phieuNhapKhoDTO.getMaPhieu());

            //Tạo các chi tiết phiếu nhập kho
            chiTietPhieuNhapKhoBUS.createChiTietPhieuNhapKho(dto);


            //Tăng so lượng sản phẩm trong kho
            // SanPhamDTO sanPhamDTO = sanPhamBUS.getById(dto.getMaSanPham());
            // sanPhamDTO.setSoLuongConLai(
            //     sanPhamDTO.getSoLuongConLai() + dto.getSoLuong()
            // );
            // sanPhamBUS.updateSoLuongSanPham(sanPhamDTO);

        }


        return true;
    }

    public boolean updatePhieuNhapKho(PhieuNhapKhoDTO phieuNhapKhoDTO){
        return phieuNhapKhoDAO.update(phieuNhapKhoDTO);
    }

    public boolean updatePhieuNhapKhoVeNCCMacDinh(Integer maNCC){
        return phieuNhapKhoDAO.updatePhieuNhapKhoByMaNhaCungCap(maNCC);
    }
    
//"tất cả","dưới 5,000,000 VNĐ", "5,000,000 VNĐ - 10,000,000 VNĐ", "10,000,000 VNĐ - 20,000,000 VNĐ","20,000,000 VNĐ - 40,000,000 VNĐ", "lớn hơn 40,000,000 VNĐ"    
    public ArrayList<PhieuNhapKhoDTO> locTheoKhoangGia(String txt){
        ArrayList<PhieuNhapKhoDTO> danhSach=new ArrayList<>();
        for(PhieuNhapKhoDTO PhieuNhap: phieuNhapKhoDAO.getAll(maKhoHang)){
            if (txt.equals("tất cả"))
                danhSach.add(PhieuNhap);
            else if ( txt.equals("dưới 5,000,000 VNĐ") && PhieuNhap.getTongGiaTri() <5000000)
                danhSach.add(PhieuNhap);
            else if ( txt.equals("5,000,000 VNĐ - 10,000,000 VNĐ") && PhieuNhap.getTongGiaTri() >=5000000 && PhieuNhap.getTongGiaTri()<10000000)
                danhSach.add(PhieuNhap);
            else if ( txt.equals("10,000,000 VNĐ - 20,000,000 VNĐ") && PhieuNhap.getTongGiaTri() >=10000000 && PhieuNhap.getTongGiaTri()<20000000)
                danhSach.add(PhieuNhap);
            else if ( txt.equals("20,000,000 VNĐ - 40,000,000 VNĐ") && PhieuNhap.getTongGiaTri() >=20000000 && PhieuNhap.getTongGiaTri()<40000000)
                danhSach.add(PhieuNhap);
            else if ( txt.equals("lớn hơn 40,000,000 VNĐ") && PhieuNhap.getTongGiaTri() >=40000000)
                danhSach.add(PhieuNhap);
        }
        return danhSach;
    }
    public ArrayList<PhieuNhapKhoDTO> locTheoTrangThai(String txt){
        ArrayList<PhieuNhapKhoDTO> danhSach=new ArrayList<>();
        for(PhieuNhapKhoDTO PhieuNhap: phieuNhapKhoDAO.getAll(maKhoHang)){
            if(txt.equals("Tất cả"))
                danhSach.add(PhieuNhap);
            else if (txt.equals("Đã Duyệt") && PhieuNhap.getTrangThai().equals("DaDuyet"))
                danhSach.add(PhieuNhap);
            else if ( txt.equals("Chờ Duyệt") && PhieuNhap.getTrangThai().equals("ChoDuyet"))
                danhSach.add(PhieuNhap);
        }
        return danhSach;
    }


    // public static void main(String[] args) {
    //     PhieuNhapKhoBUS bus = new PhieuNhapKhoBUS();
//        List<PhieuNhapKhoDTO> dtos = bus.getAllPhieuNhapKho(1);
//
//        for (PhieuNhapKhoDTO dto: dtos){
//            System.err.println("ID: " + dto.getMaPhieu());
//            System.err.println("NgayNhap: " + dto.getNgayNhapKho());
//            System.err.println("\n");
//        }

//        PhieuNhapKhoDTO dtos = bus.getPhieuNhapKhoByMaPhieu(1);
//
//            System.err.println("ID: " + dtos.getMaPhieu());
//            System.err.println("NgayNhap: " + dtos.getNgayNhapKho());
//            System.err.println("\n");


    //     PhieuNhapKhoDTO dto = new PhieuNhapKhoDTO();
    //     dto.setMaKhoHang(2);
    //     dto.setMaNCC(3);
    //     dto.setNgayNhapKho("1985-12-24");
    //     dto.setTongGiaTri(123456L);

    //     List<ChiTietPhieuNhapKhoDTO> list = new ArrayList<>();
    //     for (int i=1; i<=3; i++){
    //         ChiTietPhieuNhapKhoDTO dto1 = new ChiTietPhieuNhapKhoDTO();
    //         dto1.setThanhTien(13L);
    //         dto1.setMaPhieu(dto.getMaPhieu());
    //         dto1.setMaSanPham(i);
    //         dto1.setDonGia(12);
    //         dto1.setSoLuong(12);

    //         list.add(dto1);
    //     }

    //     bus.createPhieuNhapKho(2, dto, list);
    // }
    public int maPhieuNhapKhoTiepTheo() {
        return phieuNhapKhoDAO.maPhieuNhapKhoTiepTheo();
    }
    public String getTenKhoHang(int maKhoHang) {
        return phieuNhapKhoDAO.getTenKhoHang(maKhoHang);
    }
    public String getHoTen(int maKhoHang) {
        return phieuNhapKhoDAO.getHoTen(maKhoHang);
    }
    public String getHoTenByMaPhieuNhap(int maPhieuNhap) {
        return phieuNhapKhoDAO.getHoTenByMaPhieuNhap(maPhieuNhap);
    }
    public String getTenNhaCungCap(int maPhieuNhap) {
        return phieuNhapKhoDAO.getTenNhaCungCap(maPhieuNhap);
    }
}