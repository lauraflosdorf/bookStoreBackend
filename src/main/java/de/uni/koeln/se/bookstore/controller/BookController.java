package de.uni.koeln.se.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.service.BookService;

@RequestMapping("/bookStore")
@RestController
public class BookController {
	
	@Autowired
	BookService bookSer;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllbooks() {
		
		List<Book> books = new ArrayList<Book> ();
		books=bookSer.findBooks();
		
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}" )
	public  ResponseEntity<Book> getBookById(@PathVariable int id) {
		return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Book> addBookt(@RequestBody Book book) {
		bookSer.addBook(book);
		
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> removeBookById(@PathVariable int id) {
		Book book = bookSer.fetchBook(id).get();
		
		if(bookSer.deleteBook(id)) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/Oldest" )
	public ResponseEntity<Book> getOldestBook() {
		List<Book> books = new ArrayList<Book> ();
		books=bookSer.findBooks();
		
		Book bookWithOldestDate = books.get(0);
		int min = books.get(0).getYear();
		for(int i = 1; i < books.size(); i++) {
		    if(books.get(i).getYear() < min) {
		        min = books.get(i).getYear();
		        bookWithOldestDate=books.get(i);
		    }
		}
		
		int id = bookWithOldestDate.getId();
		Book oldestBook = bookSer.fetchBook(id).get();
		return new ResponseEntity<>(oldestBook, HttpStatus.OK);
	}
	
	
	@GetMapping("/Latest" )
	public  ResponseEntity<Book> getLatestBook() {
		List<Book> books = new ArrayList<Book> ();
		books=bookSer.findBooks();
		
		Book bookWithLatestDate = books.get(0);
		int max = books.get(0).getYear();
		for(int i = 1; i < books.size(); i++) {
		    if(books.get(i).getYear() > max) {
		        max = books.get(i).getYear();
		        bookWithLatestDate=books.get(i);
		    }
		}
		
		int id = bookWithLatestDate.getId();
		Book latestBook = bookSer.fetchBook(id).get();
		return new ResponseEntity<>(latestBook, HttpStatus.OK);
	}

}
