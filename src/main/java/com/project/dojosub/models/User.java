package com.project.dojosub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.project.dojosub.models.Sub;

@Entity
public class User{

	public User(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	@Id
	@GeneratedValue
	private long id;
	@Size(min=1,max=255,message="First name must be between 1-255 characters.")
	private String firstName;
	@Size(min=1,max=255,message="Last name must be between 1-255 characters.")
	private String lastName;
	@Email(message="Invalid Email. Ex: example@example.com")
	private String email;
	@Size(min=8,max=255,message="Password must be a least 8 characters.")
	private String password;	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@Transient
	private String passwordConfirmation;

	private Boolean isAdmin;

	private int dueDate;

	// MANY USERS CAN HAVE ONE PACKAGE

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sub_id")
    private Sub sub;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
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
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	public String getPasswordConfirmation() {
	    return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
	    this.passwordConfirmation = passwordConfirmation;
	}
	public boolean getIsAdmin() {
	    return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
	    this.isAdmin = isAdmin;
	}
	     
    public Sub getSub() {
        return sub;
    }
     
    public void setSub(Sub sub) {
        this.sub = sub;
    }

    public int getDueDate() {
	    return dueDate;
	}
	 
	public void setDueDate(int dueDate) {
	    this.dueDate = dueDate;
	}
}
