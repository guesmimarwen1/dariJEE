package managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import Entities.Annonce;
import Entities.RDV;
import Services.RDVService;

@ManagedBean(name = "MesRendezVousBean")
@ApplicationScoped
public class MesRendezVous {
	
	
	
	private List<RDV> list ;
	
	
	@EJB
	RDVService rs ;
	
	public String acceptRDV(RDV rdv) {
		if(LoginBean.getCurrentUser() != null) {
			rs.acceptRendezVous(rdv);
			return "";
		}
		return "login?faces-redirect=true";
	}
	
	public String refusRDV(RDV rdv) {
		if(LoginBean.getCurrentUser() != null) {
			rs.refusRendezVous(rdv);
			return "";
		}
		return "login?faces-redirect=true";
	}
	
	public String cancelRDV(RDV rdv) {
		if(LoginBean.getCurrentUser() != null) {
			rs.cancelRendezVous(rdv);
			return "";
		}
		return "login?faces-redirect=true";
	}
	
	public String checkUser() {
		if(LoginBean.getCurrentUser() != null) {
			return "";
		}
		return "login?faces-redirect=true";
	}
	
	public List<RDV> getList() {
		checkUser();
		if(LoginBean.getCurrentUser() != null) {
			return list = rs.GetMesRendezVous(LoginBean.getCurrentUser().getId());
		}
		return list = rs.GetMesRendezVous(1);
	}


	public void setList(List<RDV> list) {
		this.list = list;
	}
	
	

}
