package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Studente;
import java.util.*;

public class Model {
	StudenteDAO daoS = new StudenteDAO();
	CorsoDAO daoC = new CorsoDAO();

	public List<Studente> getStudentiCorso(String codCorso) {
		

		return daoS.getStudentiCorso(codCorso);

	}

	public Studente getStudenteMatricola(int matricola) {

		/*
		 * List<Studente> studenti= dao.getTuttiStudenti();
		 * 
		 * for(Studente s: studenti){ if(s.getMatricola()==matricola) { return s; } }
		 * return null;
		 */
		// Faccio la query su heidi e poi la copio
		// posso far fare la query al DB
		// SOLUZIONE 2
		return daoS.studenteByDB(matricola);

	}

	public List<String> getCorsi() {

		List<String> nomeCorso = new LinkedList<String>();

		for (int i = 0; i < daoC.getTuttiICorsi().size(); i++) {
			nomeCorso.add(daoC.getTuttiICorsi().get(i).getCodins());
			
		}

		return nomeCorso;

	}

	public List<Corso> getCorsiFrequentati(int matricola) {
		return daoC.corsiFreq(matricola);
		
	}

	public boolean isIscritto(int matricola, String codinsCorso) {
		//Studente s=this.getStudenteMatricola(matricola);
		List<Studente> sf=new LinkedList<Studente>();
		sf=this.getStudentiCorso(codinsCorso);
		for(Studente st: sf)
			if(st.getMatricola()==matricola) {
				return true;
			}
		return false;
		
	}

	
}
