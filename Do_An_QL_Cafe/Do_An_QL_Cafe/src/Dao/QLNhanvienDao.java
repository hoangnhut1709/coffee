/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.QLNhanvien;
/**
 *
 * @author Vủ Bằng
 */
public class QLNhanvienDao {
    public void insert(QLNhanvien model){
    String slq = "INSERT INTO NHANVIEN (maNV,tenNV,gioiTinh,ngaySinh,sdt,email,diaChi,hinh) VALUES (?,?,?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(slq, model.getMaNV(), model.getTenNV(), model.isGioiTinh(),model.getNgaySinh(), model.getSdt(), model.getEmail(), model.getDiaChi(),model.getHinh());  
}


 public void update(QLNhanvien model){
        String slq = "UPDATE NHANVIEN SET tenNV=?,gioiTinh=?,ngaySinh=?,sdt=?,email=?,diaChi=?,hinh=? WHERE maNV=?";
        JdbcHelper.executeUpdate(slq, model.getTenNV(), model.isGioiTinh(), model.getNgaySinh(),model.getSdt(), model.getEmail(), model.getDiaChi(),model.getHinh(),model.getMaNV());           
    }
 
 
  public void delete(String maNV){
        String sql = "DELETE FROM NHANVIEN WHERE maNV=?";
        JdbcHelper.executeUpdate(sql, maNV);
    }
  
  public List<QLNhanvien> select() {
        String sql = "SELECT * FROM NHANVIEN";
        return select(sql);
    }
  
  
   public QLNhanvien findById(String maNV) {
        String sql = "SELECT * FROM NHANVIEN WHERE maNV = ?";
        List<QLNhanvien> list = select(sql, maNV );
        return list.size() > 0 ? list.get(0) : null;
   }
    
   
   public List<QLNhanvien> select(String sql, Object... args) {
        List<QLNhanvien > list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    QLNhanvien  model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
   
   private QLNhanvien readFromResultSet(ResultSet rs) throws SQLException {
        QLNhanvien model = new QLNhanvien();
        model.setMaNV(rs.getString("maNV"));
        model.setTenNV(rs.getString("tenNV"));
        model.setGioiTinh(rs.getBoolean("gioiTinh"));
        model.setNgaySinh(rs.getDate("ngaySinh"));
        model.setSdt(rs.getString("sdt"));
        model.setEmail(rs.getString("email"));
        model.setDiaChi(rs.getString("diachi"));
        model.setHinh(rs.getString("hinh"));
        
        return model;
    }

}

