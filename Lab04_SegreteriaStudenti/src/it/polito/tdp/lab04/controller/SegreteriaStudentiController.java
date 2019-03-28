package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	Model model = new Model();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> comboCorsi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	@FXML
	private TextArea txtCorsi;

	@FXML
	void doCercaCorsi(ActionEvent event) {
		int matricola;
		String codinsCorso;
		List<Corso> corsiFreq = new LinkedList<Corso>();
		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtCorsi.clear();
			txtCorsi.appendText("Matricola inserita nel modo errato");
			return;

		}
		try {// prendo il corsodalla combobox
			codinsCorso = this.comboCorsi.getValue();
		} catch (NumberFormatException e) {
			txtCorsi.clear();
			txtCorsi.appendText("Corso inserito nel modo errato");
			return;

		}
		Studente st = this.model.getStudenteMatricola(matricola);
		if (st == null) {
			txtCorsi.clear();
			txtCorsi.appendText("studente non esistente nel DB \n");
		}
		if (st != null && codinsCorso == null) {

			corsiFreq = this.model.getCorsiFrequentati(matricola);
			txtCorsi.clear();
			txtCorsi.appendText(corsiFreq.toString());
		}

		if (st != null && codinsCorso != null) {
			// 1 se è iscritto scrivilo
			boolean iscritto = false;
			iscritto = this.model.isIscritto(matricola, codinsCorso);
			if (iscritto == true) {
				txtCorsi.clear();
				txtCorsi.appendText("lo studente con la matricola" + matricola + " e' iscritto al corso " + codinsCorso);
				
			}else { txtCorsi.clear();
				txtCorsi.appendText("lo studente con la matricola" + matricola + " NON e' iscritto al corso " + codinsCorso);
			}
			// 2 se il corso non ha persone
			List<Studente> studentiCorso = new LinkedList<Studente>();
			studentiCorso = this.model.getStudentiCorso(codinsCorso);
			if (studentiCorso == null) {
				txtCorsi.clear();
				txtCorsi.appendText("il corso selezionato non ha iscritti");
			} // 3 se la matricola non frequenta
			if (corsiFreq == null) {
				txtCorsi.clear();
				txtCorsi.appendText("la matricola selezionata non è iscritta a nessuno dei corso ");
			}

		}

	}

	@FXML
	void doCercaIscritti(ActionEvent event) {
		String codinsCorso;

		try {// prendo il corsodalla combobox
			codinsCorso = this.comboCorsi.getValue();
		} catch (NumberFormatException e) {
			txtCorsi.clear();
			txtCorsi.appendText("Corso inserito nel modo errato");
			return;

		}
		// faccio il metodo per restituirel'elenco degli studenti iscritti ai corsi
		List<Studente> studentiCorso = new LinkedList<Studente>();
		if (codinsCorso != null) {
			studentiCorso = this.model.getStudentiCorso(codinsCorso);
			txtCorsi.clear();
			txtCorsi.appendText(studentiCorso.toString());
			// txtCorsi.setText(studentiCorso.toString()+"\n");
		} else
			txtCorsi.appendText("Nussun corso scelto ");

	}

	@FXML
	void doIscrivi(ActionEvent event) {

	}

	@FXML
	void doNomeCognome(ActionEvent event) {
		int matricola;
		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtCorsi.appendText("Matricola inserita nel modo errato");
			return;

		}

		// posso inserire altri controlli per la matricola
		// immagino che il modello mi restituisca il nome e il cognome di uno studente
		// oppure un oggetto studente e poi mi prendo nome e cognome
		Studente st = this.model.getStudenteMatricola(matricola);
		if (st != null) {
			txtNome.clear();
			txtNome.appendText(st.getNome());
			txtCognome.clear();
			txtCognome.appendText(st.getCognome());
		} else {
			txtNome.clear();
			
			txtCognome.clear();
			txtCorsi.setText("la matricola non è presente nel DB");
		}
			

	}

	@FXML
	void doReset(ActionEvent event) {
		txtCorsi.clear();
		txtNome.clear();
		txtCognome.clear();
		

	}

	@FXML
	void initialize() {
		assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCorsi != null : "fx:id=\"txtCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		comboCorsi.getItems().addAll(model.getCorsi());
	}
}
