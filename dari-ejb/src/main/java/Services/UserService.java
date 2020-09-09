package Services;



import javax.ws.rs.client.WebTarget;



import java.io.StringReader;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.core.Response;

import Entities.User;
import Interfaces.IUserLocal;
import Interfaces.IUserRemote;
import Utils.Paths;



@Stateless
@LocalBean
public class UserService implements IUserLocal,IUserRemote{

	@Override
	public User Login(String mail, String pwd) {
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target(Paths.BaseURL + "Login");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();
		System.out.println("iiiiiiiiiinnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		for (int i = 0; i < object.size(); i++) {
			User u = new User();
			System.out.println("iiiiiii99999999999999999999999999999999999999999999999999999999999nnnnnnnnnnnnnn");
			if(object.getJsonObject(i).getString("Email").equals(mail)) {
				System.out.println("iiiiiiiiiinnnn");
				u.setId(object.getJsonObject(i).getInt("Id"));
				u.setFirstName(object.getJsonObject(i).getString("Nom"));
				u.setLastName(object.getJsonObject(i).getString("Prenom"));
				u.setEmail(object.getJsonObject(i).getString("Email"));
				return u ;
			}
		}
		return null;	
	}
	
	@Override
	public User getUserByID(int id) {
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target(Paths.BaseURL + "Login");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();
		for (int i = 0; i < object.size(); i++) {
			User u = new User();
			if(object.getJsonObject(i).getInt("Id") == id) {
				u.setId(object.getJsonObject(i).getInt("Id"));
				u.setFirstName(object.getJsonObject(i).getString("Nom"));
				u.setLastName(object.getJsonObject(i).getString("Prenom"));
				u.setEmail(object.getJsonObject(i).getString("Email"));
			}
		}
		return null;	
	}
	
	
	@Override
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		Client client = ClientBuilder.newClient();
		System.out.println(Paths.BaseURL + "Login");
		WebTarget web = client.target(Paths.BaseURL + "Login");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		System.out.println(result);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();
		for (int i = 0; i < object.size(); i++) {
			User u = new User();
			u.setId(object.getJsonObject(i).getInt("Id"));
			u.setFirstName(object.getJsonObject(i).getString("Nom"));
			u.setLastName(object.getJsonObject(i).getString("Prenom"));
			u.setEmail(object.getJsonObject(i).getString("Email"));
			users.add(u);

		}
		return users;	
	}
}