package DTO.NguoiDung;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaiKhoanDTO {

    private Integer maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private Integer trangThai;
    private String ngayTao;
    private String quyen;
    private String hoVaTen;
    private String ngaySinh;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private Integer maKhoHang;
}
