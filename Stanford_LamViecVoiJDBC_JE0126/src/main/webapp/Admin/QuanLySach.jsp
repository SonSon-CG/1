<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, java.util.ArrayList,vn.com.stanford.je0126dev.workingjdbc.models.*"%>
    <!-- Khai báo thư viện sử dụng java tags lib -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý thông tin sách</title>
</head>
<body>
	<%
	String tuKhoa = "", maChuDe = "";
	SachBusiness sachBusiness = new SachBusiness();
	
	//Lấy danh sách sách
	List<Sach> lstSach = new ArrayList<Sach>();
	
	if(request.getParameter("btnTimKiem")!= null)
	{
		tuKhoa = "" + request.getParameter("tuKhoa");
		maChuDe = request.getParameter("cboChuDe");
	}
	
	lstSach = sachBusiness.timKiemSach(tuKhoa, maChuDe);
	
	//Khai báo danh sách chủ đề
	List<ChuDe> lstChuDe = new ArrayList<ChuDe>();
	ChuDeBusiness chuDeBusiness = new ChuDeBusiness();

	lstChuDe = chuDeBusiness.layDanhSach();
	
	%>
	<div style="width: 100%; text-align: center;">
		<h2>Quản lý thông tin sách</h2>
	</div>
	<form method="post">
		<fieldset>
			<legend>Nhập thông tin tìm kiếm</legend>
			<table>
				<tr>
					<td>Từ khóa:</td>
					<td><input type="text" name="tuKhoa"
						placeholder="Nhập từ khóa" value="<%=tuKhoa %>" /></td>
					<td>Chủ đề:</td>
					<td><select name="cboChuDe">
							<option value="">---Tất cả---</option>
							<%
							for (ChuDe cd : lstChuDe) {
								if (maChuDe != null && maChuDe.equals(cd.getMaChuDe())) {
							%>
							<option value="<%=cd.getMaChuDe()%>" selected="selected"><%=cd.getTenChuDe()%></option>
							<%
							} else {
							%>
							<option value="<%=cd.getMaChuDe()%>"><%=cd.getTenChuDe()%></option>
							<%
							}
							}
							%>
					</select></td>
					<td><input type="submit" name="btnTimKiem" value="Tìm kiếm" />
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	<div style="width: 100%; text-align: right;">
		<a href="SachAdd.jsp">Thêm mới</a>
	</div>
	<table border="1" style="width: 100%; border-collapse: collapse;">
		<thead>
			<tr>
				<th>Ảnh sách</th>
				<th>Id</th>
				<th>Tên sách</th>
				<th>Mô tả</th>
				<th>Giá sách</th>
				<th>Tác giả</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="<%=lstSach %>">
			<tr>
				<td><img src="../images/${s.anhSach}" height="120" width="100"/></td>
				<td>${s.maSach}</td>
				<td>${s.tenSach}</td>
				<td>${s.moTa}</td>
				<td>${s.tacGia}</td>
				<td>${s.giaSach}</td>
				<td>
				<a href="SachAdd.jsp?id=${s.anhSach}">Sửa</a>
				&nbsp;
				<a onclick="return confirm('Bạn có chắc chắn muốn xóa không ?');" href="../SachServlet?id=${s.anhSach}">Xóa</a>
				</td>

			</tr>
			</c:forEach>
			</tboby>
	</table>
</body>
</html>