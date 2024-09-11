package DTO.NghiepVuNhapKho;

import lombok.*;

import java.util.List;


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
public class NhaCungCapDTO {

    private Integer maNCC;

    private String tenNCC;

    private String email;

    private String soDienThoai;

    private Integer   maKhoHang;


}
