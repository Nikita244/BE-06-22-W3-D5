package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "ricercaTessera", query = "SELECT pr FROM Prestito pr WHERE pr.utente.numTessera = :numeroTessera")
@NamedQuery(name = "ricercaPrestitiScaduti", query = "SELECT p FROM Prestito p WHERE p.restituzioneEffettiva < CURRENT_DATE ")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private Catalogo prestato;


    private LocalDate inizioPrestito;


    private LocalDate restituzionePrevista;


    private LocalDate restituzioneEffettiva;
    


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public Catalogo getPrestato() {
		return prestato;
	}


	public void setPrestato(Catalogo prestato) {
		this.prestato = prestato;
	}


	public LocalDate getInizioPrestito() {
		return inizioPrestito;
	}


	public void setInizioPrestito(LocalDate inizioPrestito) {
		this.inizioPrestito = inizioPrestito;
	}


	public LocalDate getRestituzionePrevista() {
		return restituzionePrevista;
	}


	public void setRestituzionePrevista(LocalDate restituzionePrevista) {
		this.restituzionePrevista = restituzionePrevista;
	}


	public LocalDate getRestituzioneEffettiva() {
		return restituzioneEffettiva;
	}


	public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
		this.restituzioneEffettiva = restituzioneEffettiva;
	}
	
	@Override
	public String toString() {
		return "Numero tessera: "  + getUtente().getNumTessera() + "\n Elemento prestato: " + getPrestato().toString() + "\n Inizio del prestito: " + this.inizioPrestito + "\n Restituzione prevista: " + this.restituzionePrevista + "\n";
	}
}
