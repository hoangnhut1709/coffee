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
public class SanPham2 {

    private String maSP;
    private String tenSP;
    private String maQLSP;
    private int giaBan;

    @Override
    public String toString() {
        return this.tenSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaQLSP() {
        return maQLSP;
    }

    public void setMaQLSP(String maQLSP) {
        this.maQLSP = maQLSP;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }



}
