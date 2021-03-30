/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Dao.HOADONCHITIETDAO;
import Dao.QLHoaDonDao;
import Dao.SanPhamDAO;
import Helper.DialogHelper;
import Model.HOADONCHITIET;
import Model.QLHoaDon;
import Model.SanPham;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class HOADON_CHITET extends javax.swing.JFrame {

    DefaultTableModel model;
    int index = 0;
    QLHoaDonDao hddao = new QLHoaDonDao();
    HOADONCHITIETDAO hdct = new HOADONCHITIETDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Integer tongtien = 0;
    Integer tienkhachdua = 0;
    Integer tienthua = 0;
    Integer bHeight = 0;
    ArrayList<String> tensp = new ArrayList<>();
    ArrayList<String> soluong = new ArrayList<>();
    ArrayList<String> giaban = new ArrayList<>();
    ArrayList<String> tien = new ArrayList<>();

    Date date = new Date();// Return thời gian hiện tại với định dạng rất khó coi
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng lại date                                                                                           

    /**
     * Creates new form HOADON_CHITET
     */
    public HOADON_CHITET() {

        initComponents();
        this.load();
//        loadBang();
        this.fillComboBox();
        this.fillComboBoxsp();
        setLocationRelativeTo(null);
        txtngay.setEditable(false);
        txtngay.setText(sdf.format(date));
        txttt.setEditable(false);
        txttienthua.setEditable(false);

    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblbang.getModel();
        model.setRowCount(0);
        try {
            List<HOADONCHITIET> list = hdct.select();
            for (HOADONCHITIET hd : list) {
                Object[] row = {
                    hd.getMahd(),
                    hd.getMasanpham(),
                    hd.getSoluong(),
                    hd.getTien(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu! " + e);
        }
    }

//    void loadBang() {
//        try {
//            model.setRowCount(0);
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=Coffee";
//            String user = "sa";
//            String pass = "123";
//            Connection ketNoi = DriverManager.getConnection(url, user, pass);
//            String sql = "SELECT HD_CT.maHD, SANPHAM.tenSP, HD_CT.soLuong, HD_CT.tien\n" +
//"                FROM HD_CT INNER JOIN SANPHAM\n" +
//"                ON HD_CT.maSP = SANPHAM.tenSP";
//            PreparedStatement cauLenh = ketNoi.prepareStatement(sql);
//            ResultSet ketQua = cauLenh.executeQuery();
//            while (ketQua.next()) {
//                model.addRow(new Object[]{ketQua.getString(1), ketQua.getString(2), ketQua.getInt(3), ketQua.getInt(4)});
//            }
//            ketNoi.close();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//
//    }
    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbomhd.getModel();
        model.removeAllElements();
        try {
            List<QLHoaDon> list = hddao.select();
            for (QLHoaDon nv : list) {
                model.addElement(nv.getMahoadon());
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Truy vẫn dữ liệu không thành công !");
        }
    }

    void selectComboBox() {
        SanPham sp = (SanPham) cbomsp.getSelectedItem();
        if (sp != null) {
            txtgb.setText(String.valueOf(sp.getGiaBan()));
//            txtgia.setText(String.valueOf(chuyenDe.getGia()));
        }
    }

    void fillComboBoxsp() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbomsp.getModel();
        model.removeAllElements();
        try {

            List<SanPham> list = spdao.select();
            for (SanPham sp : list) {
                model.addElement(sp);
            }

        } catch (Exception e) {
            DialogHelper.alert(this, "Truy vẫn dữ liệu không thành công !");
            e.printStackTrace();
        }

    }

    void setModel(HOADONCHITIET model) {
        cbomhd.setSelectedItem(model.getMahd());
        cbomsp.setSelectedItem(spdao.findById(model.getMasanpham()));
        cbomsp.setToolTipText(String.valueOf(model.getMahd()));
        txtsl.setText(String.valueOf(model.getSoluong()));
        txttt.setText(String.valueOf(model.getTien()));

    }

    HOADONCHITIET getModel() {
        HOADONCHITIET model = new HOADONCHITIET();
        model.setMahd(String.valueOf(cbomhd.getSelectedItem()));
//       model.setMasanpham(String.valueOf(cbomsp.getSelectedItem()));
        SanPham sp = (SanPham) cbomsp.getSelectedItem();
        model.setMasanpham(sp.getMaSP());

        model.setSoluong(Integer.valueOf(txtsl.getText()));
        model.setTien(Integer.valueOf(txttt.getText()));

        return model;

    }

    void setStatus(boolean insertable) {
//        cbomhd.setEditable(insertable);
//        btnThem.setEnabled(insertable);

        btnSua.setEnabled(!insertable);
        btnXoa.setEnabled(!insertable);

//        boolean first = this.index > 0;
//        boolean last = this.index < tblb.getRowCount() - 1;
//        btnFirst.setEnabled(!insertable && first);
//        btnPrev.setEnabled(!insertable && first);
//        btnNext.setEnabled(!insertable && last);
//        btnLast.setEnabled(!insertable && last);
    }

    void edit() {
        try {
            String ma = (String) tblbang.getValueAt(this.index, 0);
            HOADONCHITIET model = hdct.findById(ma);
            this.setModel(model);
            this.setStatus(false);

        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu kia!");
            e.printStackTrace();
        }
    }

    void insert() {
        HOADONCHITIET model = getModel();
        try {
            hdct.insert(model);
            this.load();

            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!" + e);
            e.printStackTrace();
        }
    }

    void update() {
        HOADONCHITIET model = getModel();
        try {
            hdct.update(model);
            this.load();

            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Cập nhật thất bại!" + e);
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String ma = cbomhd.getSelectedItem().toString();
            try {
                hdct.delete(ma);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (HeadlessException e) {
                DialogHelper.alert(this, "Xóa thất bại!" + e);
            }
        }
    }

    void clear() {

        txtsl.setText("");
        txtgb.setText("");
        txttt.setText("");
        txttienkhachdua.setText("");
        txttienthua.setText("");
//        txttonghoadon.setText("");

    }

    void clear1() {

        txtsl.setText("");
        txtgb.setText("");
        txttt.setText("");
        txttienkhachdua.setText("");
        txttienthua.setText("");
        txttonghoadon.setText("");
    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5;
        double footerHeight = 5;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int r = tensp.size();
//      ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg"); 
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
//            g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
                    g2d.drawString("--------------------------------------------", 12, y);
                    y += yShift;

                    g2d.drawString("            Quán Cafe VinNew ", 12, y);
                    y += yShift;
                    g2d.drawString("Đường Nguyễn Văn Linh, Ninh Kiều, TP Cần Thơ ", 12, y);
                    y += yShift;
                    g2d.drawString("           www.facebook.com/cafe ", 12, y);
                    y += yShift;
                    g2d.drawString("                +840333064032      ", 12, y);
                    y += yShift;
                    g2d.drawString("--------------------------------------------", 12, y);
                    y += headerRectHeight;
                    g2d.drawString("Ngày:                    " + txtngay.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("Sản Phẩm/SL    Giá Bán      Tổng Tiền", 10, y);
                    y += yShift;
                    g2d.drawString("--------------------------------------------", 10, y);
                    y += headerRectHeight;

                    for (int s = 0; s < r; s++) {
                        g2d.drawString(" " + tensp.get(s) + "                            ", 10, y);
                        y += yShift;
                        g2d.drawString("     " + soluong.get(s) + " * " + giaban.get(s), 10, y);
                        g2d.drawString(tien.get(s), 160, y);
                        y += yShift;

                    }

                    g2d.drawString("----------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Tổng Tiền Sản Phẩm:          " + txttonghoadon.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Tiền Khách Đưa:              " + txttienkhachdua.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Tiền Thừa:                   " + txttienthua.getText() + "   ", 10, y);
                    y += yShift;

                    g2d.drawString("********************************************", 10, y);
                    y += yShift;
                    g2d.drawString("            CẢM ƠN QUÝ KHÁCH            ", 10, y);
                    y += yShift;
                    g2d.drawString("********************************************", 10, y);
                    y += yShift;
                    g2d.drawString("    CHÚC QUÝ KHÁCH CÓ MỘT NGÀY VUI VẼ         ", 10, y);
                    y += yShift;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public boolean CheckForm() {
        String ma = txtsl.getText();
        String tt = txttt.getText();
        String gb = txtgb.getText();

        if (ma.length() == 0) {
            DialogHelper.alert(this, "Không được để trống số lượng!");
            txtsl.requestFocus();
            return false;
        } else if (!ma.matches("[0-9]")) {
            DialogHelper.alert(this, "Chỉ Được Nhập Số");
            txtsl.grabFocus();
            return false;

        } else if (tt.length() == 0) {
            DialogHelper.alert(this, "Bạn Chưa Tổng Tiền!!");
            txttt.requestFocus();
            return false;

        } else if (gb.length() == 0) {
            DialogHelper.alert(this, "Bạn Chưa nhập giá bán!");
            txtgb.requestFocus();
            return false;

        }

        return true;
    }

    public boolean Checktt() {
        String ma = txtsl.getText();
        if (ma.length() == 0) {
            DialogHelper.alert(this, "Không được để trống số lượng!");
            txtsl.requestFocus();
            return false;
        } else if (!ma.matches("[0-9]")) {
            DialogHelper.alert(this, "Số Lượng Chỉ Được Nhập Số");
            txtsl.grabFocus();
            return false;
        }

        return true;
    }

    public boolean Checkprint() {
        String ma = txttienkhachdua.getText();
        String tt = txttienthua.getText();

        if (ma.length() == 0) {
            DialogHelper.alert(this, "Không được để trống tiền khách đưa!");
            txttienkhachdua.requestFocus();
            return false;

        } else if (tt.length() == 0) {
            DialogHelper.alert(this, "Bạn Chưa Tính Tiền Thừa!!");
            txttienthua.requestFocus();
            return false;

        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbomhd = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbomsp = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtgb = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbang = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnmoi = new javax.swing.JButton();
        btntt = new javax.swing.JButton();
        txttt = new javax.swing.JTextField();
        txtsl = new javax.swing.JTextField();
        txttonghoadon = new javax.swing.JTextField();
        txttienkhachdua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txttienthua = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        btntienthua = new javax.swing.JButton();
        txtngay = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HÓA ĐƠN CHI TIẾT");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setText("Hóa Đơn Chi Tiết");

        jLabel2.setText("MÃ HÓA ĐƠN : ");

        cbomhd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbomhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomhdActionPerformed(evt);
            }
        });

        jLabel3.setText("TÊN SẢN PHẨM :");

        cbomsp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbomsp.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbomspPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbomsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomspActionPerformed(evt);
            }
        });

        jLabel4.setText("GIÁ BÁN : ");

        jLabel5.setText("SỐ LƯỢNG :");

        txtgb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgbActionPerformed(evt);
            }
        });

        tblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ HÓA ĐƠN", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "TIỀN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbang);

        btnThem.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-database-48.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-update-48.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-database-48.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnmoi.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        btnmoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-refresh-48.png"))); // NOI18N
        btnmoi.setText("Làm mới");
        btnmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmoiActionPerformed(evt);
            }
        });

        btntt.setText("TỔNG TIỀN ");
        btntt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnttActionPerformed(evt);
            }
        });

        txttt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtttActionPerformed(evt);
            }
        });

        txtsl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtslActionPerformed(evt);
            }
        });

        txttonghoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttonghoadonActionPerformed(evt);
            }
        });

        txttienkhachdua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttienkhachduaActionPerformed(evt);
            }
        });

        jLabel6.setText("TỔNG HÓA ĐƠN: ");

        jLabel7.setText("TIỀN KHÁCH ĐƯA :");

        txttienthua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttienthuaActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(102, 102, 102));
        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Print.png"))); // NOI18N
        btnPrint.setText("In Phiếu");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btntienthua.setText("TIền Thừa");
        btntienthua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntienthuaActionPerformed(evt);
            }
        });

        txtngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtngayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(25, 25, 25)
                            .addComponent(txttonghoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(btntienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbomhd, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbomsp, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)))
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnmoi)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtgb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btntt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(txttt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 16, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtngay, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cbomhd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btntt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txttt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbomsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtgb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttonghoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btntienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 3, Short.MAX_VALUE))))
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnttActionPerformed
        // TODO add your handling code here:
        Checktt();
        try {

            if (evt.getSource() == btntt) {
                Integer a = Integer.parseInt(txtsl.getText());
                Integer b = Integer.parseInt(txtgb.getText());
                txttt.setText(Integer.toString(b * a));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnttActionPerformed

    private void txtttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtttActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here
        // if (CheckForm()) {
        CheckForm();
        update();
        try {
            tensp.add(cbomsp.getSelectedItem().toString());
            soluong.add(txtsl.getText());
            giaban.add(txtgb.getText());
            tien.add(txttt.getText());
            tongtien = tongtien + Integer.valueOf(txttt.getText());
            txttonghoadon.setText(tongtien + "");
            clear();

        } catch (Exception e) {

        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmoiActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnmoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        CheckForm();
        
        insert();
        
        try {
            tensp.add(cbomsp.getSelectedItem().toString());
            soluong.add(txtsl.getText());
            giaban.add(txtgb.getText());
            tien.add(txttt.getText());
            tongtien = tongtien + Integer.valueOf(txttt.getText());
            txttonghoadon.setText(tongtien + "");
            clear();
        } catch (Exception e) {
        }

//        insert();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblbang.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();

            }
        }
    }//GEN-LAST:event_tblbangMouseClicked

    private void cbomspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomspActionPerformed
        // TODO add your handling code here:

        selectComboBox();
