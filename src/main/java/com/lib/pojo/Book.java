package com.lib.pojo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Entity
@Table(name="BOOKS")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bookId;
	
	@Column(unique=true)
	private String isbn;
	
	private String title;
	private String author;
	
	private LocalDate returnDate = null;
	private LocalDate bookingStartDate = null; //startReservationDate
	private LocalDate bookingEndDate = null;
	private boolean readyForPickUp = false;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private User reservedByUser;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private User theUser;

	
//public Book() {
//		
//	}
//
//
//	public Book(String isbn,String title, String author) {
//		super();
//		this.isbn=isbn;
//		this.title = title;
//		this.author = author;
//
//	}
	
	@Column(name = "picture")
	private String imagePath;
	
	@Transient
	private MultipartFile imageFile;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public User getTheUser() {
		return theUser;
	}

	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	
	public LocalDate getBookingEndDate() {
		return bookingEndDate;
	}
	
	public void setBookingEndDate(LocalDate bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}
	
	public LocalDate getBookingStartDate() {
		return bookingStartDate;
	}
	
	public void setBookingStartDate(LocalDate bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}
	
	public User getReservedByUser() {
		return reservedByUser;
	}
	
	public void setReservedByUser(User reservedByUser) {
		this.reservedByUser = reservedByUser;
	}
	
	public void setReadyForPickup(boolean readyForPickUp) {
		this.readyForPickUp = readyForPickUp;
	}
	
	public boolean getReadyForPickUp() {
		return readyForPickUp;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
}
