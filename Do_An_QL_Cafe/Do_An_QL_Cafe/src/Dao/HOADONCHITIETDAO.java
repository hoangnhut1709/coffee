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

import Model.HOADONCHITIET;

/**
 *
 * @author Vủ Bằng
 */
public class HOADONCHITIETDAO {

    public void insert(HOADONCHITIET model) {
        String sql = "INSERT INTO HOADON_CHITIET (maHD,maSP,soLuong,tien) VALUES (?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMahd(), model.getMasanpham(), model.getSoluong(), model.getTien());
    }

    public void update(HOADONCHITIET model) {
        String sql = "UPDATE HOADON_CHITIET SET soLuong=?,tien=? WHERE   maHD=? AND  maSP=?";
        JdbcHelper.executeUpdate(sql, model.getSoluong(), model.getTien(), model.getMahd(), model.getMasanpham());
    }

    public void delete(String maHD) {
        String sql = "DELETE FROM HOADON_CHITIET WHERE maHD=?";
        JdbcHelper.executeUpdate(sql, maHD);
    }

    public List<HOADONCHITIET> select() {
        String sql = "SELECT * FROM HOADON_CHITIET";
        return select(sql);
    }
    public List<HOADONCHITIET> select1(String maHD) {
        String sql = "SELECT * FROM HOADON_CHITIET WHERE maHD =?";
        return select(sql);
    }
   

   

    public HOADONCHITIET findById(String maHD) {
        String sql = "SELECT * FROM HOADON_CHITIET WHERE maHD =?";
        List<HOADONCHITIET> list = select(sql, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }
    

    public List<HOADONCHITIET> select(String sql, Object... args) {
        List<HOADONCHITIET> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HOADONCHITIET model = readFromResultSet(rs);
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

    private HOADONCHITIET readFromResultSet(ResultSet rs) throws SQLException {
        HOADONCHITIET model = new HOADONCHITIET();
        model.setMahd(rs.getString("maHD"));
        model.setMasanpham(rs.getString("maSP"));
        model.setSoluong(rs.getInt("soLuong"));
        model.setTien(rs.getInt("tien"));

        return model;
    }

}
