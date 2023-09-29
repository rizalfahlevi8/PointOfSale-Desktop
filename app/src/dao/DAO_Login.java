package dao;

import Main.Form_Penjualan;
import Main.Menu_Utama;
import Main.user;
import view.Form_Login_old;
import java.sql.Connection;
import config.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_login;
import service.Service_Login;

public class DAO_Login implements Service_Login{
    
    private Connection conn;
    
    public DAO_Login() throws ClassNotFoundException{
        conn = Koneksi.getConnection(); //untuk menghubungkan class dengan database
    }

    @Override
    public void prosesLogin(Model_login mod_login) {
        PreparedStatement st = null;    //hasil dari eksekusi
        ResultSet rs = null;            //membantu mengeksekusi query dari sql
        String Id = null;
        String Nama = null;
        String Level2 = null;
        String sql = "SELECT * FROM users WHERE (user_Id='"+mod_login.getId_user()+"'OR username_user='"+mod_login.getUsername()+"')"
            +"AND password_user='"+mod_login.getPass_user()+"'";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                Id = rs.getString("user_Id");
                Nama = rs.getString("nama_user");
                Level2 = rs.getString("level_user");
                user.setId(rs.getString("user_Id"));
                user.setNama(rs.getString("nama_user"));
                user.setUsername(rs.getString("username_user"));
                user.setJenisUser(rs.getString("level_user"));
                
                Menu_Utama menu = new Menu_Utama(Nama, Level2);
                menu.setVisible(true);
                menu.revalidate();
                
                Form_Login_old lg = new Form_Login_old();
                lg.tutup = true;
            }else{
                JOptionPane.showMessageDialog(null, "Username dan Password salah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                Form_Login_old lg = new Form_Login_old();
                lg.tutup = false;
            }
        
        }catch(SQLException ex){
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(st!=null){
                try{
                st.close();
                }catch(SQLException ex){
                    Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
