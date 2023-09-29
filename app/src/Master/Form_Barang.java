/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import com.barcodelib.barcode.Linear;
import config.Koneksi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class Form_Barang extends javax.swing.JPanel {

    /**
     * Creates new form Form_Barang
     */
    public Form_Barang() {
        initComponents();

        SelectKategori();
        SelectMerek();
        SelectSupplier();
        txttemp_kode.hide();
        txttemp_IDkategori.hide();
        txttemp_IDmerek.hide();
        txttemp_IDsupplier.hide();
        GetData();
        BtnEnabled(false);
        btn_simpan.setText("Simpan");
        txt_id.setEditable(false);
        btn_dapatKode.setVisible(true);
    }

    private void SelectKategori() {
        try {
            Connection conn = Koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM kategori");

            cb_kategori.addItem("Pilih");
            while (rs.next()) {
                cb_kategori.addItem(rs.getString("nama_kategori"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SelectMerek() {
        try {
            Connection conn = Koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM merek");

            cb_merek.addItem("Pilih");
            while (rs.next()) {
                cb_merek.addItem(rs.getString("nama_merek"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SelectSupplier() {
        try {
            Connection conn = Koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM supplier");

            cb_supplier.addItem("Pilih");
            while (rs.next()) {
                cb_supplier.addItem(rs.getString("nama_supplier"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TxtEmpty() {
        txt_id.setText("");
        txt_kode.setText("");
        txt_nama.setText("");
        txt_beli.setText("");
        txt_jual.setText("");
        txt_stok.setText("");
        txttemp_IDkategori.setText("");
        txttemp_IDmerek.setText("");
        txttemp_IDsupplier.setText("");
        txttemp_kode.setText("");
        cb_kategori.setSelectedItem("Pilih");
        cb_merek.setSelectedItem("Pilih");
        cb_supplier.setSelectedItem("Pilih");
    }

    private void BtnEnabled(boolean x) {
        btn_hapus.setEnabled(x);
    }

    private void GetData() {
        try {
            Connection conn = Koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("SELECT * FROM tableproduk");
            tbl_supplier.setModel(DbUtils.resultSetToTableModel(sql));
            tbl_supplier.getColumnModel().getColumn(0).setPreferredWidth(7);
            tbl_supplier.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbl_supplier.getColumnModel().getColumn(2).setPreferredWidth(70);
            tbl_supplier.getColumnModel().getColumn(3).setPreferredWidth(40);
            tbl_supplier.getColumnModel().getColumn(4).setPreferredWidth(40);
            tbl_supplier.getColumnModel().getColumn(5).setPreferredWidth(25);
            tbl_supplier.getColumnModel().getColumn(6).setPreferredWidth(40);
            tbl_supplier.getColumnModel().getColumn(7).setPreferredWidth(40);

            //sql.last();
            String count_rows = String.valueOf(tbl_supplier.getRowCount());
            lblcount_rows.setText("Jumlah Data : " + count_rows);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void GetData_View() {
        int row = tbl_supplier.getSelectedRow();
        String row_id = (tbl_supplier.getModel().getValueAt(row, 0).toString());
        txt_id.setText(row_id);
        BtnEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_supplier = new javax.swing.JTable();
        lblcount_rows = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txt_jual = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_beli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_kode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_stok = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_merek = new javax.swing.JComboBox<>();
        cb_kategori = new javax.swing.JComboBox<>();
        cb_supplier = new javax.swing.JComboBox<>();
        btn_dapatKode = new javax.swing.JButton();
        barcode = new javax.swing.JLabel();
        txttemp_kode = new javax.swing.JTextField();
        btn_segarkan = new javax.swing.JButton();
        txttemp_IDsupplier = new javax.swing.JTextField();
        txttemp_IDmerek = new javax.swing.JTextField();
        txttemp_IDkategori = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_cariBarang = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_cetak = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(960, 540));

        jPanel3.setBackground(new java.awt.Color(210, 218, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Products");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btn_tambah.setBackground(new java.awt.Color(0, 204, 0));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel3.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel3.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyReleased(evt);
            }
        });

        tbl_supplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_supplierMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_supplierMouseReleased(evt);
            }
        });
        tbl_supplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_supplierKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_supplier);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 950, 210));

        lblcount_rows.setText("Jumlah Data");
        jPanel3.add(lblcount_rows, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, -1));

        jLabel9.setText("Terpilih :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 550, -1, -1));

        txt_jual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jualActionPerformed(evt);
            }
        });
        txt_jual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jualKeyTyped(evt);
            }
        });

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        txt_beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_beliActionPerformed(evt);
            }
        });
        txt_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_beliKeyTyped(evt);
            }
        });

        jLabel3.setText("Nama Barang :");

        jLabel5.setText("Harga Beli :");

        jLabel4.setText("Harga Jual :");

        btn_simpan.setBackground(new java.awt.Color(51, 204, 0));
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setBackground(new java.awt.Color(255, 0, 0));
        btn_batal.setForeground(new java.awt.Color(255, 255, 255));
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });

        jLabel6.setText("Kode Barang :");

        jLabel7.setText("stok :");

        txt_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokActionPerformed(evt);
            }
        });
        txt_stok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stokKeyTyped(evt);
            }
        });

        jLabel8.setText("merek :");

        jLabel10.setText("kategori :");

        jLabel11.setText("Barcode :");

        jLabel12.setText("supplier :");

        cb_merek.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_merekItemStateChanged(evt);
            }
        });

        cb_kategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_kategoriItemStateChanged(evt);
            }
        });

        cb_supplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_supplierItemStateChanged(evt);
            }
        });

        btn_dapatKode.setBackground(new java.awt.Color(0, 204, 0));
        btn_dapatKode.setText("Dapatkan");
        btn_dapatKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dapatKodeActionPerformed(evt);
            }
        });

        barcode.setPreferredSize(new java.awt.Dimension(462, 92));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txt_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btn_simpan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_batal)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_dapatKode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(cb_merek, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btn_dapatKode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(cb_merek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12)
                            .addComponent(cb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_jual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan)
                            .addComponent(btn_batal)))
                    .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 950, 220));
        jPanel3.add(txttemp_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 23, -1));

        btn_segarkan.setBackground(new java.awt.Color(0, 204, 0));
        btn_segarkan.setText("Segarkan");
        btn_segarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_segarkanActionPerformed(evt);
            }
        });
        jPanel3.add(btn_segarkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, -1, -1));
        jPanel3.add(txttemp_IDsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 23, -1));
        jPanel3.add(txttemp_IDmerek, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 23, -1));
        jPanel3.add(txttemp_IDkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 23, -1));
        jPanel3.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 540, 100, 30));

        txt_cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariBarangActionPerformed(evt);
            }
        });
        jPanel3.add(txt_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 220, -1));

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/image/search.png"))); // NOI18N
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        jPanel3.add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 30, 30));

        btn_cetak.setBackground(new java.awt.Color(0, 204, 51));
        btn_cetak.setText("Cetak");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });
        jPanel3.add(btn_cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 997, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        tbl_supplier.clearSelection();
        TxtEmpty();
        BtnEnabled(false);
        barcode.setIcon(null);
        btn_simpan.setText("Simpan");
        txt_nama.requestFocus();
        btn_dapatKode.setVisible(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
        if (ok == 0) {
            try {
                String row_id = txt_id.getText();
                Connection conn = Koneksi.getConnection();
                java.sql.Statement stm = conn.createStatement();
                stm.executeUpdate("DELETE FROM produk WHERE kode_produk = '" + row_id + "'");
                JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
                btn_tambah.doClick();
                GetData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void tbl_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supplierMouseClicked
        GetData_View();
        Mouseklik();

    }//GEN-LAST:event_tbl_supplierMouseClicked

    private void tbl_supplierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supplierMouseReleased
        GetData_View();
        Mouseklik();
    }//GEN-LAST:event_tbl_supplierMouseReleased

    private void tbl_supplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_supplierKeyReleased
        GetData_View();
    }//GEN-LAST:event_tbl_supplierKeyReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased

    }//GEN-LAST:event_jScrollPane1MouseReleased

    private void jScrollPane1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyReleased

    }//GEN-LAST:event_jScrollPane1KeyReleased

    private void txt_jualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jualActionPerformed

    private void txt_jualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jualKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "Inputan hanya boleh angka", "Ilegal Input", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txt_jualKeyTyped

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_beliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_beliActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String row_id = txt_id.getText();
        String row_txtkode = txt_kode.getText();
        String row_txtnama = txt_nama.getText();
        String row_txtbeli = txt_beli.getText();
        String row_txtjual = txt_jual.getText();
        String row_txtstok = txt_stok.getText();
        String row_txttemp_kode = txttemp_kode.getText();
        String row_txtkategori = txttemp_IDkategori.getText();
        String row_txtmerek = txttemp_IDmerek.getText();
        String row_txtsupplier = txttemp_IDsupplier.getText();
        int c_kode = 0;

        if (!"".equals(row_txtkode) && !"".equals(row_txtnama) && !"".equals(row_txtbeli) && !"".equals(row_txtjual) && !"".equals(row_txtstok) && !"".equals(row_txtkategori) && !"".equals(row_txtmerek) && !"".equals(row_txtsupplier)) {
            try {
                Connection conn = Koneksi.getConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT COUNT(kode_produk) as count FROM produk WHERE kode_produk='" + row_txtkode + "'");
                sql.next();
                c_kode = sql.getInt("count");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }

            if ("".equals(row_id)) {
                if (c_kode == 0) {
                    try {
                        Connection conn = Koneksi.getConnection();
                        java.sql.Statement stm = conn.createStatement();
                        stm.executeUpdate("INSERT INTO produk(kode_produk, nama_produk, harga_beli, harga_jual, stok_produk, kategori_Id, "
                                + "merek_Id, supplier_Id) VALUES ('" + row_txtkode + "', '" + row_txtnama + "', "
                                        + "'" + row_txtbeli + "','" + row_txtjual + "', '" + row_txtstok + "', "
                                                + "'" + row_txtkategori + "', '" + row_txtmerek + "', '" + row_txtsupplier + "')");
                        JOptionPane.showMessageDialog(null, "Berhasil menyimpan data.");
                        btn_tambah.doClick();
                        GetData();
                        generate(row_txtkode);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error " + e);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Barang sudah pernah disimpan.", "Gagal Disimpan", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (c_kode == 0 || row_txttemp_kode.equals(row_txttemp_kode)) {
                    try {
                        Connection conn = Koneksi.getConnection();
                        java.sql.Statement stm = conn.createStatement();
                        stm.executeUpdate("UPDATE produk SET nama_produk ='" + row_txtnama + "',harga_beli= '" + row_txtbeli 
                                + "',harga_jual= '" + row_txtjual + "',stok_produk= '" + row_txtstok + "',kategori_Id= '" 
                                + row_txtkategori + "',merek_Id= '" + row_txtmerek + "',supplier_Id= '" + row_txtsupplier 
                                + "' WHERE kode_produk = '" + row_id + "'");
                        JOptionPane.showMessageDialog(null, "Berhasil mengubah data.");
                        btn_tambah.doClick();
                        GetData();
                        btn_dapatKode.setVisible(true);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error " + e);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Kode barang sudah pernah disimpan.", "Gagal Disimpan", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Terdapat inputan yang kosong.");
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        btn_tambah.doClick();
        btn_dapatKode.setVisible(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_segarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_segarkanActionPerformed
        GetData();
        barcode.setIcon(null);
        btn_dapatKode.setVisible(true);
        txt_id.setText("");
        txt_cariBarang.setText("");
    }//GEN-LAST:event_btn_segarkanActionPerformed

    private void txt_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeActionPerformed

    private void txt_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokActionPerformed

    private void txt_beliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_beliKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "Inputan hanya boleh angka", "Ilegal Input", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txt_beliKeyTyped

    private void txt_stokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stokKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "Inputan hanya boleh angka", "Ilegal Input", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txt_stokKeyTyped

    private void cb_merekItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_merekItemStateChanged
        String nm_merek = cb_merek.getSelectedItem().toString();
        if (!nm_merek.equals("")) {
            try {
                Connection conn = Koneksi.getConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT merek_Id FROM merek WHERE nama_merek='" + nm_merek + "'");
                if (sql.next()) {
                    txttemp_IDmerek.setText(sql.getString("merek_Id"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txttemp_IDmerek.setText("");
        }
    }//GEN-LAST:event_cb_merekItemStateChanged

    private void cb_kategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_kategoriItemStateChanged
        String nm_kategori = cb_kategori.getSelectedItem().toString();
        if (!nm_kategori.equals("")) {
            try {
                Connection conn = Koneksi.getConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT kategori_Id FROM kategori WHERE nama_kategori='" + nm_kategori + "'");
                if (sql.next()) {
                    txttemp_IDkategori.setText(sql.getString("kategori_Id"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txttemp_IDkategori.setText("");
        }
    }//GEN-LAST:event_cb_kategoriItemStateChanged

    private void cb_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_supplierItemStateChanged
        String nm_supplier = cb_supplier.getSelectedItem().toString();
        if (!nm_supplier.equals("")) {
            try {
                Connection conn = Koneksi.getConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT supplier_Id FROM supplier WHERE nama_supplier='" + nm_supplier + "'");
                if (sql.next()) {
                    txttemp_IDsupplier.setText(sql.getString("supplier_Id"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txttemp_IDsupplier.setText("");
        }
    }//GEN-LAST:event_cb_supplierItemStateChanged

    private void btn_dapatKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dapatKodeActionPerformed
        txt_kode.setText(getRandomNumberString());
    }//GEN-LAST:event_btn_dapatKodeActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        cariBarang();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariBarangActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
                    try {
                String report = ("src/report/produk.jrxml");
                HashMap hash = new HashMap();

                hash.put("",0);
                JasperReport JRpt = JasperCompileManager.compileReport(report);
                JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, Koneksi.getConnection());
                JasperViewer.viewReport(JPrint, false);
            } catch (Exception e) {
                System.out.println("Tidak dapat menampilkan struk karena " + e);
            }
    }//GEN-LAST:event_btn_cetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_dapatKode;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_segarkan;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JComboBox<String> cb_merek;
    private javax.swing.JComboBox<String> cb_supplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcount_rows;
    private javax.swing.JTable tbl_supplier;
    private javax.swing.JTextField txt_beli;
    private javax.swing.JTextField txt_cariBarang;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_jual;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_stok;
    private javax.swing.JTextField txttemp_IDkategori;
    private javax.swing.JTextField txttemp_IDmerek;
    private javax.swing.JTextField txttemp_IDsupplier;
    private javax.swing.JTextField txttemp_kode;
    // End of variables declaration//GEN-END:variables

    private String getRandomNumberString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            sb.append("1234567890".charAt(random.nextInt("1234567890"
                    .length())));
        }

        return sb.toString();
    }

    private void generate(String row_txtkode) throws Exception {
        Linear barcode = new Linear();
        barcode.setType(Linear.CODE128B);
        barcode.setData(row_txtkode);
        barcode.setI(11.0f);
        String fname = row_txtkode;
        barcode.renderBarcode("src" + "/" + "img" + "/barcode/" + fname + ".png");
    }

    private void Mouseklik() {
        String row_id = txt_id.getText();
        if (!"0".equals(row_id)) {
            try {
                btn_simpan.setText("Perbarui");
                Connection conn = Koneksi.getConnection();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT p.kode_produk, p.nama_produk, p.harga_beli, p.harga_jual, p.stok_produk, s.supplier_Id, s.nama_supplier, k.kategori_Id, k.nama_kategori, m.merek_Id, m.nama_merek "
                        + "FROM produk p JOIN supplier s ON p.supplier_Id = s.supplier_Id "
                        + "JOIN kategori k ON p.kategori_Id = k.kategori_Id "
                        + "JOIN merek m ON p.merek_Id = m.merek_Id WHERE p.kode_produk=' " + row_id + "'");
                if (sql.next()) {
                    String kode = sql.getString("kode_produk");
                    txt_id.setText(sql.getString("kode_produk"));
                    txt_kode.setText(kode);
                    txt_nama.setText(sql.getString("nama_produk"));
                    txt_beli.setText(sql.getString("harga_beli"));
                    txt_jual.setText(sql.getString("harga_jual"));
                    txt_stok.setText(sql.getString("stok_produk"));
                    cb_kategori.setSelectedItem(sql.getString("nama_kategori"));
                    txttemp_IDkategori.setText(sql.getString("kategori_Id"));
                    cb_merek.setSelectedItem(sql.getString("nama_merek"));
                    txttemp_IDmerek.setText(sql.getString("merek_Id"));
                    cb_supplier.setSelectedItem(sql.getString("nama_supplier"));
                    txttemp_IDsupplier.setText(sql.getString("supplier_Id"));
                    txttemp_kode.setText(kode);
                    ImageIcon imgThisImg = new ImageIcon("src" + "/" + "img" + "/barcode/" + txt_kode.getText() + ".png");
                    barcode.setIcon(imgThisImg);
                    txt_kode.requestFocus();
                }
                txt_kode.setEditable(false);
                btn_dapatKode.hide();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error " + e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Form_Barang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Terdapat kesalahan id null!");
        }
    }

    private void cariBarang() {
        try {
            Connection conn = Koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("SELECT * FROM tableproduk"
                    + " WHERE tableproduk.kode_produk LIKE '%" + txt_cariBarang.getText() 
                    + "%' || tableproduk.nama_produk LIKE '%" + txt_cariBarang.getText() 
                    + "%' || tableproduk.nama_kategori LIKE '%" + txt_cariBarang.getText() 
                    + "%' || tableproduk.nama_merek LIKE '%" + txt_cariBarang.getText()
                    + "%' || tableproduk.harga_beli LIKE '%" + txt_cariBarang.getText() 
                    + "%' || tableproduk.harga_jual LIKE '%" + txt_cariBarang.getText() 
                    + "%' || tableproduk.stok_produk LIKE '%" + txt_cariBarang.getText() 
                    + "%' || tableproduk.nama_supplier LIKE '%" + txt_cariBarang.getText() + "%'");
            tbl_supplier.setModel(DbUtils.resultSetToTableModel(sql));
            tbl_supplier.getColumnModel().getColumn(0).setPreferredWidth(7);
            tbl_supplier.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbl_supplier.getColumnModel().getColumn(2).setPreferredWidth(70);
            tbl_supplier.getColumnModel().getColumn(3).setPreferredWidth(40);
            tbl_supplier.getColumnModel().getColumn(4).setPreferredWidth(40);
            tbl_supplier.getColumnModel().getColumn(5).setPreferredWidth(25);
            tbl_supplier.getColumnModel().getColumn(6).setPreferredWidth(40);
            tbl_supplier.getColumnModel().getColumn(7).setPreferredWidth(40);

            //sql.last();
            String count_rows = String.valueOf(tbl_supplier.getRowCount());
            lblcount_rows.setText("Jumlah Data : " + count_rows);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
