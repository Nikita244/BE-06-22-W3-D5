package gestioneDAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.JpaUtil;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;

public class PrestitoDAO extends JpaUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PrestitoDAO.class);

	public static void salvataggio(Prestito object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();

			em.persist(object);
		
			t.commit();

			System.out.println("Elemento salvato con successo!");
		} catch (Exception ex) {
			em.getTransaction().rollback();

			logger.error("Error occurred: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public void refresh(Prestito object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.refresh(object);
			System.out.println("Refresh eseguito correttamente!");
		} finally {
			em.close();
		}
	}

	public Prestito getById(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		
			return em.find(Prestito.class, id);
			
		} finally {
			em.close();
		}

	}

	public void delete(Prestito object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
	
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(object);

			transaction.commit();
		
			System.out.println("Rimozione eseguita con successo!");
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error occurred: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}
	
	public static Prestito creaPrestito(Rivista oggetto, Utente ut, LocalDate inizio, LocalDate restituito) {
        Prestito p = new Prestito();

        p.setPrestato(oggetto);
        p.setUtente(ut);
        p.setInizioPrestito(inizio);
        p.setRestituzionePrevista(inizio.plusDays(30));
        p.setRestituzioneEffettiva(restituito);

        return p;
    }

    public static Prestito creaPrestito(Libro oggetto, Utente ut, LocalDate inizio, LocalDate restituito) {
        Prestito p = new Prestito();

        p.setPrestato(oggetto);
        p.setUtente(ut);
        p.setInizioPrestito(inizio);
        p.setRestituzionePrevista(inizio.plusDays(30));
        p.setRestituzioneEffettiva(restituito);

        return p;
    }
    
    public void ricercaTessera(String numeroTessera) {
		
		Query query = em.createNamedQuery("ricercaTessera");
		query.setParameter("numeroTessera", numeroTessera);
		List<Prestito> prestito = query.getResultList();
		System.out.println("Cerca elementi in prestito: " + numeroTessera);
		System.out.println(" ");
		if(prestito.isEmpty()) {
			System.out.println( "Nessun elemento in prestito trovato." );
		} else {
			for(Prestito p: prestito) {
				System.out.println(p);
			}
			
		}
    }
    
    public void ricercaPrestitiScaduti() {
		
		Query query = em.createNamedQuery("ricercaPrestitiScaduti");
		List<Prestito> prestito = query.getResultList();
		System.out.println("Ricerca consegne scadute: ");
		System.out.println(" ");
		if(prestito.isEmpty()) {
			System.out.println( "Nessuna consegna scaduta!" );
		} else {
			for(Prestito p: prestito) {
				System.out.println(p);
			}
		}
		
    }

}
