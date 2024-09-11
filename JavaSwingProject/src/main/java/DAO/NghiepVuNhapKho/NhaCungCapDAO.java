package DAO.NghiepVuNhapKho;

import DAO.DAOInterface;
import DTO.NghiepVuNhapKho.NhaCungCapDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.util.ArrayList;

public class NhaCungCapDAO implements DAOInterface<NhaCungCapDTO> {

    @Override
    public ArrayList<NhaCungCapDTO> getAll(Integer maKhoHang) {
        ArrayList<NhaCungCapDTO> danhSachNhaCungCap = new ArrayList<>();

        try{
            Connection connection = JDBCConfigure.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `NhaCungCap` WHERE `MaKhoHang` = ?");
            statement.setInt(1, maKhoHang);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");

                NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);

                danhSachNhaCungCap.add(nhaCungCapDTO);
            }
        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return danhSachNhaCungCap;
    }

    public NhaCungCapDTO getNhaCungCapMacDinh(){

        try{
            Connection connection = JDBCConfigure.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `NhaCungCap` WHERE `MaNCC` = 1");


            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");

                NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);
                return nhaCungCapDTO;

            }
        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }
        return null;

    }



    public ArrayList<NhaCungCapDTO> search(Integer maKhoHang, String searchValue) {
        ArrayList<NhaCungCapDTO> danhSachNhaCungCap = new ArrayList<>();

        try{
            Connection connection = JDBCConfigure.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `NhaCungCap` WHERE `MaKhoHang` = ? AND `TenNCC` LIKE ?");
            statement.setInt(1, maKhoHang);
            statement.setString(2, "%" + searchValue + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");

                NhaCungCapDTO nhaCungCapDTO = new NhaCungCapDTO();
                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);

                danhSachNhaCungCap.add(nhaCungCapDTO);
            }
        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return danhSachNhaCungCap;
    }

    @Override
    public NhaCungCapDTO getById(Integer id) {
        NhaCungCapDTO nhaCungCapDTO = null;
        try{
            JDBCConfigure.getConnection();
            String query = "SELECT * FROM `NhaCungCap` " +
                            "WHERE `MaNCC` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                nhaCungCapDTO =  new NhaCungCapDTO();
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");
                Integer maKhoHang = resultSet.getInt("MaKhoHang");


                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);
                nhaCungCapDTO.setMaKhoHang(maKhoHang);

            }

        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return nhaCungCapDTO;
    }

    public boolean isTenNCCExists(Integer maKhoHang, String tenNCC){
        String createNhaCungCapQuery =
            "SELECT * FROM `NhaCungCap`" +
                "WHERE `TenNCC` = ? AND `MaKhoHang` = ?;";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createNhaCungCapQuery);
            preparedStatement.setString(1, tenNCC);
            preparedStatement.setInt(2, maKhoHang);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCConfigure.closeConnection();
        }
    }

    public NhaCungCapDTO getNhaCungCapByTenNCC(Integer maKhoHang, String tenNcc){
        NhaCungCapDTO nhaCungCapDTO = null;
        try{
            JDBCConfigure.getConnection();
            String query = "SELECT * FROM `NhaCungCap` " +
                            "WHERE `TenNCC` = ? AND" +
                            "`MaKhoHang` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);

            preparedStatement.setString(1, tenNcc);
            preparedStatement.setInt(2, maKhoHang);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                nhaCungCapDTO = new NhaCungCapDTO();
                Integer maNCC = resultSet.getInt("MaNCC");
                String tenNCC = resultSet.getString("TenNCC");
                String email = resultSet.getString("Email");
                String sdt = resultSet.getString("SoDienThoai");
                Integer maKhoHangSQL = resultSet.getInt("MaKhoHang");


                nhaCungCapDTO.setMaNCC(maNCC);
                nhaCungCapDTO.setTenNCC(tenNCC);
                nhaCungCapDTO.setEmail(email);
                nhaCungCapDTO.setSoDienThoai(sdt);
                nhaCungCapDTO.setMaKhoHang(maKhoHangSQL);

            }

        }
        catch (SQLException e){
            System.err.println("Lỗi truy vấn !!");
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return nhaCungCapDTO;
    }

    @Override
    public boolean create(Integer maKhoHang, NhaCungCapDTO nhaCungCapDTO) {

        String createNhaCungCapQuery =
            "INSERT INTO `NhaCungCap` (`TenNCC`, `SoDienThoai`, `Email`, `MaKhoHang`) VALUES" +
                                     "(?       ,    ?          ,    ?  ,        ?   )";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createNhaCungCapQuery);
            preparedStatement.setString(1, nhaCungCapDTO.getTenNCC());
            preparedStatement.setString(2, nhaCungCapDTO.getSoDienThoai());
            preparedStatement.setString(3, nhaCungCapDTO.getEmail());
            preparedStatement.setInt   (4, maKhoHang);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean update(NhaCungCapDTO nhaCungCapDTO) {
        String updateNhaCungCapQuery = "UPDATE `NhaCungCap` SET " +
            "`TenNCC` = ?, `SoDienThoai` = ?, `Email` = ?, `MaKhoHang` = ? WHERE `MaNCC` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(updateNhaCungCapQuery);
            preparedStatement.setString(1, nhaCungCapDTO.getTenNCC());
            preparedStatement.setString(2, nhaCungCapDTO.getSoDienThoai());
            preparedStatement.setString(3, nhaCungCapDTO.getEmail());
            preparedStatement.setInt(4, nhaCungCapDTO.getMaKhoHang());
            preparedStatement.setInt(5, nhaCungCapDTO.getMaNCC());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }
    }

    @Override
    public boolean delete(NhaCungCapDTO nhaCungCapDTO) {
        String deleteNhaCungCapQuery = "DELETE FROM `NhaCungCap` WHERE `MaNCC` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(deleteNhaCungCapQuery);
            preparedStatement.setInt(1, nhaCungCapDTO.getMaNCC());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }
    }

}