package de.uni.koeln.se.bookstore.datamodel;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String author;
	private Integer dateYear;
	private BigDecimal price;
	
	public Book() {}
	
	public Book(String name, String author, Integer dateYear, BigDecimal price) {
		this.name = name;
		this.author = author;
		this.dateYear = dateYear;
		this.price = price;
	}
	
	//getters
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Integer getYear() {
		return dateYear;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	
	
	//setters
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}
	
	public void setYear(Integer newYear) {
		dateYear = newYear;
	}
	
	public void setPrice(BigDecimal newPrice) {
		price = newPrice;
	}

}
