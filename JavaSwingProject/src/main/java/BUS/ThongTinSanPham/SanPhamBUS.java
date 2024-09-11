package BUS.ThongTinSanPham;

import java.util.ArrayList;

import DAO.ThongTinSanPham.SanPhamDAO;
import DTO.ThongTinSanPham.SanPhamDTO;
import java.util.Collections;
import java.util.Comparator;


public class SanPhamBUS {
    private final SanPhamDAO sanPhamDAO= new SanPhamDAO();
    private ArrayList<SanPhamDTO> danhSachSanPham= new ArrayList<>();
    private LoaiSanPhamBUS loaiSPBUS;
    private int maKho;
    
    public SanPhamBUS(int maKhoHang) {
        maKho=maKhoHang;
        danhSachSanPham=sanPhamDAO.getAll(maKhoHang);
        loaiSPBUS= new LoaiSanPhamBUS(maKhoHang);
    }
    
    public ArrayList<SanPhamDTO> getAll(){
        
        danhSachSanPham=sanPhamDAO.getAll(this.maKho); 
        return this.danhSachSanPham;
    }
    // hàm hỗ trợ lấy ra index trong danh sách sản phẩm dành cho việc update thông tin sản phẩm
    public int getIndexByMaSP(int maSP){
        for( int i=0; i < this.danhSachSanPham.size();i++){
            if(this.danhSachSanPham.get(i).getMaSanPham()==maSP)
                return i;
        }
        return -1;
    }
    // dành cho table của nhập hàng
    public SanPhamDTO getSPByTenSP(String tenSP){
        for( int i=0; i < this.danhSachSanPham.size();i++){
            if(this.danhSachSanPham.get(i).getTenSanPham()==tenSP)
                return this.danhSachSanPham.get(i);
        }
        return null;        
    }
    
    
    public SanPhamDTO getById(int masp){
        System.out.println("Danh sách SP");
        for(SanPhamDTO a: danhSachSanPham){
            System.out.println(a);
        }
        for( int i=0; i<this.danhSachSanPham.size();i++)
            if ( this.danhSachSanPham.get(i).getMaSanPham()==masp)
                return this.danhSachSanPham.get(i);
        
        return null;
    }
     // dạng lấy sản phẩm đã xóa từ trước, dành cho phiếu đã nhập, xuất từ trước
    public SanPhamDTO getById_ver2(int masp, int makho){
        
        ArrayList<SanPhamDTO> danhSachSanPhamTuTruoc=sanPhamDAO.getAll_ver2(makho);
        for( int i=0; i<danhSachSanPhamTuTruoc.size();i++)
            if ( danhSachSanPhamTuTruoc.get(i).getMaSanPham()==masp)
                return danhSachSanPhamTuTruoc.get(i);
        
        return null;
    }    
    
    
    
    public boolean create(SanPhamDTO spMoi){
        for( int i=0; i < this.danhSachSanPham.size();i++)
            if(this.danhSachSanPham.get(i).getTenSanPham().equals(spMoi.getTenSanPham()))
                return false;
        
        if( sanPhamDAO.create(this.maKho,spMoi))
        {
            this.danhSachSanPham.add(spMoi);
            return true;
        }
        return false;
    }
    
    
    
    public boolean update(SanPhamDTO spMoi){
        SanPhamDTO spdto= sanPhamDAO.getByName(spMoi.getMaKhoHang(),spMoi.getTenSanPham());
        if(spdto!=null && spdto.getMaSanPham() != spMoi.getMaSanPham())
            return false;
        else if( sanPhamDAO.update(spMoi)){
            this.danhSachSanPham.set(getIndexByMaSP(spMoi.getMaSanPham()), spMoi);
            return true;
        }
        return false;
        
    }
    
    public boolean updateSoLuongSanPham(SanPhamDTO dto){
        return sanPhamDAO.updateSoLuongSanPham(dto);
    }
    
    
    public boolean delete(SanPhamDTO spCanXoa){
        if( sanPhamDAO.delete(spCanXoa))
        {
            this.danhSachSanPham=sanPhamDAO.getAll(this.maKho);
            return true;
        }
        return false;
    }
    
    // đang tìm kiếm với tên và loại sản phẩm
    // các loại tìm kiếm khác sau này có nhu cầu thì mở rộng sau
    public ArrayList<SanPhamDTO> search(String txt){
        txt=txt.toLowerCase();
        ArrayList<SanPhamDTO> kq= new ArrayList<>();
        for(SanPhamDTO sp: this.danhSachSanPham){
            if( sp.getTenSanPham().toLowerCase().contains(txt) || 
                loaiSPBUS.getLoaiSPChung().get(sp.getMaLoaiSanPham()).toLowerCase().equals(txt))
                    kq.add(sp);
        }
        return kq;
    }
    
    public ArrayList<SanPhamDTO> searchVoiLoaiSP(String txt){

        ArrayList<SanPhamDTO> kq= new ArrayList<>();
        for(SanPhamDTO sp: this.danhSachSanPham){
            // trừ -1 vì do Array bắt đầu từ 0, mã sp bắt đầu từ 1
            if(loaiSPBUS.getLoaiSPChung().get(sp.getMaLoaiSanPham()-1).equals(txt))
                kq.add(sp);
        }    
        return kq;
    }
    public ArrayList<SanPhamDTO> locTheoGiaTangGiam(ArrayList<SanPhamDTO> listSP, String txt){
        if (txt.equals("Tăng"))
        {
            Collections.sort(listSP, new Comparator<SanPhamDTO>() {
                @Override
                public int compare(SanPhamDTO sp1, SanPhamDTO sp2) {
                    return Double.compare(sp1.getGiaSanPham(), sp2.getGiaSanPham());
                }
            });
        }
        else {
                Collections.sort(listSP, new Comparator<SanPhamDTO>() {
                @Override
                public int compare(SanPhamDTO sp1, SanPhamDTO sp2) {
                    return Double.compare(sp2.getGiaSanPham(), sp1.getGiaSanPham());
                }
            });
        }
        return listSP;
        
    }
    public ArrayList<SanPhamDTO> locTheoKhoangGia(int loai,int num1, int num2){
        ArrayList<SanPhamDTO> kq= new ArrayList<>();
        switch (loai) {
            case 1:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() < 200000)
                    kq.add(sp);
            }    
                break;
            case 2:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > 200000 && sp.getGiaSanPham() < 1000000)
                    kq.add(sp);
            }
                break;
            case 3:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > 1000000 && sp.getGiaSanPham() < 2000000)
                    kq.add(sp);
            }
                break;
            case 4:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > 2000000)
                    kq.add(sp);
            }
                break;
            case 5:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > num1 && sp.getGiaSanPham() < num2)
                    kq.add(sp);
            }
                break;
        }
        return kq;
        
    }

    
}
