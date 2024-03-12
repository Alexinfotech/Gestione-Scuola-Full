package it.molinari.DAO;

import java.sql.SQLException;

public interface LoginDAOInterface {
    boolean emailExists(String email) throws SQLException;

    void registerNewUser(String email, String password) throws SQLException;

    String validateUser(String email, String password) throws SQLException;
}
