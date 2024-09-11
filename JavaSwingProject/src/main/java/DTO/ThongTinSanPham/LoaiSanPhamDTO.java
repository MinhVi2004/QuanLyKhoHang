package DTO.ThongTinSanPham;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
public class LoaiSanPhamDTO {

    private Integer maLoaiSanPham;

    private String tenLoaiSanPham;

    private Integer   maKhoHang;

    public LoaiSanPhamDTO(String tenLoaiSanPham, Integer maKhoHang) {
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.maKhoHang = maKhoHang;
    }

    
    

}
