/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.JdbcHelper;
import Model.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ho Gia Huy
 */
public class NhaCungCapDAO {
    public void insert(NhaCungCap model) {
        String sql = "INSERT INTO NHACUNGCAP (maNCC, tenNCC, loaiSP, giaMua, diaChi, sdt) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNCC(),
                model.getTenNCC(),
                model.getLoaiSP(),
                model.getGiaMua(),
                model.getDiaChi(),
                model.getSdt()
                
        );
    }

    public void update(NhaCungCap model) {
        String sql = "UPDATE NHACUNGCAP SET tenNCC = ?, loaiSP = ?, giaMua = ?, diaChi = ?, sdt = ? WHERE maNCC=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenNCC(),
                model.getLoaiSP(),
                model.getGiaMua(),
                model.getDiaChi(),
                model.getSdt(),
                model.getMaNCC()
        );
    }

    public void delete(String maNCC) {
        String sql = "DELETE FROM NHACUNGCAP WHERE maNCC=?";
        JdbcHelper.executeUpdate(sql, maNCC);
    }

    public List<NhaCungCap> select() {
        String sql = "SELECT * FROM NHACUNGCAP";
        return select(sql);
    }
    
       public NhaCungCap findById(String maNCC) {
        String sql = "SELECT * FROM NHACUNGCAP WHERE maNCC=?";
        List<NhaCungCap> list = select(sql, maNCC);
        return list.size() > 0 ? list.get(0) : null;
    }
       
    public List<NhaCungCap> select(String sql, Object... args) {
        List<NhaCungCap> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhaCungCap model = readFromResultSet(rs);
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

    private NhaCungCap readFromResultSet(ResultSet rs) throws SQLException {
        NhaCungCap model = new NhaCungCap();
        model.setMaNCC(rs.getString("maNCC"));
        model.setTenNCC(rs.getString("tenNCC"));
        model.setLoaiSP(rs.getString("loaiSP"));
        model.setGiaMua(rs.getString("giaMua"));
        model.setDiaChi(rs.getString("diaChi"));
        model.setSdt(rs.getString("sdt"));
        return model;
    }
}
