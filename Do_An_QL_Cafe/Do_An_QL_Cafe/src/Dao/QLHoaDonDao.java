/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import Model.QLHoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;









/**
 *
 * @author Vủ Bằng
 */
public class QLHoaDonDao {
    public void insert(QLHoaDon model){
    String slq = "INSERT INTO HOADON (maHD,ngayLap,maNV) VALUES (?,?,?)";
        JdbcHelper.executeUpdate(slq, model.getMahoadon(), model.getNgaylap(),model.getManhanvien());  
}


 public void update(QLHoaDon model){
        String slq = "UPDATE HOADON SET ngayLap=?,maNV=? WHERE maHD=?";
        JdbcHelper.executeUpdate(slq, model.getNgaylap(), model.getManhanvien(), model.getMahoadon() );
                }
 
 
  public void delete(String maHD){
        String sql = "DELETE FROM HOADON WHERE maHD=?";
        JdbcHelper.executeUpdate(sql, maHD);
    }
  
  public List<QLHoaDon> select() {
        String sql = "SELECT * FROM HOADON";
        return select(sql);
    }
  

  
   public QLHoaDon findById(String maHD) {
        String sql = "SELECT * FROM HOADON WHERE maHD =?";
        List<QLHoaDon> list = select(sql, maHD );
        return list.size() > 0 ? list.get(0) : null;
   }
    
   
   public List<QLHoaDon> select(String sql, Object... args) {
        List<QLHoaDon > list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                  QLHoaDon  model = readFromResultSet(rs);
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
   
   private QLHoaDon readFromResultSet(ResultSet rs) throws SQLException {
        QLHoaDon model = new QLHoaDon();
        model.setMahoadon(rs.getString("maHD"));
                model.setNgaylap(rs.getDate("ngayLap"));
        model.setManhanvien(rs.getString("maNV"));


    
        
        return model;
    }

 
}

