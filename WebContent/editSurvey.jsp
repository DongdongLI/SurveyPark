<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Edit Survey</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td class="tdHeader">Edit Survey Title:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
								<s:form action="survey_updateSurvey" namespace="/" method="post">
								<s:hidden name="sid" />
								<s:hidden name="id"/>
<%-- 								<s:hidden name="uid" value="${user.id}" /> --%>
								<table>
									<tr>
										<td class="tdFormLabel">Survey Title:</td>
										<td class="tdFormControl"><s:textfield name="titleTxt" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"Next Step" text:</td>
										<td class="tdFormControl"><s:textfield name="nextTxt" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"Previous Step" text:</td>
										<td class="tdFormControl"><s:textfield name="prevTxt" cssClass="text" /></td>
									</tr>
<!-- 									<tr> -->
<!-- 										<td class="tdFormLabel">"Finish" text:</td> -->
<%-- 										<td class="tdFormControl"><s:textfield name="doneText" cssClass="text" /></td> --%>
<!-- 									</tr> -->
									<tr>
										<td class="tdFormLabel">"Exit" text:</td>
										<td class="tdFormControl"><s:textfield name="exitTxt" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl"><s:submit value="Submit" cssClass="btn" /></td>
									</tr>
								</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:debug></s:debug>
	</body>
</html>