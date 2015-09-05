<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Add/Modify Page</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td class="tdHeader">Add/Modify Page:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
								<s:form action="page_saveOrUpdate" namespace="/" method="post">
								<s:hidden name="id" />
								<s:hidden name="sid" />
								<s:hidden name="orderno" />
								<table>
									<tr>
										<td class="tdFormLabel">Page Title:</td>
										<td class="tdFormControl"><s:textfield name="title" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">Page Description:</td>
										<td class="tdFormControl"><s:textarea name="description" cssClass="text" cols="20" rows="8"/></td>
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