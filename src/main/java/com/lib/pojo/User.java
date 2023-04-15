package com.lib.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lib.pojo.Book;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String name;
	private String address;
	private String contact;
	private String role;
	
	@OneToMany(mappedBy="reservedByUser")
	private List<Book> reservedBooks;
	
	@OneToMany(mappedBy="theUser")
	private List<Book> books;

	public User() {

	}

	public User(String username, String password, String name,String address,String contact,String role) {
//		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.role = role;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Book> getReservedBooks() {
		return reservedBooks;
	}

	public void setReservedBooks(List<Book> reservedBooks) {
		this.reservedBooks = reservedBooks;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}