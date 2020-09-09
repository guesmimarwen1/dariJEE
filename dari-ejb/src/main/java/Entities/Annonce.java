package Entities;

public class Annonce {

	private int id,chambres,clientId;
	private String description,adress;
	
	private User client;
	
	
	
	
	public User getClient() {
		return client;
	}


	public void setClient(User client) {
		this.client = client;
	}


	public Annonce() {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChambres() {
		return chambres;
	}
	public void setChambres(int chambres) {
		this.chambres = chambres;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
}
