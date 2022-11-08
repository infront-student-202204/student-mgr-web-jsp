package model;

/**
 * 生徒情報検索クラス
 * 
 * @author infront
 */
public class StudentC {
	
	/* セッションキー */
	public static final String SESSION_KEY = "SESSION_KEY_STUDENT_C";

	/* 生徒ID */
	private Integer studentId;
	
	/* 生徒名 */
	private String studentName;
	
	/* 都道府県コード */
	private String prefCd;

	// ------------------------------------------------
	// 以下、自動生成
	// ------------------------------------------------
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPrefCd() {
		return prefCd;
	}

	public void setPrefCd(String prefCd) {
		this.prefCd = prefCd;
	}
}
