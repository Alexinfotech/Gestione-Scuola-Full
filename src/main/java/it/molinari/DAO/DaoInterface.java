package it.molinari.DAO;

import java.sql.SQLException;

public interface DaoInterface<T> {

	T get(String id) throws SQLException; // Adesso usa String per l'ID

	void create(T t) throws SQLException;

	void delete(String id) throws SQLException; // Adesso usa String per l'ID

	void update(T t) throws SQLException;
}
