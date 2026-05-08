package vn.com.models;

import java.util.Date;

public class MonAn {

    private int id;
    private String tenMonAn;
    private String moTa;
    private String noiDung;
    private Date ngayTao;
    private Date ngayCapNhat;
    private String imageName;
    private String imagePath;
    private int chuDeId;

    // Constructor không tham số
    public MonAn() {
    }

    // Constructor đầy đủ
    public MonAn(int id, String tenMonAn, String moTa, String noiDung,
                 Date ngayTao, Date ngayCapNhat,
                 String imageName, String imagePath, int chuDeId) {
        this.id = id;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.chuDeId = chuDeId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenMonAn() { return tenMonAn; }
    public void setTenMonAn(String tenMonAn) { this.tenMonAn = tenMonAn; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public Date getNgayTao() { return ngayTao; }
    public void setNgayTao(Date ngayTao) { this.ngayTao = ngayTao; }

    public Date getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(Date ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getChuDeId() { return chuDeId; }
    public void setChuDeId(int chuDeId) { this.chuDeId = chuDeId; }
}