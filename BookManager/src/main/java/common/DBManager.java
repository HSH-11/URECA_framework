package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


// Connection Pool로 부터 Connection 객체를 가져온다.
// Connection 객체 반납
public class DBManager {

	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context context = new InitialContext();
			// JNDI를 통해 톰캣에 등록된 커넥션 풀 객체를 가져오는 것
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/madang");
			con = ds.getConnection(); // 풀에서 연결 하나를 꺼내는 것이다.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		return con;
    }
	
	// Connection Pool로부터 얻는 Connection 객체의 close()메소드는 overriding되어 있다.
	// Connection을 끊지 않고 Connection Pool에 반납되도록 한다.
	public static void releaseConnection(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
