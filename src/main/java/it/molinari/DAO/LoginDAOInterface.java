package it.molinari.DAO;

import java.sql.SQLException;

import it.molinari.model.LoginDTO;

public interface LoginDAOInterface {
	boolean emailExists(String email) throws SQLException;

	void registerNewUser(String email, String password) throws SQLException;

	LoginDTO validateUser(String email, String password) throws SQLException;
}
