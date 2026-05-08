<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Khai báo thư viện sử dụng java tags lib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Làm việc với Tags Lib</title>
</head>
<%
String khoaHoc = "Java Web  for Developer";
%>
<body>
<h2><c:out value="Làm việc với Tags Lib"/></h2>
<h3>
<c:out value="<%=khoaHoc %>"/>
</h3>
<c:set value="dangbq" var="username"/>
<p>
Tên đăng nhập là: <c:out value="${username}"/>
</p>
<jsp:useBean id="cag" class="vn.com.stanford.je0126dev.workingjdbc.models.ChuDe" scope="session"/>
<c:set target="${cag}" property="maChuDe" value="TH"/>
<c:set target="${cag}" property="tenChuDe" value="Tin học"/>
Mã chủ đề: <c:out value="${cag.maChuDe}"/><br/>
Tên chủ đề: <c:out value="${cag.tenChuDe}"/><br/>
<c:if test="${cag.maChuDe ==\"TH\"}">
Đây là chủ đề tin học
</c:if>
<form method="post">
<input type="text" name="tuoi"/><br/>
<input type="submit" name="btnThucHien" value="Thực hiện"/><br/>
<c:choose>
	<c:when test="${empty param.tuoi}">
	Bạn cần nhập tuổi
	</c:when>
	<c:when test="${param.tuoi < 13}">
		Bạn còn nhỏ
	</c:when>
	<c:when test="${param.tuoi > 13 && param.tuoi < 60}">
		Bạn còn trẻ
	</c:when>
	<c:otherwise>
		Bạn đã già
	</c:otherwise>
</c:choose>

<h3>Bảng số thứ tự</h3>
<table border=1>
<tr>
<th>STT</th>
<th>Họ tên</th>
</tr>
<tbody>
<c:forEach var="x" begin="1" end="20">
	<tr>
		<td>${x}</td>
		<td>Nguyễn Văn A ${x}</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
</body>
</html>