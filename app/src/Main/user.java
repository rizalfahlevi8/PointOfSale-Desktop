
package Main;

public class user {

    private static String id;
    private static String nama;
    private static String username;
    private static String password;
    private static String jenisUser;
    
    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        user.id = id;
    }
    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        user.nama = nama;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        user.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        user.password = password;
    }

    public static String getJenisUser() {
        return jenisUser;
    }

    public static void setJenisUser(String jenisUser) {
        user.jenisUser = jenisUser;
    }
}
