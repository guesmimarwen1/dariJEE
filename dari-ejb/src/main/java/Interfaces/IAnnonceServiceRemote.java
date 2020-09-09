package Interfaces;

import java.util.List;


import javax.ejb.Remote;

import Entities.Annonce;

 
@Remote
public interface IAnnonceServiceRemote {

	List<Annonce> getAllAnnonces(); 
	public Annonce getAnnonceByID(int id);

}
