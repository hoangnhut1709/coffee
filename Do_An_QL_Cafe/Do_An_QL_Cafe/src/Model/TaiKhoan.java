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
public class TaiKhoan {

    private String maTK;
    private String username;
    private String password;
    private String maLTK;
    private String maNV;

    
     @Override
    public String toString() {
        return this.maNV; //To change body of generated methods, choose Tools | Templates.
    }
    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaLTK() {
        return maLTK;
    }

    public void setMaLTK(String maLTK) {
        this.maLTK = maLTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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
        final TaiKhoan other = (TaiKhoan) obj;
        if (!Objects.equals(this.maTK, other.maTK)) {
            return false;
        }
        return true;
    }

}
