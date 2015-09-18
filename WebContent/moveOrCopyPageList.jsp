<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Move&Copy Page</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td colspan="3" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="3" class="tdHeader">Move/Copy page:[it would be copy within the same survey,but copy between different surveys]</td>
			</tr>
			<tr>
				<td colspan="3" class="tdWhiteLine"></td>
			</tr>
			<s:iterator var="s" value="mySurveys">
				<s:set var="sid" value="#s.id" />
				<tr>
					<td colspan="3" class="tdSHeaderL"><s:property value="title" /></td>
				</tr>
				<s:iterator var="p" value="#s.pages" status="st">
					<s:set var="pId" value="#p.id"/>
					<!-- highlight -->
					<s:if test="#pId == srcPid">
						
						<s:set var="bgcolor" value='"rgb(200,125,200)"' />
					</s:if>
					<s:else>
						<s:set var="bgcolor" value='"bgcolor=\"white\""' />
					</s:else>
					<tr bgcolor='<s:property value="#bgcolor"/>'>
						<td style="width:30px;border-width:0;background-color: white"></td>
						<td><s:property value="#p.title" /></td>
						<td>
							<s:if test="#pId != srcPid">
								<s:form name="form%{#pId}" action="moveOrCopyPageAction_doMoveOrCopy" method="post">
									<s:hidden name="srcPid" />
									<s:hidden name="targPid" value="%{#pId}" />
									<!-- when it is done, will redirect to the target survey -->
									<s:hidden name="sid" value="%{#sid}" />
									<s:radio list="#{0:'Before this page',1:'After this page'}" listKey="key" listValue="value" name="pos"/>
									<input type="submit" class="btn" value="Done">
								</s:form>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</s:iterator>
		</table>
	</body>
	<s:debug></s:debug>
</html>