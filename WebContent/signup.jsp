<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Sign Up</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="header.jsp" />
		<s:form action="reg_signup" namespace="/" method="post">
		<table>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="2" class="tdHeader">New User</td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdFormLabel">Email:</td>
				<td class="tdFormControl">
					<s:textfield name="email" cssClass="text" />
					<font class="fonterror"><s:fielderror><s:param>email</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">Password:</td>
				<td class="tdFormControl">
					<s:password name="password" cssClass="text" />
					<font class="fonterror"><s:fielderror><s:param>password</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">Confirm Password:</td>
				<td class="tdFormControl"><s:password name="confirmPassword" cssClass="text" /></td>
			</tr>
			<tr>
				<td class="tdFormLabel">Nickname:</td>
				<td class="tdFormControl">
					<s:textfield name="nickName" cssClass="text" />
					<font class="fonterror"><s:fielderror><s:param>nickName</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl"><s:submit cssClass="btn" value="Submit"/></td>
			</tr>
		</table>
		</s:form>
	</body>
</html>