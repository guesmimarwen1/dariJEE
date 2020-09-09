package managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import Entities.Annonce;
import Services.AnnonceService;

@ManagedBean(name = "AnnnonceIndexBean")
@ApplicationScoped
public class AnnnonceIndexBean {

	
	List<Annonce> list = new ArrayList<>();
	
	
	@EJB
	public AnnonceService as ;
	
	
	@PostConstruct
	public void init() {
		list = as.getAllAnnonces();
		
	}


	public List<Annonce> getList() {
		return list =as.getAllAnnonces();
	}


	public void setList(List<Annonce> list) {
		this.list = list;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
