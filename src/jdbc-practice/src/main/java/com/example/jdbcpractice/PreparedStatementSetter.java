package com.example.jdbcpractice;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    void Setter(PreparedStatement pstamt) throws SQLException;
}
