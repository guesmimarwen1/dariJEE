package Interfaces;

import java.util.ArrayList;

import javax.ejb.Local;

import Entities.User;

@Local
public interface IUserLocal {
	public User Login(String mail , String pwd);
	public User getUserByID(int id);
	public ArrayList<User> getUsers();
}
