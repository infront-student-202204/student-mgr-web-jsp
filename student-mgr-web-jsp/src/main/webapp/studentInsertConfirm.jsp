<%@page import="common.JSPUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="code.CodeMaster"%>
<%@ page import="code.AgeCodeMaster"%>
<%@ page import="code.SubjectCodeMaster"%>
<%@ page import="code.GenderCodeMaster"%>
<%@ page import="code.PrefCodeMaster"%>
    
<%--
 =========================================================
 データ処理部
 =========================================================
 --%>
<%
	// -------------------------------------------------------
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
	// コードマスタの準備
	//-------------------------------------------------------
	CodeMaster mstPrefCode = new PrefCodeMaster();
	CodeMaster mstGenderCode = new GenderCodeMaster();
	CodeMaster mstSubjectCode = new SubjectCodeMaster();
	CodeMaster mstAgeCode = new AgeCodeMaster();
%>
 
<%--
 =========================================================
 データ表示部

 ※処理部で処理されたデータ（変数）などを表示するところ。
 
 =========================================================
 --%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>生徒情報 登録確認画面</title>
</head>
<body>

	<h4>以下の内容で登録します。</h4>
	
	<p>
		生徒名：<%= pStudentName %>
	</p>
	
	<p>
		都道府県：<%= mstPrefCode.getValue(pPrefCd) %>
	</p>
	
	<p>
		性別：<%= mstGenderCode.getValue(pGenderCd) %>
	</p>
	
	<p>
		年齢：<%= mstAgeCode.getValue(pAge) %>
	</p>
	
	<p>
		生年月日：<%= pBirthday %>
	</p>
	
	<p>履修教科：
		<%
		if(pSubjectCdList != null) { 
			for(String sc : pSubjectCdList) {
				out.println(mstSubjectCode.getValue(sc));
			}
		} else {
			out.println("(未選択)");
		}
		%>
	</p>
	
	<form method="post">
		
		<%=JSPUtils.renderHidden("studentName", pStudentName) %>
		<%=JSPUtils.renderHidden("prefCd", pPrefCd) %>
		<%=JSPUtils.renderHidden("genderCd", pGenderCd) %>
		<%=JSPUtils.renderHidden("age", pAge) %>
		<%=JSPUtils.renderHidden("birthday", pBirthday) %>
		<%=JSPUtils.renderHidden("subjectCd", pSubjectCdList) %>
		
		<input type="submit" value="登録" formaction="studentInsertExecute.jsp"><br>
		<input type="submit" value="戻る" formaction="studentInsertForm.jsp">
	</form>

</body>
</html>