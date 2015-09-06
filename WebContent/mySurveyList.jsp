<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>My Surveys</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src='<s:url value="/jquery-1.7.1.js" />' ></script>
		<script type="text/javascript">
			$(function(){
				$("a[href*=delete]").click(function(){
					$(this).attr("disabled","disabled");
				});
			});
		</script>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:if test="mySurveys.isEmpty() == true">You currently don't have any surveys!</s:if >
		<s:else>
			<table>
				<tr>
					<td colspan="10" style="height: 5px"></td>
				</tr>
				<tr>
					<td colspan="10" class="tdHeader">My Survey:</td>
				</tr>
				<tr>
					<td colspan="10" style="height: 5px"></td>
				</tr>
				<tr>
					<td class="tdListHeader">ID</td>
					<td class="tdListHeader">Title</td>
					<td class="tdListHeader">Create Time</td>
					<td class="tdListHeader">Status</td>
					<td class="tdListHeader">Design</td>
					<td class="tdListHeader">Information Collection</td>
					<td class="tdListHeader">Analysis</td>
					<td class="tdListHeader">Opened/Closed</td>
					<td class="tdListHeader">Clean up</td>
					<td class="tdListHeader">Delete</td>
				</tr>
				<s:iterator value="mySurveys" >
					<s:set var="sid" value="id" />
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="titleTxt" /></td>
						<td><s:date name="createTime" format="MM/dd/yy HH:mm" /></td>
						<td>
							<s:if test="closed">Closed</s:if>
							<s:else>Opened</s:else>
						</td>
						<td><s:a action="survey_designSurvey?sid=%{#sid}" namespace="/" cssClass="aList">Design</s:a></td><!-- ?sid=%{#sid} -->
						<td><s:a action="" namespace="/" cssClass="aList">Information Collection</s:a></td>
						<td><s:a action="" namespace="/" cssClass="aList">Analysis</s:a></td>
						<td><s:a action="" namespace="/" cssClass="aList">Open/Close</s:a></td>
						<td><s:a action="" namespace="/" cssClass="aList">Clean up</s:a></td>
						<td><s:a action="survey_deleteSurvey?sid=%{#sid}" namespace="/" cssClass="aList">Delete</s:a></td>
					</tr>
				</s:iterator>
			</table>
		</s:else>
		<s:debug></s:debug>
	</body>
</html>