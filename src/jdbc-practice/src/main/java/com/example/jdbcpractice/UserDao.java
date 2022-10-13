package com.example.jdbcpractice;

import java.sql.*;

public class UserDao {

    private Connection getConnection(){
        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
        String id = "sa";
        String pw = "";

        try{
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, id, pw);
        }catch (Exception ex){
            return null;
        }
    }

    public User findByUserId(String userid) throws SQLException{
        Connection con = null;
        PreparedStatement pstamt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            String sql = "SELECT userid, password, name, email FROM USERS WHERE userid = ?";
            pstamt = con.prepareStatement(sql);
            pstamt.setString(1, userid);

            rs = pstamt.executeQuery();
            User user = null;
            if (rs.next()){
                user = new User(rs.getString("userid"),
                                rs.getString("password"),
                                rs.getString("name"),
                                rs.getString("email")
                );
            }
            return user;
        }finally {
            if (rs != null)
                rs.close();
            if (pstamt != null)
                pstamt.close();
            if (con != null)
                con.close();

        }
    }

    public void create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstamt = null;

        try{
            con = getConnection();
            String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
            assert con != null;

            pstamt = con.prepareStatement(sql);
            pstamt.setString(1, user.getUserid());
            pstamt.setString(2, user.getPassword());
            pstamt.setString(3, user.getName());
            pstamt.setString(4, user.getEmail());

            pstamt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstamt != null)
                pstamt.close();
            if (con != null)
                con.close();
        }
    }
}
