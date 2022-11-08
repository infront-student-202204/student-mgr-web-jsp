<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.StudentDAO"%>
<%@ page import="model.Student"%>
<%@ page import="model.ReceiveSubject"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="common.DateUtils"%>

<%--
 =========================================================
 データ処理部
 =========================================================
 --%>
 <%
	//-------------------------------------------------------
 	// パラメータのエンコーディング
 	// -------------------------------------------------------
 	request.setCharacterEncoding("UTF-8");
 	
 	//-------------------------------------------------------
 	// パラメータの受け取り（変数名はパラメータのPを頭文字につける。）
 	//-------------------------------------------------------
 	
 	// 生徒名を取得する
 	String pStudentName = request.getParameter("studentName");
 	
 	// 都道府県CDを取得する
 	String pPrefCd = request.getParameter("prefCd");
 	
 	// 性別を取得する
 	String pGenderCd = request.getParameter("genderCd");
 	
 	// 年齢を取得する
 	String pAge = request.getParameter("age");
 	
 	// 生年月日を取得する
 	String pBirthday = request.getParameter("birthday");
 	
 	// 履修教科を取得する
 	// ※getParameterValues : [名前」に対応する「値」を全て取得することが出来る。
 	String[] pSubjectCdList = request.getParameterValues("subjectCd");
 	
 	//-------------------------------------------------------
 	// 生徒情報登録
 	//-------------------------------------------------------
 	boolean success = true;
 	try {
 		// 生徒オブジェクトの生成
 		Student student = new Student();
		student.setStudentName(pStudentName);
		student.setGenderCd(pGenderCd);
		student.setPrefCd(pPrefCd);
		student.setAge(Integer.parseInt(pAge));
		student.setBirthday(DateUtils.parseDate(pBirthday));
		
		// 履修教科の設定
		for(String sc : pSubjectCdList) {
			student.addReceiveSubject(new ReceiveSubject(sc));
		}
		
		// 生徒情報登録実行
 		StudentDAO dao = new StudentDAO();
 		dao.insert(student);
 		
 		success = true;
 	}
 	catch (Exception e) {
 		
 		success = false;
 	}
 %>
 
<%--
 =========================================================
 データ表示部
 =========================================================
 --%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>生徒情報 登録完了画面</title>
</head>
<body>
	<% if (success) { %>
		<h4>生徒情報の登録を完了しました。</h4>
	<% } else { %>
		<h4>生徒情報の登録に失敗しました。</h4>
	<% } %>
</body>
</html>