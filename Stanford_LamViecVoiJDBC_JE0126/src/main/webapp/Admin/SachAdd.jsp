<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, java.util.ArrayList, vn.com.stanford.je0126dev.workingjdbc.models.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mới hoặc sửa thông tin sách</title>
</head>
<body>
<%
String maSach = "";

String tenSach = "", moTa = "", anhSach = "", tacGia = "", maChuDe = "";
double giaSach = 0;

//Khai báo danh sách chủ đề
List<ChuDe> lstChuDe = new ArrayList<ChuDe>();
ChuDeBusiness chuDeBusiness = new ChuDeBusiness();

lstChuDe = chuDeBusiness.layDanhSach();

if(request.getParameter("id")!= null)
{
	maSach = "" + request.getParameter("id");
	
	SachBusiness sachBusiness = new SachBusiness();
	
	Sach objSach = sachBusiness.layChiTiet(maSach);
	
	if(objSach != null)
	{
		tenSach = objSach.getTenSach();
		anhSach = objSach.getAnhSach();
		moTa = objSach.getMoTa();
		tacGia = objSach.getTacGia();
		giaSach = objSach.getGiaSach();
		maChuDe = objSach.getMaChuDe();
	}
}
%>
<div style="width: 100%; text-align: center;">
<h2>Thêm mới hoặc sửa thông tin sách</h2>
</div>
<form method="post" action="../SachServlet">
<fieldset>
<legend>Nhập thông tin sách</legend>
			<table>
			<tr>
					<td>Mã sách:</td>
					<td><input type="text" name="txtMaSach"
						placeholder="Nhập mã sách" value="<%=maSach %>" />
						</td>
				</tr>
				<tr>
					<td>Tên sách:</td>
					<td><input type="text" name="txtTenSach"
						placeholder="Nhập tên sách" value="<%=tenSach %>" />
						<input type="hidden" name="hSachId" value="<%=maSach %>"/>
						</td>
				</tr>
				<tr>
					<td>Ảnh sách:</td>
					<td><input type="text" name="txtAnhSach"
						placeholder="Nhập ảnh sách" value="<%=anhSach %>" /></td>
				</tr>
				<tr>
					<td>Mô tả:</td>
					<td><textarea name="txtMoTa" rows="5">
					<%=moTa %>
					</textarea>
					</td>
				</tr>
				<tr>
					<td>Giá sách:</td>
					<td><input type="number" name="txtGiaSach" value="<%=giaSach %>" /></td>
				</tr>
				<tr>
					<td>Tác giả:</td>
					<td><input type="text" name="txtTacGia"
						placeholder="Nhập tên tác giả" value="<%=tacGia %>" /></td>
				</tr>
				<tr>
					<td>Chủ đề:</td>
					<td><select name="cboChuDe">
							<option value="">Chọn chủ đề</option>
							<%
							for (ChuDe cd : lstChuDe) {
								if (maChuDe!= null && maChuDe.equals(cd.getMaChuDe())) {
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
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="btnThemMoi" value="Thêm mới" />
					</td>
				</tr>
			</table>
		</fieldset>
</form>
</body>
</html>