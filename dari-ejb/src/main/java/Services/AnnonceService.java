package Services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import Entities.Annonce;
import Interfaces.*;
import Utils.Paths;

@Stateful
@LocalBean
public class AnnonceService implements IAnnonceServiceLocal, IAnnonceServiceRemote {

	

	@Override
	public List<Annonce> getAllAnnonces() {
		List<Annonce> cList = new ArrayList<Annonce>();
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target(Paths.BaseURL + "getAllAnnonces");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {
			Annonce c = new Annonce();
			c.setAdress(object.getJsonObject(i).getString("address"));
			c.setDescription(object.getJsonObject(i).getString("Description"));
			c.setId(object.getJsonObject(i).getInt("AnnonceId"));
			c.setChambres(object.getJsonObject(i).getInt("chambres"));
			c.setClientId(object.getJsonObject(i).getInt("clientId"));
			UserService us = new UserService();
			c.setClient(us.getUserByID(object.getJsonObject(i).getInt("clientId")));
			cList.add(c);
		}
		return cList;
	}

	
	public Annonce getAnnonceByID(int id) {
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target(Paths.BaseURL + "getAllAnnonces");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();
		for (int i = 0; i < object.size(); i++) {
			Annonce c = new Annonce();
			if(object.getJsonObject(i).getInt("AnnonceId") == id) {
				c.setId(object.getJsonObject(i).getInt("AnnonceId"));
				c.setAdress(object.getJsonObject(i).getString("address"));
				c.setDescription(object.getJsonObject(i).getString("Description"));
				c.setId(object.getJsonObject(i).getInt("AnnonceId"));
				c.setChambres(object.getJsonObject(i).getInt("chambres"));
				c.setClientId(object.getJsonObject(i).getInt("clientId"));
				UserService us = new UserService();
				c.setClient(us.getUserByID(object.getJsonObject(i).getInt("clientId")));
				return c ;
				
			}
		}
		return null;	
	}
	

}
