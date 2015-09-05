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
				<td style="text-align: right;vertical-align: top;">Option:</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="options"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">If there is "Other" Option:</td>
				<td width="*" style="text-align: left;"><s:checkbox name="other" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">"Other" Style:</td>
				<td width="*" style="text-align: left;">
					<s:radio list="#{0:'Null',1:'Text Field',2:'Drop down list'}" listKey="key" listValue="value" name="otherStyle" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">"Other" Option Drop Down List:</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="otherSelectOptions" /></td>
			</tr>
			<tr>
				<td style="text-align: right;"></td>
				<td width="*" style="text-align: left;"><input type="submit" name="ok" value="Submit" class="btn"></td>
			</tr>
		</table>
		</s:form>
		<s:debug></s:debug>
	</body>
</html>