/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import Model.SanPham2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class SanPhamDAO2 {
    public void insert(SanPham2 model) {
        String sql = "INSERT INTO SanPham(MaSP,TenSP,MaQLSP,GiaBan) VALUES(?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMaSP(), model.getTenSP(),  model.getMaQLSP(), model.getGiaBan());
    }

    public void update(SanPham2 model) {
        String sql = "UPDATE SanPham SET TenSP=?, MaQLSP=?, GiaBan=? WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql,model.getTenSP(), model.getMaQLSP(), model.getGiaBan(),model.getMaSP());
    }

    public void delete(String MaSP) {
        String sql = "DELETE FROM SanPham WHERE MaSP=?";
        JdbcHelper.executeUpdate(sql, MaSP);
    }

    public List<SanPham2> select() {
        String sql = "SELECT * FROM SanPham";
        return select(sql);
    }

    public SanPham2 findById(String MaSP) {
        String sql = "SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham2> list = select(sql, MaSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<SanPham2> select(String sql, Object... args) {
        List<SanPham2> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham2 model = readFromResultSet(rs);
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

    private SanPham2 readFromResultSet(ResultSet rs) throws SQLException {
        SanPham2 model = new SanPham2();
        model.setMaSP(rs.getString("MaSP"));
        model.setTenSP(rs.getString("TenSP"));
        model.setMaQLSP(rs.getString("MaQLSP"));
        model.setGiaBan(rs.getInt("GiaBan"));
        return model;
    }
}
