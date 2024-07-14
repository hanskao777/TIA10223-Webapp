<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.venuearea.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  VenueArea venueArea = (VenueArea) request.getAttribute("venueArea"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>場館區域資料 - listOneVenueArea.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>場館區域資料 - listOneVenueArea.jsp</h3>
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
	</tr>
	<tr>
		<td><%=venueArea.getVenueAreaID()%></td>
		<td><%=venueArea.getVenueID()%></td>
		<td><%=venueArea.getAreaName()%></td>
		<td><%=venueArea.getAreaPicture()%></td>
	</tr>
</table>

</body>
</html>