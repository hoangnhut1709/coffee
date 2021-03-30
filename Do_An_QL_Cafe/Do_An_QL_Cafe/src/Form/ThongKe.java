/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Dao.ThongKeDAO;
import Dao.SanPhamDAO2;
import Helper.DialogHelper;
import Model.SanPham2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class ThongKe extends javax.swing.JFrame {

    ThongKeDAO dao = new ThongKeDAO();
    SanPhamDAO2 spdao = new SanPhamDAO2();
    SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    int chi, thu, dt;
    ResultSet rs, rsTK, rsInfoEmp, rsSPBan;
    Connection con;
    PreparedStatement ps, psTK, psSPBan;
    DefaultTableModel tblModel;
    Vector row, vecTK, vecSPBan;

    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        initComponents();
        setLocationRelativeTo(null);
        fillAllToTableDoanhThu();
        fillComboBoxNgay();
        fillComboBoxNam();
        fillComboBoxThang();
        fillAllToTableTKSoLuong();
        fillComboBoxSP();
        //không cho sữa dữ liệu bảng
        tblThu.setDefaultEditor(Object.class, null);
        tblSLBR.setDefaultEditor(Object.class, null);
        tblSLN.setDefaultEditor(Object.class, null);

        //không cho thay đổi vị trí các cột
        tblThu.getTableHeader().setReorderingAllowed(false);
        tblSLBR.getTableHeader().setReorderingAllowed(false);
        tblSLN.getTableHeader().setReorderingAllowed(false);

        //sắp xếp các cột trong bảng
        tblThu.setAutoCreateRowSorter(true);
        tblSLBR.setAutoCreateRowSorter(true);
        tblSLN.setAutoCreateRowSorter(true);

    }

    //lấy ngày để gán vào combobox
    void fillComboBoxNgay() {
        DefaultComboBoxModel modelngay = (DefaultComboBoxModel) cbongay.getModel();
        modelngay.removeAllElements();
        String ngaydefault = "Chọn ngày";
        modelngay.addElement(ngaydefault);
        for (int i = 1; i <= 31; i++) {
            int ngay = i;
            modelngay.addElement(ngay);
        }
        cbongay.setSelectedIndex(0);
    }

    //lấy tháng để gán vào combobox
    void fillComboBoxThang() {
        DefaultComboBoxModel modelthang = (DefaultComboBoxModel) cbothang.getModel();
        modelthang.removeAllElements();
        DefaultComboBoxModel modelthangSL = (DefaultComboBoxModel) cboThangSL.getModel();
        modelthangSL.removeAllElements();
        String thangdefault = "Chọn tháng";
        modelthang.addElement(thangdefault);
        modelthangSL.addElement(thangdefault);
        for (int i = 1; i <= 12; i++) {
            int thang = i;
            modelthang.addElement(thang);
            modelthangSL.addElement(thang);
        }
        cbothang.setSelectedIndex(0);
    }

    //lấy năm để gán vào combobox
    void fillComboBoxNam() {
        DefaultComboBoxModel modelnam = (DefaultComboBoxModel) cbonam.getModel();
        modelnam.removeAllElements();
        DefaultComboBoxModel modelnamSL = (DefaultComboBoxModel) cboNamSL.getModel();
        modelnamSL.removeAllElements();
        String namdefault = "Chọn năm";
        modelnam.addElement(namdefault);
        modelnamSL.addElement(namdefault);
        for (int i = 2019; i <= 2099; i++) {
            int nam = i;
            modelnam.addElement(nam);
            modelnamSL.addElement(nam);
        }
        cbonam.setSelectedIndex(0);
    }

    //lấy tên sản phẩm để gán vào combobox
    void fillComboBoxSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSP.getModel();
        model.removeAllElements();
        String namdefault = "Chọn sản phẩm";
        model.addElement(namdefault);
        List<SanPham2> list = spdao.select();
        for (SanPham2 nd : list) {
            model.addElement(nd);
        }
    }

    //lấy danh sách thu, chi để gán vào table
    void fillAllToTableDoanhThu() {
        //bảng thu
        DefaultTableModel modelthu = (DefaultTableModel) tblThu.getModel();
        modelthu.setRowCount(0);
        List<Object[]> listthu = dao.getSelectAllDoanhThu();
        for (Object[] row : listthu) {
            modelthu.addRow(row);
        }
        //bảng chi
        DefaultTableModel modelchi = (DefaultTableModel) tblChi.getModel();
        modelchi.setRowCount(0);
        List<Object[]> listchi = dao.getSelectAllChi();
        for (Object[] row : listchi) {
            modelchi.addRow(row);
        }
        doanhThu();
    }

    //bảng thống kê số lượng
    void fillAllToTableTKSoLuong() {
        //số lượng bán ra
        DefaultTableModel modelbr = (DefaultTableModel) tblSLBR.getModel();
        modelbr.setRowCount(0);
        List<Object[]> listbr = dao.getSelectSoLuongBanRa();
        for (Object[] row : listbr) {
            modelbr.addRow(row);
        }
        //bảng số lượng nhập vào
        DefaultTableModel modelnv = (DefaultTableModel) tblSLN.getModel();
        modelnv.setRowCount(0);
        List<Object[]> listnv = dao.getSelectSoLuongNhap();
        for (Object[] row : listnv) {
            modelnv.addRow(row);
        }
        sumSoLuong();
    }

    //kiểm tra chọn ngày - tháng- năm doanh thu
    public boolean CheckXemTime() {
        if (cbongay.getSelectedItem().toString().trim().equals("Chọn ngày")
                && cbothang.getSelectedItem().toString().trim().equals("Chọn tháng")
                && cbonam.getSelectedItem().toString().trim().equals("Chọn năm")) {
            return true;
        }
        btnPrint.setEnabled(false);
        return false;
    }

    //kiểm tra chọn ngày - tháng- năm số lượng
    public boolean CheckXemTimeSL() {
        if (cboThangSL.getSelectedItem().toString().trim().equals("Chọn tháng")
                && cboNamSL.getSelectedItem().toString().trim().equals("Chọn năm")) {
            return true;
        }
        if (cboThangSL.getSelectedItem().toString().trim().equals("Chọn sản phẩm")) {
            return true;
        }
        btnPrint.setEnabled(false);
        return false;
    }

    //chọn ngày - tháng- năm doanh thu
    public String Choose() {
        //chọn ngày - tháng - năm
        if (!cbongay.getSelectedItem().toString().equals("Chọn ngày")
                && !cbothang.getSelectedItem().toString().equals("Chọn tháng")
                && !cbonam.getSelectedItem().toString().equals("Chọn năm")) {
            return "ChooseNgay";
        }
        //chọn tháng- năm
        if (cbongay.getSelectedItem().toString().equals("Chọn ngày")
                && !cbothang.getSelectedItem().toString().equals("Chọn tháng")
                && !cbonam.getSelectedItem().toString().equals("Chọn năm")) {
            return "ChooseThang";
        }
        //chọn năm
        if (cbongay.getSelectedItem().toString().equals("Chọn ngày")
                && cbothang.getSelectedItem().toString().equals("Chọn tháng")
                && !cbonam.getSelectedItem().toString().equals("Chọn năm")) {
            return "ChooseNam";
        } else {
            return "ChooseFail";
        }
    }

    //chọn ngày - tháng - năm số lượng
    public String ChooseSL() {
        if (!cboThangSL.getSelectedItem().toString().equals("Chọn tháng")
                && !cboNamSL.getSelectedItem().toString().equals("Chọn năm")) {
            return "ChooseThang";
        }
        if (cboThangSL.getSelectedItem().toString().equals("Chọn tháng")
                && !cboNamSL.getSelectedItem().toString().equals("Chọn năm")) {
            return "ChooseNam";
        }
        if (!cboSP.getSelectedItem().toString().equals("Chọn sản phẩm")) {
            return "ChooseSP";
        } else {
            return "ChooseFail";
        }
    }

    //làm mới thống kê số lượng
    void clearSL() {
        lblTongBR.setText("0");
        lblTongNV.setText("0");
        lblSLCL.setText("0");
    }

    //làm mới thống kê doanh thu
    void clearDT() {
        lblTongThu.setText("0 VNĐ");
        lblTongChi.setText("0 VNĐ");
        lblDoanhThu.setText("0 VNĐ");
    }

    //lấy danh sách bảng doanh thu theo ngày - tháng - năm 
    void xem() {
        if (CheckXemTime()) {
            DialogHelper.alert(this, "Vui lòng chọn thông tin để xem!");
            fillAllToTableDoanhThu();
            btnPrint.setEnabled(false);

        } else {
            switch (Choose()) {
                case "ChooseNgay": {
                    String ngay = cbongay.getSelectedItem().toString();
                    String thang = cbothang.getSelectedItem().toString();
                    String nam = cbonam.getSelectedItem().toString();
                    //add lấy danh sách thu theo ngày vào bảng
                    DefaultTableModel modelthu = (DefaultTableModel) tblThu.getModel();
                    modelthu.setRowCount(0);
                    List<Object[]> listthu = dao.getFindDayThu(ngay, thang, nam);
                    for (Object[] row : listthu) {
                        modelthu.addRow(row);
                    }
                    //add lấy danh sách chi theo ngày vào bảng
                    DefaultTableModel modelchi = (DefaultTableModel) tblChi.getModel();
                    modelchi.setRowCount(0);
                    List<Object[]> listchi = dao.getFindDayChi(ngay, thang, nam);
                    for (Object[] row : listchi) {
                        modelchi.addRow(row);
                    }
                    btnPrint.setEnabled(true);
                    doanhThu();
                    break;
                }
                case "ChooseThang": {
                    String thang = cbothang.getSelectedItem().toString();
                    String nam = cbonam.getSelectedItem().toString();
                    //add danh sách thu theo tháng vào bản
                    DefaultTableModel modelthu = (DefaultTableModel) tblThu.getModel();
                    modelthu.setRowCount(0);
                    List<Object[]> listthu = dao.getFindMonthThu(thang, nam);
                    for (Object[] row : listthu) {
                        modelthu.addRow(row);
                    }
                    //add danh sách chi theo tháng vào bản
                    DefaultTableModel modelchi = (DefaultTableModel) tblChi.getModel();
                    modelchi.setRowCount(0);
                    List<Object[]> listchi = dao.getFindMonthChi(thang, nam);
                    for (Object[] row : listchi) {
                        modelchi.addRow(row);
                    }
                    btnPrint.setEnabled(true);
                    doanhThu();
                    break;
                }
                case "ChooseNam": {
                    String nam = cbonam.getSelectedItem().toString();
                    //add danh sách thu theo năm vào bảng
                    DefaultTableModel modelthu = (DefaultTableModel) tblThu.getModel();
                    modelthu.setRowCount(0);
                    List<Object[]> listthu = dao.getFindYearThu(nam);
                    for (Object[] row : listthu) {
                        modelthu.addRow(row);
                    }
                    //add danh sách chi theo năm vào bảng
                    DefaultTableModel modelchi = (DefaultTableModel) tblChi.getModel();
                    modelchi.setRowCount(0);
                    List<Object[]> listchi = dao.getFindYearChi(nam);
                    for (Object[] row : listchi) {
                        modelchi.addRow(row);
                    }
                    btnPrint.setEnabled(true);
                    doanhThu();
                    break;
                }
                case "ChooseFail":
                    fillAllToTableDoanhThu();
                    DialogHelper.alert(this, "Xem thất bại!\nVui lòng chọn đúng định dạng DD-MM-YYYY hoặc MM/YYYY hoặc YYYY!");
                    btnPrint.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    }

    //lấy danh sách số lượng theo tháng - năm
    void XemSL() {
        if (CheckXemTimeSL()) {
            DialogHelper.alert(this, "Vui lòng chọn thời gian muốn xem!!!");
            fillAllToTableTKSoLuong();
        } else {
            switch (ChooseSL()) {
                case "ChooseThang": {
                    String thang = cboThangSL.getSelectedItem().toString();
                    String nam = cboNamSL.getSelectedItem().toString();

                    DefaultTableModel modelchi = (DefaultTableModel) tblSLN.getModel();
                    modelchi.setRowCount(0);
                    DefaultTableModel modelthu = (DefaultTableModel) tblSLBR.getModel();
                    modelthu.setRowCount(0);

                    List<Object[]> listchi = dao.getSelectMonthSLChi(thang, nam);
                    for (Object[] row : listchi) {
                        modelchi.addRow(row);
                    }

                    List<Object[]> list = dao.getSelectMonthSLThu(thang, nam);
                    for (Object[] row : list) {
                        modelthu.addRow(row);
                    }
                    sumSoLuong();
                    break;
                }
                case "ChooseNam": {
                    String nam = cboNamSL.getSelectedItem().toString();

                    DefaultTableModel modelchi = (DefaultTableModel) tblSLN.getModel();
                    modelchi.setRowCount(0);
                    DefaultTableModel modelthu = (DefaultTableModel) tblSLBR.getModel();
                    modelthu.setRowCount(0);

                    List<Object[]> listchi = dao.getSelectYearSLChi(nam);
                    for (Object[] row : listchi) {
                        modelchi.addRow(row);
                    }

                    List<Object[]> list = dao.getSelectYearSLThu(nam);
                    for (Object[] row : list) {
                        modelthu.addRow(row);
                    }
                    sumSoLuong();
                    break;
                }
                case "ChooseFail":
                    fillAllToTableTKSoLuong();
                    DialogHelper.alert(this, "Xem thất bại!\nVui lòng chọn đúng định dạng MM/YYYY hoặc YYYY!");
                    break;
                default:
                    break;
            }
        }
    }

    //tìm kím số lượng sản phẩn qua tên sản phẩm
    void findTenSP() {
        if (cboSP.getSelectedItem().toString().equals("Chọn sản phẩm")) {
            DialogHelper.alert(this, "Vui lòng chọn sản phẩm muốn xem!!!");
            fillAllToTableTKSoLuong();
        } else {
            switch (ChooseSL()) {
                case "ChooseSP": {
                    String tenSP = cboSP.getSelectedItem().toString();

                    DefaultTableModel modelchi = (DefaultTableModel) tblSLN.getModel();
                    modelchi.setRowCount(0);
                    DefaultTableModel modelthu = (DefaultTableModel) tblSLBR.getModel();
                    modelthu.setRowCount(0);

                    List<Object[]> listchi = dao.getFindSLChi_tenSP(tenSP);
                    for (Object[] row : listchi) {
                        modelchi.addRow(row);
                    }

                    List<Object[]> list = dao.getFindSLThu_tenSP(tenSP);
                    for (Object[] row : list) {
                        modelthu.addRow(row);
                    }
                    sumSoLuong();
                    break;
                }
                case "ChooseFail":
                    fillAllToTableTKSoLuong();
                    DialogHelper.alert(this, "Xem thất bại!");
                    break;
                default:
                    break;
            }
        }

    }

    //thống kê doanh thu: bán ra, nhập vào, tính doanh thu = bán ra - nhập vào
    void doanhThu() {
        try {
            clearDT();
            String patternTong = "###,### VNĐ";
            DecimalFormat formatTienTe = new DecimalFormat(patternTong);
            chi = 0;
            thu = 0;
            dt = 0;

            for (int i = 0; i < tblChi.getRowCount(); i++) {
                chi += Integer.parseInt(tblChi.getValueAt(i, 2).toString());
                String stringChi = formatTienTe.format(chi);
                lblTongChi.setText(String.valueOf(stringChi));
            }
            for (int i = 0; i < tblThu.getRowCount(); i++) {
                thu += Integer.parseInt(tblThu.getValueAt(i, 2).toString());
                String stringThu = formatTienTe.format(thu);
                lblTongThu.setText(String.valueOf(stringThu));
            }
            dt = thu - chi;
            String stringDoanhThu = formatTienTe.format(dt);
            lblDoanhThu.setText(String.valueOf(stringDoanhThu));

        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi ");
            e.printStackTrace();
        }
    }

    //tổng số lượng bán ra và nhập vào
    void sumSoLuong() {
        try {
            clearSL();
            String patternTong = "###,###"; // Khi hiển thị sẽ có ký tự "$" ở đầu
            DecimalFormat formatTienTe = new DecimalFormat(patternTong);
            int br = 0, nv = 0, cl = 0;
            for (int i = 0; i < tblSLBR.getRowCount(); i++) {
                br += Integer.parseInt(tblSLBR.getValueAt(i, 1).toString());
                String stringBR = formatTienTe.format(br);
                lblTongBR.setText(String.valueOf(stringBR));
            }
            for (int i = 0; i < tblSLN.getRowCount(); i++) {
                nv += Integer.parseInt(tblSLN.getValueAt(i, 1).toString());
                String stringNV = formatTienTe.format(nv);
                lblTongNV.setText(String.valueOf(stringNV));
            }
            cl = nv - br;
            String stringCL = formatTienTe.format(cl);
            lblSLCL.setText(String.valueOf(stringCL));
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThu = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        cbongay = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbonam = new javax.swing.JComboBox<>();
        cbothang = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTongThu = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTongChi = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChi = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSLBR = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSLN = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTongBR = new javax.swing.JLabel();
        lblTongNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblSLCL = new javax.swing.JLabel();
        cboSP = new javax.swing.JComboBox<>();
        btnRefresh1 = new javax.swing.JButton();
        btnTKDate = new javax.swing.JButton();
        cboThangSL = new javax.swing.JComboBox<>();
        cboNamSL = new javax.swing.JComboBox<>();
        btnTKTen = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("THỐNG KÊ");

        tblThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản Phẩm", "Ngày", "Tiền (VNĐ)"
            }
        ));
        jScrollPane1.setViewportView(tblThu);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/reset.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/icons8-print-100.png"))); // NOI18N
        btnPrint.setEnabled(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Tìm kiếm ngày - tháng - năm:");

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tổng thu:");

        lblTongThu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongThu.setForeground(new java.awt.Color(255, 0, 51));
        lblTongThu.setText("0 VNĐ");
        lblTongThu.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tổng chi:");

        lblTongChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongChi.setForeground(new java.awt.Color(255, 0, 51));
        lblTongChi.setText("0 VNĐ");
        lblTongChi.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Doanh thu");

        lblDoanhThu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(255, 0, 51));
        lblDoanhThu.setText("0 VNĐ");
        lblDoanhThu.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongThu)
                .addGap(107, 107, 107)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongChi)
                .addGap(89, 89, 89)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoanhThu)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDoanhThu)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTongChi)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTongThu)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tblChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản Phẩm", "Ngày", "Tiền (VNĐ)"
            }
        ));
        jScrollPane4.setViewportView(tblChi);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Danh sách thu:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Danh sách chi:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRefresh)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(cbongay, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbothang, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbonam, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbongay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbothang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(cbonam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(btnPrint))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thống kê doanh thu", jPanel1);

        tblSLBR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản Phẩm", "Số Lượng", "Ngày"
            }
        ));
        jScrollPane2.setViewportView(tblSLBR);
        if (tblSLBR.getColumnModel().getColumnCount() > 0) {
            tblSLBR.getColumnModel().getColumn(2).setResizable(false);
        }

        tblSLN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản Phẩm", "Số Lượng", "Ngày"
            }
        ));
        jScrollPane3.setViewportView(tblSLN);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Số lượng bán ra:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Số lượng nhập vào:");

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Tổng số lượng bán ra:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Tổng số lượng nhập vào:");

        lblTongBR.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblTongBR.setForeground(new java.awt.Color(204, 0, 0));
        lblTongBR.setText("0 ");

        lblTongNV.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblTongNV.setForeground(new java.awt.Color(204, 0, 0));
        lblTongNV.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 204));
        jLabel11.setText("Số lượng còn lại");

        lblSLCL.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lblSLCL.setForeground(new java.awt.Color(204, 0, 0));
        lblSLCL.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lblTongBR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblTongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(lblSLCL, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSLCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongBR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnRefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/reset.png"))); // NOI18N
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        btnTKDate.setText("Tìm kiếm");
        btnTKDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKDateActionPerformed(evt);
            }
        });

        btnTKTen.setText("Tìm kiếm");
        btnTKTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKTenActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Tên Sản Phẩm:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("Tìm kiếm theo tháng - năm:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRefresh1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboSP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTKTen)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboThangSL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboNamSL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTKDate))
                            .addComponent(jLabel4)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRefresh1)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboThangSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNamSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTKDate)
                            .addComponent(btnTKTen))
                        .addGap(42, 42, 42)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống kê số lượng", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        this.fillAllToTableDoanhThu();
        cbongay.setSelectedIndex(0);
        cbothang.setSelectedIndex(0);
        cbonam.setSelectedIndex(0);
        btnPrint.setEnabled(false);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        //xóa file txt
        File file = new File("DoanhThu.txt");
        file.delete();
        //Viết vào file txt
        try {
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("DoanhThu.txt"), "UTF8"));
            String ngay, thang, nam;
            Date now = new Date();

            b.write(" Cafe VinNew \r\n\r\n");
            b.write("Địa chỉ: Cần Thơ\r\n");
            b.write("SĐT: +840333064032\r\n");
            b.write("Thời gian: " + ft.format(now) + "\r\n\r\n");
            if (cbongay.getSelectedItem().toString().matches("[0-9]{0,2}")
                    && cbothang.getSelectedItem().toString().matches("[0-9]{0,2}")
                    && cbonam.getSelectedItem().toString().matches("[0-9]{4}")) {
                ngay = "ngày " + cbongay.getSelectedItem().toString();
                thang = " tháng " + cbothang.getSelectedItem().toString();
                nam = " năm " + cbonam.getSelectedItem().toString();
                b.write("\tBẢNG THỐNG KÊ DOANH THU (theo " + ngay + thang + nam + ")\r\n\r\n");
            } else if (cbothang.getSelectedItem().toString().matches("[0-9]{0,2}")
                    && cbonam.getSelectedItem().toString().matches("[0-9]{4}")) {
                thang = " tháng " + cbothang.getSelectedItem().toString();
                nam = " năm " + cbonam.getSelectedItem().toString();
                b.write("\tBẢNG THỐNG KÊ DOANH THU (theo " + thang + nam + ")\r\n\r\n");
            } else if (cbonam.getSelectedItem().toString().matches("[0-9]{4}")) {
                nam = " năm " + cbonam.getSelectedItem().toString();
                b.write("\tBẢNG THỐNG KÊ DOANH THU (theo " + nam + ")\r\n\r\n");
            }
            //in sản phẩm bán ra
            b.write("\tSẢN PHẨM BÁN RA\r\n");
            b.write("\t---------------------------------\r\n");
            b.write("\tID\t\t\tNgày thu\t\tSố tiền\r\n");
            b.write("\t---------------------------------\r\n");
            int linethu = tblThu.getRowCount();
            for (int i = 0; i < linethu; i++) {
                String idthu = (String) tblThu.getValueAt(i, 0);
                String datethu = (String) tblThu.getValueAt(i, 1);
                int moneythu = (int) tblThu.getValueAt(i, 2);
                b.write("\t" + idthu + "\t\t\t" + datethu + "\t\t" + moneythu + "\r\n");
            }
            b.write("\t---------------------------------\r\n");
            b.write("\tTổng tiền bán ra:\t" + lblTongThu.getText().trim() + "\r\n\r\n");
            b.write("\t---------------------------------------------------------------\r\n\r\n");
            //in sản phẩm nhập vào
            b.write("\tSẢN PHẨM NHẬP VÀO\r\n");
            b.write("\t---------------------------------\r\n");
            b.write("\tID\t\t\tNgày thu\t\tSố tiền\r\n");
            b.write("\t---------------------------------\r\n");
            int linechi = tblChi.getRowCount();
            for (int i = 0; i < linechi; i++) {
                String idchi = (String) tblChi.getValueAt(i, 0);
                String datechi = (String) tblChi.getValueAt(i, 1);
                int moneychi = (int) tblChi.getValueAt(i, 2);
                b.write("\t" + idchi + "\t\t\t" + datechi + "\t\t" + moneychi + "\r\n\r\n");
            }
            b.write("\t---------------------------------\r\n");
            b.write("\tTổng tiền nhập vào:\t" + lblTongChi.getText().trim() + "\r\n\r\n");
            b.write("\t---------------------------------\r\n");
            b.write("\t=>Tổng doanh thu:\t" + lblDoanhThu.getText().trim() + "\r\n\r\n\r\n");
            b.write("Người lập (Ký và ghi rõ họ tên)");
            b.close();
        } catch (IOException | NumberFormatException e) {
            DialogHelper.alert(this, "Lỗi " + e);
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad DoanhThu.txt");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi " + e);
        }
        btnRefreshActionPerformed(evt);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.xem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        this.fillAllToTableTKSoLuong();
        btnTKTen.setEnabled(true);
        btnTKDate.setEnabled(true);
        cboThangSL.setSelectedIndex(0);
        cboNamSL.setSelectedIndex(0);
        cboSP.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void btnTKDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKDateActionPerformed
        // TODO add your handling code here:
        XemSL();
        btnTKTen.setEnabled(false);
    }//GEN-LAST:event_btnTKDateActionPerformed

    private void btnTKTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKTenActionPerformed
        // TODO add your handling code here:
        findTenSP();
        btnTKDate.setEnabled(false);
    }//GEN-LAST:event_btnTKTenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnTKDate;
    private javax.swing.JButton btnTKTen;
    private javax.swing.JComboBox<String> cboNamSL;
    private javax.swing.JComboBox<String> cboSP;
    private javax.swing.JComboBox<String> cboThangSL;
    private javax.swing.JComboBox<String> cbonam;
    private javax.swing.JComboBox<String> cbongay;
    private javax.swing.JComboBox<String> cbothang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblSLCL;
    private javax.swing.JLabel lblTongBR;
    private javax.swing.JLabel lblTongChi;
    private javax.swing.JLabel lblTongNV;
    private javax.swing.JLabel lblTongThu;
    private javax.swing.JTable tblChi;
    private javax.swing.JTable tblSLBR;
    private javax.swing.JTable tblSLN;
    private javax.swing.JTable tblThu;
    // End of variables declaration//GEN-END:variables

}
