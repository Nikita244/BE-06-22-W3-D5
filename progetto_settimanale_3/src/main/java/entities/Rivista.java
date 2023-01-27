package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Rivista extends Catalogo {

    @Enumerated(EnumType.STRING)
    private Periodic periodic;

    //getter setter
    public Periodic getPeriodicita() {
        return periodic;
    }

    public void setPeriodicita(Periodic periodicita) {
        this.periodic = periodicita;
    }
    
    @Override
    public String toString() {
        return "Titolo: " + this.titolo + "\n Anno di pubblicazione: " + this.annoPub + "\n Periodicita: " + this.periodic;
    }

}

