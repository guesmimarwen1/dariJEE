package Services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import Entities.RDV;
import Entities.User;
import Entities.stateRDV;
import Interfaces.IRDVLocal;
import Interfaces.IRDVRemote;
import Utils.Paths;

@Stateless
@LocalBean
public class RDVService implements IRDVLocal,IRDVRemote {

	@Override
	public void addRendezVous(RDV rdv,String date) {
		Client client = ClientBuilder.newClient();
		String data = "?annonceId="+rdv.getAnnonceId()+"&userId="+rdv.getVisiteurId()+"&date="+date;
		System.out.println(Paths.BaseURL + "addRendezVous"+data);
		WebTarget web = client.target(Paths.BaseURL +  "addRendezVous"+data);
		Response response = web.request().get();
		response.readEntity(String.class);
		
	}

	@Override
	public void editRendezVous(RDV rdv) {
		Client client = ClientBuilder.newClient();
		String data = "?id="+rdv.getAnnonceId()+"&date="+rdv.getDate();
		System.out.println(Paths.BaseURL + "editRendezVous"+data);
		WebTarget web = client.target(Paths.BaseURL +  "editRendezVous"+data);
		Response response = web.request().get();
		response.readEntity(String.class);
		
	}

	@Override
	public void cancelRendezVous(RDV rdv) {
		Client client = ClientBuilder.newClient();
		String data = "?id="+rdv.getId();
		System.out.println(Paths.BaseURL + "cancelRendezVous"+data);
		WebTarget web = client.target(Paths.BaseURL +  "cancelRendezVous"+data);
		Response response = web.request().get();
		response.readEntity(String.class);
		
	}

	@Override
	public void acceptRendezVous(RDV rdv) {
		Client client = ClientBuilder.newClient();
		String data = "?id="+rdv.getId();
		System.out.println(Paths.BaseURL + "acceptRendezVous"+data);
		WebTarget web = client.target(Paths.BaseURL +  "acceptRendezVous"+data);
		Response response = web.request().get();
		response.readEntity(String.class);
		
	}

	@Override
	public void refusRendezVous(RDV rdv) {
		Client client = ClientBuilder.newClient();
		String data = "?id="+rdv.getId();
		System.out.println(Paths.BaseURL + "refusRendezVous"+data);
		WebTarget web = client.target(Paths.BaseURL +  "refusRendezVous"+data);
		Response response = web.request().get();
		response.readEntity(String.class);
		
	}

	@Override
	public ArrayList<RDV> GetMesRendezVous(int id) {
		ArrayList<RDV> cList = new ArrayList<RDV>();
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target(Paths.BaseURL + "GetMesRendezVous/"+id);
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();
		UserService us = new UserService();
		AnnonceService as = new AnnonceService();
		User user = us.getUserByID(id);
		for (int i = 0; i < object.size(); i++) {
			RDV c = new RDV();
			String jsonDate = object.getJsonObject(i).getString("date");
			jsonDate = jsonDate.replace("/Date(", "");
			jsonDate = jsonDate.replace(")/", "");
			Date time = new Date(Long.parseLong(jsonDate));
			c.setDate(time);
			c.setId(object.getJsonObject(i).getInt("id"));
			c.setVisiteurId(object.getJsonObject(i).getInt("visiteurID"));
			c.setAnnonceId(object.getJsonObject(i).getInt("annonceID"));
			c.setVisiteur(user);
			c.setAnnonce(as.getAnnonceByID(object.getJsonObject(i).getInt("annonceID")));
			if(object.getJsonObject(i).getInt("state")== 0) {
				c.setState(stateRDV.demand);
			} else if(object.getJsonObject(i).getInt("state")== 1){
				c.setState(stateRDV.accepted);
			} else if(object.getJsonObject(i).getInt("state")== 2){
				c.setState(stateRDV.refused);
			}else {
				c.setState(stateRDV.canceled);
			}
			cList.add(c);
		}
		return cList;
	}

}
