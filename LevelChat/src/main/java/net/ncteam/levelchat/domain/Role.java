package net.ncteam.levelchat.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "ROLES")
public class Role {

	@Id
	@Column(name = "ID")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "ROLE")
	private String role;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "USERS_ROLES",
                joinColumns={@JoinColumn(name = "ROLE_ID")},
                inverseJoinColumns={@JoinColumn(name = "USER_ID")})
    private Set<UserInfo> users = new HashSet<UserInfo>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public Set<UserInfo> getUsers() {
        return users;
    }
 
    public void setUsers(Set<UserInfo> users) {
        this.users = users;
    }
}
