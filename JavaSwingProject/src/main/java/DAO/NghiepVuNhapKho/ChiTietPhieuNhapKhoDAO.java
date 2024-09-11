package DAO.NghiepVuNhapKho;

import DAO.DAOInterface;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhapKhoDAO implements DAOInterface<ChiTietPhieuNhapKhoDTO> {

    @Override
    public ArrayList<ChiTietPhieuNhapKhoDTO> getAll(Integer maPhieuNhap) {
        ArrayList<ChiTietPhieuNhapKhoDTO> danhSachChiTietPhieuNhapKho = new ArrayList<>();

        try {
            JDBCConfigure.getConnection();
            String query = "SELECT * FROM `CTPNK` WHERE `MaPhieu` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, maPhieuNhap);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer maPhieu     = resultSet.getInt("MaPhieu");
                Integer maSanPham   = resultSet.getInt("MaSanPham");
                Integer donGia     = resultSet.getInt("DonGiaNhap");
                Integer soLuong     = resultSet.getInt("SoLuong");
                Long thanhTien      = resultSet.getLong("ThanhTien");

                ChiTietPhieuNhapKhoDTO chiTietPhieuNhapKhoDTO = new ChiTietPhieuNhapKhoDTO();
                chiTietPhieuNhapKhoDTO.setMaPhieu(maPhieu);
                chiTietPhieuNhapKhoDTO.setMaSanPham(maSanPham);
                chiTietPhieuNhapKhoDTO.setDonGia(donGia);
                chiTietPhieuNhapKhoDTO.setSoLuong(soLuong);
                chiTietPhieuNhapKhoDTO.setThanhTien(thanhTien);

                danhSachChiTietPhieuNhapKho.add(chiTietPhieuNhapKhoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConfigure.closeConnection();
        }

        return danhSachChiTietPhieuNhapKho;
    }

    @Override
    public ChiTietPhieuNhapKhoDTO getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Integer maKhoHang, ChiTietPhieuNhapKhoDTO chiTietPhieuNhapKhoDTO) {
        try {
            JDBCConfigure.getConnection();
            String query = "INSERT INTO `CTPNK` (`MaPhieu`, `MaSanPham`, `DonGiaNhap`, `SoLuong`, `ThanhTien`) " +
                                                        "VALUES (?,         ?,              ?,      ?,              ?)";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, chiTietPhieuNhapKhoDTO.getMaPhieu());

            preparedStatement.setInt(2, chiTietPhieuNhapKhoDTO.getMaSanPham());

            preparedStatement.setInt(3, chiTietPhieuNhapKhoDTO.getDonGia());

            preparedStatement.setInt(4, chiTietPhieuNhapKhoDTO.getSoLuong());

            preparedStatement.setLong(5, chiTietPhieuNhapKhoDTO.getThanhTien());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConfigure.closeConnection();
        }
        return true;
    }


    @Override
    public boolean update(ChiTietPhieuNhapKhoDTO chiTietPhieuNhapKhoDTO) {
        return false;
    }

    @Override
    public boolean delete(ChiTietPhieuNhapKhoDTO chiTietPhieuNhapKhoDTO) {
        try {
            JDBCConfigure.getConnection();
            String query = "DELETE FROM `CTPNK` WHERE `MaPhieu` = ? AND `MaSanPham` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, chiTietPhieuNhapKhoDTO.getMaPhieu());
            preparedStatement.setInt(2, chiTietPhieuNhapKhoDTO.getMaSanPham());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConfigure.closeConnection();
        }
        return true;
    }
}