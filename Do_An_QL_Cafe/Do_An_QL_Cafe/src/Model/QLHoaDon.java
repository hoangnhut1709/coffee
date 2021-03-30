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
 * @author Vủ Bằng
 */
public class QLHoaDon {
    private String mahoadon;
    private Date ngaylap;
    private String manhanvien;

    public QLHoaDon() {
    }

   
    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public QLHoaDon(String mahoadon, Date ngaylap, String manhanvien) {
        this.mahoadon = mahoadon;
        this.ngaylap = ngaylap;
        this.manhanvien = manhanvien;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngaylap) {
        this.ngaylap = ngaylap;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }




 @Override
    public String toString(){
        return this.mahoadon;
    }

     @Override
    public int hashCode() {
        int hash = 7;
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
        final QLHoaDon other = (QLHoaDon) obj;
        if (!Objects.equals(this.mahoadon, other.mahoadon)) {
            return false;
        }
        return true;
    }

 
}
 
    
        


    
    

