package gestioneDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.JpaUtil;
import entities.Catalogo;
import entities.Libro;
import entities.Periodic;
import entities.Rivista;

public class CatalogoDAO extends JpaUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogoDAO.class);

	public static void salvataggio(Catalogo object) {
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

	public void refresh(Catalogo object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.refresh(object);
			System.out.println("Refresh eseguito correttamente!");
		} finally {
			em.close();
		}
	}

	public Catalogo getById(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
		
			return em.find(Catalogo.class, id);
			
		} finally {
			em.close();
		}

	}

	public void delete(Catalogo object) {
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
	
	public Libro getByIsbn(int isbn) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
           
            return em.createQuery("SELECT l FROM Libro l WHERE l.codiceISBN = :isbn", Libro.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("Nessun elemento trovato con ISBN: " + isbn);
            return null;
        } finally {
            em.close();
        }
    }

    public Libro getByAnnoPub(int anno) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
 
            return em.createQuery("SELECT l FROM Libro l WHERE l.annoPub = :annoPub", Libro.class)
                .setParameter("annoPub", anno)
                .getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("Nessun elemento trovato con anno: " + anno);
            return null;
        } finally {
            em.close();
        }
    }
    
    public static Libro creaLibro(String titolo, int anno, int pagine, String autore, String genere, int ISBN ) {
        Libro l = new Libro();

        l.setTitolo(titolo);
        l.setAnnoPub(anno);
        l.setNumPag(pagine);
        l.setAutore(autore);
        l.setGenere(genere);
        l.setCodiceISBN(ISBN);

        return l;
    }

    public static Rivista creaRivista(String titolo, int anno, int pagine, Periodic prd, int ISBN ) {
        Rivista r = new Rivista();

        r.setTitolo(titolo);
        r.setAnnoPub(anno);
        r.setNumPag(pagine);
        r.setPeriodicita(prd);
        r.setCodiceISBN(ISBN);

        return r;
    }

	public void ricercaElementoPerAutore(String autore) {
		Query query = em.createNamedQuery("ricercaAutore");
		query.setParameter("autore", autore);
		List<Libro> libri = query.getResultList();
		System.out.println("Cerco libri scritti da: " + autore);
		if(libri.isEmpty()) {
			System.out.println( "Nessun libro trovato!!" );
		} else {
			for(Libro l: libri) {
				System.out.println(l);
			}
		}
		
	}

	public static void ricercaElementoPerAnno(int anno) {

		Query query = em.createNamedQuery("ricercaAnno");
		query.setParameter("annoPub", anno);
		List<Catalogo> elemento = query.getResultList();
		for (Catalogo e : elemento) {
            System.out.println(e.toString());
        }

		if (elemento.isEmpty()) {
			System.out.println("Nessun testo trovato!!");
		
			}

		}
	
	public void ricercaElementoPerTitolo(String titolo) {
		
		Query query = em.createNamedQuery("ricercaTitolo");
		query.setParameter("titolo", "%"+titolo+"%");
		List<Catalogo> elemento = query.getResultList();
		System.out.println("Cerco testi con titolo: " + titolo);
		if(elemento.isEmpty()) {
			System.out.println( "Nessun elemento trovato!!" );
		} else {
			for(Catalogo e: elemento) {
				System.out.println(e);
			}
		}
	}
	


}
