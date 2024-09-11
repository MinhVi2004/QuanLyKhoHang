package DAO.NghiepVuXuatKho;

import DAO.DAOInterface;
import DTO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDTO;
import Others.JDBCConfigure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class ChiTietPhieuXuatKhoDAO implements DAOInterface<ChiTietPhieuXuatKhoDTO> {

      @Override
      public ArrayList<ChiTietPhieuXuatKhoDTO> getAll(Integer maPhieuXuat) {
            ArrayList<ChiTietPhieuXuatKhoDTO> list = new ArrayList<>();
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet resultSet = statement.executeQuery("select * from ctpxk where ctpxk.MaPhieu = " + maPhieuXuat);
 
                  while (resultSet.next()) {
                        ChiTietPhieuXuatKhoDTO ctpxkObject = new ChiTietPhieuXuatKhoDTO();
 
                        ctpxkObject.setSoLuong(resultSet.getInt("SoLuong"));
                        ctpxkObject.setThanhTien(resultSet.getInt("ThanhTien"));
                        ctpxkObject.setDonGia(resultSet.getInt("DonGia"));
                        ctpxkObject.setMaPhieu(maPhieuXuat);
                        ctpxkObject.setMaSanPham(resultSet.getInt("MaSanPham"));
 
                        list.add(ctpxkObject);
                  }
                  JDBCConfigure.closeConnection();
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
            }
            return list;
      }
      @Override
      public ChiTietPhieuXuatKhoDTO getById(Integer id) {
            ChiTietPhieuXuatKhoDTO chiTietPhieuXuatKho = new ChiTietPhieuXuatKhoDTO();
            try {
                  Statement statement = JDBCConfigure.getConnection().createStatement();
                  ResultSet resultSet = statement.executeQuery("select * from ctpxk where ctpxk.MaPhieu = " + id);
 
                  while (resultSet.next()) {
                        ChiTietPhieuXuatKhoDTO ctpxkObject = new ChiTietPhieuXuatKhoDTO();
 
                        ctpxkObject.setSoLuong(resultSet.getInt("SoLuong"));
                        ctpxkObject.setThanhTien(resultSet.getInt("ThanhTien"));
                        ctpxkObject.setDonGia(resultSet.getInt("DonGia"));
                        ctpxkObject.setMaSanPham(resultSet.getInt("MaSanPham"));
                  }
                  JDBCConfigure.closeConnection();
            } catch (SQLException e) {
                  e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
            }
            return chiTietPhieuXuatKho;
      }


    //

      @Override
      public boolean create(Integer maKhoHang, ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            try{
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int taoPhieuXuatHang = state.executeUpdate("INSERT INTO `ctpxk` (`SoLuong`, `ThanhTien`, `DonGia`, `MaPhieu`, `MaSanPham`) VALUES ('"+ChiTietPhieuXuatKhoDTO.getSoLuong()+"', '"+ChiTietPhieuXuatKhoDTO.getThanhTien()+"', '"+ChiTietPhieuXuatKhoDTO.getDonGia()+"', '"+ChiTietPhieuXuatKhoDTO.getMaPhieu()+"', '"+ChiTietPhieuXuatKhoDTO.getMaSanPham()+"');");
                  if(taoPhieuXuatHang != 1) {
                        System.out.println("Khoi tao phieu xuat hàng that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.out.println("khu b");
                  System.err.println(e.getMessage());
                  return false;
            }
      }

      @Override
      public boolean update(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            try{
                  //? UPDATE `phieuxuatkho` SET `NgayXuatKho` = now(), `TongGiaTri` = '15000000' WHERE `phieuxuatkho`.`MaPhieu` = 33;

                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int capNhatChiTietPhieuXuatHang = state.executeUpdate("UPDATE `ctpxk` SET `SoLuong` = '"+ChiTietPhieuXuatKhoDTO.getSoLuong()+"', `ThanhTien` = '"+ChiTietPhieuXuatKhoDTO.getThanhTien()+"' WHERE `ctpxk`.`MaPhieu` = "+ChiTietPhieuXuatKhoDTO.getMaPhieu()+" AND `ctpxk`.`MaSanPham` = "+ ChiTietPhieuXuatKhoDTO.getMaSanPham());
                  if(capNhatChiTietPhieuXuatHang != 1) {
                        System.out.println("Cap nhat phieu xuat hàng that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
                  return false;
            }
      }

      @Override
      public boolean delete(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            try{
                  //? DELETE FROM ctpxk WHERE `ctpxk`.`MaPhieu` = 30 AND `ctpxk`.`MaSanPham` = 5

                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int capNhatChiTietPhieuXuatHang = state.executeUpdate("DELETE FROM ctpxk WHERE `ctpxk`.`MaPhieu` = "+ChiTietPhieuXuatKhoDTO.getMaPhieu()+" AND `ctpxk`.`MaSanPham` = " + ChiTietPhieuXuatKhoDTO.getMaSanPham());
                  if(capNhatChiTietPhieuXuatHang != 1) {
                        System.out.println("Xoa phieu xuat hàng that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
                  return false;
            }
      }
      public boolean deleteALL(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            try{
                  //? DELETE FROM ctpxk WHERE `ctpxk`.`MaPhieu` = 30
                  
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int capNhatChiTietPhieuXuatHang = state.executeUpdate("DELETE FROM ctpxk WHERE `ctpxk`.`MaPhieu` = "+ChiTietPhieuXuatKhoDTO.getMaPhieu());
                  if(capNhatChiTietPhieuXuatHang != 1) {
                        System.out.println("Xoa phieu xuat hàng that bai !");
                        return false;
                  } else {
                        JDBCConfigure.closeConnection();
                        return true;
                  }
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
                  return false;
            }
      }
}