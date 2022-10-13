package com.javatechie.spring.mongo.api.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;


@Getter
@Setter
@ToString

@Document(collection = "Book")
public class Book {
@Id	

	private int id;
	private String bookName;
	private String authorName;
	
	}

