/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author DELL
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        init();
        
    }

    public void init() {

        setLocationRelativeTo(null);

        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lbltime.setText(format.format(new Date()));
            }
        }).start();
        this.openLogin();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblten = new java.awt.Label();
        lbltime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblu = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnhoadon = new javax.swing.JButton();
        btnhoadoncc = new javax.swing.JButton();
        btnncc = new javax.swing.JButton();
        btnnhanvien = new javax.swing.JButton();
        btnqlsp = new javax.swing.JButton();
        btnsp = new javax.swing.JButton();
        btntaikhoan = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnlogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Qu???n L?? Qu??n Cafe");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusableWindowState(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        lblten.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblten.setForeground(new java.awt.Color(255, 255, 255));
        lblten.setText("COFFEE");

        lbltime.setText("time");

        jLabel1.setText("288, Nguy???n V??n Linh");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Alarm.png"))); // NOI18N

        btnLogin.setBackground(new java.awt.Color(0, 153, 102));
        btnLogin.setText("???Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblu.setText("user");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(516, Short.MAX_VALUE)
                        .addComponent(lblten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(142, 142, 142)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltime)
                    .addComponent(lblu))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(lblu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(lbltime))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogin))
        );

        lblten.getAccessibleContext().setAccessibleName("COFFEE ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnhoadon.setBackground(new java.awt.Color(0, 153, 102));
        btnhoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Lists.png"))); // NOI18N
        btnhoadon.setText("H??a ????n");
        btnhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadonActionPerformed(evt);
            }
        });

        btnhoadoncc.setBackground(new java.awt.Color(0, 153, 102));
        btnhoadoncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Info.png"))); // NOI18N
        btnhoadoncc.setText("H??a ????n Chi Ti???t");
        btnhoadoncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhoadonccActionPerformed(evt);
            }
        });

        btnncc.setBackground(new java.awt.Color(0, 153, 102));
        btnncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Brick house.png"))); // NOI18N
        btnncc.setText("Nh?? Cung C???p");
        btnncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnccActionPerformed(evt);
            }
        });

        btnnhanvien.setBackground(new java.awt.Color(0, 153, 102));
        btnnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png"))); // NOI18N
        btnnhanvien.setText("Nh??n Vi??n");
        btnnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhanvienActionPerformed(evt);
            }
        });

        btnqlsp.setBackground(new java.awt.Color(0, 153, 102));
        btnqlsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bar chart.png"))); // NOI18N
        btnqlsp.setText("Qu???n L?? S???n Ph???m");
        btnqlsp.setToolTipText("");
        btnqlsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnqlspActionPerformed(evt);
            }
        });

        btnsp.setBackground(new java.awt.Color(0, 153, 102));
        btnsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/box_world.png"))); // NOI18N
        btnsp.setText("S???n Ph???m");
        btnsp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnspActionPerformed(evt);
            }
        });

        btntaikhoan.setBackground(new java.awt.Color(0, 153, 102));
        btntaikhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Conference.png"))); // NOI18N
        btntaikhoan.setText("T??i Kho???n");
        btntaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaikhoanActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(0, 153, 102));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Lists.png"))); // NOI18N
        btnThongKe.setText("Th???ng K??");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnqlsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnncc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnhoadoncc, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsp, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(btnhoadoncc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnncc, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnqlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/biglogo.jpg"))); // NOI18N

        jLabel3.setText("Powered by FPT");

        btnlogout.setBackground(new java.awt.Color(0, 153, 102));
        btnlogout.setText("Log Out");
        btnlogout.setAlignmentY(0.0F);
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnlogout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(335, 335, 335))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnlogout, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnccActionPerformed
        // TODO add your handling code here
        if (!ShareHelper.USER.getMaLTK().equals("LTK1")) {
            DialogHelper.alert(this, "Nh??n vi??n kh??ng ???????c xem.");
        } else {
            this.openNCC();

        }
    }//GEN-LAST:event_btnnccActionPerformed

    private void btnspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnspActionPerformed

        this.openSanPham();


    }//GEN-LAST:event_btnspActionPerformed

    private void btnqlspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnqlspActionPerformed
        // TODO add your handling code here:
        if (!ShareHelper.USER.getMaLTK().equals("LTK1")) {
            DialogHelper.alert(this, "Nh??n vi??n kh??ng ???????c xem.");
        } else {
            this.openQLSanPham();

        }

    }//GEN-LAST:event_btnqlspActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        if (!ShareHelper.USER.getMaLTK().equals("LTK1")) {
            DialogHelper.alert(this, "Nh??n vi??n kh??ng ???????c xem.");
        } else {
            this.openThongKe();

        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        this.openLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        // TODO add your handling code here:
        this.openLogin();
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void btntaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaikhoanActionPerformed
        // TODO add your handling code here:
        if (!ShareHelper.USER.getMaLTK().equals("LTK1")) {
            DialogHelper.alert(this, "Nh??n vi??n kh??ng ???????c xem.");
        } else {
            this.openTaiKhoan();

        }

    }//GEN-LAST:event_btntaikhoanActionPerformed

    private void btnnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanvienActionPerformed
        // TODO add your handling code here:
        if (!ShareHelper.USER.getMaLTK().equals("LTK1")) {
            DialogHelper.alert(this, "Nh??n vi??n kh??ng ???????c xem.");
        } else {
            this.openNhanVien();

        }

    }//GEN-LAST:event_btnnhanvienActionPerformed

    private void btnhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadonActionPerformed
        // TODO add your handling code here:
        this.openHoaDon();
    }//GEN-LAST:event_btnhoadonActionPerformed

    private void btnhoadonccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhoadonccActionPerformed
        // TODO add your handling code here:

        this.openHDCC();
    }//GEN-LAST:event_btnhoadonccActionPerformed
    private void openLogin() {
        new LoginJFrame(this, true).setVisible(true);
        u();
    }

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnhoadon;
    private javax.swing.JButton btnhoadoncc;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnncc;
    private javax.swing.JButton btnnhanvien;
    private javax.swing.JButton btnqlsp;
    private javax.swing.JButton btnsp;
    private javax.swing.JButton btntaikhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label lblten;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lblu;
    // End of variables declaration//GEN-END:variables

    public void openSanPham() {
        if (ShareHelper.authenticated()) {
            new SP().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openHoaDon() {
        if (ShareHelper.authenticated()) {
            new QLHoadon().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openHDCC() {
        if (ShareHelper.authenticated()) {
            new HOADON_CHITET().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openQLSanPham() {
        if (ShareHelper.authenticated()) {
            new QLSP().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openNCC() {
        if (ShareHelper.authenticated()) {
            new NhaCungCap().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openNhanVien() {
        if (ShareHelper.authenticated()) {
            new QLNhanVien().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openTaiKhoan() {
        if (ShareHelper.authenticated()) {
            new QLTaiKhoan().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

    }

    public void openThongKe() {
        if (ShareHelper.authenticated()) {
            new ThongKe().setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui l??ng ????ng nh???p!");
        }

//new ThongKe().setVisible(true);
    }

    public void u() {
            
        String a = ShareHelper.USER.getUsername();
        lblu.setText(a);
    }
}
