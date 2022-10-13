package com.example.jdbcpractice;

import java.sql.*;

import static com.example.jdbcpractice.ConnectionManager.getConnection;

public class UserDao {

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

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
        jdbcTemplate.executeUpdate(user, sql, pstamt -> {
            pstamt.setString(1, user.getUserid());
            pstamt.setString(2, user.getPassword());
            pstamt.setString(3, user.getName());
            pstamt.setString(4, user.getEmail());
        });
    }
}
