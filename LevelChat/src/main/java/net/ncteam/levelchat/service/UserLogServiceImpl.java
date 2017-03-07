package net.ncteam.levelchat.service;
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.ncteam.levelchat.dao.UserLogDAO;
import net.ncteam.levelchat.domain.UserInfo;
import net.ncteam.levelchat.domain.UsersLog;
 
@Service
public class UserLogServiceImpl implements UserLogService {
 
    private UserLogDAO userLogDAO;
    
    public void setUserLogDAO (UserLogDAO userLogDAO)
    {
    	this.userLogDAO = userLogDAO;
    }
 
    public String addUser(UserInfo userInfo) {
        return userLogDAO.addUser(userInfo);
    }
 
    public List<UsersLog> listUser() {
 
        return userLogDAO.listUser();
    }
 
    public void removeUser(Integer iduserlog) {
    	userLogDAO.removeUser(iduserlog);
    }
    
    
    
    public List<String> getMessages(String username) {
    	return userLogDAO.getMessages(username);
    }
    
    public List<String> getMessages(String username, int mid) {
    	return userLogDAO.getMessages(username, mid);
    }
    
    public void addMessage(String username, String messages, int mid) {
    	File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true);
            fr.write("in Service\n");
            fr.close();
           
        } catch (IOException e) {
        }
    	userLogDAO.addMessage(username, messages,mid);
    }
}
