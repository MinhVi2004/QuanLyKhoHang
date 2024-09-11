package DAO.NghiepVuNhapKho;

import DAO.DAOInterface;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PhieuNhapKhoDAO implements DAOInterface<PhieuNhapKhoDTO> {

    @Override
    public ArrayList<PhieuNhapKhoDTO> getAll(Integer maKhoHang) {
        ArrayList<PhieuNhapKhoDTO> danhSachPhieuNhapKho = new ArrayList<>();

        try {
            Connection connection = JDBCConfigure.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `PhieuNhapKho` WHERE `MaKhoHang` = ?");
            statement.setInt(1, maKhoHang); // Thiết lập giá trị cho tham số trong câu truy vấn

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer maPhieu = resultSet.getInt("MaPhieu");
                String dateString = resultSet.getString("NgayNhapKho");
                Long tongGiaTri = resultSet.getLong("TongGiaTri");
                Integer maNCC = resultSet.getInt("MaNCC");
                Integer maKhoHangPhieu = resultSet.getInt("MaKhoHang");
                String trangthai= resultSet.getString("TrangThai");

                PhieuNhapKhoDTO phieuNhapKhoDTO = new PhieuNhapKhoDTO();
                phieuNhapKhoDTO.setMaPhieu(maPhieu);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parsedDateTime = LocalDate.parse(dateString, formatter);
                phieuNhapKhoDTO.setNgayNhapKho(parsedDateTime);
                phieuNhapKhoDTO.setTongGiaTri(tongGiaTri);
                phieuNhapKhoDTO.setMaNCC(maNCC);
                phieuNhapKhoDTO.setMaKhoHang(maKhoHangPhieu);
                phieuNhapKhoDTO.setTrangThai(trangthai);

                danhSachPhieuNhapKho.add(phieuNhapKhoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi truy vấn!!");
        } finally {
            JDBCConfigure.closeConnection();
        }

        return danhSachPhieuNhapKho;
    }


    @Override
    public PhieuNhapKhoDTO getById(Integer id) {
        PhieuNhapKhoDTO phieuNhapKhoDTO = new PhieuNhapKhoDTO();

        try {
            JDBCConfigure.getConnection();
            String query = "SELECT * FROM `PhieuNhapKho` WHERE `MaPhieu` = ?";

            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer maPhieu = resultSet.getInt("MaPhieu");
                String dateString = resultSet.getString("NgayNhapKho");
                Long tongGiaTri = resultSet.getLong("TongGiaTri");
                Integer maNCC = resultSet.getInt("MaNCC");
                Integer maKhoHangPhieu = resultSet.getInt("MaKhoHang");
                String trangthai= resultSet.getString("TrangThai");
                phieuNhapKhoDTO.setMaPhieu(maPhieu);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parsedDateTime = LocalDate.parse(dateString, formatter);
                phieuNhapKhoDTO.setNgayNhapKho(parsedDateTime);
                phieuNhapKhoDTO.setTongGiaTri(tongGiaTri);
                phieuNhapKhoDTO.setMaNCC(maNCC);
                phieuNhapKhoDTO.setMaKhoHang(maKhoHangPhieu);
                phieuNhapKhoDTO.setTrangThai(trangthai);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn!!");
        } finally {
            JDBCConfigure.closeConnection();
        }

        return phieuNhapKhoDTO;
    }

    @Override
    public boolean create(Integer maKhoHang, PhieuNhapKhoDTO phieuNhapKhoDTO) {
        String createPhieuNhapKhoQuery = "INSERT INTO `PhieuNhapKho` " +
            "(`NgayNhapKho`, `TongGiaTri`, `MaNCC`, `MaKhoHang`) " +
            "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(createPhieuNhapKhoQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,
                (phieuNhapKhoDTO.getNgayNhapKho() != null) ?
                    phieuNhapKhoDTO.getNgayNhapKho().toString() : LocalDate.now().toString()
            );

            preparedStatement.setLong(2, phieuNhapKhoDTO.getTongGiaTri());
            preparedStatement.setInt(3, phieuNhapKhoDTO.getMaNCC());
            preparedStatement.setInt(4, maKhoHang);

            preparedStatement.executeUpdate();
            // Lấy ra giá trị MaPhieu của PhieuNhapKho vừa được tạo
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int maPhieu = generatedKeys.getInt(1);
                phieuNhapKhoDTO.setMaPhieu(maPhieu );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean update(PhieuNhapKhoDTO phieuNhapKhoDTO) {
        String updatePhieuNhapKhoQuery =
            "UPDATE `PhieuNhapKho` SET " +
                "`NgayNhapKho` = ?, " +
                "`TongGiaTri` = ?, " +
                "`MaNCC` = ?, " +
                "`TrangThai` = ? " +
                "WHERE `MaPhieu` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(updatePhieuNhapKhoQuery);
            preparedStatement.setString(1, phieuNhapKhoDTO.getNgayNhapKho().toString());
            preparedStatement.setLong(2, phieuNhapKhoDTO.getTongGiaTri());
            preparedStatement.setInt(3, phieuNhapKhoDTO.getMaNCC());
            preparedStatement.setString(4, phieuNhapKhoDTO.getTrangThai());
            preparedStatement.setInt(5, phieuNhapKhoDTO.getMaPhieu());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    public boolean updatePhieuNhapKhoByMaNhaCungCap(Integer maNCC) {
        String updatePhieuNhapKhoQuery =
            "UPDATE `PhieuNhapKho` SET " +
                "`MaNCC` = 1 " +
                "WHERE `MaNCC` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(updatePhieuNhapKhoQuery);
            preparedStatement.setInt(1, maNCC);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }

    @Override
    public boolean delete(PhieuNhapKhoDTO phieuNhapKhoDTO) {
        String deletePhieuNhapKhoQuery = "DELETE FROM `PhieuNhapKho` WHERE `MaPhieu` = ?";

        try {
            PreparedStatement preparedStatement = JDBCConfigure.getConnection().prepareStatement(deletePhieuNhapKhoQuery);
            preparedStatement.setInt(1, phieuNhapKhoDTO.getMaPhieu());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConfigure.closeConnection();
        }

        return true;
    }
        public int maPhieuNhapKhoTiepTheo() {
            try {
                    Statement statement = JDBCConfigure.getConnection().createStatement();
                    ResultSet maPhieuNhapTiepTheo = statement.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'javaswing_database'AND TABLE_NAME = 'phieunhapkho';");
                    while(maPhieuNhapTiepTheo.next()) {
                        return maPhieuNhapTiepTheo.getInt("AUTO_INCREMENT");
                    }
                    return -1;
            } catch (SQLException e) {
                    e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                    return -1;
            }
        }
        public String getTenKhoHang(int maPhieuNhap) {
            try {
                Statement statement = JDBCConfigure.getConnection().createStatement();
                ResultSet phieuNhapKho = statement.executeQuery("SELECT * FROM `phieunhapkho`,`khohang` WHERE phieunhapkho.MaKhoHang = khohang.MaKhoHang and phieunhapkho.MaPhieu = " + maPhieuNhap);
                while(phieuNhapKho.next()) {
                        return phieuNhapKho.getString("TenKhoHang");
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                return null;
            }
    }
    public String getHoTen(int maKhoHang) {
            try {
                Statement statement = JDBCConfigure.getConnection().createStatement();
                ResultSet phieuNhapKho = statement.executeQuery("SELECT * FROM `khohang`,`taikhoan` WHERE khohang.maKhoHang = taikhoan.MaKhoHang and khohang.maKhoHang =" + maKhoHang);
                while(phieuNhapKho.next()) {
                        return phieuNhapKho.getString("HoTen");
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                return null;
            }
    }
    public String getHoTenByMaPhieuNhap(int maPhieuNhap) {
            try {
                Statement statement = JDBCConfigure.getConnection().createStatement();
                ResultSet phieuNhapKho = statement.executeQuery("SELECT * FROM `khohang`,`phieunhapkho`, `taikhoan` where khohang.MaKhoHang = phieunhapkho.MaKhoHang and khohang.MaKhoHang = taikhoan.MaKhoHang and phieunhapkho.MaPhieu =" + maPhieuNhap);
                while(phieuNhapKho.next()) {
                        return phieuNhapKho.getString("HoTen");
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                return null;
            }
    }
    public String getTenNhaCungCap(int maPhieuNhap) {
            try {
                Statement statement = JDBCConfigure.getConnection().createStatement();
                ResultSet phieuNhapKho = statement.executeQuery("SELECT * FROM `phieunhapkho`, `nhacungcap` where phieunhapkho.MaNCC = nhacungcap.MaNCC and phieunhapkho.MaPhieu = " + maPhieuNhap);
                while(phieuNhapKho.next()) {
                        return phieuNhapKho.getString("TenNCC");
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace(); // hoặc xử lý ngoại lệ theo cách phù hợp
                return null;
            }
    }
}