package dao;

import java.sql.*;
import model.User;
import util.DBConnection;

public class UserDAO {
    public User checkLogin(String username, String password) {
        User user = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setVerified(rs.getBoolean("verified"));
            }
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getAdminEmail() {
        String email = "";
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT email FROM users WHERE role='admin'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                email = rs.getString("email");
            }
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public boolean updatePassword(String email, String newPassword) {
        boolean status = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE users SET password=(?) WHERE email=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            status = ps.executeUpdate() > 0;
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public boolean emailExists(String email) {
        boolean exists = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT email FROM users WHERE email=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            exists = rs.next();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
    
}
