import entity.DcmLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/image";
        String username = "root";
        String password = "US3igroup2017";
//        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insert(DcmLog dcmLog) {
        Connection conn = getConn();
        int i = 0;
        String sql = "INSERT INTO dcm_log (dcm_name,dcm_age,dcm_sex,dcm_date,dcm_time,dcm_lr,dcm_cc,patient_Name) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, dcmLog.getDcmName());
            pstmt.setString(2, dcmLog.getDcmAge());
            pstmt.setInt(3, dcmLog.getDcmSex());
            pstmt.setString(4,dcmLog.getDcmDate());
            pstmt.setString(5,dcmLog.getDcmTime());
            pstmt.setInt(6,dcmLog.getDcmLR());
            pstmt.setString(7,dcmLog.getDcmCC());
            pstmt.setString(8,dcmLog.getPatientName());
            try {
                i = pstmt.executeUpdate();
            }catch (Exception e){
                if(e.toString().contains("dcm_name")) {
                    System.out.println("数据重复写入失败");
                }else{
                    System.out.println(e);
                }
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
