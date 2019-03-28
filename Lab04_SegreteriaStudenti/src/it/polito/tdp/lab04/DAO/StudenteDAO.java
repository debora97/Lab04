package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
//SOLUZIONE1 (MODEL)
	public List<Studente> getTuttiStudenti() {
		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection(); // devi fare la classe connection
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery(); // qui ho i risultati della query che ho fatto

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("cds"));
				studenti.add(s);
			}
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

//SOLUZIONE 2 (MODEL)
	public Studente studenteByDB(int matricola) {
		final String sql = "SELECT * FROM studente WHERE matricola=?";

		// List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection(); // devi fare la classe connection
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery(); // qui ho i risultati della query che ho fatto

			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("cds"));
				return s;
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}

	public List<Studente> getStudentiCorso(String codCorso) {
		final String sql = "SELECT DISTINCT studente.* \n" + 
				"FROM studente,iscrizione,corso \n" + 
				"WHERE  studente.matricola=iscrizione.matricola AND corso.codins=iscrizione.codins AND corso.codins=?";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection(); // devi fare la classe connection
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codCorso);
			

			ResultSet rs = st.executeQuery(); // qui ho i risultati della query che ho fatto

			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("cds"));
				studenti.add(s);

			}
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	
	
	

	
}
