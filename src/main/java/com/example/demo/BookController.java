package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Author;
import com.example.demo.models.AuthorRepository;
import com.example.demo.models.Book;
import com.example.demo.models.BookRepository;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String bookName, @RequestParam String authorName) {
        Author author = new Author();
        author.setName(authorName);
        authorRepository.save(author);

        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        bookRepository.save(book);

        return "redirect:/books";
    }
}