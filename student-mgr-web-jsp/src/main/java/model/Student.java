package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import code.SubjectCodeMaster;

/**
 * 生徒テーブルに対応したデータ格納用のクラス
 * 
 * @author infront
 */
public class Student {
	
	// 生徒ID
	private int studentId;
	// 生徒名
	private String studentName;
	// 性別コード
	private String genderCd;
	// 性別
	private String genderName;
	// 都道府県CD
	private String prefCd;
	// 都道府県
	private String prefName;
	// 年齢
	private int age;
	// 生年月日
	private Date birthday;
	// 履修教科リスト
	private List<ReceiveSubject> receiveSubjectList = new ArrayList<>();
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "生徒ID : " + this.studentId
				+ ", 生徒名 : " + this.studentName
				+ ", 性別コード : " + this.genderCd
				+ ", 性別 : " + this.genderName
				+ ", 都道府県ID : " + this.prefCd
				+ ", 都道府県 : " + this.prefName
				+ ", 年齢 : " + this.age
				+ ", 生年月日 : " + this.birthday
				+ ", 履修教科 : " + this.receiveSubjectList.toString();
	}
	
	/**
	 * 教科名のリストをスペース区切りで取得
	 * @return 教科名のリスト
	 */
	public String getReceiveSubjectNameList() {
		
		SubjectCodeMaster mstSubjectCode = new SubjectCodeMaster();  //※ほんとはここでインスタンス化するのは望ましくない
		
		StringBuilder result = new StringBuilder();
		for(ReceiveSubject rs : this.receiveSubjectList) {
			
			result.append(mstSubjectCode.getValue(rs.getSubjectCd()));
			result.append(" ");
		}
		
		return result.toString();
	}

	/**
	 * 履修教科の追加
	 * 
	 * @param rs
	 */
	public void addReceiveSubject(ReceiveSubject rs) {
		
		rs.setStudentId(this.studentId);
		this.receiveSubjectList.add(rs);
	}
	
	// ------------------------------------------------
	// 以下、自動生成
	// ------------------------------------------------
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGenderCd() {
		return genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getPrefCd() {
		return prefCd;
	}

	public void setPrefCd(String prefCd) {
		this.prefCd = prefCd;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<ReceiveSubject> getReceiveSubjectList() {
		return receiveSubjectList;
	}

	public void setReceiveSubjectList(List<ReceiveSubject> receiveSubjectList) {
		this.receiveSubjectList = receiveSubjectList;
	}
}