<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.venuearea.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    VenueAreaService venueAreaSvc = new VenueAreaService();
    List<VenueArea> list = venueAreaSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有場館區域資料 - listAllVenueArea.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有場館區域資料 - listAllVenueArea.jsp</h3>
		 <h4><a href="/TIA102G5/back-end/venuearea/select_page.jsp"><img src="/TIA102G5/back-end/venuearea/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>場館區域編號</th>
		<th>場館編號</th>
		<th>場館區域名稱</th>
		<th>場館圖片</th>
<!-- 		<th>薪水</th> -->
<!-- 		<th>獎金</th> -->
<!-- 		<th>部門</th> -->
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="venueArea" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${venueArea.venueAreaID}</td>
			<td>${venueArea.venueID}</td>
			<td>${venueArea.areaName}</td>
			<td>${venueArea.areaPicture}</td>
<%-- 			<td>${venueArea.hiredate}</td> --%>
<%-- 			<td>${empVO.sal}</td> --%>
<%-- 			<td>${empVO.comm}</td>  --%>
<%-- 			<td>${empVO.deptno}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/venueArea/venueArea.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="venueAreaID"  value="${venueArea.venueAreaID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/venueArea/venueArea.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="venueAreaID"  value="${venueArea.venueAreaID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>