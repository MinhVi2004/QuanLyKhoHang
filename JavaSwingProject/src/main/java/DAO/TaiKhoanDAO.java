package DAO;

import DTO.NguoiDung.TaiKhoanDTO;
import Others.JDBCConfigure;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TaiKhoanDAO implements DAOAccount<TaiKhoanDTO>{


    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }

        @Override
  public ArrayList<TaiKhoanDTO> getAllAccounts() {
        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<TaiKhoanDTO>();
        try {
            Connection con = JDBCConfigure.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE Quyen = 'User'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Integer maTaiKhoan = rs.getInt("MaTaiKhoan");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                Integer trangThai = rs.getInt("TrangThai");
                String ngayTao = rs.getString("NgayTao");
                String quyen = rs.getString("Quyen");
                String hoVaTen = rs.getString("HoTen");
                String ngaySinh = rs.getString("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                Integer maKhoHang = rs.getInt("MaKhoHang");
                
                TaiKhoanDTO acc = new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, trangThai, ngayTao, quyen, hoVaTen, ngaySinh, gioiTinh, soDienThoai, email, diaChi, maKhoHang);
                ketQua.add(acc);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
  
public ArrayList<TaiKhoanDTO> getAccountsByStatus(int status) {
    ArrayList<TaiKhoanDTO> ketQua = new ArrayList<TaiKhoanDTO>();
    try {
        Connection con = JDBCConfigure.getConnection();
        String sql = "SELECT * FROM TaiKhoan WHERE Quyen = 'User' and TrangThai = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, status);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            Integer maTaiKhoan = rs.getInt("MaTaiKhoan");
            String tenDangNhap = rs.getString("TenDangNhap");
            String matKhau = rs.getString("MatKhau");
            Integer trangThai = rs.getInt("TrangThai");
            String ngayTao = rs.getString("NgayTao");
            String quyen = rs.getString("Quyen");
            String hoVaTen = rs.getString("HoTen");
            String ngaySinh = rs.getString("NgaySinh");
            String gioiTinh = rs.getString("GioiTinh");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String diaChi = rs.getString("DiaChi");
            Integer maKhoHang = rs.getInt("MaKhoHang");
            
            TaiKhoanDTO acc = new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, trangThai, ngayTao, quyen, hoVaTen, ngaySinh, gioiTinh, soDienThoai, email, diaChi, maKhoHang);
            ketQua.add(acc);
        }
        rs.close();
        pst.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ketQua;
}

    @Override
public int insert(TaiKhoanDTO taiKhoanDTO) {
    int result = 0;
        
    try (Connection con = JDBCConfigure.getConnection()) {
        con.setAutoCommit(false);

        try (PreparedStatement pstCheckExistence = con.prepareStatement("SELECT COUNT(*) AS total FROM TaiKhoan WHERE TenDangNhap = ?");
             PreparedStatement pstCheckWarehouse = con.prepareStatement("SELECT COUNT(*) AS total FROM KhoHang WHERE TenKhoHang = ?");
             PreparedStatement pstInsertWarehouse = con.prepareStatement("INSERT INTO KhoHang (TenKhoHang) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstInsertAccount = con.prepareStatement(
                 "INSERT INTO taikhoan (TenDangNhap, MatKhau, TrangThai, Quyen, MaKhoHang, HoTen, NgaySinh, GioiTinh, SoDienThoai, Email, DiaChi) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
        ) {
            pstCheckExistence.setString(1, taiKhoanDTO.getTenDangNhap());
            try (ResultSet rs = pstCheckExistence.executeQuery()) {
                if (rs.next() && rs.getInt("total") > 0) {
                    System.out.println("Tên đăng nhập đã tồn tại.");
                    return 0;
                }
            }
            pstCheckWarehouse.setString(1, "Kho hàng của " + taiKhoanDTO.getTenDangNhap());
            try (ResultSet rs = pstCheckWarehouse.executeQuery()) {
                if (rs.next() && rs.getInt("total") > 0) {
                    System.out.println("Kho hàng đã tồn tại.");
                    return 0;
                }
            }

            pstInsertWarehouse.setString(1, "Kho hàng của " + taiKhoanDTO.getTenDangNhap());
            pstInsertWarehouse.executeUpdate();
            int maKhoHang = 0;
            try (ResultSet rs = pstInsertWarehouse.getGeneratedKeys()) {
                if (rs.next()) {
                    maKhoHang = rs.getInt(1);
                }
            }

            pstInsertAccount.setString(1, taiKhoanDTO.getTenDangNhap());
            pstInsertAccount.setString(2, taiKhoanDTO.getMatKhau());
            pstInsertAccount.setInt(3, 1);
            pstInsertAccount.setString(4, taiKhoanDTO.getQuyen() != null ? taiKhoanDTO.getQuyen() : "User");
            pstInsertAccount.setInt(5, maKhoHang); 
            pstInsertAccount.setString(6, taiKhoanDTO.getHoVaTen() != null ? taiKhoanDTO.getHoVaTen() : "");
            pstInsertAccount.setDate(7, taiKhoanDTO.getNgaySinh() != null ? Date.valueOf(taiKhoanDTO.getNgaySinh()) : null); 
            pstInsertAccount.setString(8, taiKhoanDTO.getGioiTinh() != null ? taiKhoanDTO.getGioiTinh() : "");
            pstInsertAccount.setString(9, taiKhoanDTO.getSoDienThoai() != null ? taiKhoanDTO.getSoDienThoai() : "");
            pstInsertAccount.setString(10, taiKhoanDTO.getEmail() != null ? taiKhoanDTO.getEmail() : "");
            pstInsertAccount.setString(11, taiKhoanDTO.getDiaChi() != null ? taiKhoanDTO.getDiaChi() : "");

    

            result = pstInsertAccount.executeUpdate();

            con.commit();
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return result;
}


    @Override
    public int update(TaiKhoanDTO taiKhoanDTO) {
        int ketQua = 0;
        System.out.println(taiKhoanDTO.getTenDangNhap());
        try {
            Connection con = JDBCConfigure.getConnection();
            String sql = "UPDATE taikhoan SET TenDangNhap = ?, MatKhau = ?,"
                    + " TrangThai = ?, Quyen = ?, HoTen = ?, NgaySinh = ?, "
                    + "GioiTinh = ?, SoDienThoai = ?, Email = ?, DiaChi = ?"
                    + " WHERE MaTaiKhoan = " + taiKhoanDTO.getMaTaiKhoan();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, taiKhoanDTO.getTenDangNhap() != null ? taiKhoanDTO.getTenDangNhap() : "");
            pst.setString(2, taiKhoanDTO.getMatKhau() != null ? taiKhoanDTO.getMatKhau() : "");
            pst.setInt(3, taiKhoanDTO.getTrangThai());
            pst.setString(4, taiKhoanDTO.getQuyen() != null ? taiKhoanDTO.getQuyen() : "User");
            pst.setString(5, taiKhoanDTO.getHoVaTen() != null ? taiKhoanDTO.getHoVaTen() : "");
            pst.setDate(6, taiKhoanDTO.getNgaySinh() != null ? Date.valueOf(taiKhoanDTO.getNgaySinh()) : null);
            pst.setString(7, taiKhoanDTO.getGioiTinh() != null ? taiKhoanDTO.getGioiTinh() : "");
            pst.setString(8, taiKhoanDTO.getSoDienThoai() != null ? taiKhoanDTO.getSoDienThoai() : "");
            pst.setString(9, taiKhoanDTO.getEmail() != null ? taiKhoanDTO.getEmail() : "");
            pst.setString(10, taiKhoanDTO.getDiaChi() != null ? taiKhoanDTO.getDiaChi() : "");

            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(TaiKhoanDTO taiKhoanDTO) {
        int ketQua = 0;
        try {
            Connection con = JDBCConfigure.getConnection();
            String sql = "UPDATE taikhoan SET TrangThai = 0 WHERE MaTaiKhoan = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, taiKhoanDTO.getMaTaiKhoan());
            ketQua = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }


 @Override
public TaiKhoanDTO getUserByUserName(String userName) {
    TaiKhoanDTO acc = null;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        con = JDBCConfigure.getConnection();
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, userName);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            Integer maTaiKhoan = rs.getInt("MaTaiKhoan");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                Integer trangThai = rs.getInt("TrangThai");
                String ngayTao = rs.getString("NgayTao");
                String quyen = rs.getString("Quyen");
                String hoVaTen = rs.getString("HoTen");
                String ngaySinh = rs.getString("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                Integer maKhoHang = rs.getInt("MaKhoHang");
            
            acc = new TaiKhoanDTO(
                maTaiKhoan, tenDangNhap, matKhau, trangThai, ngayTao, quyen, hoVaTen,
                ngaySinh, gioiTinh, soDienThoai, email, diaChi, maKhoHang
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return acc;
}


public ArrayList<TaiKhoanDTO> searchUserName(String userName) {
    ArrayList<TaiKhoanDTO> accounts = new ArrayList<>();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        con = JDBCConfigure.getConnection();
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap LIKE ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, "%" + userName + "%");
        rs = pst.executeQuery();
        
        while (rs.next()) {
                Integer maTaiKhoan = rs.getInt("MaTaiKhoan");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                Integer trangThai = rs.getInt("TrangThai");
                String ngayTao = rs.getString("NgayTao");
                String quyen = rs.getString("Quyen");
                String hoVaTen = rs.getString("HoTen");
                String ngaySinh = rs.getString("NgaySinh");
                String gioiTinh = rs.getString("GioiTinh");
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                Integer maKhoHang = rs.getInt("MaKhoHang");
            
            TaiKhoanDTO account = new TaiKhoanDTO(
                maTaiKhoan, tenDangNhap, matKhau, trangThai, ngayTao, quyen, hoVaTen,
                ngaySinh, gioiTinh, soDienThoai, email, diaChi, maKhoHang
            );
            accounts.add(account);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return accounts;
}

public ArrayList<TaiKhoanDTO> searchByUserNameAndStatus(String userName, int status) {
    ArrayList<TaiKhoanDTO> accounts = new ArrayList<>();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        con = JDBCConfigure.getConnection();
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap LIKE ? AND TrangThai = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, "%" + userName + "%");
        pst.setInt(2, status);
        rs = pst.executeQuery();
        
        while (rs.next()) {
            // Lấy thông tin từ ResultSet
            Integer maTaiKhoan = rs.getInt("MaTaiKhoan");
            String tenDangNhap = rs.getString("TenDangNhap");
            String matKhau = rs.getString("MatKhau");
            Integer trangThai = rs.getInt("TrangThai");
            String ngayTao = rs.getString("NgayTao");
            String quyen = rs.getString("Quyen");
            String hoVaTen = rs.getString("HoTen");
            String ngaySinh = rs.getString("NgaySinh");
            String gioiTinh = rs.getString("GioiTinh");
            String soDienThoai = rs.getString("SoDienThoai");
            String email = rs.getString("Email");
            String diaChi = rs.getString("DiaChi");
            Integer maKhoHang = rs.getInt("MaKhoHang");
            
            // Tạo đối tượng TaiKhoanDTO từ thông tin lấy được
            TaiKhoanDTO account = new TaiKhoanDTO(
                maTaiKhoan, tenDangNhap, matKhau, trangThai, ngayTao, quyen, hoVaTen,
                ngaySinh, gioiTinh, soDienThoai, email, diaChi, maKhoHang
            );
            // Thêm đối tượng vào danh sách kết quả
            accounts.add(account);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return accounts;
}



public int updatePassword(String userName, String password) {
        int ketQua = 0;
        try {
            Connection con = JDBCConfigure.getConnection();
            String sql = "UPDATE Account SET MatKhau = ? WHERE TenDangNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setString(2, userName);

            ketQua = pst.executeUpdate();
            JDBCConfigure.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

}
