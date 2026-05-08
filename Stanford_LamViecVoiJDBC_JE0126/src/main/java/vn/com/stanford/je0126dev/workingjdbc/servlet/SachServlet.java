package vn.com.stanford.je0126dev.workingjdbc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.stanford.je0126dev.workingjdbc.models.SachBusiness;
import vn.com.stanford.je0126dev.workingjdbc.models.Sach;

import java.io.IOException;
import java.util.Date;

/**
 * Servlet implementation class SachServlet
 */
@WebServlet("/SachServlet")
public class SachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SachBusiness sachBusiness = new SachBusiness();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SachServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sachId = "";
		sachId = request.getParameter("id");
		if(!sachId.isEmpty()) {
			boolean ketQua = sachBusiness.xoa(sachId);
			if(ketQua)
			{
				response.sendRedirect("Admin/QuanLySach.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Khai báo biến
				request.setCharacterEncoding("utf-8");
				
				String maSach = "", tenSach = "", moTa = "", anhSach = "", tacGia = "", maChuDe = "";
				double giaSach = 0;
				
				//Lấy thông tin trên giao diện
				tenSach = request.getParameter("txtTenSach");
				moTa = request.getParameter("txtMoTa");
				anhSach = request.getParameter("txtAnhSach");
				giaSach = Integer.parseInt(request.getParameter("txtGiaSach"));
				tacGia = request.getParameter("txtTacGia");
				
				maSach = "" + request.getParameter("hSachId");
				maChuDe = "" + request.getParameter("cboChuDe");
				
				boolean isInsert = true;
				Sach objSach = null;

				objSach = new Sach();
				if(!maSach.isEmpty())//TH sửa
				{
					isInsert = false;
				}
				else //Thêm mới
				{
					maSach = "" + request.getParameter("txtMaSach");
				}

				objSach.setMaSach(maSach);
				objSach.setTenSach(tenSach);
				objSach.setMoTa(moTa);
				objSach.setGiaSach(giaSach);
				objSach.setAnhSach(anhSach);
				objSach.setTacGia(tacGia);
				objSach.setMaChuDe(maChuDe);
				boolean ketQua = false;
				if(isInsert)
				{
					objSach.setNgayTao(new Date());
					objSach.setNgayCapNhat(new Date());
					ketQua = sachBusiness.themMoi(objSach);
				}
				else
				{
					objSach.setNgayCapNhat(new Date());
					ketQua = sachBusiness.capNhat(objSach);
				}
				
				if(ketQua)
				{
					response.sendRedirect("Admin/QuanLySach.jsp");
				}
	}

}
