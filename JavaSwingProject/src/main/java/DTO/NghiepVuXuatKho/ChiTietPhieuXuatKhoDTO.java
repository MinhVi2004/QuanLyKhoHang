package DTO.NghiepVuXuatKho;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
/************************************************************
 * @Data = @Setter + @Getter + @RequiredArgsConstructor + .....
 *          @Setter: Lombok tự động viết toàn bộ Setter cho class
 *          @Getter: Lombok tự động viết toàn bộ Getter cho class
 *          @RequiredArgsConstructor: Dùng để tạo các constructor có các tham số chỉ định
 *              Sử dụng @NonNull để chỉ định trường đặc biệt
 *******************************************/
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietPhieuXuatKhoDTO {
      
    private Integer maSanPham;

    private Integer donGia;

    private Integer maPhieu;

    private Integer soLuong;

    private Integer thanhTien;

}
