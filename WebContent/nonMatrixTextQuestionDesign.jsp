<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Non-Matrix Style Question Design</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:form action="question_saveOrUpdateQuestion.action" method="post">
		<s:hidden name="id" />
		<s:hidden name="questionType" />
		<s:hidden name="pId" />
		<s:hidden name="sid" />
		<table>
			<tr>
				<td colspan="2" class="tdQHeaderL">Non-Matrix Style Question Design:</td>
			</tr>
			<tr>
				<td width="35%" style="text-align: right;">Question:</td>
				<td width="*" style="text-align: left;"><s:textfield name="title" cssClass="text" /></td>
			</tr>
			<tr>
				<td style="text-align: right;"></td>
				<td width="*" style="text-align: left;"><input type="submit" name="ok" value="Submit" class="btn"></td>
			</tr>
		</table>
		</s:form>
	</body>
</html>