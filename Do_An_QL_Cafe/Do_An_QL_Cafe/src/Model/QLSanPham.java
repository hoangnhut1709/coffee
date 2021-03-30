/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class QLSanPham {
    private String maQLSP;
    private String maNCC;
    private String tenLoai;
    private int soLuong;
    private String tenSP;
    private String ngay;

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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

   @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QLSanPham other = (QLSanPham) obj;
        if (!Objects.equals(this.maQLSP, other.maQLSP)) {
            return false;
        }
        return true;
    }
}
