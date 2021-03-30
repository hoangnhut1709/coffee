/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import Model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class SanPhamDAO {
    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham(maSP,tenSP,maQLSP,giaBan) VALUES(?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMaSP(), model.getTenSP(),  model.getMaQLSP(), model.getGiaBan());
    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET tenSP=?, maQLSP=?, giaBan=? WHERE maSP=?";
        JdbcHelper.executeUpdate(sql,model.getTenSP(), model.getMaQLSP(), model.getGiaBan(),model.getMaSP());
    }

    public void delete(String MaSP) {
        String sql = "DELETE FROM SanPham WHERE maSP=?";
        JdbcHelper.executeUpdate(sql, MaSP);
    }

    public List<SanPham> select() {
        String sql = "SELECT * FROM SanPham";
        return select(sql);
    }

    public SanPham findById(String MaSP) {
        String sql = "SELECT * FROM SanPham WHERE maSP=?";
        List<SanPham> list = select(sql, MaSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham model = readFromResultSet(rs);
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

    private SanPham readFromResultSet(ResultSet rs) throws SQLException {
        SanPham model = new SanPham();
        model.setMaSP(rs.getString("maSP"));
        model.setTenSP(rs.getString("tenSP"));
        model.setMaQLSP(rs.getString("maQLSP"));
        model.setGiaBan(rs.getInt("giaBan"));
        return model;
    }
}
