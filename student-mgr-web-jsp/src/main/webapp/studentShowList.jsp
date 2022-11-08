<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.StudentC"%>
<%@ page import="common.JSPUtils"%>
<%@ page import="common.NumberUtils"%>
<%@ page import="common.StringUtils"%>
<%@ page import="code.CodeMaster"%>
<%@ page import="code.PrefCodeMaster"%>
<%@ page import="dao.StudentDAO"%>
<%@ page import="model.Student"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="code.SubjectCodeMaster"%>
<%@ page import="code.GenderCodeMaster"%>
<%@ page import="code.AgeCodeMaster"%>

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
	String pStudentName = request.getParameter("studentName");
	String pPrefCd = request.getParameter("prefCd");
	String pOnSearchBtn = request.getParameter("onSearchBtn");

	//-------------------------------------------------------
	// 検索処理
	//-------------------------------------------------------

	// 検索条件
	StudentC studentC = null;
	
	// 生徒一覧リスト
	List<Student> studentList = null;
	
	// ボタンの押下判定 
	if(pOnSearchBtn != null) { 
		
		studentC = new StudentC();
		studentC.setStudentName(pStudentName);
		studentC.setPrefCd(pPrefCd);
		
		// 検索条件をセッションに保持
		session.setAttribute(StudentC.SESSION_KEY, studentC);
	}
	
	// 検索条件がセッションに保持されているなら検索する
	studentC = (StudentC) session.getAttribute(StudentC.SESSION_KEY);
	if (studentC != null) {
		
		StudentDAO dao = new StudentDAO();
		studentList = dao.findByCondition(studentC);
		
		// 入力項目に検索内容を表示させる
		pStudentName = studentC.getStudentName();
		pPrefCd = studentC.getPrefCd();
	}
	
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
<title>生徒情報 検索画面</title>
</head>
<body>
	
	<form action="studentShowList.jsp" method="post">
		
		<p>
			生徒名：<%=JSPUtils.renderText("studentName", pStudentName) %>
		</p>
		
		<p>
			都道府県：<%=JSPUtils.renderSelect("prefCd", mstPrefCode.getMap(), pPrefCd) %>
		</p>
		
		<p>
			<input type="submit" value="検索" name="onSearchBtn">
		</p>
		
		<% if(studentList != null) { %>
			
			<table border = "1">
				
				<tr>
					<th>生徒ID</th>
					<th>生徒名</th>
					<th>都道府県</th>
					<th>性別</th>
					<th>年齢</th>
					<th>生年月日</th>
					<th>履修教科</th>
				</tr>
				
				<% for(Student student : studentList) { %>
					<tr>
						<td>
							<a href="studentShowDetail.jsp?studentId=<%= student.getStudentId() %>">
							<%= student.getStudentId() %></a>
						</td>
						
						<td><%= student.getStudentName() %></td>
						<td><%= mstPrefCode.getValue(student.getPrefCd()) %></td>
						<td><%= mstGenderCode.getValue(student.getGenderCd()) %></td>
						<td><%= mstAgeCode.getValue(student.getAge()) %></td>
						<td><%= student.getBirthday() %></td>
						<td><%= student.getReceiveSubjectNameList() %></td>
					</tr>
					
				<% } %>
				
			</table>
			
		<% } %>
		
	</form>
	
	<form action="studentInsertForm.jsp">
		<input type="submit" value="新規登録">
	</form>	
	
</body>
</html>