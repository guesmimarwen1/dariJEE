package Interfaces;

import java.util.ArrayList;

import javax.ejb.Local;

import Entities.RDV;

@Local
public interface IRDVLocal {
	public void addRendezVous(RDV rdv,String date);
	public void editRendezVous(RDV rdv);
	public void cancelRendezVous(RDV rdv);
	public void acceptRendezVous(RDV rdv);
	public void refusRendezVous(RDV rdv);
	public ArrayList<RDV> GetMesRendezVous(int id);
}
