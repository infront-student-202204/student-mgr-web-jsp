package model;

/**
 * 生徒別履修教科テーブルに対応したデータ格納クラス
 * 
 * @author infront
 */
public class ReceiveSubject {
	
	// 生徒ID
	private int studentId;
	// 教科コード
	private String subjectCd;
	// 教科名
	private String subjectName;

	/**
	 * デフォルトコンストラクタ
	 */
	public ReceiveSubject() {
	}
	
	/**
	 * 引数ありコンストラクタ
	 * 
	 * @param studentId
	 * @param subjectCd
	 * @param subjectName
	 */
	public ReceiveSubject(int studentId, String subjectCd, String subjectName) {
		this.studentId = studentId;
		this.subjectCd = subjectCd;
		this.subjectName = subjectName;
	}
	
	public ReceiveSubject(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		
		return "生徒ID : " + studentId
				+ ", 教科コード : " + subjectCd 
				+ ", 教科名 : " + subjectName;
	}
	
	// ------------------------------------------------
	// 以下、自動生成
	// ------------------------------------------------
	
	public int getStudentId() {
		return studentId;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public String getSubjectCd() {
		return subjectCd;
	}
	
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}