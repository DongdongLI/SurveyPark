<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Select Question Type</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:form action="question_toDesignQuestionPage.action" method="post">
		<s:hidden name="pId" />
		<s:hidden name="sid" />
		<table>
			<tr>
				<td colspan="2">
					<select name="questionType" onchange="this.form.submit();">
						<option selected="selected">--Please choose question Style--</option>
						<option value="0">Horizontal Single Choice</option>
						<option value="1">Vertical Single Choice</option>
						<option value="2">Horizontal Multiple Choice</option>
						<option value="3">Vertical Multiple Choice</option>
						<option value="4">Drop Dropdown List</option>
						<option value="5">Text Field</option>
						<option value="6">Matrix Single Choice</option>
						<option value="7">Matrix Multiple Choice</option>
						<option value="8">Matrix Dropdown List</option>
					</select>
				</td>
			</tr>
		</table>
		</s:form>
		<s:debug></s:debug>
	</body>
</html>