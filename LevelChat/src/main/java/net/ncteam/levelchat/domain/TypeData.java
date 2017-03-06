package net.ncteam.levelchat.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TYPE_DATA")
public class TypeData {
    @Id
    @Column(name = "TYPE", length = 30)
    private String type;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    public Set<UserData> userDatas = new HashSet<UserData>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Set<UserData> getUserDatas() {
        return userDatas;
    }
 
    public void setUserDatas(Set<UserData> userDatas) {
        this.userDatas = userDatas;
    }
}
