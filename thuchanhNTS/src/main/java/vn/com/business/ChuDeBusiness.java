package vn.com.business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.com.dataprovider.DataProvider;
import vn.com.models.ChuDe;

public class ChuDeBusiness {

    /**
     * Lấy toàn bộ danh sách chủ đề
     */
    public List<ChuDe> layDanhSach() {
        List<ChuDe> lstChuDe = new ArrayList<ChuDe>();
        String strSQL = "SELECT Id, TenChuDe, MoTa FROM ChuDe";
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(strSQL);
            while (rs.next()) {
                ChuDe obj = new ChuDe();
                obj.setId(rs.getInt("Id"));
                obj.setTenChuDe(rs.getString("TenChuDe"));
                obj.setMoTa(rs.getString("MoTa"));
                lstChuDe.add(obj);
            }
        } catch (Exception e) {
            System.err.println("Lỗi layDanhSach ChuDe: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return lstChuDe;
    }

    /**
     * Lấy chi tiết 1 chủ đề theo Id
     */
    public ChuDe layChiTiet(int id) {
        ChuDe obj = null;
        String strSQL = "SELECT Id, TenChuDe, MoTa FROM ChuDe WHERE Id = " + id;
        Connection conn = null;
        try {
            conn = DataProvider.ketNoi();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(strSQL);
            if (rs.next()) {
                obj = new ChuDe();
                obj.setId(rs.getInt("Id"));
                obj.setTenChuDe(rs.getString("TenChuDe"));
                obj.setMoTa(rs.getString("MoTa"));
            }
        } catch (Exception e) {
            System.err.println("Lỗi layChiTiet ChuDe: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return obj;
    }
}