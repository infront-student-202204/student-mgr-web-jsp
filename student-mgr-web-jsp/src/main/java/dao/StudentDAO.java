package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBUtils;
import common.StringUtils;
import model.ReceiveSubject;
import model.Student;
import model.StudentC;

/**
 * 生徒情報DAO
 * 
 * @author infront
 */
public class StudentDAO {
	
	/**
	 * 生徒IDから生徒、生徒別履修教科のデータ取得メソッド
	 * 
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public Student findById(int studentId) throws Exception {
		
		StudentC studentC = new StudentC();
		studentC.setStudentId(studentId);
		
		List<Student> studentList = findByCondition(studentC);
		if (studentList.size() == 0) {
			return null;
		}
		return studentList.get(0);
	}
	
	/**
	 * 全件検索メソッド
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Student> findAll() throws Exception {

		return findByCondition(new StudentC());
	}
	
	/**
	 * 検索メソッド
	 * 
	 * @param pStudentId 生徒ID
	 * @param pStudentName 生徒名
	 * @param pPrefCd 都道府県コード
	 * @return
	 * @throws Exception
	 */
	public List<Student> findByCondition(StudentC studentC) throws Exception {
		
		// JDBCを利用するための準備
		DBUtils.initJDBC();
		
		Connection conn = null;
		
		//SQLインジェクション脆弱性対応
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		//例外処理
		try {
			// DB接続
			conn = DBUtils.getConnection();
			
			// SQL文構築（StringBuilderは文字列操作を行うための部品）
			StringBuilder sql = new StringBuilder();
			
			// 生徒情報の全件を取得するSQLを生成する
			sql.append("     SELECT s.student_id                  ");
			sql.append("          , s.student_name                ");
			sql.append("          , g.gender_cd                   ");
			sql.append("          , g.gender_name                 ");
			sql.append("          , p.pref_cd                     ");
			sql.append("          , p.pref_name                   ");
			sql.append("          , s.age                         ");
			sql.append("          , s.birthday                    ");
			sql.append("          , sbj.subject_cd                ");
			sql.append("          , sbj.subject_name              ");
			sql.append("       FROM m_student s                   ");
			sql.append(" INNER JOIN t_receive_subject r           ");
			sql.append("         ON s.student_id = r.student_id   ");
			sql.append("  LEFT JOIN m_gender g                    ");
			sql.append("         ON s.gender_cd = g.gender_cd     ");
			sql.append("  LEFT JOIN m_pref p                      ");
			sql.append("         ON s.pref_cd = p.pref_cd         ");
			sql.append("  LEFT JOIN m_subject sbj                 ");
			sql.append("         ON r.subject_cd = sbj.subject_cd ");
			sql.append(" WHERE true ");
			
			// 少し可読性をあげるためにローカル変数化
			Integer condStudentId = studentC.getStudentId();
			String condStudentName = studentC.getStudentName();
			String condPrefCd = studentC.getPrefCd();
			
			if (condStudentId != null) {
				sql.append(" AND s.student_id = ? ");
			}
			if (StringUtils.isNotEmpty(condStudentName)) {
				sql.append(" AND s.student_name like ? ");
			}
			if (StringUtils.isNotEmpty(condPrefCd)) {
				sql.append(" AND p.pref_cd = ? ");
			}
			sql.append("   ORDER BY s.student_id");
			
			// ステートメントオブジェクトを作成する。
			stmt = conn.prepareStatement(sql.toString());
			
			int paramIdx = 1;
			
			if (condStudentId != null) {
				stmt.setInt(paramIdx++, condStudentId);
			}
			if (StringUtils.isNotEmpty(condStudentName)) {
				stmt.setString(paramIdx++, "%" + condStudentName + "%");
			}
			
			if (StringUtils.isNotEmpty(condPrefCd)) {
				stmt.setString(paramIdx++, condPrefCd);
			}
			
			// SELECT文を実行して結果を受け取る。
			rs = stmt.executeQuery();
			
			// studentオブジェクトを生成
			List<Student> studentList = new ArrayList<>();
			
			// ResultSetから1行ずつデータを取り出す。
			while(rs.next()) {
				
				int studentId = rs.getInt("student_id");
				
				// 一つ目の条件：studentList.size() == 0 は一週目
				// 二つ目の条件：studentIdが変わる時
				if (studentList.size() == 0 || studentId != studentList.get(studentList.size() - 1).getStudentId()) {
				
					Student student = new Student();
					student.setStudentId(studentId);
					student.setStudentName(rs.getString("student_name"));
					student.setGenderCd(rs.getString("gender_cd"));
					student.setGenderName(rs.getString("gender_name"));
					student.setPrefCd(rs.getString("pref_cd"));
					student.setPrefName(rs.getString("pref_name"));
					student.setAge(rs.getInt("age"));
					student.setBirthday(rs.getDate("birthday"));
					
					studentList.add(student);
				}
				
				// 履修教科のオブジェクトを生成
				ReceiveSubject receiveSubject = new ReceiveSubject();
				receiveSubject.setStudentId(studentId);
				receiveSubject.setSubjectCd(rs.getString("subject_cd"));
				receiveSubject.setSubjectName(rs.getString("subject_name"));
				
				// 最終行の生徒オブジェクトに履修教科を追加する。
				Student lastStudent = studentList.get(studentList.size() - 1);
				lastStudent.addReceiveSubject(receiveSubject);
			}
			
			return studentList;
		}
		catch (Exception e) {
			System.out.println("findByConditionでエラーが発生しました。");
			DBUtils.handleException(e);
			throw e;
		}
		// 後処理（エラーが起きても実行される）
		finally {
			
			// 結果セットの破棄
			DBUtils.close(rs);
			
			// ステートメントの破棄
			DBUtils.close(stmt);
			
			// DBの切断
			DBUtils.close(conn);
		}
	}
	
