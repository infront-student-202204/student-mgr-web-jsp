package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBユーティリティ（DB操作するための便利なクラス）
 * 
 * @author infront
 */
public class DBUtils {

	/** 接続URL */
	private static final String DB_CONN_URL = "jdbc:postgresql://localhost:5432/student-mgr-db";
	
	/** ユーザーID */
	private static final String DB_CONN_USER = "student-mgr";
	
	/** パスワード*/
	private static final String DB_CONN_PW = "student-mgr";
	
	/**
	 * JDBCを利用するための準備
	 * 
	 * @throws ClassNotFoundException JDBCドライバが見つからないエラー
	 */
	public static void initJDBC() throws ClassNotFoundException {

		try {
			Class.forName("org.postgresql.Driver");
		}
		// ClassNotFoundException：部品が見つからないエラー
		catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバの読み込みに失敗しました。");
			throw e;
		}
	}

	/**
	 * DB接続
	 * 
	 * @return コネクション
	 * @throws SQLException DB接続エラー
	 */
	public static Connection getConnection() throws SQLException {

		try {
			Connection conn = DriverManager.getConnection(
					  DB_CONN_URL
					, DB_CONN_USER
					, DB_CONN_PW
			);

			// 自動コミットを解除する。
			conn.setAutoCommit(false);
			
			System.out.println("DBに接続しました。(自動コミット=false)");
			return conn;

		} catch (SQLException e) {
			System.out.println("DB接続に失敗しました。");
			throw e;
		}

	}
	
	/**
	 * コミット
	 * 
	 * @param conn
	 * @throws Exception
	 */
	public static void commit(Connection conn) throws SQLException {
		if (conn != null) {
			conn.commit();
			System.out.println("コミットが完了しました。");
		}
	}
	
	/**
	 * ロールバック
	 * 
	 * @param conn
	 * @throws Exception
	 */
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
				System.out.println("ロールバックを行いました。");
				
			} catch (SQLException e) {
				handleException(e);
			}
		}
	}
	
	/**
	 * 例外ハンドリング
	 * 
	 * @param e
	 */
	public static void handleException(Exception e) {
		
		System.out.println("エラーが発生しました。");
		if (e instanceof SQLException) {
			SQLException sqle = (SQLException) e;
			System.out.println("ERROR_MESSAGE:" + sqle.getMessage());
			System.out.println("SQL_STATE:" + sqle.getSQLState());
			System.out.println("ERROR_CODE:" + sqle.getErrorCode());
		}
		e.printStackTrace();
	}

	/**
	 * オブジェクトのクローズ
	 * 
	 * @param closeable AutoCloseableを実装しているオブジェクト
	 */
	public static void close(AutoCloseable closeable) {
		// オブジェクトのクローズ。
		try {
			if(closeable != null) {
				closeable.close();
				System.out.println(closeable.getClass().getSimpleName() +  "のクローズが完了しました。");
			}
		} catch (Exception e) {
			handleException(e);
		}
	}
}
