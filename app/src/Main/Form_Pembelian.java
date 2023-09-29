/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import config.Koneksi;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class Form_Pembelian extends javax.swing.JPanel {

    PreparedStatement ps;
    ResultSet rs;
    
    public Form_Pembelian() {
        initComponents();
        
        txt_idKasir.setText(user.getId());
        txt_namaKasir.setText(user.getNama());
        tampilBarang();
        setEditableFalse();
        id();
        date();
    }

    void tampilBarang() {
        try {
            String tampilBarang = "SELECT produk.kode_produk, produk.nama_produk, kategori.nama_kategori, merek.nama_merek, "
                    + "produk.stok_produk, produk.harga_beli "
                    + "FROM produk JOIN kategori ON produk.kategori_Id = kategori.kategori_Id "
                    + "JOIN merek ON produk.merek_Id = merek.merek_Id;";
            ps = Koneksi.getConnection().prepareStatement(tampilBarang);
            rs = ps.executeQuery();

            DefaultTableModel barang = new DefaultTableModel();
            barang.addColumn("Kode Barang");
            barang.addColumn("Nama Barang");
            barang.addColumn("Kategori");
            barang.addColumn("merek");
            barang.addColumn("Stok");
            barang.addColumn("Harga Beli");

            barang.getDataVector().removeAllElements();
            barang.fireTableDataChanged();
            barang.setRowCount(0);

            while (rs.next()) {
                barang.addRow(new Object[]{
                    rs.getString("produk.kode_produk"),
                    rs.getString("produk.nama_produk"),
                    rs.getString("kategori.nama_kategori"),
                    rs.getString("merek.nama_merek"),
                    rs.getInt("produk.stok_produk"),
                    rs.getInt("produk.harga_beli")
                });
                tbl_barang.setModel(barang);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cek Kembali " + e + "");
        }
    }

    void cariBarang() {
        DefaultTableModel Barang = new DefaultTableModel();
        Barang.addColumn("Kode Barang");
        Barang.addColumn("Nama Barang");
        Barang.addColumn("Kategori");
        Barang.addColumn("merek");
        Barang.addColumn("Stok");
        Barang.addColumn("Harga Jual");
        try {
            String cari = "SELECT produk.kode_produk, produk.nama_produk, kategori.nama_kategori, merek.nama_merek, "
                    + "produk.stok_produk, produk.harga_beli "
                    + "FROM produk JOIN kategori ON produk.kategori_Id = kategori.kategori_Id "
                    + "JOIN merek ON produk.merek_Id = merek.merek_Id "
                    + "WHERE produk.kode_produk LIKE '%" + txt_cariBarang.getText() + "%' || produk.nama_produk LIKE '%" 
                    + txt_cariBarang.getText() + "%' || kategori.nama_kategori LIKE '%" + txt_cariBarang.getText() 
                    + "%' || merek.nama_merek LIKE '%" + txt_cariBarang.getText() + "%'";
            ps = Koneksi.getConnection().prepareStatement(cari);
            rs = ps.executeQuery();
            while (rs.next()) {
                Barang.addRow(new Object[]{
                    rs.getString("produk.kode_produk"),
                    rs.getString("produk.nama_produk"),
                    rs.getString("kategori.nama_kategori"),
                    rs.getString("merek.nama_merek"),
                    rs.getInt("produk.stok_produk"),
                    rs.getInt("produk.harga_beli")
                });
                tbl_barang.setModel(Barang);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cek Kembali " + e + "");
        }
    }
    
        void setEditableFalse() {
        txt_kodeBarang.setEditable(false);
        txt_kategori.setEditable(false);
        txt_merek.setEditable(false);
        txt_namaBarang.setEditable(false);
        txt_hargaJual.setEditable(false);
        txt_namaKasir.setEditable(false);
        txt_idKasir.setEditable(false);
    }

    void id() {
        try {
            String idPenjualan = "SELECT MAX(pembelian.pembelian_Id) FROM pembelian;";
            ps = Koneksi.getConnection().prepareStatement(idPenjualan);
            rs = ps.executeQuery();
            if (rs.next()) {
                int idJual = rs.getInt(1);
                txt_kodeTransaksi.setText(Integer.toString(idJual + 1));
                txt_kodeTransaksi.setEditable(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "di cek Kembali " + e + "");
        }
    }

    void date() {
        Date tanggal = new Date();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("YYYY-MM-dd");
        txt_tanggalTransaksi.setText(formatTanggal.format(tanggal));
        txt_tanggalTransaksi.setEditable(false);
    }

    void bersihInput() {
        txt_kategori.setText("");
        txt_kodeBarang.setText("");
        txt_namaBarang.setText("");
        txt_merek.setText("");
        txt_jumlahBarang.setText("");
        txt_hargaJual.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_detailBarang = new javax.swing.JTable();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_segarkan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_idKasir = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_namaKasir = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_kodeTransaksi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_tanggalTransaksi = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_cari = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_cariBarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_barang = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_namaBarang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_kodeBarang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_hargaJual = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_merek = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_kategori = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_jumlahBarang = new javax.swing.JTextField();
        btn_batal = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_perbarui = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(960, 540));
        setPreferredSize(new java.awt.Dimension(960, 540));

        jPanel2.setBackground(new java.awt.Color(210, 218, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(960, 540));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setText("Restock");

        tbl_detailBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jumlah", "Harga Beli"
            }
        ));
        tbl_detailBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_detailBarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_detailBarang);

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(0, 255, 51));
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_segarkan.setBackground(new java.awt.Color(0, 255, 51));
        btn_segarkan.setText("Segarkan");
        btn_segarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_segarkanActionPerformed(evt);
            }
        });

        jLabel4.setText("Id Petugas :");

        jLabel3.setText("Nama :");

        jLabel7.setText("Kode Transaksi :");

        jLabel6.setText("Tanggal Transaksi :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_idKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_namaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txt_tanggalTransaksi))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(txt_namaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_tanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/image/search.png"))); // NOI18N
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        jLabel5.setText("Cari Barang :");

        txt_cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariBarangActionPerformed(evt);
            }
        });

        tbl_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_barang);

        jLabel8.setText("Nama Barang :");

        jLabel10.setText("Kode Barang :");

        jLabel11.setText("Harga Beli :");

        jLabel12.setText("merek :");

        txt_merek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_merekActionPerformed(evt);
            }
        });

        jLabel9.setText("Kategori :");

        jLabel13.setText("Jumlah Barang :");

        txt_jumlahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahBarangActionPerformed(evt);
            }
        });
        txt_jumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahBarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlahBarangKeyTyped(evt);
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

        btn_tambah.setBackground(new java.awt.Color(0, 204, 51));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_perbarui.setBackground(new java.awt.Color(0, 204, 51));
        btn_perbarui.setText("Perbarui");
        btn_perbarui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_perbaruiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_namaBarang)
                            .addComponent(txt_kodeBarang)
                            .addComponent(txt_hargaJual)
                            .addComponent(txt_merek)
                            .addComponent(txt_kategori)
                            .addComponent(txt_jumlahBarang))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_batal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_perbarui)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_tambah)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_hargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txt_merek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_batal)
                    .addComponent(btn_tambah)
                    .addComponent(btn_perbarui))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(401, 401, 401)
                                .addComponent(btn_hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_simpan))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_segarkan)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_segarkan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan)
                            .addComponent(btn_hapus))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        cariBarang();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void tbl_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_barangMouseClicked
        txt_kodeBarang.setText(tbl_barang.getValueAt(tbl_barang.getSelectedRow(), 0).toString());
        txt_namaBarang.setText(tbl_barang.getValueAt(tbl_barang.getSelectedRow(), 1).toString());
        txt_kategori.setText(tbl_barang.getValueAt(tbl_barang.getSelectedRow(), 2).toString());
        txt_merek.setText(tbl_barang.getValueAt(tbl_barang.getSelectedRow(), 3).toString());
        txt_hargaJual.setText(tbl_barang.getValueAt(tbl_barang.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tbl_barangMouseClicked

    private void txt_jumlahBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahBarangKeyReleased

    }//GEN-LAST:event_txt_jumlahBarangKeyReleased

    private void txt_jumlahBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahBarangKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "Inputan hanya boleh angka", "Ilegal Input", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txt_jumlahBarangKeyTyped

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        DefaultTableModel barang = (DefaultTableModel) tbl_detailBarang.getModel();

        int kodeBarang = Integer.valueOf(txt_kodeBarang.getText());
        String namaBarang = txt_namaBarang.getText();
        int jumlahBarang = Integer.parseInt(txt_jumlahBarang.getText());
        int hargaBeli = Integer.parseInt(txt_hargaJual.getText());

        barang.addRow(new Object[]{
            kodeBarang, namaBarang, jumlahBarang, hargaBeli
        });
        bersihInput();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tbl_detailBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_detailBarangMouseClicked
        txt_kodeBarang.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 0).toString());
        txt_namaBarang.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 1).toString());
        txt_jumlahBarang.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 2).toString());
        txt_hargaJual.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_tbl_detailBarangMouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        txt_kodeBarang.setText("");
        txt_kategori.setText("");
        txt_merek.setText("");
        txt_namaBarang.setText("");
        txt_hargaJual.setText("");
        txt_jumlahBarang.setText("");
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        DefaultTableModel detail = (DefaultTableModel) tbl_detailBarang.getModel();
        int row = tbl_detailBarang.getSelectedRow();
        if (row >= 0) {
            detail.removeRow(row);
            bersihInput();
        } else {
            JOptionPane.showMessageDialog(null, "Pilih Terlebih dahulu baris yang akan diHapus");
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_perbaruiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_perbaruiActionPerformed
        DefaultTableModel detail = (DefaultTableModel) tbl_detailBarang.getModel();

        int kodeBarang = Integer.valueOf(txt_kodeBarang.getText());
        String namaBarang = txt_namaBarang.getText();
        int jumlahBarang = Integer.parseInt(txt_jumlahBarang.getText());
        int hargaBeli = Integer.parseInt(txt_hargaJual.getText());

        int row = tbl_detailBarang.getSelectedRow();
        if (row >= 0) {
            detail.removeRow(row);
            detail.addRow(new Object[]{
               kodeBarang, namaBarang, jumlahBarang, hargaBeli
            });
        }
        bersihInput();
    }//GEN-LAST:event_btn_perbaruiActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed

        try {
            int idTransaksi = Integer.valueOf(txt_kodeTransaksi.getText());
            String tanggal = txt_tanggalTransaksi.getText();
            int idUser = Integer.valueOf(txt_idKasir.getText());

            String penjualan = "INSERT INTO `pembelian`(`pembelian_Id`, `tanggal_pembelian`, `user_Id` ) "
            + "VALUES (?,?,?)";
            ps = Koneksi.getConnection().prepareStatement(penjualan);
            ps.setInt(1, idTransaksi);
            ps.setString(2, tanggal);
            ps.setInt(3, idUser);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "berhasil penjualan");

            int jumlahBaris = tbl_detailBarang.getRowCount();

            for (int i = 0; i < jumlahBaris; i++) {
                String detail = "INSERT INTO `detail_pembelian`(`pembelian_Id`, `kode_produk`, `jumlah`) "
                + "VALUES ('" + Integer.valueOf(txt_kodeTransaksi.getText()) + "','" + tbl_detailBarang.getValueAt(i, 0) + "',"
                + "'" + tbl_detailBarang.getValueAt(i, 2) + "')";
                ps = Koneksi.getConnection().prepareStatement(detail);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "berhasil detail");
            }

            txt_idKasir.setText(user.getId());
            txt_namaKasir.setText(user.getNama());
            tampilBarang();
            id();
            bersihInput();
            DefaultTableModel model = (DefaultTableModel) tbl_detailBarang.getModel();
            model.setRowCount(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cek kembali " + e + "");
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_segarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_segarkanActionPerformed
        txt_idKasir.setText(user.getId());
        txt_namaKasir.setText(user.getNama());
        tampilBarang();
        id();
        bersihInput();
        DefaultTableModel model = (DefaultTableModel) tbl_detailBarang.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_btn_segarkanActionPerformed

    private void txt_jumlahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahBarangActionPerformed

    private void txt_merekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_merekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_merekActionPerformed

    private void txt_cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariBarangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_perbarui;
    private javax.swing.JButton btn_segarkan;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_barang;
    private javax.swing.JTable tbl_detailBarang;
    private javax.swing.JTextField txt_cariBarang;
    private javax.swing.JTextField txt_hargaJual;
    private javax.swing.JTextField txt_idKasir;
    private javax.swing.JTextField txt_jumlahBarang;
    private javax.swing.JTextField txt_kategori;
    private javax.swing.JTextField txt_kodeBarang;
    private javax.swing.JTextField txt_kodeTransaksi;
    private javax.swing.JTextField txt_merek;
    private javax.swing.JTextField txt_namaBarang;
    private javax.swing.JTextField txt_namaKasir;
    private javax.swing.JTextField txt_tanggalTransaksi;
    // End of variables declaration//GEN-END:variables
}
