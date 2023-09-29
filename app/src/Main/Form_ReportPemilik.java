/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import config.Koneksi;
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
public class Form_ReportPemilik extends javax.swing.JPanel {

    PreparedStatement ps;
    ResultSet rs;
    Date tanggal = new Date();

    public Form_ReportPemilik() {
        initComponents();

        tampilLaporan();
        formatTanggal();
        txt_tanggaldetail.setDate(tanggal);
        totalPenjualan();
        totalKeuntungan();
        totalPendapatan();
        tampilDetailLaporan();
    }

    private void formatTanggal() {
        txt_tanggaldetail.setDateFormatString("yyyy-MM-dd");
        txt_date.setDateFormatString("yyyy-MM-dd");
    }

    private void totalPenjualan() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalPenjualan = dateFormat.format(txt_tanggaldetail.getDate());

            String jumlahPenjualan = "CALL JumlahPenjualan('" + tanggalPenjualan + "', @JumlahPenjualan);";
            String selectJumlahPenjualan = "SELECT @JumlahPenjualan;";

            // Eksekusi perintah CALL JumlahPenjualan
            ps = Koneksi.getConnection().prepareStatement(jumlahPenjualan);
            ps.execute();

            // Eksekusi perintah SELECT untuk mengambil nilai @JumlahPenjualan
            ps = Koneksi.getConnection().prepareStatement(selectJumlahPenjualan);
            rs = ps.executeQuery();
            if (rs.next()) {
                lb_penjualan.setText(Integer.toString(rs.getInt(1)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Periksa kembali " + e.getMessage());
        }
    }

    private void totalPendapatan() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalPenjualan = dateFormat.format(txt_tanggaldetail.getDate());

            String totalPendapatan = "CALL TotalPendapatan('" + tanggalPenjualan + "', @totalHargaPenjualan)";
            String selectTotalPendapatan = "SELECT @totalHargaPenjualan;";

            // Eksekusi perintah CALL TotalPendapatan
            ps = Koneksi.getConnection().prepareStatement(totalPendapatan);
            ps.execute();

            // Eksekusi perintah SELECT untuk mengambil nilai @totalHargaPenjualan
            ps = Koneksi.getConnection().prepareStatement(selectTotalPendapatan);
            rs = ps.executeQuery();
            if (rs.next()) {
                lb_pendapatan.setText(Integer.toString(rs.getInt(1)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Periksa kembali " + e.getMessage());
        }
    }

    private void totalKeuntungan() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalPenjualan = dateFormat.format(txt_tanggaldetail.getDate());

            String totalKeuntungan = "CALL Keuntungan('" + tanggalPenjualan + "', @totalKeuntungan)";
            String selectTotalKeuntungan = "SELECT @totalKeuntungan;";

            // Eksekusi perintah CALL Keuntungan
            ps = Koneksi.getConnection().prepareStatement(totalKeuntungan);
            ps.execute();

            // Eksekusi perintah SELECT untuk mengambil nilai @totalKeuntungan
            ps = Koneksi.getConnection().prepareStatement(selectTotalKeuntungan);
            rs = ps.executeQuery();
            if (rs.next()) {
                lb_keuntungan.setText(Integer.toString(rs.getInt(1)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Periksa kembali " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_date = new com.toedter.calendar.JDateChooser();
        cari_laporan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_laporan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_segarkan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_tanggaldetail = new com.toedter.calendar.JDateChooser();
        lb_penjualan = new javax.swing.JLabel();
        lb_pendapatan = new javax.swing.JLabel();
        cari_details = new javax.swing.JButton();
        Rp1 = new javax.swing.JLabel();
        Rp2 = new javax.swing.JLabel();
        lb_keuntungan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_detailLaporan = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1030, 590));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(210, 218, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1030, 590));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Report Sell");

        txt_date.setBackground(new java.awt.Color(255, 255, 255));

        cari_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/image/search.png"))); // NOI18N
        cari_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_laporanActionPerformed(evt);
            }
        });

        tbl_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksi", "Tanggal Penjualan", "Total Harga", "Kasir"
            }
        ));
        jScrollPane1.setViewportView(tbl_laporan);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setText("Details");

        jLabel3.setText("Total Pendapatan :");

        jLabel4.setText("Tanggal :");

        jLabel5.setText("Keuntungan :");

        btn_segarkan.setBackground(new java.awt.Color(0, 255, 51));
        btn_segarkan.setText("Segarkan");
        btn_segarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_segarkanActionPerformed(evt);
            }
        });

        jLabel6.setText("Jumlah penjualan :");

        txt_tanggaldetail.setBackground(new java.awt.Color(255, 255, 255));

        lb_penjualan.setText("0");

        lb_pendapatan.setText("0");

        cari_details.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/image/search.png"))); // NOI18N
        cari_details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_detailsActionPerformed(evt);
            }
        });

        Rp1.setText("Rp");

        Rp2.setText("Rp");

        lb_keuntungan.setText("0");

        tbl_detailLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksil", "Kode Produk", "Nama", "Jumlah", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tbl_detailLaporan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_penjualan)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Rp1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(lb_pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Rp2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(lb_keuntungan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_tanggaldetail, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cari_details, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_segarkan)))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btn_segarkan))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cari_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cari_details, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_tanggaldetail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lb_penjualan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Rp1)
                            .addComponent(lb_pendapatan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Rp2)
                            .addComponent(lb_keuntungan))))
                .addContainerGap(364, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 860));
    }// </editor-fold>//GEN-END:initComponents

    private void cari_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_laporanActionPerformed
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String caritanggal = date.format(txt_date.getDate());

        DefaultTableModel laporan = new DefaultTableModel();
        laporan.addColumn("Kode Transaksi");
        laporan.addColumn("Tanggal Penjualan");
        laporan.addColumn("Total Harga");
        laporan.addColumn("Kasir");

        try {
            String cari = "SELECT * FROM laporan_penjualan WHERE tanggal_penjualan LIKE '%" + caritanggal + "%'";
            ps = Koneksi.getConnection().prepareStatement(cari);
            rs = ps.executeQuery();

            boolean dataFound = false;

            while (rs.next()) {
                dataFound = true;
                laporan.addRow(new Object[]{
                    rs.getString("penjualan_Id"),
                    rs.getString("tanggal_penjualan"),
                    rs.getString("total_pembayaran"),
                    rs.getString("nama_user")
                });
            }

            if (dataFound) {
                tbl_laporan.setModel(laporan);
            } else {
                // Jika tidak ada data ditemukan, kosongkan tabel
                laporan.setRowCount(0);
                tbl_laporan.setModel(laporan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cek kembali " + e + "");
        }
    }//GEN-LAST:event_cari_laporanActionPerformed

    private void cari_detailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_detailsActionPerformed
        totalKeuntungan();
        totalPenjualan();
        totalPendapatan();
        tampilDetailLaporan();
    }//GEN-LAST:event_cari_detailsActionPerformed

    private void btn_segarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_segarkanActionPerformed
        tampilLaporan();
        txt_tanggaldetail.setDate(tanggal);
        totalPenjualan();
        totalKeuntungan();
        totalPendapatan();
        tampilDetailLaporan();
    }//GEN-LAST:event_btn_segarkanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Rp1;
    private javax.swing.JLabel Rp2;
    private javax.swing.JButton btn_segarkan;
    private javax.swing.JButton cari_details;
    private javax.swing.JButton cari_laporan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_keuntungan;
    private javax.swing.JLabel lb_pendapatan;
    private javax.swing.JLabel lb_penjualan;
    private javax.swing.JTable tbl_detailLaporan;
    private javax.swing.JTable tbl_laporan;
    private com.toedter.calendar.JDateChooser txt_date;
    private com.toedter.calendar.JDateChooser txt_tanggaldetail;
    // End of variables declaration//GEN-END:variables

    private void tampilLaporan() {
        try {
            String tampilBarang = "SELECT * FROM laporan_penjualan;";
            ps = Koneksi.getConnection().prepareStatement(tampilBarang);
            rs = ps.executeQuery();

            DefaultTableModel laporan = new DefaultTableModel();
            laporan.addColumn("Kode Transaksi");
            laporan.addColumn("Tanggal Penjualan");
            laporan.addColumn("Total Harga");
            laporan.addColumn("Kasir");

            laporan.getDataVector().removeAllElements();
            laporan.fireTableDataChanged();
            laporan.setRowCount(0);

            while (rs.next()) {
                laporan.addRow(new Object[]{
                    rs.getString("penjualan_Id"),
                    rs.getString("tanggal_penjualan"),
                    rs.getString("total_pembayaran"),
                    rs.getString("nama_user")
                });
                tbl_laporan.setModel(laporan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cek Kembali " + e + "");
        }
    }

    private void tampilDetailLaporan() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggalPenjualan = dateFormat.format(txt_tanggaldetail.getDate());

            String tampilBarang = "SELECT penjualan_Id, kode_produk, nama_produk, jumlah, Subtotal  FROM nota_penjualan WHERE tanggal_penjualan = '" + tanggalPenjualan + "' ORDER BY penjualan_Id;";
            ps = Koneksi.getConnection().prepareStatement(tampilBarang);
            rs = ps.executeQuery();

            DefaultTableModel laporan = new DefaultTableModel();
            laporan.addColumn("Kode Transaksi");
            laporan.addColumn("Kode Produk");
            laporan.addColumn("Nama");
            laporan.addColumn("Jumlah");
            laporan.addColumn("Subtotal");

            laporan.getDataVector().removeAllElements();
            laporan.fireTableDataChanged();
            laporan.setRowCount(0);

            while (rs.next()) {
                laporan.addRow(new Object[]{
                    rs.getString("penjualan_Id"),
                    rs.getString("kode_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("jumlah"),
                    rs.getString("Subtotal")
                });
                tbl_detailLaporan.setModel(laporan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "cek Kembali " + e + "");
        }
    }

}
