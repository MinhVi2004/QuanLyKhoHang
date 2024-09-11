
package DAO.ThongTinSanPham;

import DAO.DAOInterface;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import DTO.ThongTinSanPham.SanPhamDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public class SanPhamDAO implements DAOInterface<SanPhamDTO> {

    public int check=0;
    public int maKho;
    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }


    @Override
    public ArrayList<SanPhamDTO> getAll(Integer maKhoHang) {
        this.maKho=maKhoHang;
        ArrayList<SanPhamDTO> list= new ArrayList<>();
        try {
            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From sanpham Where TrangThai=True and MaKhoHang=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, maKhoHang);
            ResultSet kq=pst.executeQuery();
            while( kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                SanPhamDTO sp= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maLoaiSP, maKho);
                list.add(sp);
                
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL getALL từ SPDAO");
        }finally{
            JDBCConfigure.closeConnection();
        }
        return list;
    }
    
    // dạng lấy cả sản phẩm đã xóa từ trước, dành cho phiếu đã nhập, xuất từ trước
    public ArrayList<SanPhamDTO> getAll_ver2(Integer maKhoHang) {
        this.maKho=maKhoHang;
        ArrayList<SanPhamDTO> list= new ArrayList<>();
        try {
            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From sanpham Where MaKhoHang=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, maKhoHang);
            ResultSet kq=pst.executeQuery();
            while( kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                SanPhamDTO sp= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maLoaiSP, maKho);
                list.add(sp);
                
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL getALL từ SPDAO");
        }finally{
            JDBCConfigure.closeConnection();
        }
        return list;
    }    

    @Override
    public SanPhamDTO getById(Integer id) {
        SanPhamDTO sanPham=null;
        try {
            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From sanpham Where MaSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));
            ResultSet kq=pst.executeQuery();    
            while(kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                String maLoaiSP=String.valueOf(kq.getInt("MaLoaiSanPham"));
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                sanPham= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maSP, maKho);
            }

        } catch (SQLException ex) {
            System.out.println("Lỗi SQL từ SP DAO");
        }
        finally{
            JDBCConfigure.closeConnection();
        }
        return sanPham;
    }
    public SanPhamDTO getByName(int maKhoHang,String name) {
        SanPhamDTO sanPham=null;
        try {
            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From sanpham Where TenSanPham=? and MaKhoHang=? and TrangThai=True";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, maKhoHang);
            ResultSet kq=pst.executeQuery();    
            while(kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                String maLoaiSP=String.valueOf(kq.getInt("MaLoaiSanPham"));
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                sanPham= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maSP, maKho);
            }

        } catch (SQLException ex) {
            System.out.println("Lỗi SQL getByName SPDAO: ");
        }
        finally{
            JDBCConfigure.closeConnection();
        }
        return sanPham;
    }
    
    
    
    
    @Override
    public boolean create(Integer maKhoHang, SanPhamDTO sanPhamDTO) {
        try {

                Connection con= JDBCConfigure.getConnection();
                String sql = "INSERT INTO sanpham (TenSanPham, XuatXu, Gia, SoLuongConLai, TrangThai, AnhMinhHoa, MaLoaiSanPham, MaKhoHang)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, sanPhamDTO.getTenSanPham());
                pst.setString(2, sanPhamDTO.getXuatXu());
                pst.setInt(3, sanPhamDTO.getGiaSanPham());
                pst.setInt(4, sanPhamDTO.getSoLuongConLai());
                pst.setBoolean(5, true);
                pst.setString(6, sanPhamDTO.getAnhMinhhoa());
                pst.setInt(7, sanPhamDTO.getMaLoaiSanPham());
                pst.setInt(8, maKhoHang); 
                check=pst.executeUpdate();

                return true;

        } catch (SQLException ex) {
            System.out.println("Lỗi SQL create SPDAO:" + ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    public boolean updateSoLuongSanPham(SanPhamDTO sanPhamDTO){
        String createPhieuNhapKhoQuery =
            "UPDATE `SanPham` " +
                "SET `SoLuongConLai`=? Where `MaSanPham`=?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createPhieuNhapKhoQuery);

            preparedStatement.setInt(1, sanPhamDTO.getSoLuongConLai());
            preparedStatement.setInt(2, sanPhamDTO.getMaSanPham());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Lỗi SQL update SPDAO: ");
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean update(SanPhamDTO sanPhamDTO) {
        Connection con = JDBCConfigure.getConnection();
        try {

            String sql="Update sanpham Set TenSanPham=?, XuatXu=?, Gia=?, SoLuongConLai=?, TrangThai=1, MaLoaiSanPham=?, AnhMinhHoa=?, MaKhoHang=? Where MaSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, sanPhamDTO.getTenSanPham());
            pst.setString(2, sanPhamDTO.getXuatXu());
            pst.setInt(3, sanPhamDTO.getGiaSanPham());
            pst.setInt(4, sanPhamDTO.getSoLuongConLai());
            pst.setInt(5, sanPhamDTO.getMaLoaiSanPham());
            pst.setString(6, sanPhamDTO.getAnhMinhhoa());
            pst.setInt(7, sanPhamDTO.getMaKhoHang());
            pst.setInt(8, sanPhamDTO.getMaSanPham());
            check=pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL update SPDAO: ");
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    public boolean updateWithDelteLoaiSP(LoaiSanPhamDTO loaiSP){
        Connection con = JDBCConfigure.getConnection();
        try {
            String sql="Update sanpham Set MaLoaiSanPham=1 Where MaLoaiSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1, loaiSP.getMaLoaiSanPham());
            check=pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL updateWith từ SPDAO");
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }
    
    @Override
    public boolean delete(SanPhamDTO sanPhamDTO) {
        Connection con=JDBCConfigure.getConnection();
        try {
            String sql="Update sanpham Set TrangThai=? Where MaSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setBoolean(1, false);
            pst.setInt(2, sanPhamDTO.getMaSanPham());
            check=pst.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL delete từ SPDAO");
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }
}
