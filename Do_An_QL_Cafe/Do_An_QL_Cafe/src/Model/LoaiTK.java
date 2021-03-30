/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Vủ Bằng
 */
public class LoaiTK {

    private String maLTK;
    private String mota;

    public String getMaLTK() {
        return maLTK;
    }

    public void setMaLTK(String maLTK) {
        this.maLTK = maLTK;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public String toString() {
        return this.mota;
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
        final LoaiTK other = (LoaiTK) obj;
        if (!Objects.equals(this.maLTK, other.maLTK)) {
            return false;
        }
        return true;
    }
}
