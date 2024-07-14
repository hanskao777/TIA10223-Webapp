<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>場館區域查詢及編輯</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>場館區域查詢及編輯</h3><h4>( MVC )</h4></td></tr>
</table>

<p>這是場館區域查詢及編輯的頁面</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='/TIA102G5/back-end/venuearea/listAllVenueArea.jsp'>List</a> all VenueAreas.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/TIA102G5/venueArea/venueArea.do" >
        <b>輸入場館區域編號 (如13):</b>
        <input type="text" name="venueAreaID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="venueAreaSvc" scope="page" class="com.venuearea.model.VenueAreaService" />
   
  <li>
     <FORM METHOD="post" ACTION="/TIA102G5/venueArea/venueArea.do" >
       <b>選擇場館區域編號：</b>
       <select size="1" name="venueAreaID">
         <c:forEach var="venueArea" items="${venueAreaSvc.all}" > 
          <option value="${venueArea.venueAreaID}">${venueArea.venueAreaID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TIA102G5/venueArea/venueArea.do" >
       <b>選擇場館名稱：</b>
       <select size="1" name="venueAreaID">
         <c:forEach var="venueArea" items="${venueAreaSvc.all}" > 
          <option value="${venueArea.venueAreaID}">${venueArea.areaName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>場館區域管理</h3>

<ul>
  <li><a href='/TIA102G5/back-end/venuearea/addVenueArea.jsp'>Add</a> a new VenueArea.</li>
</ul>

</body>
</html>