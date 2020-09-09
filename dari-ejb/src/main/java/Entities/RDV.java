package Entities;

import java.util.Date;

public class RDV {
	
	private int id,visiteurId,AnnonceId;
	
	private User visiteur ;
	private Annonce annonce;
	private Date date;
	private stateRDV state ;
	
	
	
	
	
	public User getVisiteur() {
		return visiteur;
	}
	public void setVisiteur(User visiteur) {
		this.visiteur = visiteur;
	}
	public Annonce getAnnonce() {
		return annonce;
	}
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RDV other = (RDV) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVisiteurId() {
		return visiteurId;
	}
	public void setVisiteurId(int visiteurId) {
		this.visiteurId = visiteurId;
	}
	public int getAnnonceId() {
		return AnnonceId;
	}
	public void setAnnonceId(int annonceId) {
		AnnonceId = annonceId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public stateRDV getState() {
		return state;
	}
	public void setState(stateRDV state) {
		this.state = state;
	}
	
	
	

}
