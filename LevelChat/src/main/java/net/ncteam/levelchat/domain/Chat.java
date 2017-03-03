package net.ncteam.levelchat.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"chat\"")
public class Chat {

	
	@Id
	@Column(name = "\"idchat\"")
	private int idchat;

	@Column(name = "\"first_human\"")
	private String first_human;
	
	@Column(name = "\"second_human\"")
	private String second_human;
	
	@Column(name = "\"countMessage\"")
	private String countMessage;

	@Column(name = "\"firstStatus\"")
	private char firstStatus;

	@Column(name = "\"secondStatus\"")
	private char secondStatus;
	
	@Column(name = "\"name_lvl\"")
	private String name_lvl;
	
	@Column(name = "\"id_mess\"")
	private int id_mess;

	public int getIdchat() {
		return idchat;
	}

	public void setIdchat(int idchat) {
		this.idchat = idchat;
	}

	public String getFirst_human() {
		return first_human;
	}

	public void setFirst_human(String first_human) {
		this.first_human = first_human;
	}

	public String getSecond_human() {
		return second_human;
	}

	public void setSecond_human(String second_human) {
		this.second_human = second_human;
	}

	public String getCountMessage() {
		return countMessage;
	}

	public void setCountMessage(String countMessage) {
		this.countMessage = countMessage;
	}

	public char getFirstStatus() {
		return firstStatus;
	}

	public void setFirstStatus(char firstStatus) {
		this.firstStatus = firstStatus;
	}

	public char getSecondStatus() {
		return secondStatus;
	}

	public void setSecondStatus(char secondStatus) {
		this.secondStatus = secondStatus;
	}

	public String getName_lvl() {
		return name_lvl;
	}

	public void setName_lvl(String name_lvl) {
		this.name_lvl = name_lvl;
	}

	public int getId_mess() {
		return id_mess;
	}

	public void setId_mess(int id_mess) {
		this.id_mess = id_mess;
	}
	
	
}
