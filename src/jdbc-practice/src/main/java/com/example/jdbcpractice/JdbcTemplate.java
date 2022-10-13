package com.example.jdbcpractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.jdbcpractice.ConnectionManager.getConnection;

public class JdbcTemplate {
    public void executeQuery(User user, String sql, PreparedStatementSetter pss) throws SQLException{
        Connection con = null;
        PreparedStatement pstamt = null;

        try{
            con = getConnection();
            assert con != null;

            pstamt = con.prepareStatement(sql);
            pss.Setter(pstamt);

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
