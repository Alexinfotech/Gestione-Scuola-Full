package it.molinari.service;

import java.sql.SQLException;
import java.util.List;
import it.molinari.DAO.CarrelloDAO;
import it.molinari.model.CarrelloDTO;

public class CarrelloService implements ServiceInterface<CarrelloDTO> {
    private CarrelloDAO carrelloDAO = new CarrelloDAO();

    // Questo metodo potrebbe essere usato per recuperare i dettagli del carrello di un utente specifico.
    public CarrelloDTO getCarrelloUtente(int idUtente) throws SQLException {
        return carrelloDAO.getCarrelloUtente(idUtente);
    }

    @Override
    public void create(CarrelloDTO carrello) throws SQLException {
        carrelloDAO.create(carrello);
    }

    // Potresti non avere bisogno di un metodo di aggiornamento tradizionale per un carrello.
    // Questo esempio è solo indicativo.
    @Override
    public void update(CarrelloDTO carrello) throws SQLException {
        carrelloDAO.update(carrello);
    }

    @Override
    public void delete(int id) throws SQLException {
        carrelloDAO.delete(id);
    }

    // Metodo per recuperare tutti i carrelli, potrebbe non essere necessario a seconda dell'uso.
    public List<CarrelloDTO> recupera() throws SQLException {
        return carrelloDAO.recupera();
    }
}
