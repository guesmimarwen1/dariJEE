package managedBeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import Entities.Annonce;
import Entities.RDV;
import Services.RDVService;

@ManagedBean(name = "PassRDVBean")
@ApplicationScoped
public class PassRDVBean {

	private String date;

	@PostConstruct
	public void init() {

		date = GregorianCalendar.getInstance().getTime().toGMTString();
	}

	public static Annonce annonceToPassRDV;
	@EJB
	RDVService rs;

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String addRendezVoud() {
		if (LoginBean.getCurrentUser() != null) {
			if (PassRDVBean.annonceToPassRDV != null) {
				RDV r = new RDV();
				r.setAnnonceId(PassRDVBean.annonceToPassRDV.getId());
				System.out.println("Date : ======== " + date);
				String sDate5 = "Thu, Dec 31 1998 23:37:50";
				SimpleDateFormat formatter5 = new SimpleDateFormat("dd MMM yyyy HH:mm:ss ");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				try {
					Date date5 = formatter5.parse(date);
					String str = dateFormat.format(date5);
					r.setVisiteurId(LoginBean.getCurrentUser().getId());
					rs.addRendezVous(r,str);
					System.out.println("Insertion Try ************************************************************");
				} catch (ParseException ex) {
					System.out.println("Insertion CAtch ************************************************************");
					r.setDate(new Date());
					r.setVisiteurId(LoginBean.getCurrentUser().getId());
					String str = dateFormat.format(new Date());
					rs.addRendezVous(r,str);
				}
				System.out.println("Insertion Done ************************************************************");
				return "MesRendezVous?faces-redirect=true";
			} else {
				addMessage("choisir Annonce");
				System.out.println("Annonce null ----------------------------------------------------------------");
				return "indexAnnonce?faces-redirect=true";
			}
		} else {
			addMessage("SVP Sign in");
			return "login?faces-redirect=true";
		}
	}

	public static Annonce getAnnonceToPassRDV() {
		return annonceToPassRDV;
	}

	public String setAnnonceToPassRDV(Annonce annonceToPassRDV) {
		PassRDVBean.annonceToPassRDV = annonceToPassRDV;
		System.out.println("Annonce Id ============= :   ===  :" + PassRDVBean.annonceToPassRDV.getId());
		return "PassRDV?faces-redirect=true";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
