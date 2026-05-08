package vn.com.stanford.je0126dev.workingjdbc.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChuDeBusiness {

	/**
	 * Hàm lấy danh sách chủ đề sách
	 * @return
	 */
	public List<ChuDe> layDanhSach()
	{
		List<ChuDe> lstChuDe = new ArrayList<ChuDe>();
		
		//Khai báo câu lệnh truy vấn
		String strSQL = "Select MaChuDe, TenChuDe, MoTa from ChuDe";
		
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
			ChuDe objCD;
			while(rs.next())
			{
				objCD = new ChuDe();
				
				//Gán giá trị cho các thuộc tính
				objCD.setMaChuDe(rs.getString("MaChuDe"));
				objCD.setTenChuDe(rs.getString("TenChuDe"));
				objCD.setMoTa(rs.getString("MoTa"));
				//Thêm vào danh sách
				lstChuDe.add(objCD);
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
		
		return lstChuDe;
		
	}//end layDanhSach
}
