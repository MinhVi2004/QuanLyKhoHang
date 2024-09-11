package Others;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilServices {
   public static String convertToDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }

    public static String convertFromDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }

    public static String convertFromDateNew(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }

    public static String convertToDateNew(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }


    public static boolean isValidDate2(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Tắt chế độ linh hoạt

        try {
            // Kiểm tra định dạng ngày
            Date date = sdf.parse(dateStr);

            // Kiểm tra xem chuỗi đã được parse thành ngày hợp lệ chưa
            if (!dateStr.equals(sdf.format(date))) {
                return false;
            }

            // Kiểm tra ngày thực tế
            Date currentDate = new Date();
            if (date.after(currentDate)) {
                return false;
            }

            return true;
        } catch (ParseException e) {
            System.err.println("Ngày đầu vào không hợp lệ: " + e.getMessage());
            return false;
        }
    }

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Tắt chế độ linh hoạt

        try {
            // Kiểm tra định dạng ngày
            Date date = sdf.parse(dateStr);

            // Kiểm tra xem chuỗi đã được parse thành ngày hợp lệ chưa
            if (!dateStr.equals(sdf.format(date))) {
                return false;
            }

            // Kiểm tra ngày thực tế
            Date currentDate = new Date();
            if (date.after(currentDate)) {
                return false;
            }

            return true;
        } catch (ParseException e) {
            System.err.println("Ngày đầu vào không hợp lệ: " + e.getMessage());
            return false;
        }
    }

    public static boolean isValidDateRange(String dateStr1, String dateStr2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Tắt chế độ linh hoạt

        try {
            // Parse chuỗi thành ngày
            Date date1 = sdf.parse(dateStr1);
            Date date2 = sdf.parse(dateStr2);

            // Kiểm tra xem dateStr1 có nhỏ hơn dateStr2 không
            if (date1.after(date2)) {
                return false;
            }

            // Tính khoảng cách thời gian (milliseconds)
            long diff = Math.abs(date2.getTime() - date1.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);

            // Kiểm tra xem khoảng cách có ít nhất 1 ngày không
            return diffDays >= 1;
        } catch (ParseException e) {
            System.err.println("Ngày đầu vào không hợp lệ: " + e.getMessage());
            return false; // Nếu có lỗi, trả về false
        }
    }

    public static String formatVND(Number number) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number) + " VNĐ";
    }



}
