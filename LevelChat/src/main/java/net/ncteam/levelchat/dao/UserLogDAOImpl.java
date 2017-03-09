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
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserLogDAOImpl implements UserDetailsService, UserLogDAO  {

	private SessionFactory sessionFactory;
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	public void setSessionFactory (SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public void setBcryptEncoder(BCryptPasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

	@Transactional
	public String addUser(UserInfo userInfo) {
		
		if (existUser(userInfo))
		{
			userInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
			Role role=new Role();
			role.setRole("ROLE_USER");
			role.setId(1);
			Set<Role> setRole = new HashSet<Role>();
			setRole.add(role);
			userInfo.setRoles(setRole);
			try
			{
				sessionFactory.getCurrentSession().save(userInfo);
				return "success";
			}
			catch(HibernateException e)
			{
				return e.getMessage();
			}
		}
		else
		{
			return "A user with this login already exist";
		}
	}
	
	@Transactional
	public boolean existUser(UserInfo userInfo) {
		//return sessionFactory.getCurrentSession().find("from UsersLog where login='"+userLog.getLogin()+"'").isEmpty();
		//return true;
		return sessionFactory.getCurrentSession().createQuery("from UsersLog where login='"
		+userInfo.getLogin()+"'").list().isEmpty();
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

	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String strQuery = "from UserInfo u where u.login='"+username+"'";
		UserInfo userInfo=null;
		Set<Role> roles=null;
		try
		{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(strQuery);
			userInfo = (UserInfo)query.uniqueResult();
			roles= userInfo.getRoles();
		}
		catch (HibernateException e)
		{
			e=e;
		}
	
		Iterator<Role> it = roles.iterator();
		Collection<GrantedAuthority> collectionGA = new ArrayList<GrantedAuthority>();
		while (it.hasNext())
		{
			collectionGA.add(new SimpleGrantedAuthority(it.next().getRole()));
		}
		
		UserDetails user = new User(username, userInfo.getPassword(), true, true, true, true, collectionGA);
		return user;
	}
	
	
}
