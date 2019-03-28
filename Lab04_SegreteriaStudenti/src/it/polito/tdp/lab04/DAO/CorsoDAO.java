package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection(); //devi fare la classe connection 
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery(); //qui ho i risultati della query che ho fatto
			

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

			//	System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Corso c= new Corso(rs.getNString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}
			rs.close();
			conn.close();

			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nome) {
		for(Corso c: this.getTuttiICorsi()) {
			if(c.getCodins().equals(nome)) {
				return c;
			}
			
		}return null;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
	public List<Corso> corsiFreq(int matricola) {
		final String sql = "SELECT DISTINCT corso.* \n" + 
				"FROM studente,iscrizione,corso \n" + 
				"WHERE  studente.matricola=iscrizione.matricola AND corso.codins=iscrizione.codins AND studente.matricola=?";

		 List<Corso> corsiF = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection(); // devi fare la classe connection
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery(); // qui ho i risultati della query che ho fatto

			while (rs.next()) {
				Corso c= new Corso(rs.getNString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsiF.add(c);
				
			}
			conn.close();
			return corsiF;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
}
