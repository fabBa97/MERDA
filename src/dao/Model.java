package dao;

import java.sql.*;
import java.util.ArrayList;

public class Model {
    private String url1 = "";
    private String user = "";
    private String password = "";

    public Model(String url, String user, String pwd) {
        url1 = url;
        this.user = user;
        password = pwd;
        registerDriver();
    }

    private void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public ArrayList<User> queryDB() {
        Connection conn1 = null;
        ArrayList<User> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM utenti");
            while (rs.next()) {
                User u = new User(rs.getInt("id_utenti"), rs.getString("Username"), rs.getString("Password"), rs.getString("Ruolo"));
                out.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }
}
