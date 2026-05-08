package vn.com.stanford.je0126dev.workingjdbc.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SachBusiness {

	/**
	 * Hàm lấy danh sách thông tin sách từ db
	 * @return
	 */
	public List<Sach> layDanhSach()
	{
		List<Sach> lstSach = new ArrayList<Sach>();
		
		//Khai báo câu lệnh truy vấn
		String strSQL = "Select MaSach, TenSach, MoTa, AnhSach, GiaSach, TacGia, NgayTao, NgayDuyet, MaChuDe from Sach";
		
		//Khai báo kết nối
		Connection conn = null;
		
		try
		{
			conn = DataProvider.ketNoi();
			
			//Khai báo công việc
			Statement stm = conn.createStatement();
			
			//Lấy kết quả
			ResultSet rs = stm.executeQuery(strSQL);
			
			//Đọc từng thông tin lấy được
			Sach objSach;
			while(rs.next())
			{
				objSach = new Sach();
				
				//Gán giá trị cho các thuộc tính
				objSach.setMaSach(rs.getString("MaSach"));
				objSach.setTenSach(rs.getString("TenSach"));
				objSach.setMoTa(rs.getString("MoTa"));
				objSach.setAnhSach(rs.getString("AnhSach"));
				objSach.setTacGia(rs.getString("TacGia"));
				objSach.setNgayTao(rs.getDate("NgayTao"));
				objSach.setNgayDuyet(rs.getDate("NgayDuyet"));
				objSach.setMaChuDe(rs.getString("MaChuDe"));
				objSach.setGiaSach(rs.getDouble("GiaSach"));
				//Thêm vào danh sách
				lstSach.add(objSach);
			}
		}
		catch (Exception e) {
			
			System.err.println("Có lỗi xảy ra, chi tiết: " + e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lstSach;
		
	}//end layDanhSach
	
	/**
	 * Hàm tìm kiếm theo từ khóa, mã chủ đề
	 * @param tuKhoa, Từ khóa
	 * @param maChuDe, Mã chủ đề
	 * @return Danh sách thông tin sách thỏa mãn điều kiện tìm kiếm
	 */
	public List<Sach> timKiemSach(String tuKhoa, String maChuDe)
	{
		List<Sach> lstSach = new ArrayList<Sach>();
		
		//Khai báo câu lệnh truy vấn
		String strSQL = "Select MaSach, TenSach, MoTa, AnhSach, GiaSach, TacGia, NgayTao, NgayDuyet, MaChuDe from Sach where 1=1";
		
		if(tuKhoa != null && !tuKhoa.isEmpty())
		{
			strSQL += " AND (MaSach = '" + tuKhoa + "' OR TenSach like '%" + tuKhoa + "%' OR TacGia like '%" + tuKhoa + "%')";
		}
		
		if(maChuDe != null && !maChuDe.isEmpty())
		{
			strSQL += " AND MaChuDe = '" + maChuDe + "'";
		}
			
		
		//Khai báo kết nối
		Connection conn = null;
		
		try
		{
			conn = DataProvider.ketNoi();
			
			//Khai báo công việc
			Statement stm = conn.createStatement();
			
			//Lấy kết quả
			ResultSet rs = stm.executeQuery(strSQL);
			
			//Đọc từng thông tin lấy được
			Sach objSach;
			while(rs.next())
			{
				objSach = new Sach();
				
				//Gán giá trị cho các thuộc tính
				objSach.setMaSach(rs.getString("MaSach"));
				objSach.setTenSach(rs.getString("TenSach"));
				objSach.setMoTa(rs.getString("MoTa"));
				objSach.setAnhSach(rs.getString("AnhSach"));
				objSach.setTacGia(rs.getString("TacGia"));
				objSach.setNgayTao(rs.getDate("NgayTao"));
				objSach.setNgayDuyet(rs.getDate("NgayDuyet"));
				objSach.setMaChuDe(rs.getString("MaChuDe"));
				objSach.setGiaSach(rs.getDouble("GiaSach"));
				//Thêm vào danh sách
				lstSach.add(objSach);
			}
		}
		catch (Exception e) {
			
			System.err.println("Có lỗi xảy ra, chi tiết: " + e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lstSach;
		
	}//end timKiemSach
	
	/**
	 * Hàm lấy chi tiết sách
	 * @param maSach
	 * @return
	 */
	public Sach layChiTiet(String maSach)
	{
		Sach objSach = null;
		
		//Khai báo câu lệnh truy vấn
		String strSQL = "Select MaSach, TenSach, MoTa, AnhSach, GiaSach, TacGia, NgayTao, NgayDuyet, MaChuDe from Sach where MaSach='" + maSach + "'";
		
		//Khai báo kết nối
		Connection conn = null;
		
		try
		{
			conn = DataProvider.ketNoi();
			
			//Khai báo công việc
			Statement stm = conn.createStatement();
			
			//Lấy kết quả
			ResultSet rs = stm.executeQuery(strSQL);
			
			//Đọc từng thông tin lấy được
			while(rs.next())
			{
				objSach = new Sach();
				
				//Gán giá trị cho các thuộc tính
				objSach.setMaSach(rs.getString("MaSach"));
				objSach.setTenSach(rs.getString("TenSach"));
				objSach.setMoTa(rs.getString("MoTa"));
				objSach.setAnhSach(rs.getString("AnhSach"));
				objSach.setTacGia(rs.getString("TacGia"));
				objSach.setNgayTao(rs.getDate("NgayTao"));
				objSach.setNgayDuyet(rs.getDate("NgayDuyet"));
				objSach.setMaChuDe(rs.getString("MaChuDe"));
				objSach.setGiaSach(rs.getDouble("GiaSach"));
			}
		}
		catch (Exception e) {
			
			System.err.println("Có lỗi xảy ra, chi tiết: " + e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return objSach;
		
	}//end layChiTiet
	
	/**
	 * Hàm thêm mới thông tin sách
	 * @param objSach
	 * @return
	 */
	public boolean themMoi(Sach objSach)
	{
		Connection conn = null;
		try
		{
			conn = DataProvider.ketNoi();
			
			String strInsert = "Insert into Sach(MaSach, TenSach, MoTa, AnhSach, GiaSach, TacGia, NgayTao, NgayCapNhat, MaChuDe) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement stm = conn.prepareStatement(strInsert);
			stm.setString(1, objSach.getMaSach());
			stm.setString(2, objSach.getTenSach());
			stm.setString(3, objSach.getMoTa());
			stm.setString(4, objSach.getAnhSach());
			stm.setDouble(5, objSach.getGiaSach());
			stm.setString(6, objSach.getTacGia());
			stm.setDate(7, new Date(objSach.getNgayTao().getTime()));
			stm.setDate(8, new Date(objSach.getNgayCapNhat().getTime()));
			stm.setString(9, objSach.getMaChuDe());
			
			return stm.executeUpdate() > 0;
		}
		catch (Exception e) {
			
			System.err.println("Có lỗi xảy ra, chi tiết: " + e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Hàm cập nhật thông tin sách
	 * @param objSach
	 * @return
	 */
	public boolean capNhat(Sach objSach)
	{
		Connection conn = null;
		try
		{
			conn = DataProvider.ketNoi();
			
			String strUpdate = "Update Sach set TenSach=?, MoTa = ?, AnhSach = ?, GiaSach = ?, TacGia = ?, NgayCapNhat = ?, MaChuDe = ? where MaSach = ?";
			PreparedStatement stm = conn.prepareStatement(strUpdate);
			
			stm.setString(1, objSach.getTenSach());
			stm.setString(2, objSach.getMoTa());
			stm.setString(3, objSach.getAnhSach());
			stm.setDouble(4, objSach.getGiaSach());
			stm.setString(5, objSach.getTacGia());
			stm.setDate(6, new Date(objSach.getNgayCapNhat().getTime()));
			stm.setString(7, objSach.getMaChuDe());
			stm.setString(8, objSach.getMaSach());
			
			return stm.executeUpdate() > 0;
		}
		catch (Exception e) {
			
			System.err.println("Có lỗi xảy ra, chi tiết: " + e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Xóa thông tin sách
	 * @param maSach
	 * @return
	 */
	public boolean xoa(String maSach)
	{
		Connection conn = null;
		try
		{
			conn = DataProvider.ketNoi();
			
			String strDelete = "Delete from Sach where MaSach = ?";
			PreparedStatement stm = conn.prepareStatement(strDelete);
			
			stm.setString(1, maSach);
			
			return stm.executeUpdate() > 0;
		}
		catch (Exception e) {
			
			System.err.println("Có lỗi xảy ra, chi tiết: " + e.getMessage());
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
