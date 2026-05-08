package vn.com.stanford.je0126dev.workingjdbc.models;

import java.util.Date;

public class Sach {

	String maSach;
	String tenSach;
	String moTa;
	String anhSach;
	String maChuDe;
	Date ngayTao;
	Date ngayCapNhat;
	Date ngayDuyet;
	int daDuyet;
	double giaSach;
	String tacGia;
	
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getAnhSach() {
		return anhSach;
	}
	public void setAnhSach(String anhSach) {
		this.anhSach = anhSach;
	}
	public String getMaChuDe() {
		return maChuDe;
	}
	public void setMaChuDe(String maChuDe) {
		this.maChuDe = maChuDe;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	public Date getNgayDuyet() {
		return ngayDuyet;
	}
	public void setNgayDuyet(Date ngayDuyet) {
		this.ngayDuyet = ngayDuyet;
	}
	public int getDaDuyet() {
		return daDuyet;
	}
	public void setDaDuyet(int daDuyet) {
		this.daDuyet = daDuyet;
	}
	public double getGiaSach() {
		return giaSach;
	}
	public void setGiaSach(double giaSach) {
		this.giaSach = giaSach;
	}
}
