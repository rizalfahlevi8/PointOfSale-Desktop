/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.user;
import config.Koneksi;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class Form_Penjualan extends javax.swing.JPanel {

    PreparedStatement ps;
    ResultSet rs;

    public Form_Penjualan() {
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
                    + "produk.stok_produk, produk.harga_jual "
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
            barang.addColumn("Harga Jual");

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
                    rs.getInt("produk.harga_jual")
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
            String cari = "SELECT produk.kode_produk, produk.nama_produk, kategori.nama_kategori, merek.nama_merek, produk.stok_produk, "
                    + "produk.harga_jual "
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
                    rs.getInt("produk.harga_jual")
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
        txt_subTotal.setEditable(false);
        txt_namaKasir.setEditable(false);
        txt_idKasir.setEditable(false);
    }

    void id() {
        try {
            String idPenjualan = "SELECT MAX(penjualan.penjualan_Id) FROM penjualan;";
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
        txt_subTotal.setText("");
    }

    void totalPembayaran() {
        int jumlahBaris = tbl_detailBarang.getRowCount();
        int totalPembayaran = 0;
        int subTotal;
        for (int i = 0; i < jumlahBaris; i++) {
            subTotal = Integer.parseInt(tbl_detailBarang.getValueAt(i, 4).toString());
            totalPembayaran = totalPembayaran + subTotal;
        }
        txt_totalPembayaran.setText(Integer.valueOf(totalPembayaran).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_namaKasir = new javax.swing.JTextField();
        txt_idKasir = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_cariBarang = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_barang = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txt_kodeTransaksi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namaBarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_tanggalTransaksi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_merek = new javax.swing.JTextField();
        txt_kodeBarang = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_hargaJual = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_kategori = new javax.swing.JTextField();
        txt_jumlahBarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_detailBarang = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txt_subTotal = new javax.swing.JTextField();
        btn_batal = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_totalPembayaran = new javax.swing.JTextField();
        txt_uangDiterima = new javax.swing.JTextField();
        txt_uangKembalian = new javax.swing.JTextField();
        btn_hapus = new javax.swing.JButton();
        btn_perbarui = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_segarkan = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(960, 540));
        setPreferredSize(new java.awt.Dimension(960, 540));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(210, 218, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Sell");

        jLabel2.setText("Nama :");

        jLabel3.setText("Id Kasir :");

        jLabel4.setText("Cari Barang :");

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/image/search.png"))); // NOI18N
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
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

        jLabel5.setText("Tanggal Transaksi :");

        jLabel6.setText("Kode Transaksi :");

        jLabel7.setText("Nama Barang :");

        jLabel8.setText("Kategori :");

        jLabel9.setText("Kode Barang :");

        jLabel10.setText("Harga Jual :");

        jLabel11.setText("merek :");

        txt_jumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahBarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlahBarangKeyTyped(evt);
            }
        });

        jLabel12.setText("Jumlah Barang :");

        btn_tambah.setBackground(new java.awt.Color(0, 204, 51));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        tbl_detailBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jumlah", "Harga Jual", "Subtotal"
            }
        ));
        tbl_detailBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_detailBarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_detailBarang);

        jLabel13.setText("subTotal :");

        btn_batal.setBackground(new java.awt.Color(255, 0, 0));
        btn_batal.setForeground(new java.awt.Color(255, 255, 255));
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        jLabel14.setText("Uang Pembayaran :");

        jLabel15.setText("Total Harga :");

        jLabel16.setText("Kembalian :");

        txt_totalPembayaran.setEditable(false);
        txt_totalPembayaran.setBackground(new java.awt.Color(255, 255, 255));

        txt_uangDiterima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_uangDiterimaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_uangDiterimaKeyTyped(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_perbarui.setBackground(new java.awt.Color(0, 204, 0));
        btn_perbarui.setText("Perbarui");
        btn_perbarui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_perbaruiActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(0, 204, 51));
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_segarkan.setBackground(new java.awt.Color(0, 204, 51));
        btn_segarkan.setText("Segarkan");
        btn_segarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_segarkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_totalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_uangKembalian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_uangDiterima, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(355, 355, 355)
                        .addComponent(btn_perbarui)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_simpan))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel10))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_kodeBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_hargaJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_tanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_idKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_namaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(394, 394, 394)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel11))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_merek, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(1, 1, 1))
                                        .addComponent(txt_subTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(214, 214, 214)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_segarkan)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_batal)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_tambah))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(txt_namaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_tanggalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_hargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(btn_tambah)
                            .addComponent(btn_batal)
                            .addComponent(btn_segarkan)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txt_cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_merek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_perbarui)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_totalPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_hapus)
                        .addComponent(btn_simpan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_uangDiterima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_uangKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 590));
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
        int lihatStok = Integer.parseInt(tbl_barang.getValueAt(tbl_barang.getSelectedRow(), 4).toString());
        int jumlah = Integer.valueOf(txt_jumlahBarang.getText().toString());
        int hargaJual = Integer.valueOf(txt_hargaJual.getText());
        if (jumlah > lihatStok) {
            JOptionPane.showMessageDialog(null, "jumlah melebihi stok");
            txt_jumlahBarang.setText("");
            txt_subTotal.setText(null);
        } else {
            txt_subTotal.setText("" + (jumlah * hargaJual) + "");
            int subTotal = Integer.valueOf(txt_subTotal.getText());
            txt_subTotal.setText("" + (subTotal) + "");
        }
    }//GEN-LAST:event_txt_jumlahBarangKeyReleased

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        txt_kodeBarang.setText("");
        txt_kategori.setText("");
        txt_merek.setText("");
        txt_namaBarang.setText("");
        txt_hargaJual.setText("");
        txt_jumlahBarang.setText("");
        txt_subTotal.setText("");
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        DefaultTableModel barang = (DefaultTableModel) tbl_detailBarang.getModel();

        int kodeBarang = Integer.valueOf(txt_kodeBarang.getText());
        String namaBarang = txt_namaBarang.getText();
        int jumlahBarang = Integer.parseInt(txt_jumlahBarang.getText());
        int hargaJual = Integer.parseInt(txt_hargaJual.getText());
        int subTotal = Integer.parseInt(txt_subTotal.getText());

        barang.addRow(new Object[]{
            kodeBarang, namaBarang, jumlahBarang, hargaJual, subTotal
        });
        bersihInput();
        totalPembayaran();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tbl_detailBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_detailBarangMouseClicked
        txt_kodeBarang.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 0).toString());
        txt_namaBarang.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 1).toString());
        txt_jumlahBarang.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 2).toString());
        txt_hargaJual.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 3).toString());
        txt_subTotal.setText(tbl_detailBarang.getValueAt(tbl_detailBarang.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_tbl_detailBarangMouseClicked

    private void btn_perbaruiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_perbaruiActionPerformed
        DefaultTableModel detail = (DefaultTableModel) tbl_detailBarang.getModel();

        int kodeBarang = Integer.valueOf(txt_kodeBarang.getText());
        String namaBarang = txt_namaBarang.getText();
        int jumlahBarang = Integer.parseInt(txt_jumlahBarang.getText());
        int hargaJual = Integer.parseInt(txt_hargaJual.getText());
        int subTotal = Integer.parseInt(txt_subTotal.getText());

        int row = tbl_detailBarang.getSelectedRow();
        if (row >= 0) {
            detail.removeRow(row);
            detail.addRow(new Object[]{
                kodeBarang, namaBarang, jumlahBarang, hargaJual, subTotal
            });
        }
        totalPembayaran();
        bersihInput();
    }//GEN-LAST:event_btn_perbaruiActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        DefaultTableModel detail = (DefaultTableModel) tbl_detailBarang.getModel();
        int row = tbl_detailBarang.getSelectedRow();
        if (row >= 0) {
            detail.removeRow(row);
            bersihInput();
            if (tbl_detailBarang.getRowCount() == 0) {
                txt_totalPembayaran.setText("");
                txt_subTotal.setText("");
            }
            totalPembayaran();
        } else {
            JOptionPane.showMessageDialog(null, "Pilih Terlebih dahulu baris yang akan diHapus");
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_uangDiterimaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uangDiterimaKeyReleased
        int totalPembayaran = Integer.valueOf(txt_totalPembayaran.getText());
        int uangBayar = Integer.valueOf(txt_uangDiterima.getText());
        if (uangBayar >= totalPembayaran) {
            txt_uangKembalian.setText("" + (uangBayar - totalPembayaran) + "");
            int Kembalian = Integer.valueOf(txt_uangKembalian.getText());
            txt_uangKembalian.setText("" + (Kembalian) + "");
        }
    }//GEN-LAST:event_txt_uangDiterimaKeyReleased

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed

        try {
            int idTransaksi = Integer.valueOf(txt_kodeTransaksi.getText());
            String tanggal = txt_tanggalTransaksi.getText();
            int totalPembayaran = Integer.valueOf(txt_totalPembayaran.getText());
            int uangKembalian = Integer.valueOf(txt_uangKembalian.getText());
            int uangDiterima = Integer.valueOf(txt_uangDiterima.getText());
            int idUser = Integer.valueOf(txt_idKasir.getText());

            String penjualan = "INSERT INTO `penjualan`(`penjualan_Id`, `tanggal_penjualan`, `total_Pembayaran`, `uang_diterima`, `uang_kembalian`, `user_Id` ) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = Koneksi.getConnection().prepareStatement(penjualan);
            ps.setInt(1, idTransaksi);
            ps.setString(2, tanggal);
            ps.setInt(3, totalPembayaran);
            ps.setInt(4, uangDiterima);
            ps.setInt(5, uangKembalian);
            ps.setInt(6, idUser);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "berhasil penjualan");

            int jumlahBaris = tbl_detailBarang.getRowCount();

            for (int i = 0; i < jumlahBaris; i++) {
                String detail = "INSERT INTO `detail_penjualan`(`penjualan_Id`, `kode_produk`, `jumlah`, `Subtotal`) "
                        + "VALUES ('" + Integer.valueOf(txt_kodeTransaksi.getText()) + "','" + tbl_detailBarang.getValueAt(i, 0) + "',"
                        + "'" + tbl_detailBarang.getValueAt(i, 2) + "',"
                        + "'" + tbl_detailBarang.getValueAt(i, 4) + "')";
                ps = Koneksi.getConnection().prepareStatement(detail);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "berhasil detail");
            }
            try {
                String report = ("src/report/nota_penjualan.jrxml");
                HashMap hash = new HashMap();

                hash.put("kode", txt_kodeTransaksi.getText());
                JasperReport JRpt = JasperCompileManager.compileReport(report);
                JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, Koneksi.getConnection());
                JasperViewer.viewReport(JPrint, false);
            } catch (Exception e) {
                System.out.println("Tidak dapat menampilkan struk karena " + e);
            }
            txt_idKasir.setText(user.getId());
            txt_namaKasir.setText(user.getNama());
            tampilBarang();
            id();
            bersihInput();
            txt_totalPembayaran.setText("");
            txt_uangDiterima.setText("");
            txt_uangKembalian.setText("");
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
        txt_totalPembayaran.setText("");
        txt_uangDiterima.setText("");
        txt_uangKembalian.setText("");
        DefaultTableModel model = (DefaultTableModel) tbl_detailBarang.getModel();
        model.setRowCount(0);

    }//GEN-LAST:event_btn_segarkanActionPerformed

    private void txt_jumlahBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahBarangKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "Inputan hanya boleh angka", "Ilegal Input", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txt_jumlahBarangKeyTyped

    private void txt_uangDiterimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uangDiterimaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "Inputan hanya boleh angka", "Ilegal Input", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_txt_uangDiterimaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_perbarui;
    private javax.swing.JButton btn_segarkan;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTextField txt_subTotal;
    private javax.swing.JTextField txt_tanggalTransaksi;
    private javax.swing.JTextField txt_totalPembayaran;
    private javax.swing.JTextField txt_uangDiterima;
    private javax.swing.JTextField txt_uangKembalian;
    // End of variables declaration//GEN-END:variables
}
