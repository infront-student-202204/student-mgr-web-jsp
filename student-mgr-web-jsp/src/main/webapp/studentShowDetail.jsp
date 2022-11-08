<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="common.NumberUtils"%>
<%@ page import="code.GenderCodeMaster"%>
<%@ page import="code.PrefCodeMaster"%>
<%@ page import="code.CodeMaster"%>
<%@ page import="dao.StudentDAO"%>
<%@ page import="model.Student"%>
<%@ page import="code.AgeCodeMaster"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="code.SubjectCodeMaster"%>

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
	// パラメータの受け取り
	//-------------------------------------------------------
	String pStudentId = request.getParameter("studentId");

	//-------------------------------------------------------
	// 検索実行
	//-------------------------------------------------------
	
	// studentIdに紐づく生徒情報を検索
	StudentDAO dao = new StudentDAO();
	Student student = dao.findById(NumberUtils.intValue(pStudentId));
	
	//-------------------------------------------------------
	// コードマスタの準備
	//-------------------------------------------------------
	CodeMaster mstPrefCode = new PrefCodeMaster();
	CodeMaster mstGenderCode = new GenderCodeMaster();
	CodeMaster mstAgeCode = new AgeCodeMaster();
	CodeMaster mstSubjectCode = new SubjectCodeMaster();	
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
<title>生徒情報 詳細画面</title>
</head>
<body>
	
	<p>
		生徒ID：<%= student.getStudentId() %>
	</p>
	
	<p>
		生徒名：<%= student.getStudentName() %>
	</p>
	
	<p>
		都道府県：<%= mstPrefCode.getValue(student.getPrefCd()) %>
	</p>
	
	<p>
		性別：<%= mstGenderCode.getValue(student.getGenderCd()) %>
	</p>
	
	<p>
		年齢：<%= mstAgeCode.getValue(student.getAge()) %>
	</p>
	
	<p>
		生年月日：<%= student.getBirthday() %>
	</p>
	
	<p>
		履修教科：<%= student.getReceiveSubjectNameList() %>
	</p>
	
	<form action="studentShowList.jsp">
		<input type="submit" value="検索一覧">
	</form>
	
</body>
</html>