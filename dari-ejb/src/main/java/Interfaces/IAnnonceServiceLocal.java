package Interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.Annonce;

 
@Local
public interface IAnnonceServiceLocal {
	
	List<Annonce> getAllAnnonces(); 
	public Annonce getAnnonceByID(int id) ;

}
