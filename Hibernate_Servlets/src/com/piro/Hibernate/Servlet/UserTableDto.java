package com.piro.Hibernate.Servlet;

import java.io.*;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserTableDto implements Serializable 
{
	@Id
	private int id;
	
	@Column(name="uname")
	private String uname;
	
	@Column(name="uemail")
	private String email;
	
	@Column(name="umno")
	private String umno;
	
	@Column(name="urole")
	private String urole;

	public String getUrole() {
		return urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUmno() {
		return umno;
	}

	public void setUmno(String umno) {
		this.umno = umno;
	}
	
	public UserTableDto() {
		System.out.println("User roles Created");
	}
}
