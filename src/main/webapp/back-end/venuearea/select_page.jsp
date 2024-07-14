<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>���]�ϰ�d�ߤνs��</title>

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
   <tr><td><h3>���]�ϰ�d�ߤνs��</h3><h4>( MVC )</h4></td></tr>
</table>

<p>�o�O���]�ϰ�d�ߤνs�誺����</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
        <b>��J���]�ϰ�s�� (�p13):</b>
        <input type="text" name="venueAreaID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="venueAreaSvc" scope="page" class="com.venuearea.model.VenueAreaService" />
   
  <li>
     <FORM METHOD="post" ACTION="/TIA102G5/venueArea/venueArea.do" >
       <b>��ܳ��]�ϰ�s���G</b>
       <select size="1" name="venueAreaID">
         <c:forEach var="venueArea" items="${venueAreaSvc.all}" > 
          <option value="${venueArea.venueAreaID}">${venueArea.venueAreaID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TIA102G5/venueArea/venueArea.do" >
       <b>��ܳ��]�W�١G</b>
       <select size="1" name="venueAreaID">
         <c:forEach var="venueArea" items="${venueAreaSvc.all}" > 
          <option value="${venueArea.venueAreaID}">${venueArea.areaName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���]�ϰ�޲z</h3>

<ul>
  <li><a href='/TIA102G5/back-end/venuearea/addVenueArea.jsp'>Add</a> a new VenueArea.</li>
</ul>

</body>
</html>