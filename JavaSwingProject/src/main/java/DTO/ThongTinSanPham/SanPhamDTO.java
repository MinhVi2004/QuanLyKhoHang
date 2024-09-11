package DTO.ThongTinSanPham;

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
public class SanPhamDTO {
      private Integer   maSanPham;

      private String    tenSanPham;

      private String    xuatXu;

      private Integer   giaSanPham;

      private Integer   soLuongConLai;

      private Boolean   trangThai;

      private String    anhMinhhoa;

      private Integer   maLoaiSanPham;

      private Integer   maKhoHang;
      
      public SanPhamDTO(String tenSanPham, String xuatXu, Integer giaSanPham, Integer soLuongConLai, Boolean trangThai, String anhMinhHoa, Integer maLoaiSanPham, Integer maKhoHang){
          this.tenSanPham=tenSanPham;
          this.xuatXu=xuatXu;
          this.giaSanPham=giaSanPham;
          this.soLuongConLai=soLuongConLai;
          this.trangThai=trangThai;
          this.anhMinhhoa=anhMinhHoa;
          this.maLoaiSanPham=maLoaiSanPham;
          this.maKhoHang=maLoaiSanPham;
      }

}
