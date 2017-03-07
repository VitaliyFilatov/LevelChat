package net.ncteam.levelchat.service;

import java.util.List;

import net.ncteam.levelchat.domain.UserInfo;
import net.ncteam.levelchat.domain.UsersLog;

public interface UserLogService {

	public String addUser(UserInfo userInfo);

	public List<UsersLog> listUser();

	public void removeUser(Integer iduserlog);
	
    public List<String> getMessages(String username);
    
    public List<String> getMessages(String username, int mid);
    
    public void addMessage(String username, String message, int mid);
}