package DAO.ThongKe;

import DTO.ThongKe.ThongKeSanPham;
import Others.JDBCConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    Integer maKhoHang = null;

    public ThongKeDAO(Integer maKhoHang){
        this.maKhoHang = maKhoHang;
    }

    public Long tongDoanhThu(String minDate, String maxDate){
        Long tongTien = 0L;
        Connection connection = JDBCConfigure.getConnection();
        String query = "SELECT SUM(TongGiaTri) AS TongGiaTri FROM PhieuXuatKho\n" +
                        "WHERE MaKhoHang = ?\n" +
                        "AND TrangThai = 'DaDuyet'\n" +
                        "AND NgayXuatKho BETWEEN COALESCE(?, '2010-01-01') AND COALESCE(?, CURRENT_DATE())";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.maKhoHang);
            if (minDate == null){
                preparedStatement.setNull(2, Types.INTEGER);
            }else{
                preparedStatement.setString(2, minDate);
            }

            if (maxDate == null){
                preparedStatement.setNull(3, Types.INTEGER);
            }else{
                preparedStatement.setString(3, maxDate);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                tongTien += resultSet.getLong(1);
            }



        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            JDBCConfigure.closeConnection();
        }
        return tongTien;
    }

    public Long tongChiTieu(String minDate, String maxDate){
        Long tongTien = 0L;
        Connection connection = JDBCConfigure.getConnection();
        String query = "SELECT SUM(TongGiaTri) AS TongGiaTri FROM PhieuNhapKho\n" +
            "WHERE MaKhoHang = ?\n" +
            "AND TrangThai = 'DaDuyet'\n" +
            "AND NgayNhapKho BETWEEN COALESCE(?, '2010-01-01') AND COALESCE(?, CURRENT_DATE())";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.maKhoHang);
            if (minDate == null){
                preparedStatement.setNull(2, Types.INTEGER);
            }else{
                preparedStatement.setString(2, minDate);
            }

            if (maxDate == null){
                preparedStatement.setNull(3, Types.INTEGER);
            }else{
                preparedStatement.setString(3, maxDate);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                tongTien += resultSet.getLong(1);
            }



        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            JDBCConfigure.closeConnection();
        }
        return tongTien;
    }

    public List<ThongKeSanPham> thongKeChiTietNhapKho(String minDate, String maxDate){
        List<ThongKeSanPham> list = new ArrayList<>();
        Connection connection = JDBCConfigure.getConnection();

        String query = "SELECT sp.TenSanPham as TenSanPham, SUM(ct.SoLuong) as SoLuong, SUM(ct.ThanhTien) as TongTien FROM PhieuNhapKho pnk \n" +
                        "JOIN CTPNK ct ON pnk.MaPhieu = ct.MaPhieu\n" +
                        "JOIN SanPham sp ON sp.MaSanPham = ct.MaSanPham\n" +
                        "\n" +
                        "WHERE pnk.MaKhoHang = ?\n" +
                        "AND  pnk.TrangThai = 'DaDuyet'\n" +
                        "AND  pnk.NgayNhapKho BETWEEN COALESCE(?, '2010-01-01') AND COALESCE(?, CURRENT_DATE())\n" +
                        "GROUP BY sp.TenSanPham\n" +
                        "ORDER BY TongTien desc\n" +
                        "\n";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1 , this.maKhoHang);

            if (minDate == null){
                preparedStatement.setNull(2, Types.INTEGER);
            }else{
                preparedStatement.setString(2, minDate);
            }


            if (maxDate == null){
                preparedStatement.setNull(3, Types.INTEGER);
            }else{
                preparedStatement.setString(3, maxDate);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ThongKeSanPham dto = new ThongKeSanPham();
                dto.setTenSanPham(resultSet.getString(1));
                dto.setSoLuong(resultSet.getInt(2));
                dto.setTongTien(resultSet.getLong(3));
                list.add(dto);

            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            JDBCConfigure.closeConnection();
        }

        return list;
    }

    public List<ThongKeSanPham> thongKeChiTietXuatKho(String minDate, String maxDate){
        List<ThongKeSanPham> list = new ArrayList<>();
        Connection connection = JDBCConfigure.getConnection();

        String query = "SELECT sp.TenSanPham as TenSanPham, SUM(ct.SoLuong) as SoLuong, SUM(ct.ThanhTien) as TongTien FROM PhieuXuatKho pnk \n" +
            "JOIN CTPXK ct ON pnk.MaPhieu = ct.MaPhieu\n" +
            "JOIN SanPham sp ON sp.MaSanPham = ct.MaSanPham\n" +
            "\n" +
            "WHERE pnk.MaKhoHang = ?\n" +
            "AND  pnk.TrangThai = 'DaDuyet'\n" +
            "AND  pnk.NgayXuatKho BETWEEN COALESCE(?, '2010-01-01') AND COALESCE(?, CURRENT_DATE())\n" +
            "GROUP BY sp.TenSanPham\n" +
            "ORDER BY TongTien desc\n" +
            "\n";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1 , this.maKhoHang);

            if (minDate == null){
                preparedStatement.setNull(2, Types.INTEGER);
            }else{
                preparedStatement.setString(2, minDate);
            }


            if (maxDate == null){
                preparedStatement.setNull(3, Types.INTEGER);
            }else{
                preparedStatement.setString(3, maxDate);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ThongKeSanPham dto = new ThongKeSanPham();
                dto.setTenSanPham(resultSet.getString(1));
                dto.setSoLuong(resultSet.getInt(2));
                dto.setTongTien(resultSet.getLong(3));
                list.add(dto);

            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            JDBCConfigure.closeConnection();
        }

        return list;
    }

}
