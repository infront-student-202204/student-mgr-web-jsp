<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="code.PrefCodeMaster"%>
<%@ page import="code.GenderCodeMaster"%>
<%@ page import="code.SubjectCodeMaster"%>
<%@ page import="code.AgeCodeMaster"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="code.CodeMaster"%>
<%@ page import="common.JSPUtils"%>

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
	// パラメータの受け取り（パラメータのPを頭文字につける。）
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
	// ※パッケージインポートの自動生成は、
	// クラスにカーソルを当ててCtrl + Space
	//-------------------------------------------------------
	CodeMaster mstPrefCode = new PrefCodeMaster();
	CodeMaster mstGenderCode = new GenderCodeMaster();
	CodeMaster mstSubjectCode = new SubjectCodeMaster();
	CodeMaster mstAgeCode = new AgeCodeMaster();
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
<title>生徒情報 登録画面</title>
</head>
<body>

	<form action="studentInsertConfirm.jsp" method="post">
		
		<p>
			生徒名：<%=JSPUtils.renderText("studentName", pStudentName) %>
		</p>
		
		<p>
			都道府県：<%=JSPUtils.renderSelect("prefCd", mstPrefCode.getMap(), pPrefCd) %>
		</p>
		
		<p>
			性別：<%=JSPUtils.renderRadio("genderCd", mstGenderCode.getMap(), pGenderCd) %>
			
		</p>
		
		<p>
			年齢：<%=JSPUtils.renderSelect("age", mstAgeCode.getMap(), pAge) %>
		</p>
		
		<p>
			生年月日：<%=JSPUtils.renderDate("birthday", pBirthday) %>
		</p>
		
		<p>
			履修教科：<%=JSPUtils.renderCheckbox("subjectCd", mstSubjectCode.getMap(), pSubjectCdList) %>
		</p>
		
		<p>
			<input type="submit" value="登録確認">
		</p>
		
	</form>
	
</body>
</html>