	/**
	 * 生徒情報登録
	 * 
	 * @param student
	 * @throws Exception
	 */
	public void insert(Student student) throws Exception {
		
		// JDBCを利用するための準備
		DBUtils.initJDBC();
		
		Connection conn = null;
		
		//例外処理
		try {
			// DB接続
			conn = DBUtils.getConnection();
			
			// シーケンスメソッドで取得した値を代入
			int studentId = getStudentIdFromSeq(conn);
			
			// フィールドの値を更新（カプセル化の話）
			// 生徒IDに自動採番されたidを設定
			// (Studentクラスのセッターメソッドを使って、)
			// (シーケンスでとった値を、StudentクラスのstudentIdにセットする)
			student.setStudentId(studentId);
			
			//生徒テーブルの登録 
			insertStudentTable(conn, student);
			
			// 履修教科分繰り返す
			for (ReceiveSubject rs : student.getReceiveSubjectList()) {
				
				// 履修教科に自動採番されたidを設定
				rs.setStudentId(studentId);
				
				// 履修教科テーブルの登録
				insertReceiveSubjectTable(conn, rs);
			}
			
			System.out.println("INSERTが完了しました。");
			
			// INSERT文をコミットする
			DBUtils.commit(conn);
		}
		catch (Exception e) {
			System.out.println("getStudentIdFromSeqでエラーが発生しました。");
			
			// 例外ハンドリング
			DBUtils.handleException(e);
			
			// エラーが出たので ロールバックする。
			DBUtils.rollback(conn);
			
			throw e;
		}
		// 後処理
		finally {
			
			// DBの切断
			DBUtils.close(conn);
		}
	}
	
	/**
	 * 生徒情報個別登録
	 * 
	 * @param conn
	 * @param student
	 * @throws Exception
	 */
	private void insertStudentTable(Connection conn, Student student) throws Exception {
		
		PreparedStatement stmt = null;
		
		try {
			// SQL文構築（StringBuilderは文字列操作を行うための部品）
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO m_student(   ");
			sql.append("            student_id   ");
			sql.append("          , student_name ");
			sql.append("          , gender_cd    ");
			sql.append("          , pref_cd      ");
			sql.append("          , age          ");
			sql.append("          , birthday     ");
			sql.append(" ) VALUES (              ");
			sql.append("        ?                ");
			sql.append("      , ?                ");
			sql.append("      , ?                ");
			sql.append("      , ?                ");
			sql.append("      , ?                ");
			sql.append("      , ?                ");
			sql.append(" )                       ");
			
			// utll.Dateをsql.Dateに変換
			long timeInMilliSeconds = student.getBirthday().getTime();
			java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
			
			// ステートメントオブジェクトを作成する。
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt   (1, student.getStudentId()  );
			stmt.setString(2, student.getStudentName() );
			stmt.setString(3, student.getGenderCd()    );
			stmt.setString(4, student.getPrefCd()      );
			stmt.setInt   (5, student.getAge()         );
			stmt.setDate  (6, date1                    );
			
			// INSERT文を実行する。
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertStudentTableの実行に失敗しました。");
			throw e;
			
		} finally {
			// ステートメントの破棄
			DBUtils.close(stmt);
		}
	}
	
	/**
	 * 履修教科個別登録
	 * 
	 * @param conn
	 * @param rs
	 * @throws Exception
	 */
	private void insertReceiveSubjectTable(Connection conn, ReceiveSubject rs) throws Exception {
		
		PreparedStatement stmt = null;
		
		// SQL文構築（StringBuilderは文字列操作を行うための部品）
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO t_receive_subject(  ");
		sql.append("            student_id          ");
		sql.append("          , subject_cd          ");
		sql.append(" ) VALUES (                     ");
		sql.append("        ?                       ");
		sql.append("      , ?                       ");
		sql.append(" )                              ");
		
		try {
			// ステートメントオブジェクトを作成する。
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt   (1, rs.getStudentId()        );
			stmt.setString(2, rs.getSubjectCd()        );
			
			// INSERT文を実行する。
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertReceiveSubjectTableの実行に失敗しました。");
			throw e;
			
		} finally {
			// ステートメントの破棄
			DBUtils.close(stmt);
		}
	}
	
	/**
	 * 生徒IDの自動採番
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private int getStudentIdFromSeq(Connection conn) throws Exception {
		
		//SQLインジェクション脆弱性対応
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		//例外処理
		try {
			// SQL文構築（StringBuilderは文字列操作を行うための部品）
			StringBuilder sql1 = new StringBuilder();
			sql1.append("SELECT nextval('student_id_seq') AS student_id");
			
			// ステートメントオブジェクトを作成する
			stmt = conn.prepareStatement(sql1.toString());
			
			// SELECT文を実行して結果を受け取る
			rs = stmt.executeQuery();
			
			// 取得できる値があるかどうか
			if (!rs.next()) {
				throw new SQLException("シーケンスが取得できません： SELECT nextval('student_id_seq') AS student_id ");
			}
			
			return rs.getInt("student_id");
			
		} catch (Exception e) {
			System.out.println("getStudentIdFromSeqでエラーが発生しました。");
			throw e;
			
		} finally {
			
			// 結果セットの破棄
			DBUtils.close(rs);
			
			// ステートメントの破棄
			DBUtils.close(stmt);
		}
	}
}