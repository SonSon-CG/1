package vn.com.models;

public class ChuDe {

    private int id;
    private String tenChuDe;
    private String moTa;

    
    public ChuDe() {
    }

    
    public ChuDe(int id, String tenChuDe, String moTa) {
        this.id = id;
        this.tenChuDe = tenChuDe;
        this.moTa = moTa;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenChuDe() { return tenChuDe; }
    public void setTenChuDe(String tenChuDe) { this.tenChuDe = tenChuDe; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}