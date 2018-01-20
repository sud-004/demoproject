package com.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "id")
	private Integer id;
	// @Column(name = "name")
	@Column(nullable = false)
	private String name;
	// @Column(name = "email")
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String password;
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] userIamge;

	public byte[] getUserIamge() {
		return userIamge;
	}

	public void setUserIamge(byte[] userIamge) {
		this.userIamge = userIamge;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean validate() {
		if (this.name == null || this.email == null || this.password == null) {
			return false;
		} else
			return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
