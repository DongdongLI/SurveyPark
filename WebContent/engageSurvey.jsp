<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Engage Survey</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css"/>'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:form action="engageSurvey_doEngageSurvey" method="post" >
			<s:hidden name="curPid" value="%{curPage.id}" />
			<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<!-- title of the survey -->
					<td colspan="2" class="tdHeader"><s:property value="#session.current_survey.titleTxt" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<!-- title of the page -->
					<td colspan="2" class="tdPHeaderL"><s:property value="curPage.title" /></td>
				</tr>
				<tr>
					<td width="30px"></td>
					<td>
						<table>
							<!-- traverse the question set -->
							<s:iterator var="q" value="curPage.questions">
								<!-- create var to keep question id -->
								<s:set var="qId" value="#q.id" />
								<!-- create var to keep question type -->
								<s:set var="qt" value="#q.questionType" />
								<tr>
									<td class="tdQHeaderL"><s:property value="#q.title"/></td>
								</tr>
								<tr>
									<td class="tdOptionArea">
										<!-- check if the question type is among 1 to 4 -->
										<s:if test='#qt lt 4'>
											<s:iterator var="option" value="#q.optionArr" status="st">
												<input type='<s:property value="#qt<2?'radio':'checkbox'" />'
													   name='q<s:property value="#qId" />'
													   value='<s:property value="#st.index" />'
													   <s:property value="setTag('q' + #qId,#st.index,'checked')" />
													   >
												<s:property />
												<s:if test="#qt == 1 || #qt == 3"><br></s:if>
											</s:iterator>
											<!-- check if it has 'other' options -->
											<s:if test="#q.other">
												<input type='<s:property value="#qt<2?'radio':'checkbox'" />' 
														name='q<s:property value="#qId"/>' 
														value="other"
														<s:property value="setTag('q' + #qId,'other','checked')" />
														>Other
												<s:if test="#q.otherStyle == 1">
													<input type="text" 
															class="text" 
															name='q<s:property value="#qId"/>other'
															<s:property value="setText('q' + #qId + 'other')" />
															>
												</s:if>
												<!-- other option style: drop list -->
												<s:elseif test="#q.otherStyle == 2">
													<select name='q<s:property value="#qId"/>other'>
														<s:iterator var="option" value="#q.otherSelectOptionArr" status="optst">
															<option value='<s:property value="#optst.index" />'
																	<s:property value="setTag('q' + #qId+'other',#optst.index,'selected')" />
																	><s:property /></option>
														</s:iterator>
													</select>
												</s:elseif>
											</s:if>
										</s:if>
										
										<!-- drop list -->
										<s:if test="#qt == 4">
											<select name='q<s:property value="#qId" />'>
												<s:iterator var="option" value="#q.optionArr" status="optst">
													<option value='<s:property value="#optst.index" />'
															<s:property value="setTag('q' + #qId,#optst.index,'selected')" />
															><s:property /></option>
												</s:iterator>
											</select>
										</s:if>
										
										<!-- text area -->
										<s:if test="#qt == 5">
											<input type="text" 
												name='q<s:property value="#qId" />'
												<s:property value="setText('q' + #qId)" />
												>
										</s:if>
										
										<!-- matrix style -->
										<s:if test='#qt> 5'>
											<table>
												<tr>
													<td></td>
													<s:iterator var="col" value="#q.matrixColTitleArr">
														<td><s:property value="#col" /></td>
													</s:iterator>
												</tr>
												<!-- traverse the row title  -->
												<s:iterator var="row" value="#q.matrixRowTitleArr" status="rowst">
													<tr>
														<td><s:property value="#row" /></td>
														<!-- print the component -->
														<s:iterator var="col" value="#q.matrixColTitleArr" status="colst">
															<td>
																<!-- matrix single choice -->
																<s:if test="#qt == 6">
																	<input type="radio" 
																			name='q<s:property value="#qId+'_' + #rowst.index" />' 
																			value='<s:property value="#rowst.index + '_' + #colst.index"/>'
																			<s:property value="setTag('q' + #qId+'_' + #rowst.index,#rowst.index + '_' + #colst.index,'checked')" />
																			>
																</s:if>
																<!--  matrix multiple choice -->
																<s:elseif test="#qt == 7">
																	<input type="checkbox" 
																		name='q<s:property value="#qId" />' 
																		value='<s:property value="#rowst.index+'_' +#colst.index"/>'
																		<s:property value="setTag('q' + #qId,#rowst.index + '_' + #colst.index,'checked')" />
																		>
																</s:elseif>
																<!-- matrix drop list -->
																<s:elseif test="#qt == 8">
																	<select name='q<s:property value="#qId"/>'>
																		<s:iterator var="option" value="#q.matrixSelectOptionArr" status="optst">
																			<option value='<s:property value="#rowst.index+'_'+#colst.index+'_'+#optst.index"/>'
																					<s:property value="setTag('q' + #qId,#rowst.index + '_' + #colst.index+'_' +#optst.index,'selected')" />
																					><s:property value="#option"/></option>
																		</s:iterator>
																	</select>
																</s:elseif>
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
				<tr>
					<td colspan="2" align="center">
						<!-- build "previous" button -->
						<s:if test="curPage.orderNo != #session.current_survey.minOrderNo">
							<input type="submit" name='submit_pre' value='<s:property value="#session.current_survey.prevTxt"/>' class="btn">
						</s:if>
						<!-- build "next" button -->
						<s:if test="curPage.orderNo != #session.current_survey.maxOrderNo">
							<input type="submit" name='submit_next' value='<s:property value="#session.current_survey.nextTxt"/>' class="btn">
						</s:if>
						<!-- build "finish" button -->
						<s:if test="curPage.orderNo == #session.current_survey.maxOrderNo">
							<input type="submit" name="submit_done" value='<s:property value="#session.current_survey.doneTxt"/>' class="btn">
						</s:if>
						<input type="submit" name="submit_exit" value='<s:property value="#session.current_survey.exitTxt"/>' class="btn">
					</td>
				</tr>
			</table>
			</s:form>
	</body>
	<s:debug></s:debug>
</html>