package app;

import java.time.LocalDate;
import java.util.ArrayList;


import entities.Libro;
import entities.Periodic;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import gestioneDAO.CatalogoDAO;
import gestioneDAO.PrestitoDAO;
import gestioneDAO.UtenteDAO;

public class Main {
	
	static UtenteDAO uDao = new UtenteDAO();
    static PrestitoDAO pDao = new PrestitoDAO();
    static CatalogoDAO cDao = new CatalogoDAO();


    public static void main(String[] args) {
    	
	    //aggiungi();
    	
    	//cercaPerAutore();
    	
    	//cercaPerAnno();

    	//cercaTitolo();
    
    	//cercaPerTessera();
    
    	ricercaPrestitiScaduti();
    	
    }

    
    
    @SuppressWarnings("static-access")
	private static void aggiungi() {
    	
		    	// libri
				Libro l1 = cDao.creaLibro("Lo Straniero", 1942, 100, "Albert Camus", "Narrativa", 2227568);
				Libro l2 = cDao.creaLibro("Il Piccolo Principe", 1943, 75, "Antoine de Saint-Exupery", "Fantasy", 222475);
				Libro l3 = cDao.creaLibro("Il Loto Blu", 1934, 225, "Herge", "Avventura", 22247);
				cDao.salvataggio(l1);
				cDao.salvataggio(l2);
				cDao.salvataggio(l3);
				
				
    			// riviste
    			Rivista r1 = cDao.creaRivista("Trekking", 2022, 30, Periodic.SETTIMANALE, 111054);
    			Rivista r2 = cDao.creaRivista("Focus", 2020, 65, Periodic.MENSILE, 111875);
    			Rivista r3 = cDao.creaRivista("Vogue", 2021, 300, Periodic.SEMESTRALE, 1112222);
    			cDao.salvataggio(r1);
    			cDao.salvataggio(r2);
    			cDao.salvataggio(r3);
    			
    			
    			// utenti
    			Utente u1 = uDao.creaUtente("Mario", "Rossi", LocalDate.of(1990, 12, 15), "utf890");
    			Utente u2 = uDao.creaUtente("Marco", "Verdi", LocalDate.of(2000, 9, 10), "utpdf00");
    			Utente u3 = uDao.creaUtente("Giovanna", "Neri", LocalDate.of(1976, 3, 23), "ut5i676");
    			uDao.salvataggio(u1);
    			uDao.salvataggio(u2);
    			uDao.salvataggio(u3);
    			
    			
    			// prestito con data di ritiro e data di consegna effettiva
    			Prestito p1 = pDao.creaPrestito(r1, u2, LocalDate.of(2022, 1, 10), LocalDate.of(2022, 2, 15));
    			Prestito p2 = pDao.creaPrestito(r2, u1, LocalDate.of(2021, 10, 15), LocalDate.of(2022, 1, 1));
    			Prestito p3 = pDao.creaPrestito(l1, u3, LocalDate.of(2022, 6, 27), LocalDate.of(2023, 1, 31));
    			pDao.salvataggio(p1);
    			pDao.salvataggio(p2);
    			pDao.salvataggio(p3);
    }
    
    
    
    public static void cercaPerAutore() {
		CatalogoDAO elemento = new CatalogoDAO();
		elemento.ricercaElementoPerAutore("Albert Camus");
	}
    
    public static void cercaPerAnno() {
		CatalogoDAO elemento = new CatalogoDAO();
		elemento.ricercaElementoPerAnno(1934);
	}
    
    public static void cercaTitolo() {
		CatalogoDAO elemento = new CatalogoDAO();
		elemento.ricercaElementoPerTitolo("Focu");
	}
    
    public static void cercaPerTessera() {
		PrestitoDAO prestito = new PrestitoDAO();
		prestito.ricercaTessera("ut5i676");
	}
    
    public static void ricercaPrestitiScaduti() {
		PrestitoDAO prestito = new PrestitoDAO();
		prestito.ricercaPrestitiScaduti();
	}
}



/*L'archivio deve permettere le seguenti operazioni:
- Aggiunta di un elemento del catalogo
- Rimozione di un elemento del catalogo dato un codice ISBN
- Ricerca per ISBN
- Ricerca per anno pubblicazione
- Ricerca per autore
- Ricerca per titolo o parte di esso
- Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
- Ricerca di tutti i prestiti scaduti e non ancora restituiti*/