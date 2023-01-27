package gestioneDAO;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.JpaUtil;

import entities.Utente;

public class UtenteDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UtenteDAO.class);

	public static void salvataggio(Utente object) {
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

	public void refresh(Utente object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.refresh(object);
			System.out.println("Refresh eseguito correttamente!");
		} finally {
			em.close();
		}
	}

	public Utente getById(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		
			return em.find(Utente.class, id);
			
		} finally {
			em.close();
		}

	}

	public void delete(Utente object) {
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
	
	public static Utente creaUtente(String nome, String cognome, LocalDate nascita, String tessera ) {
        Utente u = new Utente();

        u.setNome(nome);
        u.setCognome(cognome);
        u.setDataNascita(nascita);
        u.setNumTessera(tessera);

        return u;
    }

}
