package com.javatechie.spring.mongo.api.resourcecontroller;

import java.security.PublicKey;
import java.util.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.javatechie.spring.mongo.api.model.Book;

import com.javatechie.spring.mongo.api.repository.BookRepository;

@RestController
public class BookController {
 
	@Autowired
    private BookRepository repository;
	
	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
	  repository.save(book);
		return "Added Book with id : ";
	}
	
	@GetMapping("/findAllBooks")
	public List<Book> getBooks(){
		
		return repository.findAll();
	}
	
	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBookOptional(@PathVariable int id){
		return repository.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "Book Deleted by Id :" + id ;
	}
}
