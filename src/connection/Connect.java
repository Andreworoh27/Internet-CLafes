package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public final class Connect {

	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "InternetCLafes";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection con;
	private Statement st;
	private static Connect connect;

	private Connect() {
		try {
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD); // mencoba membuat hubungan ke url
																				// CONNECTION
			st = con.createStatement(); // menjalankan query
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connect getConnection() {
		return connect = (connect == null) ? new Connect() : connect;
	}

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void executeUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}