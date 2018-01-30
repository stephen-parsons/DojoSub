package com.project.dojosub.models;

import java.util.Date;
import java.util.List;
import java.math.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.project.dojosub.models.User;

@Entity
public class Sub{
	@Id
	@GeneratedValue
	private long id;
	@Size(min=1,max=255,message="Name must be between 1-255 characters.")
	private String name;

	@NotNull(message="Must include price")
	@DecimalMin(value = "0.01", inclusive = true, message="Cost must be at least 0.01")
	private BigDecimal cost;

	private boolean available = true;

	// ONE PACKAGE CAN BELONG TO MANY USERS

	@OneToMany(mappedBy="sub", fetch = FetchType.LAZY)
    private List<User> users;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	// GETTERS AND SETTERS

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
		public boolean getAvailable() {
	    return available;
	}
	public void setAvailable(boolean available) {
	    this.available = available;
	}
	public BigDecimal getCost() {
	    return cost;
	}
	public void setCost(BigDecimal cost) {
	    this.cost = cost;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}

	public List<User> getUsers() {
        return users;
    }
     
    public void setUsers(List<User> users) {
        this.users = users;
    }
	
	public Sub(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

}
