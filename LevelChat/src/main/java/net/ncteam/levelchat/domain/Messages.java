package net.ncteam.levelchat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;

@Entity
@Table(name = "\"messages\"")
public class Messages {

	
	@EmbeddedId
	private MessageKey pk_idmess;

	@Column(name = "\"message\"")
	private String message;
	
	@Column(name = "\"recepient\"")
	private String recepient;

	public String getMessage() {
		return this.message;
	}
	
	public void setMessageKey(MessageKey mk)
	{
		this.pk_idmess=mk;
	}
	
	public MessageKey getMessageKey() {
		return this.pk_idmess;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecepient() {
		return this.recepient;
	}

	public void setRecepient(String recepient) {
		this.recepient = recepient;
	}
	
}
