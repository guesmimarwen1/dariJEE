package managedBeans;



import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import Entities.User;
import Services.UserService;

@ManagedBean(name = "loginBean")
@ApplicationScoped
public class LoginBean  {

 
	public String email;
	public String password;
	private static User user;
	public Boolean loggedIn;


	@EJB
	UserService us;
	
	
	
	public String doLogin()   {
		String navigateTo = "null";
		System.out.println("========================================================");
		System.out.println(email);
		System.out.println("========================================================");
		System.out.println(password);
		System.out.println("========================================================");
		user = us.Login(this.email, this.password);
		System.out.println(user.Email);
		navigateTo = redirectUser(navigateTo);
		return navigateTo;
	}

	private String redirectUser(String navigateTo) {
		if (user != null) {

			navigateTo = "indexAnnonce?faces-redirect=true";
			loggedIn = true;
		}

		else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));

		}
		return navigateTo;
	}

	public static User getCurrentUser() {
		return user;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
