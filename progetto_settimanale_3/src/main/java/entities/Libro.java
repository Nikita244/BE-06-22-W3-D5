package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "ricercaAutore", query = "SELECT l FROM Libro l WHERE l.autore = :autore")
public class Libro extends Catalogo{
	
    private String autore;
    private String genere;
    

    
    public String getAutore() {
        return autore;
    }
    
    public void setAutore(String autore) {
        this.autore = autore;
    }
    
    public String getGenere() {
        return genere;
    }
    
    public void setGenere(String genere) {
        this.genere = genere;
    }
    
    @Override
	public String toString() {
		return "Titolo: " + this.getTitolo() + "\n Anno pubblicazione: " + this.getAnnoPub() + "\n Pagine: " + this.getNumeroPagine() + "\n Autore: " + this.autore + "\n Genere: " + this.genere + "\n Codice ISBN: " + this.getCodiceISBN();
	}

	private Integer getNumeroPagine() {
	
		return numPag;
	}
}

/*I libri hanno inoltre:
- Autore
- Genere*/
