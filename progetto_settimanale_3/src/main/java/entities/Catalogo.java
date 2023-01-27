package entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Categoria")
@NamedQuery(name = "ricercaAnno", query = "SELECT e FROM Catalogo e WHERE e.annoPub = :annoPub")
@NamedQuery(name = "ricercaTitolo", query = "SELECT e FROM Catalogo e WHERE e.titolo LIKE :titolo")
public abstract class Catalogo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(name = "codice_isbn")
	protected int codiceISBN;

	protected String titolo;
	@Column(name = "anno_pubblicazione")
	protected int annoPub;
	@Column(name = "numero_pagine")
	protected int numPag;
	
	@OneToMany(mappedBy = "prestato")
	protected List<Prestito> prestiti;
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCodiceISBN() {
		return codiceISBN;
	}


	public void setCodiceISBN(int codiceISBN) {
		this.codiceISBN = codiceISBN;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public int getAnnoPub() {
		return annoPub;
	}


	public void setAnnoPub(int annoPub) {
		this.annoPub = annoPub;
	}


	public Integer getNumPag() {
		return numPag;
	}

	public void setNumPag(Integer numPag) {
		this.numPag = numPag;
	}

	public List<Prestito> getPrestiti() {
		return prestiti;
	}

	public void setPrestiti(List<Prestito> prestiti) {
		this.prestiti = prestiti;
	}


	@Override
	public String toString() {
		return "Catalogo [codiceISBN=" + codiceISBN + "\n titolo=" + titolo + "\n annoPub=" + annoPub + "\n numPag="
				+ getNumPag() + "\n prestiti=" + prestiti + "]";
	}

	
}

/*
 * Creare le classi necessarie a gestire un catalogo bibliotecario. Il catalogo
 * è formato da elementi che possono essere Libri o Riviste. Sia Libri che
 * riviste hanno i seguenti attributi: - Codice ISBN (codice univoco) - Titolo -
 * Anno pubblicazione - Numero pagine
 */