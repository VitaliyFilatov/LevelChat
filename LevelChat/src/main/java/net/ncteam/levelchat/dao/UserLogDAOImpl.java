package net.ncteam.levelchat.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.ncteam.levelchat.domain.UsersLog;
import net.ncteam.levelchat.domain.MessageKey;
import net.ncteam.levelchat.domain.Messages;
import net.ncteam.levelchat.domain.Role;
import net.ncteam.levelchat.domain.UserInfo;

import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserLogDAOImpl implements UserLogDAO, UserDetailsService {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory (SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public String addUser(UsersLog userLog) {
		
		if (existUser(userLog))
		{
			Role role=new Role();
			role.setRole("ROLE_USER");
			role.setId(2);
			Set<Role> setRole = new HashSet<Role>();
			setRole.add(role);
			userLog.setRoles(setRole);
			sessionFactory.getCurrentSession().save(userLog);
			return "success";
		}
		else
		{
			return "A user with this login already exist";
		}
	}
	
	public boolean existUser(UsersLog userLog) {
		//return sessionFactory.getCurrentSession().find("from UsersLog where login='"+userLog.getLogin()+"'").isEmpty();
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<UsersLog> listUser() {

		return sessionFactory.getCurrentSession().createQuery("from UsersLog")
			.list();
	}

	public void removeUser(Integer iduserlog) {
		UsersLog userLog = (UsersLog) sessionFactory.getCurrentSession().load(
				UsersLog.class, iduserlog);
		if (null != userLog) {
			sessionFactory.getCurrentSession().delete(userLog);
		}

	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
	       throws UsernameNotFoundException, DataAccessException
	{
		
		/*UsersLog userLog = (UsersLog)sessionFactory.getCurrentSession().createQuery(
				"from UsersLog u where u.login='"
				+username+"'").uniqueResult();*/
		File file = new File("c:/LOGs.txt");
		FileWriter fr=null;
		try
		{
			fr = new FileWriter(file,true);
		}
		catch(IOException e)
		{}
		
		try
		{
	        fr.write("before loading of user");
	        fr.close();
		}
		catch(IOException e)
		{
			
		}
		try
		{
			fr = new FileWriter(file,true);
	        fr.write("lajsndlkja");
	        fr.close();
		}
		catch(IOException e)
		{
			
		}
		
		UserInfo userInfo=null;
		try
		{
			userInfo = (UserInfo)sessionFactory.getCurrentSession().createQuery(
					"u.login, u.passwor, u.roles from UserInfo u where u.login='"
					+username+"'").uniqueResult();
		}
		catch (HibernateException e)
		{
			try
			{
				fr = new FileWriter(file,true);
		        fr.write(e.getMessage());
		        fr.close();
			}
			catch(IOException eio)
			{
				
			}
		}
		finally
		{
			try
			{
				fr = new FileWriter(file,true);
		        fr.write("after all");
		        fr.close();
			}
			catch(IOException eio)
			{
				
			}
		}
		
		try
		{
			fr = new FileWriter(file,true);
	        if(userInfo.getName()==null)
	        {
	            fr.write("we have been there value null after loading of user");
	        }
	        else
	        {
	        	fr.write("we have been there value not null after loading of user");
	        }
	        fr.close();
		}
		catch(IOException e)
		{
			
		}
		
		Set<Role> roles= userInfo.getRoles();
		Iterator<Role> it = roles.iterator();
		Collection<GrantedAuthority> collectionGA = new ArrayList<GrantedAuthority>();
		while (it.hasNext())
		{
			collectionGA.add(new SimpleGrantedAuthority(it.next().getRole()));
		}
		
		UserDetails user = new User(username, userInfo.getPassword(), true, true, true, true, collectionGA);
		return user;
	}
	
	@Transactional
	public List<String> getMessages(String username)
	       throws DataAccessException
	{
		
		Integer idIserLog = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.iduserlog from UsersLog u where u.login='"
				+username+"'").list().get(0);
		Integer idChat = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.idchat from UserInfo u where u.iduserlog='"
				+idIserLog+"'").list().get(0);
		Integer idMess = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.id_mess from Chat u where u.idchat='"
				+idChat+"'").list().get(0);
		List<String> messages = sessionFactory.getCurrentSession().createQuery(
				"select u.message from Messages u where u.pk_idmess.id_mess='"
				+idMess+"'").list();
		return messages;
	}
	
	@Transactional
	public List<String> getMessages(String username, int mid)
	       throws DataAccessException
	{
		
		Integer idIserLog = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.iduserlog from UsersLog u where u.login='"
				+username+"'").list().get(0);
		Integer idChat = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.idchat from UserInfo u where u.iduserlog='"
				+idIserLog+"'").list().get(0);
		Integer idMess = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.id_mess from Chat u where u.idchat='"
				+idChat+"'").list().get(0);
		List<String> messages = sessionFactory.getCurrentSession().createQuery(
				"select u.message from Messages u where u.pk_idmess.id_mess='"
				+idMess+"' and u.pk_idmess.id>='"+mid+"'").list();
		return messages;
	}
	
	@Transactional
	public void addMessage(String username, String message, int mid)
	       throws DataAccessException
	{
		File file = new File("c:/LOGs.txt");
        FileWriter fr = null;
		Integer idIserLog = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.iduserlog from UsersLog u where u.login='"
				+username+"'").list().get(0);
		Integer idChat = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.idchat from UserInfo u where u.iduserlog='"
				+idIserLog+"'").list().get(0);
		Integer idMess = (Integer)sessionFactory.getCurrentSession().createQuery(
				"select u.id_mess from Chat u where u.idchat='"
				+idChat+"'").list().get(0);
		MessageKey mk = new MessageKey();
		mk.setId_mess(idMess);
		mk.setId(mid+1);
		/*Messages mes = new Messages();
		mes.setMessageKey(mk);
		mes.setMessage(message);
		mes.setRecepient("a");
		sessionFactory.getCurrentSession().save(mes);*/
	}
	
	
}
