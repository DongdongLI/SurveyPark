<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Survey Design</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src="<s:url value="/jquery-1.7.1.js" />"></script>
		<script type="text/javascript">
			$(function(){
				$("a[href*=delete]").click(function(){
					return confirm("Delete this item?");
				});				
			});
		</script>
	</head>
	<body>
		<s:include value="header.jsp" />
		<s:set var="sid" value="sid" />
		
		<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader">Design Survey</td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td class="tdSHeaderL">
						<s:if test="photoExists()">
							<img src="<s:url value="%{logoPhotoPath}" />" height="25px" width="50px">
						</s:if>
						<!-- title of the survey -->
						<s:property value="titleTxt" />
					</td>
					<td class="tdSHeaderR">
						<s:a namespace="/" action="survey_toAddLogoPage?sid=%{#sid}">Add Logo</s:a>&nbsp;<!-- sid or sId -->
						<s:a action="survey_editSurvey?sid=%{#sid}" namespace="/">Modify Survey</s:a>&nbsp;
						<s:a namespace="/" action="page_addPage?sid=%{#sid}">Add Page</s:a>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;vertical-align: top;">
						<table>
							<tr>
								<td width="30px"></td>
								<td width="*">
									<table>
										<!-- page list -->
										<s:iterator var="p" value="pages">
										<s:set var="pId" value="#p.id" />
										
										<tr>
											<td>
												<table>
													<tr>
														<!-- page title -->
														<td class="tdPHeaderL"><s:property value="#p.title" /></td>
														<td class="tdPHeaderR">
															<s:a namespace="/" action="page_editPage?sid=%{#sid}&pId=%{#pId}">Modify Page Title</s:a>&nbsp;
															<s:a namespace="/" action="moveOrCopyPageAction_toSelectTargetPage?srcPid=%{#pId}">Copy or Move Page</s:a>&nbsp;
															<s:a namespace="/" action="question_toSelection?sid=%{#sid}&pId=%{#pId}">Add Question</s:a>&nbsp;
															<s:a namespace="/" action="page_deletePage?sid=%{#sid}&pId=%{#pId}">Delete Page</s:a>&nbsp;
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table>
													<tr>
														<td width="30px"></td>
														<td width="*">
															<table>
																<tr>
																	<td>
																		<table>
																			<!-- problem set -->
																			<s:iterator var="q" value="#p.questions">
<%-- 																			<s:property value="#p.questions.size"/> --%>
																			<s:set var="qId" value="#q.id" />
																			<tr>
																				<!-- the question -->
																				<td class="tdQHeaderL"><s:property value="#q.title" /></td>
																				<td class="tdQHeaderR">
																					<s:a namespace="/" action="question_editQuestion?qId=%{#qId}&sid=%{#sid}&pId=%{#pId}">Modify Question</s:a>&nbsp;
																					<s:a namespace="/" action="question_deleteQuestion?qId=%{#qId}&sid=%{#sid}">Delete Question</s:a>&nbsp;
																				</td>
																			</tr>
																			<tr>
																				<td colspan="2" style="text-align: left;color: black;background-color: white">
																					<!-- based on questionType -->
																					<s:set var="qt" value="#q.questionType" />
																					<!-- in the first big type -->
																					<s:if test='#qt lt 4'>
																						<s:iterator value="#q.optionArr">
																							<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'><s:property />
																							<s:if test="#qt == 1 || #qt == 3"><br></s:if>
																						</s:iterator>
																						<!-- if there is other -->
																						<s:if test="#q.other">
																							<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'>Other
																							<!-- text -->
																							<s:if test="#q.otherStyle == 1">
																								<input type="text">
																							</s:if>
																							<!--  select -->
																							<s:elseif test="#q.otherStyle == 2">
																								<select>
																									<s:iterator value="#q.otherSelectOptionArr" >
																										<option><s:property /></option>
																									</s:iterator>
																								</select>
																							</s:elseif>
																						</s:if>
																					</s:if>
																					
																					<!-- select -->
																					<s:if test="#qt == 4">
																						<select>
																							<s:iterator value="#q.optionArr" >
																								<option><s:property /></option>
																							</s:iterator>
																						</select>
																					</s:if>
																					<!-- text -->
																					<s:if test="#qt == 5">
																						<input type="text">
																					</s:if>
																					
																					<!-- matrix(6,7,8) -->
																					<s:if test="#qt > 5">
																						<table>
																							<!-- header -->
																							<tr>
																								<td></td>
																								<s:iterator value="#q.matrixColTitleArr">
																									<td><s:property /></td>
																								</s:iterator>
																							</tr>
																							<!-- n more rows -->
																							<s:iterator value="#q.matrixRowTitleArr">
																								<tr>
																									<td><s:property /></td>
																									<s:iterator value="#q.matrixColTitleArr">
																										<td>
																											<!-- radio -->
																											<s:if test="#qt == 6"><input type="radio"></s:if>
																											<s:if test="#qt == 7"><input type="checkbox"></s:if>
																											<s:if test="#qt == 8">
																												<select>
																													<s:iterator value="#q.matrixSelectOptionArr">
																														<option><s:property /></option>
																													</s:iterator>
																												</select>
																											</s:if>
																										</td>
																									</s:iterator>
																								</tr>
																							</s:iterator>
																						</table>
																					</s:if>
																				</td>
																			</tr>
																			</s:iterator>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<s:debug>ewfhwiu</s:debug>
	</body>
</html>