/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import Model.QLSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class QLSanPhamDAO {

    public void insert(QLSanPham model) {
        String sql = "INSERT INTO QLSanPham(maNCC, tenLoai, soLuong, tenSP, ngay) VALUES(?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, model.getMaNCC(), model.getTenLoai(), model.getSoLuong(), model.getTenSP(), model.getNgay());
    }

    public void update(QLSanPham model) {
        String sql = "UPDATE QLSanPham SET maNCC=?, tenLoai=?, soLuong=?, tenSP=?, ngay=? WHERE maQLSP=?";
        JdbcHelper.executeUpdate(sql, model.getMaNCC(), model.getTenLoai(), model.getSoLuong(), model.getTenSP(), model.getNgay(), model.getMaQLSP());
    }

    public void delete(String maQLSP) {
        String sql = "DELETE FROM QLSanPham WHERE maQLSP=?";
        JdbcHelper.executeUpdate(sql, maQLSP);
    }

    public List<QLSanPham> select() {
        String sql = "SELECT * FROM QLSanPham";
        return select(sql);
    }

    public QLSanPham findById(String maQLSP) {
        String sql = "SELECT * FROM QLSanPham WHERE maQLSP=?";
        List<QLSanPham> list = select(sql, maQLSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<QLSanPham> select(String sql, Object... args) {
        List<QLSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    QLSanPham model = readFromResultSet(rs);
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

    private QLSanPham readFromResultSet(ResultSet rs) throws SQLException {
        QLSanPham model = new QLSanPham();
        model.setMaQLSP(rs.getString("maQLSP"));
        model.setMaNCC(rs.getString("maNCC"));
        model.setTenLoai(rs.getString("tenLoai"));
        model.setSoLuong(rs.getInt("soLuong"));
        model.setTenSP(rs.getString("tenSP"));
        model.setNgay(rs.getString("ngay"));
        return model;
    }

}
