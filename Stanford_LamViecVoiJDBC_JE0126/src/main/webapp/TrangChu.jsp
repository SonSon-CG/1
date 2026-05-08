<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, java.util.ArrayList,vn.com.stanford.je0126dev.workingjdbc.models.*"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ - BookStore</title>
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />
</head>
<%
SachBusiness sachBusiness = new SachBusiness();

//Lấy danh sách sách
List<Sach> lstSach = new ArrayList<Sach>();

lstSach = sachBusiness.layDanhSach();
%>
<body>
<body>
<!-- Templates from www.stanford.com.vn -->
<div id="templatemo_container">
	<jsp:include page="layout/menu.jsp"/>
    
    <jsp:include page="layout/header.jsp"/>
    
    <div id="templatemo_content">
    	
         <jsp:include page="layout/nav.jsp"/>
        
        <div id="templatemo_content_right">
        <c:forEach var="s" varStatus="status" items="<%=lstSach %>">
        	<div class="templatemo_product_box">
            	<h1>${s.tenSach}<span>(by ${s.tacGia})</span></h1>
   	      <img src="images/${s.anhSach}" alt="image" height="150" width="100" />
                <div class="product_info">
                	<p>${s.moTa}</p>
                  <h3>${s.giaSach}</h3>
                    <div class="buy_now_button"><a href="subpage.html">Mua ngay</a></div>
                    <div class="detail_button"><a href="subpage.html">Chi tiết</a></div>
                </div>
                <div class="cleaner">&nbsp;</div>
            </div>
            <c:if test="${status.index %2 == 0}">
            	<div class="cleaner_with_width">&nbsp;</div>
            </c:if>
            <c:if test="${status.index %2 != 0}">
            	<div class="cleaner_with_height">&nbsp;</div>
            </c:if>
            
           </c:forEach> 
            
            <a href="subpage.html"><img src="images/templatemo_ads.jpg" alt="ads" /></a>
        </div> <!-- end of content right -->
    
    	<div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->
    <jsp:include page="layout/footer.jsp"/>
    
</body>
</html>