package vn.com.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.com.dataprovider.DataProvider;
import vn.com.models.MonAn;

public class MonAnBusiness {

    /**
     * Lấy toàn bộ danh sách món ăn
     */
    public List<MonAn> layDanhSach() {
        List<MonAn> lst = new ArrayList<MonAn>();
        String strSQL = "SELECT Id, TenMonAn, MoTa, NoiDung, NgayTao, NgayCapNhat, ImageName, ImagePath, ChuDeId FROM MonAn";
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(strSQL);
            while (rs.next()) {
                MonAn obj = new MonAn();
                obj.setId(rs.getInt("Id"));
                obj.setTenMonAn(rs.getString("TenMonAn"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setNoiDung(rs.getString("NoiDung"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgayCapNhat(rs.getDate("NgayCapNhat"));
                obj.setImageName(rs.getString("ImageName"));
                obj.setImagePath(rs.getString("ImagePath"));
                obj.setChuDeId(rs.getInt("ChuDeId"));
                lst.add(obj);
            }
        } catch (Exception e) {
            System.err.println("Lỗi layDanhSach MonAn: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return lst;
    }

    /**
     * Lấy danh sách món ăn theo chủ đề
     */
    public List<MonAn> layDanhSachTheoChuDe(int chuDeId) {
        List<MonAn> lst = new ArrayList<MonAn>();
        String strSQL = "SELECT Id, TenMonAn, MoTa, NoiDung, NgayTao, NgayCapNhat, ImageName, ImagePath, ChuDeId FROM MonAn WHERE ChuDeId = " + chuDeId;
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(strSQL);
            while (rs.next()) {
                MonAn obj = new MonAn();
                obj.setId(rs.getInt("Id"));
                obj.setTenMonAn(rs.getString("TenMonAn"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setNoiDung(rs.getString("NoiDung"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgayCapNhat(rs.getDate("NgayCapNhat"));
                obj.setImageName(rs.getString("ImageName"));
                obj.setImagePath(rs.getString("ImagePath"));
                obj.setChuDeId(rs.getInt("ChuDeId"));
                lst.add(obj);
            }
        } catch (Exception e) {
            System.err.println("Lỗi layDanhSachTheoChuDe MonAn: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return lst;
    }

    /**
     * Lấy chi tiết 1 món ăn theo Id
     */
    public MonAn layChiTiet(int id) {
        MonAn obj = null;
        String strSQL = "SELECT Id, TenMonAn, MoTa, NoiDung, NgayTao, NgayCapNhat, ImageName, ImagePath, ChuDeId FROM MonAn WHERE Id = " + id;
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(strSQL);
            if (rs.next()) {
                obj = new MonAn();
                obj.setId(rs.getInt("Id"));
                obj.setTenMonAn(rs.getString("TenMonAn"));
                obj.setMoTa(rs.getString("MoTa"));
                obj.setNoiDung(rs.getString("NoiDung"));
                obj.setNgayTao(rs.getDate("NgayTao"));
                obj.setNgayCapNhat(rs.getDate("NgayCapNhat"));
                obj.setImageName(rs.getString("ImageName"));
                obj.setImagePath(rs.getString("ImagePath"));
                obj.setChuDeId(rs.getInt("ChuDeId"));
            }
        } catch (Exception e) {
            System.err.println("Lỗi layChiTiet MonAn: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return obj;
    }

    /**
     * Thêm mới món ăn
     */
    public boolean themMoi(MonAn obj) {
        String strSQL = "INSERT INTO MonAn(TenMonAn, MoTa, NoiDung, NgayTao, NgayCapNhat, ImageName, ImagePath, ChuDeId) VALUES(?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            PreparedStatement stm = conn.prepareStatement(strSQL);
            stm.setString(1, obj.getTenMonAn());
            stm.setString(2, obj.getMoTa());
            stm.setString(3, obj.getNoiDung());
            stm.setDate(4, new java.sql.Date(obj.getNgayTao().getTime()));
            stm.setDate(5, new java.sql.Date(obj.getNgayCapNhat().getTime()));
            stm.setString(6, obj.getImageName());
            stm.setString(7, obj.getImagePath());
            stm.setInt(8, obj.getChuDeId());
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Lỗi themMoi MonAn: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    /**
     * Cập nhật món ăn
     */
    public boolean capNhat(MonAn obj) {
        String strSQL = "UPDATE MonAn SET TenMonAn=?, MoTa=?, NoiDung=?, NgayCapNhat=?, ImageName=?, ImagePath=?, ChuDeId=? WHERE Id=?";
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            PreparedStatement stm = conn.prepareStatement(strSQL);
            stm.setString(1, obj.getTenMonAn());
            stm.setString(2, obj.getMoTa());
            stm.setString(3, obj.getNoiDung());
            stm.setDate(4, new java.sql.Date(obj.getNgayCapNhat().getTime()));
            stm.setString(5, obj.getImageName());
            stm.setString(6, obj.getImagePath());
            stm.setInt(7, obj.getChuDeId());
            stm.setInt(8, obj.getId());
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Lỗi capNhat MonAn: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }

    /**
     * Xóa món ăn theo Id
     */
    public boolean xoa(int id) {
        String strSQL = "DELETE FROM MonAn WHERE Id = ?";
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            PreparedStatement stm = conn.prepareStatement(strSQL);
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Lỗi xoa MonAn: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return false;
    }
}