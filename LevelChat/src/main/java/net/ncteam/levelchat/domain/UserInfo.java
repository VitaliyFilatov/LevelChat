package net.ncteam.levelchat.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"user_info\"")
public class UserInfo {

	
	@Id
	@Column(name = "\"idinfo\"")
	private int idinfo;

	@Column(name = "\"name\"")
	private String name;
	
	@Column(name = "\"surname\"")
	private String surname;
	
	@Column(name = "\"sex\"")
	private String sex;

	@Column(name = "\"age\"")
	private int age;

	@Column(name = "\"country\"")
	private String country;
	
	@Column(name = "\"city\"")
	private String city;
	
	@Column(name = "\"iduserlog\"")
	private int iduserlog;
	
	@Column(name = "\"id_chat\"")
	private int idchat;

	public int getIdinfo() {
		return idinfo;
	}

	public void setIdinfo(int idinfo) {
		this.idinfo = idinfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getIduserlog() {
		return iduserlog;
	}

	public void setIduserlog(int iduserlog) {
		this.iduserlog = iduserlog;
	}

	public int getIdchat() {
		return idchat;
	}

	public void setIdchat(int idchat) {
		this.idchat = idchat;
	}
	
	
	
}
