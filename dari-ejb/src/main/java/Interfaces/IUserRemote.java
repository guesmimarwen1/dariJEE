package Interfaces;

import java.util.ArrayList;


import javax.ejb.Remote;

import Entities.User;

@Remote
public interface IUserRemote {
	public User Login(String mail , String pwd);
	public User getUserByID(int id);
	public ArrayList<User> getUsers();
}
