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
import Model.LoaiTK;
/**
 *
 * @author Vủ Bằng
 */
public class LoaiTKDao {

    public void insert(LoaiTK model) {
        String sql = "INSERT INTO PHANLOAI_TAIKHOAN (maLTK,vaiTro) VALUES (?,?)";
        JdbcHelper.executeUpdate(sql, model.getMaLTK(), model.getMota());
    }

    public void update(LoaiTK model) {
        String sql = "UPDATE PHANLOAI_TAIKHOAN  SET vaiTro=? WHERE maLTK=?";
        JdbcHelper.executeUpdate(sql, model.getMota(), model.getMaLTK());
    }

    public void delete(int maLTK) {
        String sql = "DELETE PHANLOAI_TAIKHOAN  WHERE maLTK=?";
        JdbcHelper.executeUpdate(sql, maLTK);
    }

    public List<LoaiTK> select() {
        String sql = "SELECT * FROM PHANLOAI_TAIKHOAN";
        return select(sql);
    }


    public LoaiTK findById(String maLTK) {
        String sql = "SELECT * FROM PHANLOAI_TAIKHOAN WHERE maLTK=?";
        List<LoaiTK> list = select(sql, maLTK);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<LoaiTK> select(String sql, Object... args) {
        List<LoaiTK> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);

                while (rs.next()) {
                    LoaiTK model = readFromResultSet(rs);
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

    private LoaiTK readFromResultSet(ResultSet rs) throws SQLException {
        LoaiTK model = new LoaiTK();
        model.setMaLTK(rs.getString("maLTK"));
        model.setMota(rs.getString("vaiTro"));
        return model;
    }

}
