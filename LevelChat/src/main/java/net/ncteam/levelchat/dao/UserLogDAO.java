package net.ncteam.levelchat.dao;

import java.util.List;
import net.ncteam.levelchat.domain.UsersLog;

public interface UserLogDAO {

	public String addUser(UsersLog userLog);

	public List<UsersLog> listUser();

	public void removeUser(Integer iduserlog);
	
	public boolean existUser(UsersLog userLog); 
	
	public List<String> getMessages(String username);
	
	public List<String> getMessages(String username,int mid);
	
	public void addMessage(String username, String message, int mid);

}