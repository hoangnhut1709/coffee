/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author PC
 */
public class QLSanPham2 {
    private String maQLSP;
    private String maNCC;
    private String tenLoai;
    private String soLuong;
    private String tenSP;

    @Override
    public String toString() {
        return this.tenLoai; //To change body of generated methods, choose Tools | Templates.
    }

    public String getMaQLSP() {
        return maQLSP;
    }

    public void setMaQLSP(String maQLSP) {
        this.maQLSP = maQLSP;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
    
}
