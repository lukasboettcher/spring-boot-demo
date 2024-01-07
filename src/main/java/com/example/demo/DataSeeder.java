package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.models.Author;
import com.example.demo.models.AuthorRepository;
import com.example.demo.models.Book;
import com.example.demo.models.BookRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        Author a = new Author();
        a.setName("test");
        authorRepository.save(a);
        
        Book b = new Book();
        b.setAuthor(a);
        b.setName("test");
        bookRepository.save(b);
    }
}