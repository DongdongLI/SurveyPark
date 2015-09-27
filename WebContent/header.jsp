<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="divOuterFrame">
	<div class="divInnerFrame">Welcome to Survey Park</div>
</div>
<div class="divWhiteLine"></div>
<div class="divNavigatorOuterFrame">
	<div class="divNavigatorInnerFrame">
		<s:a action="login_toPage" namespace="/">[Home]</s:a>&nbsp;
		<s:a action="survey_newSurvey" namespace="/">[New Survey]</s:a>&nbsp;
		<s:a action="survey_mySurveys" namespace="/">[My Survey]</s:a>&nbsp;
		<s:a action="engageSurvey_findAllAvailableSurveys" namespace="/">[Take a Survey]</s:a>&nbsp;
		<s:a action="reg_toPage" namespace="/">[Sign Up]</s:a>&nbsp;
		<s:a namespace="/" action="UserAuthorizeAction_findAllUsers">[User Management]</s:a>&nbsp;
		<s:a namespace="/" action="RoleAction_findAllRoles">[Role Management]</s:a>&nbsp;
		<s:a namespace="/" action="RightAction_findAllRights">[Privilege Management]</s:a>&nbsp;
		<s:a namespace="/" action="LogAction_findNearestLogs">[Log Management]</s:a>&nbsp;
	</div>
</div>
<div class="divWhiteLine"></div>