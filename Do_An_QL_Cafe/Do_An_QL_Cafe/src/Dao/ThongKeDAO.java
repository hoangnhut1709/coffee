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
import java.util.Vector;
import Model.QLHoaDon;

/**
 *
 * @author DELL
 *
 */
public class ThongKeDAO {

    //lấy danh sách doanh thu từ bảng hóa đơn và hóa đơn chi tiết
    public List<Object[]> getSelectAllDoanhThu() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_SelectAllDoanhThu}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1), //mã sản phẩm
                        rs.getString(2), //ngày lập hóa đơn
                        rs.getInt(3) //số tiền trong bảng hóa đơn chi tiết
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách chi
    public List<Object[]> getSelectAllChi() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_SelectAllChi}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1), //mã sản phẩm
                        rs.getString(2), //ngày lập hóa đơn
                        rs.getInt(3) //số tiền trong bảng hóa đơn chi tiết
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //tìm kím thông tin doanh thu theo ngày
    public List<Object[]> getFindDayThu(String ngay, String thang, String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindDayThu (?,?,?)}";
                rs = JdbcHelper.executeQuery(sql, ngay, thang, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //tìm kím thông tin doanh thu theo tháng
    public List<Object[]> getFindMonthThu(String nam, String ma) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindMonthThu (?,?)}";
                rs = JdbcHelper.executeQuery(sql, nam, ma);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //tìm kím thông tin doanh thu theo năm
    public List<Object[]> getFindYearThu(String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindYearThu (?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //tìm kím thông tin chi theo ngày
    public List<Object[]> getFindDayChi(String ngay, String thang, String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindDayChi (?,?,?)}";
                rs = JdbcHelper.executeQuery(sql, ngay, thang, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //tìm kím thông tin chi theo tháng
    public List<Object[]> getFindMonthChi(String nam, String ma) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindMonthChi (?,?)}";
                rs = JdbcHelper.executeQuery(sql, nam, ma);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //tìm kím thông tin chi theo năm
    public List<Object[]> getFindYearChi(String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindYearChi (?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách số lượng doanh thu
    public List<Object[]> getSelectSoLuongBanRa() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_SelectSoLuongBanRa}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách số lượng sản phẩm
    public List<Object[]> getSelectSoLuongNhap() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_SelectSoLuongNhap}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách sản phẩm để tìm kiếm số lượng doanh thu
    public List<Object[]> getFindSLThu_tenSP(String tenSP) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindSoLuongThu_getTenSP(?)}";
                rs = JdbcHelper.executeQuery(sql, tenSP);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách sản phẩm để tìm kiếm số lượng chi
    public List<Object[]> getFindSLChi_tenSP(String tenSP) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindSoLuongChi_getTenSP(?)}";
                rs = JdbcHelper.executeQuery(sql, tenSP);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách số lượng chi theo tháng
    public List<Object[]> getSelectMonthSLChi(String thang, String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindMonthSoLuongChi(?,?)}";
                rs = JdbcHelper.executeQuery(sql, thang, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách số lượng bán ra theo tháng
    public List<Object[]> getSelectMonthSLThu(String thang, String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindMonthSoLuongThu(?,?)}";
                rs = JdbcHelper.executeQuery(sql, thang, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách số lượng bán ra theo năm
    public List<Object[]> getSelectYearSLThu(String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindYearSoLuongThu(?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    //lấy danh sách số lượng chi theo năm
    public List<Object[]> getSelectYearSLChi(String nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_FindYearSoLuongChi(?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getDate(3)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
     //lấy danh sách sản phẩm bán nhiều nhất
    public List<Object[]> getSelectTopSLSP() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_SelectTopSL()}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("tenSP"),
                        rs.getString("tong"),
                        rs.getString("tien")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
