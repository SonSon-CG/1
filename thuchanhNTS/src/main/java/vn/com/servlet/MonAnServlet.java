package vn.com.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.com.business.ChuDeBusiness;
import vn.com.business.MonAnBusiness;
import vn.com.models.ChuDe;
import vn.com.models.MonAn;

@WebServlet("/monan")
public class MonAnServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    MonAnBusiness monAnBusiness = new MonAnBusiness();
    ChuDeBusiness chuDeBusiness = new ChuDeBusiness();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {

            
            case "index":
            default: {
                List<ChuDe> lstChuDe = chuDeBusiness.layDanhSach();
                req.setAttribute("lstChuDe", lstChuDe);
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
                break;
            }

          
            case "chude": {
                int chuDeId = Integer.parseInt(req.getParameter("id"));
                ChuDe chuDe = chuDeBusiness.layChiTiet(chuDeId);
                List<MonAn> lstMonAn = monAnBusiness.layDanhSachTheoChuDe(chuDeId);
                req.setAttribute("chuDe", chuDe);
                req.setAttribute("lstMonAn", lstMonAn);
                req.getRequestDispatcher("/views/chude.jsp").forward(req, resp);
                break;
            }

           
            case "chitiet": {
                int id = Integer.parseInt(req.getParameter("id"));
                MonAn monAn = monAnBusiness.layChiTiet(id);
                ChuDe chuDe = chuDeBusiness.layChiTiet(monAn.getChuDeId());
                req.setAttribute("monAn", monAn);
                req.setAttribute("chuDe", chuDe);
                req.getRequestDispatcher("/views/monan.jsp").forward(req, resp);
                break;
            }

            
            case "them": {
                List<ChuDe> lstChuDe = chuDeBusiness.layDanhSach();
                req.setAttribute("lstChuDe", lstChuDe);
                req.setAttribute("monAn", new MonAn());
                req.setAttribute("mode", "them");
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
                break;
            }

           
            case "sua": {
                int id = Integer.parseInt(req.getParameter("id"));
                MonAn monAn = monAnBusiness.layChiTiet(id);
                List<ChuDe> lstChuDe = chuDeBusiness.layDanhSach();
                req.setAttribute("monAn", monAn);
                req.setAttribute("lstChuDe", lstChuDe);
                req.setAttribute("mode", "sua");
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
                break;
            }

         
            case "xoa": {
                int id = Integer.parseInt(req.getParameter("id"));
                monAnBusiness.xoa(id);
                resp.sendRedirect(req.getContextPath() + "/monan");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {

          
            case "them": {
                MonAn obj = new MonAn();
                obj.setTenMonAn(req.getParameter("tenMonAn"));
                obj.setMoTa(req.getParameter("moTa"));
                obj.setNoiDung(req.getParameter("noiDung"));
                obj.setNgayTao(new Date());
                obj.setNgayCapNhat(new Date());
                obj.setImageName(req.getParameter("imageName"));
                obj.setImagePath(req.getParameter("imagePath"));
                obj.setChuDeId(Integer.parseInt(req.getParameter("chuDeId")));
                monAnBusiness.themMoi(obj);
                resp.sendRedirect(req.getContextPath() + "/monan");
                break;
            }

            
            case "sua": {
                MonAn obj = new MonAn();
                obj.setId(Integer.parseInt(req.getParameter("id")));
                obj.setTenMonAn(req.getParameter("tenMonAn"));
                obj.setMoTa(req.getParameter("moTa"));
                obj.setNoiDung(req.getParameter("noiDung"));
                obj.setNgayCapNhat(new Date());
                obj.setImageName(req.getParameter("imageName"));
                obj.setImagePath(req.getParameter("imagePath"));
                obj.setChuDeId(Integer.parseInt(req.getParameter("chuDeId")));
                monAnBusiness.capNhat(obj);
                resp.sendRedirect(req.getContextPath() + "/monan");
                break;
            }

            default:
                resp.sendRedirect(req.getContextPath() + "/monan");
        }
    }
}