//   String masanpham = cbomsp.getSelectedItem().toString();
//   SanPham model= new SanPham();
//   masanpham = txtgb.setText(model.getGiaBan());
//        

    }//GEN-LAST:event_cbomspActionPerformed

    private void cbomhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomhdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomhdActionPerformed

    private void txtslActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtslActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtslActionPerformed

    private void txtgbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgbActionPerformed

    private void txttonghoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttonghoadonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttonghoadonActionPerformed

    private void txttienkhachduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttienkhachduaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienkhachduaActionPerformed

    private void txttienthuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttienthuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienthuaActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        
        Checkprint();
        
        bHeight = Integer.valueOf(tensp.size());
        //JOptionPane.showMessageDialog(rootPane, bHeight);      
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();
            clear1();

        } catch (PrinterException ex) {

            ex.printStackTrace();

        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btntienthuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntienthuaActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == btntienthua) {
            Integer a = Integer.parseInt(txttienkhachdua.getText());
            Integer b = Integer.parseInt(txttonghoadon.getText());
            txttienthua.setText(Integer.toString(a - b));
        }
    }//GEN-LAST:event_btntienthuaActionPerformed

    private void cbomspPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbomspPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
//        String masanpham = cbomsp.getSelectedItem().toString();
//       String sql="select giaBan from SANPHAM where maSP=?";
//        try {
//             
//            pst =conn.prepareStatement(sql);
//            pst.setString(1,(String) cbomsp.getSelectedItem());
//            rs=pst.executeQuery();
//            if(rs.next()){
//                String giaBan = rs.getString("giaBan");
//                txtgb.setText(giaBan);
//                
//            }
//       } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_cbomspPopupMenuWillBecomeInvisible

    private void txtngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtngayActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtngayActionPerformed

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
            java.util.logging.Logger.getLogger(HOADON_CHITET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HOADON_CHITET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HOADON_CHITET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HOADON_CHITET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HOADON_CHITET().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnmoi;
    private javax.swing.JButton btntienthua;
    private javax.swing.JButton btntt;
    private javax.swing.JComboBox<String> cbomhd;
    private javax.swing.JComboBox<String> cbomsp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblbang;
    private javax.swing.JTextField txtgb;
    private javax.swing.JTextField txtngay;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JTextField txttienthua;
    private javax.swing.JTextField txttonghoadon;
    private javax.swing.JTextField txttt;
    // End of variables declaration//GEN-END:variables
}
