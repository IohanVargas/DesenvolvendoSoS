package br.com.object.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "constituentSystem")
@XmlRootElement

public class ConstituentSystem implements Serializable{
    
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id") 
   private int id;
   @Column(name = "name")
   private String name;
   @Column(name = "serverAddress") 
   private String serverAddress;
   @Column(name = "authentication") 
   private String authentication;
   
   public ConstituentSystem(){
       
   }

    public ConstituentSystem(int id, String name, String serverAddress, String authentication, boolean active) {
        this.id = id;
        this.name = name;
        this.serverAddress = serverAddress;
        this.authentication = authentication;
        this.active = active;
    }
   
   

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
   private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
   
   
   
     
}
