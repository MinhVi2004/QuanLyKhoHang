package Others;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConfigure {
    public static Connection connection = null;

    public static Connection getConnection() {
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\dvmv2\\OneDrive\\Documents\\Nam_2\\Java\\QuanLyKhoHang\\Java-Swing-main-7-5-10g30\\Java-Swing-main\\JavaSwingProject\\src\\main\\java\\Others\\mysql.properties"));
            String username= properties.getProperty("user");
            String password= properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            Class.forName(driver);

            connection = DriverManager.getConnection(url,username,password);
           if (connection != null){
               System.out.println("Kết nối Database  Thành Công !!!");
           }
           else {
               System.out.println("Kết nối Database Không Thành Công !! Mời Kiểm Tra Lại");
           }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return connection;
    }
    public static void closeConnection(){
        if (connection!=null){
            try{
                connection.close();
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

}