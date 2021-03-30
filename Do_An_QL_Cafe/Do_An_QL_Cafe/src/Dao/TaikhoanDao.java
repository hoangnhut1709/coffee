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
import Model.TaiKhoan;

/**
 *
 * @author Vủ Bằng
 */
public class TaikhoanDao {

    public void insert(TaiKhoan model) {
        String sql = "INSERT INTO TAIKHOAN(maTK,username,password,maLTK,maNV) VALUES(?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql,model.getMaTK(),model.getUsername(), model.getPassword(), model.getMaLTK(), model.getMaNV());
    }

    public void update(TaiKhoan model) {
        String sql = "UPDATE TAIKHOAN SET username=?, password=?, maLTK=?, maNV =? WHERE maTK=?";
        JdbcHelper.executeUpdate(sql, model.getUsername(), model.getPassword(), model.getMaLTK(), model.getMaNV(), model.getMaTK());
    }

    public void delete(String maTK) {
        String sql = "DELETE FROM TAIKHOAN WHERE maTK=?";
        JdbcHelper.executeUpdate(sql, maTK);
    }

    public List<TaiKhoan> select() {
        String sql = "SELECT * FROM TAIKHOAN";
        return select(sql);
    }

    public TaiKhoan findById(String maTK) {
        String sql = "SELECT DISTINCT * FROM TAIKHOAN WHERE maTK=?";
        List<TaiKhoan> list = select(sql, maTK);
        return list.size() > 0 ? list.get(0) : null;
    }

    public TaiKhoan findtaikhoan(String username) {
        String sql = "SELECT * FROM TAIKHOAN WHERE username=?";
        List<TaiKhoan> list = select(sql, username);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<TaiKhoan> select(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    TaiKhoan model = readFromResultSet(rs);
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

    private TaiKhoan readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoan model = new TaiKhoan();
        model.setMaTK(rs.getString("maTK"));
        model.setUsername(rs.getString("username"));
        model.setPassword(rs.getString("password"));
        model.setMaLTK(rs.getString("maLTK"));
        model.setMaNV(rs.getString("maNV"));

        return model;
    }

}
