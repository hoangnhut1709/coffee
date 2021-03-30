/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import Model.QLSanPham2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class QLSanPhamDAO2 {

    public void insert(QLSanPham2 model) {
        String sql = "INSERT INTO QLSanPham(MaNCC, TenLoai, SoLuong, TenSP) VALUES(?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMaNCC(),  model.getTenLoai(), model.getSoLuong(), model.getTenSP());
    }

    public void update(QLSanPham2 model) {
        String sql = "UPDATE QLSanPham SET MaNCC=?, TenLoai=?, SoLuong=?, TenSP=? WHERE MaQLSP=?";
        JdbcHelper.executeUpdate(sql, model.getMaQLSP(), model.getMaNCC(),  model.getTenLoai(), model.getSoLuong(), model.getTenSP());
    }

    public void delete(String MaSP) {
        String sql = "DELETE FROM QLSanPham WHERE MaQLSP=?";
        JdbcHelper.executeUpdate(sql, MaSP);
    }

    public List<QLSanPham2> select() {
        String sql = "SELECT * FROM QLSanPham";
        return select(sql);
    }

    public QLSanPham2 findById(String MaSP) {
        String sql = "SELECT * FROM QLSanPham WHERE MaQLSP=?";
        List<QLSanPham2> list = select(sql, MaSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<QLSanPham2> select(String sql, Object... args) {
        List<QLSanPham2> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    QLSanPham2 model = readFromResultSet(rs);
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

    private QLSanPham2 readFromResultSet(ResultSet rs) throws SQLException {
        QLSanPham2 model = new QLSanPham2();
        model.setMaQLSP(rs.getString("MaQLSP"));
        model.setMaNCC(rs.getString("MaNCC"));
        model.setTenLoai(rs.getString("TenLoai"));
        model.setSoLuong(rs.getString("SoLuong"));
        model.setTenSP(rs.getString("TenSP"));
        return model;
    }
    
}
